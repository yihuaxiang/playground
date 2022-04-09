package com.fudongdong.website.service.impl;

import com.fudongdong.website.entity.BlogPv;
import com.fudongdong.website.mapper.BlogPvMapper;
import com.fudongdong.website.service.IBlobPvService;
import com.fudongdong.website.service.ILbsService;
import com.fudongdong.website.wrapper.BlogPvQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author dongdong.fdd
 * @date 2022/4/5 11:08
 */
@Service
@Primary()
public class BlobPvServiceImpl implements IBlobPvService {
    private final BlogPvMapper blogPvMapper;
    private final ILbsService lbsService;

    public BlobPvServiceImpl(BlogPvMapper blogPvMapper, ILbsService lbsService) {this.blogPvMapper = blogPvMapper;
        this.lbsService = lbsService;
    }

    @Override
    @Async
    public void save(String url, String headers, String ip) {
        BlogPv pv = new BlogPv();
        pv.setUrl(url);
        pv.setHeaders(headers);
        pv.setRemoteIp(ip);
        if(StringUtils.isNotBlank(ip)) {
            String city = lbsService.ip2city(ip);
            pv.setCity(city);
        }
        blogPvMapper.saveOrUpdate(pv);
    }

    @Override
    public int accumulation(String url) {
        BlogPvQuery query = new BlogPvQuery();
        query.where.url().eq(url);
        return blogPvMapper.count(query);
    }
}
