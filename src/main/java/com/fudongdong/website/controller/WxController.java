package com.fudongdong.website.controller;

import com.fudongdong.website.service.IWxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信公众号api
 *
 * @author dongdong.fdd
 * @date 2022/6/25 19:48
 */
@Slf4j
@RestController()
@RequestMapping("/wx")
@CrossOrigin(origins = {"https://yidaoyilu.z.wiki", "http://localhost:8080/"})
@RequiredArgsConstructor
public class WxController {

    private final IWxService wxServiceImpl;

    @RequestMapping("/test")
    public String test() {
        return this.wxServiceImpl.getAccessToken();
    }

    @RequestMapping("/test1")
    public String test1() {
        return this.wxServiceImpl.getJsapiTicket();
    }

    @RequestMapping("/getSignature")
    public String getSignature(
        @RequestParam(value = "noncestr") String noncestr,
        @RequestParam(value = "timestamp") Long timestamp,
        @RequestParam(value = "url") String url
    ) {
        return this.wxServiceImpl.getJsSDKSignature(noncestr, timestamp, url);
    }
}
