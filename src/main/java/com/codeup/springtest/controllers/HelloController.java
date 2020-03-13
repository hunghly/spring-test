package com.codeup.springtest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Spring!";
    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/join")
    public String showJoinForm() {
        return "join";
    }

    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
        System.out.println(cohort);
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        return "join";
    }


//    @RequestMapping(path="/increment/{number}", method = RequestMethod.GET)
//    @ResponseBody
//    public String addOne(@PathVariable int number) {
//        return number + " plus one is " + (number + 1);
//    }

//    @GetMapping("/decrement/{number}")
//    @ResponseBody
//    public String subOne(@PathVariable int number) {
//        return number + " minus one is " + (number - 1);
//    }
}
