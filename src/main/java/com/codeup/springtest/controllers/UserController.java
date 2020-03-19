package com.codeup.springtest.controllers;

import com.codeup.springtest.models.User;
import com.codeup.springtest.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model view) {
        view.addAttribute("user", new User());
        return "users/sign-up";
    }

    @RequestMapping(path = "/sign-up", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User user) {

        return "redirect:/login";
    }

}
