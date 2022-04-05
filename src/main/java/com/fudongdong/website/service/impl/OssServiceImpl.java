package com.fudongdong.website.service.impl;

import java.io.InputStream;
import java.util.UUID;

import javax.annotation.PostConstruct;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.fudongdong.website.service.IOssService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author dongdong.fdd
 * @date 2022/4/5 17:09
 */
@Service
@Primary()
@Slf4j
public class OssServiceImpl implements IOssService {
    private OSS ossClient = null;

    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucket}")
    private String bucketName;

    @PostConstruct
    public void init() {
        this.ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    @Override
    public String uploadImage(InputStream imgInputStream, String suffix) {
        DateTime today = new DateTime();
        String objectKey = String.format("autoupload/%s/%s.%s", today.toString("YYYY-MM-DD"),
            UUID.randomUUID(), suffix);
        PutObjectResult putObjectResult = this.ossClient.putObject(bucketName, objectKey,
            imgInputStream);
        log.info("uploadImage result is {}", putObjectResult);
        return String.format("https://%s.%s/%s", bucketName, endpoint, objectKey);
    }
}
