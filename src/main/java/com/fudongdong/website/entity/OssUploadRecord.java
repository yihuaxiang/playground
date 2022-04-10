package com.fudongdong.website.entity;

import java.util.Date;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import lombok.Data;

/**
 * @author dongdong.fdd
 * @date 2022/4/10 20:23
 */
@FluentMybatis(table = "oss_upload_record")
@Data
public class OssUploadRecord extends RichEntity {
    /**
     * ID
     */
    private Integer id;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 上传日期
     */
    private Date time;

    /**
     * 上传后的 url
     */
    private String url;

    /**
     * 用户唯一标志
     */
    private String uid;
}
