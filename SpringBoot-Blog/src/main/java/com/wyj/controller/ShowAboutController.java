package com.wyj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAboutController {

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
