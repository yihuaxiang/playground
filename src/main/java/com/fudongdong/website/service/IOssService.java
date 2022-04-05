package com.fudongdong.website.service;

import java.io.InputStream;

/**
 * @author dongdong.fdd
 * @date 2022/4/5 17:09
 */
public interface IOssService {
    /**
     * 上传照片
     *
     * @param imgInputStream
     * @param suffix
     * @return
     */
    String uploadImage(InputStream imgInputStream, String suffix);
}
