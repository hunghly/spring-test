package com.codeup.springtest.controllers;

import com.codeup.springtest.models.Post;
import com.codeup.springtest.models.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackageClasses=Post.class)
@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailDao;

//    public PostController(){};

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailDao = emailDao;
    }


    @RequestMapping(path="/posts", method = RequestMethod.GET)
    public String viewIndexPage(Model view) {
        List<Post> posts = postDao.findAll();
//        User hung = new User(1,"hung", "hung@email.com", "123");
//        posts.add(new Post("I like mangoes", "they are juicy!", hung));
//        posts.add(new Post("Selling beer", "No corona for sale.", hung));
        view.addAttribute("Posts", posts);

//        view.addAttribute("posts", postDao.findAll());
//        this.postDao.delete(new Post("My first Post!", "I'm learning about Repos and JPA!"));
//        postDao.deleteById((long) 1);
//        postDao.updateById("I like mangoes", "It works somehow...", 2);
//        this.postDao.save(new Post("some title", "some body", hung));

        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable long id, Model view) {
//        Post post = new Post("I like turtles", "I like all the ninja turtles", bob);
        view.addAttribute("post", postDao.getOne(id));
        return "/posts/show";
    }

    @GetMapping("/posts/create")
    public String viewPostCreation(Model view) {
        view.addAttribute("action", "create");
        view.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String doPostCreation(@ModelAttribute Post post) {
        User hung = new User(1,"hung", "hunghly@gmail.com", "123");
        post.setUser(hung);
        postDao.save(post);
        emailDao.prepareAndSend(post, "Post Created!", "We created your post: " + post.getTitle());
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String viewPostEdit(@PathVariable long id, Model view) {
        Post post = postDao.getOne(id);
        view.addAttribute("action", "edit");
        view.addAttribute("post", post);
        return "posts/create";
    }

    @RequestMapping(path = "/posts/edit", method = RequestMethod.POST)
    public String doPostEdit(@ModelAttribute Post post, @RequestParam Long id) {
        // need stuff here
        Post foundPost = postDao.getOne(id);
        postDao.updateById(post.getTitle(), post.getBody(), foundPost.getUser(), id);
        return "redirect:/posts";
    }
}
