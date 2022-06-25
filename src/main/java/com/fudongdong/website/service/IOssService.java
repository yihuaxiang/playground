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
   * 上传 base64 编码的照片，返回 URL，不保存上传记录
   *
   * @param base64Image base64Image
   * @return url
   */
  String uploadBase64(String base64Image);

  /**
   * 上传历史
   *
   * @param uid 用户唯一标志
   * @return list
   */
  List<OssUploadRecord> history(String uid);

  /**
   * 获取记录详情
   *
   * @param uid 用户唯一标志
   * @param id  主键 ID
   * @return OSSUploadRecord
   */
  OssUploadRecord detail(String uid, Integer id);
}
