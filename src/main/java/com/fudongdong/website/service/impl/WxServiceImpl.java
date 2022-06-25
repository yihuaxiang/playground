package com.fudongdong.website.service.impl;

import com.fudongdong.website.service.IWxService;
import org.springframework.stereotype.Service;

/**
 * @author dongdong.fdd
 * @date 2022/6/25 19:49
 */
@Service
public class WxServiceImpl implements IWxService {
    @Override
    public String getAccessToken() {
        return null;
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
