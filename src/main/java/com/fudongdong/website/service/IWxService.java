package com.fudongdong.website.service;

/**
 * @author dongdong.fdd
 * @date 2022/6/25 19:48
 */
public interface IWxService {

    /**
     * 获取微信 access_token
     *
     * access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。
     * 文档：https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html
     *
     * @return access_token
     */
    String getAccessToken();

    /**
     * 获取 微信 jsapi_ticket
     *
     * jsapi_ticket是公众号用于调用微信 JS 接口的临时票据。
     * 文档：https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/JS-SDK.html#62:~:text=%E6%9D%83%E9%99%90%E7%AD%BE%E5%90%8D%E7%AE%97%E6%B3%95-,jsapi_ticket,-%E7%94%9F%E6%88%90%E7%AD%BE%E5%90%8D%E4%B9%8B%E5%89%8D
     *
     * @return jsapi_ticket
     */
    String getJsapiTicket();

    /**
     * 获取微信 JS-SDK 签名
     * @param noncestr 随机字符串
     * @param timestamp 时间戳
     * @param url 签名所在的URL中
     *
     * @return 签名
     */
    String getJsSDKSignature(String noncestr, Long timestamp, String url);
}
