package com.fudongdong.website.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fudongdong.website.service.IOssService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传相关接口
 *
 * @author dongdong.fdd
 * @date 2022/4/5 17:07
 */
@Slf4j
@RequestMapping("/img")
@RestController
@CrossOrigin(origins = {"https://fudongdong.com", "https://www.fudongdong.com", "https://www.weizeling.com",
    "https://51shazhu.com", "https://playground.fudongdong.com", "http://localhost:8080/"})
public class ImgController {
    private final IOssService ossService;

    public ImgController(IOssService ossService) {this.ossService = ossService;}

    private final Set<String> contentTypeSet = new HashSet<>(
        Lists.newArrayList("image/svg+xml", "image/png", "image/jpeg", "image/gif", "video/mp4", "video/mov",
            "video/avi", "video/3gp", "video/rm", "video/flv"));

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestPart MultipartFile file) throws IOException {
        log.info("get upload request");
        String contentType = file.getContentType();
        if (Objects.nonNull(contentType)) {
            if (contentTypeSet.contains(contentType.toLowerCase())) {
                String suffix = StringUtils.substringBefore(StringUtils.substringAfter(contentType, "/"), "+");
                return ossService.uploadImage(file.getInputStream(), suffix);
            }
        }
        return "failed";
    }
}
