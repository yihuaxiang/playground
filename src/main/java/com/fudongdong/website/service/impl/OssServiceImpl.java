package com.fudongdong.website.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.fudongdong.website.entity.OssUploadRecord;
import com.fudongdong.website.mapper.OssUploadRecordMapper;
import com.fudongdong.website.service.IOssService;
import com.fudongdong.website.wrapper.OssUploadRecordQuery;
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

    private final OssUploadRecordMapper ossUploadRecordMapper;

    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucket}")
    private String bucketName;

    public OssServiceImpl(OssUploadRecordMapper ossUploadRecordMapper) {
        this.ossUploadRecordMapper = ossUploadRecordMapper;
    }

    @PostConstruct
    public void init() {
        this.ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    @Override
    public String uploadImage(InputStream imgInputStream, String fileName) {
        log.info("begin uploadImage");
        DateTime today = new DateTime();
        String objectKey = String.format("autoupload/%s/%s.%s", today.toString("YYYY-MM-dd"),
            UUID.randomUUID().toString().replaceAll("-", ""), fileName);
        PutObjectResult putObjectResult = this.ossClient.putObject(bucketName, objectKey,
            imgInputStream);
        log.info("uploadImage result is {}", putObjectResult);
        String url = String.format("https://%s.%s/%s", bucketName, endpoint, objectKey);

        OssUploadRecord record = new OssUploadRecord();
        record.setFileName(fileName);
        record.setTime(new Date());
        record.setUrl(url);
        log.info("save to db {},{}", url, record);
        ossUploadRecordMapper.save(record);
        log.info("save to db successfully");
        return url;

    }

    @Override
    public List<OssUploadRecord> history() {
        OssUploadRecordQuery query = new OssUploadRecordQuery();
        query.orderBy.time().desc();
        query.limit(1000);
        return ossUploadRecordMapper.listEntity(query);
    }
}
