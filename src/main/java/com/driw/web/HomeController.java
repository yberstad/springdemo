package com.driw.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class HomeController {
    @RequestMapping(value = "/")
    String index() {
        return "index";
    }
}
