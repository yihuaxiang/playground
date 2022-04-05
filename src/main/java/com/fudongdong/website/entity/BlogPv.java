package com.fudongdong.website.entity;

import java.util.Date;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import lombok.Data;

/**
 * 博客网站 pv 统计 entity
 * @author dongdong.fdd
 * @date 2022/4/5 11:05
 */
@FluentMybatis( table = "blog_pv")
@Data
public class BlogPv extends RichEntity {
    /**
     * 递增ID
     */
    private Long id;

    /**
     * 访问的 url
     */
    private String url;

    /**
     * header 信息
     */
    private String headers;

    /**
     * 数据创建日期
     */
    private Date time;
}
