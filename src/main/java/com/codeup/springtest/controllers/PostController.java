package com.codeup.springtest.controllers;

import com.codeup.springtest.models.Post;
import com.codeup.springtest.models.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackageClasses=Post.class)
@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

//    public PostController(){};

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }


    @RequestMapping(path="/posts", method = RequestMethod.GET)
    public String viewIndexPage(Model view) {
        ArrayList<Post> posts = new ArrayList<>();
        User hung = new User(1,"hung", "hung@email.com", "123");
        posts.add(new Post("I like mangoes", "they are juicy!", hung));
        posts.add(new Post("Selling beer", "No corona for sale.", hung));
        view.addAttribute("Posts", posts);

//        view.addAttribute("posts", postDao.findAll());
//        this.postDao.delete(new Post("My first Post!", "I'm learning about Repos and JPA!"));
//        postDao.deleteById((long) 1);
//        postDao.updateById("I like mangoes", "It works somehow...", 2);
//        this.postDao.save(new Post("some title", "some body", hung));
        System.out.println("saved");

        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable long id, Model view) {
//        Post post = new Post("I like turtles", "I like all the ninja turtles", bob);
        view.addAttribute("post", postDao.getOne(id));
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
