package com.fudongdong.website.service.impl;

import com.fudongdong.website.entity.BlogPv;
import com.fudongdong.website.mapper.BlogPvMapper;
import com.fudongdong.website.service.IBlobPvService;
import com.fudongdong.website.wrapper.BlogPvQuery;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author dongdong.fdd
 * @date 2022/4/5 11:08
 */
@Service
@Primary()
public class BlobPvServiceImpl implements IBlobPvService {
    private final BlogPvMapper blogPvMapper;

    public BlobPvServiceImpl(BlogPvMapper blogPvMapper) {this.blogPvMapper = blogPvMapper;}

    @Override
    public void save(String url, String headers) {
        BlogPv pv = new BlogPv();
        pv.setUrl(url);
        pv.setHeaders(headers);
        blogPvMapper.saveOrUpdate(pv);
    }

    @Override
    public int accumulation(String url) {
        BlogPvQuery query = new BlogPvQuery();
        query.where.url().eq(url);
        return blogPvMapper.count(query);
    }
}
