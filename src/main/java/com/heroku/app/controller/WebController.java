package com.heroku.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class WebController {

    @GetMapping("/vue")
    public String vue(){
        log.info("Vue Page");
        
        return "vue/index";
    }
}
