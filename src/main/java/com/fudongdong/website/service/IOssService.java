package com.fudongdong.website.service;

import java.io.InputStream;
import java.util.List;

import com.fudongdong.website.entity.OssUploadRecord;

/**
 * @author dongdong.fdd
 * @date 2022/4/5 17:09
 */
public interface IOssService {
    /**
     * 上传照片
     *
     * @param imgInputStream 上传文件的文件数据流
     * @param fileName       上传文件的文件名
     * @param uid            用户唯一标志，客户端产生
     * @return 上传后的 url
     */
    OssUploadRecord uploadImage(InputStream imgInputStream, String fileName, String uid);

    /**
     * 上传历史
     *
     * @param uid 用户唯一标志
     * @return list
     */
    List<OssUploadRecord> history(String uid);
}
