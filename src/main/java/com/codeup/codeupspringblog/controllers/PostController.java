package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
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
//        Post post1 = new Post("title", "description");
        Post post = postDao.findById(id).get();
        model.addAttribute("post", post);
        return "posts/show";
    }


    @GetMapping("/posts/create")
    public String showPostForm(Model model) {
        model.addAttribute("user", userDao.findAll());
        return "posts/create";
    }


    @PostMapping("/posts/create")
//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String submitNewPost(@RequestParam (name="title") String title, @RequestParam (name="body") String body, Model model, @RequestParam (name="userId") long userId) {
        if(userDao.findById(userId).isPresent()) {
            User user = userDao.findById(userId).get();
            Post post = new Post(title, body, user);
            postDao.save(post);
        }
        return "redirect:/posts";
    }
}