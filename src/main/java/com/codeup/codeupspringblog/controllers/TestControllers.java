package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//Extend HTTpServlet and setup our class to controll url patterns -URL(Uniform Resource Identifier)
@Controller
public class TestControllers {

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "Hello Kotlin!";
    }

    @GetMapping("/parks")
    @ResponseBody
    public String parks(){
        return "<h1>Hello Nature</h1>";
    }
}
