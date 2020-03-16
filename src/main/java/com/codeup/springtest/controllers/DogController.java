package com.codeup.springtest.controllers;

import com.codeup.springtest.models.Dog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DogController {

    private final DogRepository dogDao;

    public DogController(DogRepository dogDao) {
        this.dogDao = dogDao;
    }


    @GetMapping("/dogs")
    public String viewDogs(Model view) {
        List<Dog> dogs = dogDao.findAll();
        view.addAttribute("dogs", dogs);
        return "dogs/index";
    }

    @GetMapping("/dogs/create")
    public String showDogCreateForm(Model view) {
        view.addAttribute("dog", new Dog());
        return "dogs/create";
    }

    @RequestMapping(path = "/dogs/create", method = RequestMethod.POST)
    public String doCreateDog(@ModelAttribute Dog dog, Model view) {
        dogDao.save(dog);
        return "redirect:/dogs";
    }

}
