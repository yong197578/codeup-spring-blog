package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")

    public String viewPosts(Model model) {
//        List<Post> posts = postDao.findAll();
//        posts.add(new Post("first post", "this is my first post"));
//        posts.add(new Post("second post", "this is my second post"));
        model.addAttribute("posts", postDao.findAll());
        return "/posts/index";
    }


    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
        Post post1 = new Post(1L, "title", "description");
        model.addAttribute("post", post1);
        return "posts/show";
    }


    @GetMapping("/posts/create")
    public String showPostForm() {

        return "posts/create";
    }


    @PostMapping("/posts/create")
//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String submitNewPost(@RequestParam (name="title") String title, @RequestParam (name="body") String body, Model model) {
        Post post = new Post(title, body);
        postDao.save(post);
        return "redirect:/posts";
    }
}