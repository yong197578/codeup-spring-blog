package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.config.SecurityConfiguration;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.PostCategories;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostCategoriesRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final PostCategoriesRepository catDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, PostCategoriesRepository catDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.catDao = catDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")

    public String viewPosts(Model model) {
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
//        model.addAttribute("user", userDao.findAll());

        model.addAttribute("categories", catDao.findAll());
        model.addAttribute("post", new Post());

        return "posts/create";
    }

    @PostMapping("/posts/create")
//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String submitNewPost(@ModelAttribute Post post) {

       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       post.setUser(user);
//     post.setCategories(categories);
       emailService.prepareAndSend(post,"new post has been created!", "your post is: " + post.getBody());
       postDao.save(post);
       return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditPost(@PathVariable long id, Model model){
        if(postDao.findById(id).isPresent()){
            model.addAttribute("post", postDao.findById(id).get());
        }
        return "posts/create";
    }
    @PostMapping("/posts/{id}/edit")
    public String submitEditPost(@PathVariable long id, @ModelAttribute Post newPost){
//        Post editPost = postDao.findById(id).get();
//        editPost.setTitle(post.getTitle());
//        editPost.setBody(post.getBody());
        User user = userDao.findById(1L).get();
        newPost.setUser(user);
        postDao.save(newPost);
        return "redirect:/posts";
    }
}