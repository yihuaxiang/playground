package com.fudongdong.website.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import javax.annotation.PostConstruct;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.fudongdong.website.entity.OssUploadRecord;
import com.fudongdong.website.mapper.OssUploadRecordMapper;
import com.fudongdong.website.service.IOssService;
import com.fudongdong.website.wrapper.OssUploadRecordQuery;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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

    private String host = "z.wiki";

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

    @SneakyThrows
    @Override
    public OssUploadRecord uploadImage(InputStream imgInputStream, String fileName, String uid) {
        log.info("begin uploadImage");
        byte[] imgBytes = IOUtils.toByteArray(imgInputStream);
        DateTime today = new DateTime();
        String objectKey = String.format("autoupload/%s/%s.%s", today.toString("YYYY-MM-dd"),
            UUID.randomUUID().toString().replaceAll("-", ""), fileName);

        PutObjectResult putObjectResult = this.ossClient.putObject(bucketName, objectKey,
            new ByteArrayInputStream(imgBytes));
        log.info("uploadImage result is {}", putObjectResult);
        String url = String.format("https://%s/%s", bucketName, host, objectKey);

        OssUploadRecord record = new OssUploadRecord();
        record.setFileName(fileName);
        record.setUid(uid);
        record.setTime(new Date());
        record.setUrl(url);

        // 生成照片的 base64 编码内容
        String base64Content = new String(Base64.getEncoder().encode(imgBytes));
        String fileSuffix = StringUtils.substringAfterLast(fileName, ".");
        if (StringUtils.isBlank(fileSuffix)) {
          fileSuffix = "png";
        }
        String base64 = String.format("data:image/%s;base64,%s", fileSuffix, base64Content);
        record.setBase64(base64);

        log.info("save to db {},{}", url, record);
        ossUploadRecordMapper.save(record);
        log.info("save to db successfully");
        return record;

    }

  @Override
  public String uploadBase64(String base64Image) {
    String base64Content = StringUtils.substringAfter(base64Image, ",");
    String suffix = StringUtils.substringBetween(base64Image, "/", ";");
    byte[] bytes = Base64.getDecoder().decode(base64Content);
    String objectKey = String.format("autoupload/%s/%s.%s", new DateTime().toString("YYYY-MM-dd"), UUID.randomUUID(), suffix);
    this.ossClient.putObject(bucketName, objectKey, new ByteArrayInputStream(bytes));
    return String.format("https://%s/%s", bucketName, host, objectKey);
  }

  @Override
    public List<OssUploadRecord> history(String uid) {
        OssUploadRecordQuery query = new OssUploadRecordQuery();
        query.where.uid().eq(uid);
        query.orderBy.time().desc();
        query.limit(10);

        return ossUploadRecordMapper.listEntity(query);
    }
}
