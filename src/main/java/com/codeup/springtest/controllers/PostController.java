package com.codeup.springtest.controllers;

import com.codeup.springtest.models.Post;
import com.codeup.springtest.models.User;
import com.codeup.springtest.repositories.PostRepository;
import com.codeup.springtest.repositories.UserRepository;
import com.codeup.springtest.services.EmailService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Configuration
//@EnableAutoConfiguration
//@EntityScan(basePackageClasses=Post.class)
@ComponentScan("com.codeup.springtest.services")
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


    @GetMapping("/posts")
    public String viewIndexPage(Model view) {
        List<Post> posts = postDao.findAll();
        view.addAttribute("posts", posts);
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable long id, Model view) {
        System.out.println("Something broke here");
        view.addAttribute("post", postDao.getOne(id));
        return "/posts/show";
    }

    @PostMapping("/posts/delete/{id}")
    public String doDeletePost(@PathVariable long id, Model view) {
        System.out.println(id);
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String viewPostCreation(Model view) {
        view.addAttribute("action", "create");
        view.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String doPostCreation(@ModelAttribute Post post) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(loggedInUser);
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

    @RequestMapping(path = "/posts/edit/{id}", method = RequestMethod.POST)
    public String doPostEdit(@ModelAttribute Post post, @PathVariable Long id) {
        // need stuff here
        Post foundPost = postDao.getOne(id);
        postDao.updateById(post.getTitle(), post.getBody(), foundPost.getUser(), id);
        return "redirect:/posts/" + id;
    }
}
