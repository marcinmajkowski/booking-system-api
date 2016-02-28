package com.marcinmajkowski.bookingsystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/admin")
    public String admin() {
        return "forward:/admin/index.html";
    }
}
