package com.codeup.springtest.controllers;

import com.codeup.springtest.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    @RequestMapping(path="/posts", method = RequestMethod.GET)
    public String viewIndexPage(Model view) {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("I like mangoes", "they are juicy!"));
        posts.add(new Post("Selling beer", "No corona for sale."));
        view.addAttribute("Posts", posts);
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable int id, Model view) {
        Post post = new Post("I like turtles", "I like all the ninja turtles");
        view.addAttribute("Post", post);
        return "/posts/show";
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
