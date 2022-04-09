package com.fudongdong.website.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fudongdong.website.service.ILbsService;
import com.fudongdong.website.utils.RequestUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dongdong.fdd
 * @date 2022/4/9 15:41
 */
@Controller
@RequestMapping("/lbs")
@Slf4j
public class LbsController {
    @Autowired
    private ILbsService lbsService;
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 查询 ip 和所在城市，返回 JSON 数据，如果有传 ip，则使用参数中的ip，否则使用调用方的ip
     *
     * @param request 当前请求对象
     * @param ip      查询的目标 ip
     * @return
     */
    @SneakyThrows
    @RequestMapping("getIp")
    @ResponseBody
    public ObjectNode ip(HttpServletRequest request, @RequestParam(value = "ip", required = false) String ip) {
        if (StringUtils.isBlank(ip)) {
            ip = RequestUtils.getRemoteId(request);
        }
        String city = lbsService.ip2city(ip);
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("ip", ip);
        objectNode.put("city", city);

        return objectNode;
    }

    /**
     * 返回调用方的 ip 和所在城市，页面显示
     *
     * @param request
     * @return
     */
    @RequestMapping("/showIp")
    public String showIp(HttpServletRequest request, Model model,
                         @RequestParam(value = "ip", required = false) String ip) {
        String type = "查询";
        if (StringUtils.isBlank(ip)) {
            ip = RequestUtils.getRemoteId(request);
            type = "当前";
        }
        String city = lbsService.ip2city(ip);
        model.addAttribute("ip", Optional.ofNullable(ip).orElse("-"));
        model.addAttribute("city", Optional.ofNullable(city).orElse("未知城市"));
        model.addAttribute("type", type);

        return "showIp";
    }

}
