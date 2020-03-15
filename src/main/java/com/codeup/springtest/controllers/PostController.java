package com.codeup.springtest.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
//@Configuration
//@EnableAutoConfiguration
//@EntityScan(basePackageClasses=Post.class)
@Controller
public class PostController {

    private final PostRepository postDao;

//    public PostController(){};

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }


    @RequestMapping(path="/posts", method = RequestMethod.GET)
    public String viewIndexPage(Model view) {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("I like mangoes", "they are juicy!"));
        posts.add(new Post("Selling beer", "No corona for sale."));
        view.addAttribute("Posts", posts);

//        view.addAttribute("posts", postDao.findAll());
//        this.postDao.delete(new Post("My first Post!", "I'm learning about Repos and JPA!"));
//        postDao.deleteById((long) 1);
        postDao.updateById("I like mangoes", "It works somehow...", 2);
        System.out.println("saved");

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
