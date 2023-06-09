    package com.codeup.codeupspringblog.controllers;

    import com.codeup.codeupspringblog.models.User;
    import com.codeup.codeupspringblog.repositories.UserRepository;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;

    @Controller
    public class UserController {
        private final UserRepository usersDao;
        private final PasswordEncoder passwordEncoder;

        public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
            this.usersDao = userDao;
            this.passwordEncoder = passwordEncoder;
        }
        @GetMapping("/sign-up")
        public String showSignupForm(Model model){
            model.addAttribute("user", new User());
            return "sign-up";
        }
        @PostMapping("/sign-up")
        public String registerUser(@ModelAttribute User user){
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            usersDao.save(user);
            return "redirect:/login";
        }
    }
