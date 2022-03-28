package com.fudongdong.website.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import lombok.Data;

/**
 * @author dongdong.fdd
 * @date 2022/3/28 23:25
 */
@FluentMybatis( table = "pg_project")
@Data
public class PgProject extends RichEntity {
    /**
     *
     */
    private int id;

    /**
     * 项目url
     */
    private String url;

    /**
     * 预览图
     */
    private String preview;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目描述
     */
    private String description;

    /**
     *
     */
    private String gmtCreate;
}
