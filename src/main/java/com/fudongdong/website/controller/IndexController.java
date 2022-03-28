package com.fudongdong.website.controller;

import java.util.List;

import com.fudongdong.website.entity.PgProject;
import com.fudongdong.website.service.IPgProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author dongdong.fdd
 * @date 2022/3/20 16:07
 */
@Controller
public class IndexController {
    private final IPgProjectService pgProjectService;

    public IndexController(IPgProjectService pgProjectService) {this.pgProjectService = pgProjectService;}

    @GetMapping("/")
    public String index(Model model) {
        List<PgProject> list = pgProjectService.list();
        model.addAttribute("list", list);
        return "index";
    }
}
