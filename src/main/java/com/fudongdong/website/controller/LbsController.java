package com.fudongdong.website.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fudongdong.website.service.ILbsService;
import com.fudongdong.website.utils.RequestUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * 返回调用方的 ip 和所在城市，返回 JSON 数据
     *
     * @param request
     * @return
     */
    @SneakyThrows
    @RequestMapping("getIp")
    @ResponseBody
    public ObjectNode ip(HttpServletRequest request) {
        String ip = RequestUtils.getRemoteId(request);
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
    public String showIp(HttpServletRequest request, Model model) {
        String ip = RequestUtils.getRemoteId(request);
        String city = lbsService.ip2city(ip);
        model.addAttribute("ip", Optional.ofNullable(ip).orElse("-"));
        model.addAttribute("city", Optional.ofNullable(city).orElse("未知城市"));

        return "showIp";
    }

}
