package com.fudongdong.website.service.impl;

import java.net.URI;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fudongdong.website.service.ILbsService;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Lbs 相关服务
 *
 * @author dongdong.fdd
 * @date 2022/4/9 10:00
 */
@Service
@Primary()
public class LbsServiceImpl implements ILbsService {
    @Value("lbs.webapi.key")
    private String key;

    /**
     * IP 转城市接口地址
     */
    private String ipApiUrl = "https://restapi.amap.com/v3/ip";

    @SneakyThrows
    @Override
    public String ip2city(String ip) {
        URI url = new URIBuilder(ipApiUrl).addParameter("key", key).addParameter("ip", ip).build();
        HttpResponse response = this.doGet(url.toString());
        String content = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(content);
        JsonNode cityJsonNode = jsonNode.get("city");
        if (Objects.nonNull(cityJsonNode)) {
            return cityJsonNode.textValue();
        } else {
            return null;
        }
    }

    @SneakyThrows
    private HttpResponse doGet(String url) {
        HttpClient client = HttpClientBuilder.create().build();
        return client.execute(new HttpGet(url));
    }
}
