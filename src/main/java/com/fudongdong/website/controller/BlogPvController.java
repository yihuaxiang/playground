package com.fudongdong.website.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fudongdong.website.service.IBlobPvService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 博客网站 pv 统计 controller
 * @author dongdong.fdd
 * @date 2022/4/5 11:14
 */
@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = {"https://fudongdong.com", "https://www.fudongdong.com", "https://www.weizeling.com", "https://51shazhu.com"})
public class BlogPvController {
    private final IBlobPvService blogPvService;

    public BlogPvController(IBlobPvService blogPvService) {this.blogPvService = blogPvService;}

    @GetMapping("/pv")
    public String addPv(
        @RequestParam(value ="url") String url,
        HttpServletRequest request
    ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Enumeration<String> headerNames = request.getHeaderNames();
        ObjectNode headers = mapper.createObjectNode();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String next = headerNames.nextElement();
                headers.put(next, request.getHeader(next));
            }
        }

        String headersString = mapper.writeValueAsString(headers);

        blogPvService.save(url, headersString);

        return "success";
    }

    /**
     * 指定 url 的累计访问量
     * @param url
     * @return
     */
    @GetMapping("/accumulation")
    public int accumulation(
        @RequestParam(value = "url") String url
    ) {
        return blogPvService.accumulation(url);
    }
}
