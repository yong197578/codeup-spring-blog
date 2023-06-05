package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public int add(@PathVariable int num1, @PathVariable int num2) {
        return num1 + num2;
    }


    @GetMapping(path = "/subtract/{num1}/from/{num2}")

    @ResponseBody public int subtract(@PathVariable int num1, @PathVariable int num2){
        return num1 - num2;
    }

    @GetMapping(path = "/multiply/{num1}/and/{num2}")
    @ResponseBody public int multiply(@PathVariable int num1, @PathVariable int num2){
        return num1 * num2;
    }

    @GetMapping(path = "/divide/{num1}/by/{num2}")
    @ResponseBody public int divide(@PathVariable int num1, @PathVariable int num2){
        return num1/num2;
    }

}
