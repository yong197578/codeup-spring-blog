package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String viewPost(){
        return "posts index page";
    }
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postsId(@PathVariable long id){
        return "view and individual post" + id;
    }

    @GetMapping( "/posts/create")
    @ResponseBody
    public String createForm(){
        return "view the form for creating a post";
    }
    @PostMapping( "posts/create/")
    @ResponseBody
    public String createPost(){
        return "create a new post";
    }
}
