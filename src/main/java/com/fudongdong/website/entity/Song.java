package com.fudongdong.website.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import lombok.Data;

/**
 * @author dongdong.fdd
 * @date 2022/3/20 18:42
 */
@FluentMybatis( table = "songs")
@Data
public class Song extends RichEntity {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 歌曲名称
     */
    private String song;

    /**
     * 歌手名称
     */
    private String by_;

    /**
     * 专辑名称
     */
    private String album;

    /**
     * 评分
     */
    private Integer rating;

    /**
     * name
     */
    private String name;
}
