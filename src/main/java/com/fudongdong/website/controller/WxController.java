package com.fudongdong.website.controller;

import com.fudongdong.website.service.IWxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信公众号api
 *
 * @author dongdong.fdd
 * @date 2022/6/25 19:48
 */
@RestController("/wx")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://yidaoyilu.z.wiki"})
public class WxController {
    private final IWxService wxServiceImpl;

    @GetMapping("/test")
    public String test() {
        return this.wxServiceImpl.getAccessToken();
    }
}
