package com.du.bagunee20250917.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaguneeController {

    @GetMapping("/")
    public String bagunee() {
        return "bagunee";
    }
}
