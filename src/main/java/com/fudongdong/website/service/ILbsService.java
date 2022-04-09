package com.fudongdong.website.service;

/**
 * @author dongdong.fdd
 * @date 2022/4/9 10:00
 */
public interface ILbsService {
    /**
     * 根据 IP 定位到所在城市
     * @param ip
     * @return
     */
    String ip2city(String ip);
}
