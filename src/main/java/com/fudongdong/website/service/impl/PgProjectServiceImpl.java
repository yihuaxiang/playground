package com.fudongdong.website.service.impl;

import java.util.List;

import com.fudongdong.website.entity.PgProject;
import com.fudongdong.website.mapper.PgProjectMapper;
import com.fudongdong.website.service.IPgProjectService;
import com.fudongdong.website.wrapper.PgProjectQuery;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author dongdong.fdd
 * @date 2022/3/28 23:27
 */
@Service
@Primary()
public class PgProjectServiceImpl implements IPgProjectService {
    private final PgProjectMapper pgProjectMapper;

    public PgProjectServiceImpl(PgProjectMapper pgProjectMapper) {this.pgProjectMapper = pgProjectMapper;}

    @Override
    public List<PgProject> list() {
        PgProjectQuery query = new PgProjectQuery();
        return pgProjectMapper.listEntity(query);
    }
}
