package com.fudongdong.website.service.impl;

import cn.hutool.http.HttpUtil;
import com.fudongdong.website.service.IWxService;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public String getAccessToken() {
        String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
        String url = String.format("%s?grant_type=client_credential&appid=%s&secret=%s", getTokenUrl, this.wxAppId, this.wxAppSecret);
        log.info("getAccessToken, url is {}", url);
        return HttpUtil.get(url);
    }

    @Override
    public String getJsapiTicket() {
        return null;
    }

    @Override
    public String getJsSDKSignature(String noncestr, String jsapi_ticket, Long timestamp, String url) {
        return null;
    }
}
