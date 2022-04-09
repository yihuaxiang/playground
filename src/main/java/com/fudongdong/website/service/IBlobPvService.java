package com.fudongdong.website.service;

/**
 * @author dongdong.fdd
 * @date 2022/4/5 11:08
 */
public interface IBlobPvService {
    /**
     * 保存 pv 信息
     * @param url 用户当前访问的 url
     * @param headers header
     * @param ip 调用方 IP
     */
    void save(String url, String headers, String ip);

    /**
     * 指定 url 的访问量
     * @param url 指定的 url
     * @return
     */
    int accumulation(String url);
}
