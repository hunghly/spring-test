package com.codeup.springtest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @RequestMapping(path="/posts", method = RequestMethod.GET)
    @ResponseBody
    public String viewIndexPage() {
        return "posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewIndividualPost(@PathVariable int id) {
        return "view an individual post by id: " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewPostCreation() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String doPostCreation() {
        return "create a new post";
    }
}
