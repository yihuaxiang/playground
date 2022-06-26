package com.fudongdong.website.service.impl;

import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LFUCache;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fudongdong.website.bo.OAuth2AccessTokenResBo;
import com.fudongdong.website.bo.WxAccessTokenResBo;
import com.fudongdong.website.bo.WxJsapiTicketResBo;
import com.fudongdong.website.bo.WxUserInfo;
import com.fudongdong.website.service.IWxService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author dongdong.fdd
 * @date 2022/6/25 19:49
 */
@Service
@Slf4j
public class WxServiceImpl implements IWxService {

    @Value("${yidaoyilu.wx.appId}")
    private String wxAppId;
    @Value("${yidaoyilu.wx.appSecret}")
    private String wxAppSecret;

    private final ObjectMapper mapper = new ObjectMapper();

    // 专用于保存 微信 access_token 等 token 的缓存
    private final LFUCache<Object, Object> cache = CacheUtil.newLFUCache(100);

    @SneakyThrows
    @Override
    public String getAccessToken() {
        String accessTokenCacheKey = "access_token_cache_key";
        String preToken = (String)cache.get(accessTokenCacheKey);
        if (StringUtils.isNotBlank(preToken)) {
            log.info("getAccessToken from cache");
            // 优先从缓存中获取 access_token
            return preToken;
        } else {
            String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
            String url = String.format("%s?grant_type=client_credential&appid=%s&secret=%s", getTokenUrl, this.wxAppId,
                this.wxAppSecret);
            log.info("getAccessToken, url is {}", url);
            String content = HttpUtil.get(url);
            WxAccessTokenResBo res = mapper.readValue(content, WxAccessTokenResBo.class);
            if (res.isSuccess()) {
                log.info("save accessToken to cache");
                // 缓存 access_token
                Long expireSeconds = res.getExpires_in();
                cache.put(accessTokenCacheKey, res.getAccess_token(), expireSeconds * 1000);
                return res.getAccess_token();
            } else {
                log.error("failed to get accessToken ,response is {}", content);
            }

            return null;
        }
    }

    @SneakyThrows
    @Override
    public String getJsapiTicket() {
        String jsapiTicketKey = "jsapi_ticket_key";
        String prevTicket = (String)cache.get(jsapiTicketKey);

        if (StringUtils.isNotBlank(prevTicket)) {
            log.info("getJsapiTicket from cache");
            return prevTicket;
        } else {
            String getTicketUrl = String.format(
                "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi", this.getAccessToken());
            String content = HttpUtil.get(getTicketUrl);
            WxJsapiTicketResBo res = mapper.readValue(content, WxJsapiTicketResBo.class);
            if (res.isSuccess()) {
                log.info("save jsapi ticket to cache");
                Long expireSeconds = res.getExpires_in();
                cache.put(jsapiTicketKey, res.getTicket(), expireSeconds * 1000);
                return res.getTicket();
            } else {
                log.error("failed to get jsapi ticket, response is {}", content);
            }
        }
        return null;
    }

    @Override
    public String getJsSDKSignature(String noncestr, Long timestamp, String url) {
        String jsapi_ticket = getJsapiTicket();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("noncestr", noncestr);
        map.put("jsapi_ticket", jsapi_ticket);
        map.put("timestamp", Objects.toString(timestamp, ""));
        map.put("url", Objects.toString(url, ""));
        String keyValuePairsString = map.keySet().stream().map(s -> s + "=" + map.get(s)).collect(
            Collectors.joining("&"));
        log.info("keyValuePairsString is {}", keyValuePairsString);
        return DigestUtil.sha1Hex(keyValuePairsString);
    }

    @SneakyThrows
    @Override
    public WxUserInfo getWxUserInfo(String code) {
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", wxAppId, wxAppSecret, code);
        String content = HttpUtil.get(url);
        OAuth2AccessTokenResBo resBo = mapper.readValue(content, OAuth2AccessTokenResBo.class);
        if (resBo.isSuccess()) {
            return WxUserInfo.builder().openid(resBo.getOpenid()).build();
        } else {
            log.error("failed to getWxUserInfo,response is {}", content);
            return null;
        }
    }
}
