package com.fudongdong.website.service;

import java.util.List;

import com.fudongdong.website.entity.PgProject;

/**
 * @author dongdong.fdd
 * @date 2022/3/28 23:27
 */
public interface IPgProjectService {

    /**
     * 查询全量数据
     * @return
     */
    List<PgProject> list();
}
