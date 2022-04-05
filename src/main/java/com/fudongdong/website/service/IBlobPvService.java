package com.fudongdong.website.service;

/**
 * @author dongdong.fdd
 * @date 2022/4/5 11:08
 */
public interface IBlobPvService {
    /**
     * 保存 pv 信息
     * @param url
     * @param headers
     */
    void save(String url, String headers);

    /**
     * 指定 url 的访问量
     * @param url
     * @return
     */
    int accumulation(String url);
}
