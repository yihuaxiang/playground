package com.fudongdong.website.service.impl;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LFUCache;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fudongdong.website.bo.WxAccessTokenResBo;
import com.fudongdong.website.bo.WxJsapiTicketResBo;
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

    private ObjectMapper mapper = new ObjectMapper();

    // 专用于保存 微信 access_token 等 token 的缓存
    private LFUCache<Object, Object> cache = CacheUtil.newLFUCache(100);

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
            String url = String.format("%s?grant_type=client_credential&appid=%s&secret=%s", getTokenUrl, this.wxAppId, this.wxAppSecret);
            log.info("getAccessToken, url is {}", url);
            String content = HttpUtil.get(url);
            WxAccessTokenResBo res = mapper.readValue(content, WxAccessTokenResBo.class);
            if (res.isSuccess()) {
                log.info("save accessToken to cache");
                // 缓存 access_token
                Long expireSeconds = res.getExpires_in();
                cache.put(accessTokenCacheKey, res.getAccess_token(), expireSeconds);
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
        } else {
            String getTicketUrl = String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi", this.getAccessToken());
            String content = HttpUtil.get(getTicketUrl);
            WxJsapiTicketResBo res = mapper.readValue(content, WxJsapiTicketResBo.class);
            if (res.isSuccess()) {
                log.info("save jsapi ticket to cache");
                Long expireSeconds = res.getExpires_in();
                cache.put(jsapiTicketKey, res.getTicket(), expireSeconds);
                return res.getTicket();
            } else {
                log.error("failed to get jsapi ticket, response is {}", content);
            }
        }
        return null;
    }

    @Override
    public String getJsSDKSignature(String noncestr, String jsapi_ticket, Long timestamp, String url) {
        return null;
    }
}
