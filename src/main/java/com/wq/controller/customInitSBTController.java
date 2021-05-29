package com.wq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customInitSBTController")
public class customInitSBTController {
    @RequestMapping("/test1")
    @ResponseBody
    public String test1(){
        return "test1";
    }

}
