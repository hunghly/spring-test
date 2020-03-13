package com.codeup.springtest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String viewDicePage() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDice(@PathVariable int n, Model model) {
        ArrayList<Integer> diceRolls = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            diceRolls.add((int) Math.floor((Math.random() * 6)) + 1);
        }
        model.addAttribute("diceRolls", diceRolls);
        model.addAttribute("userRoll", n);
        return "roll-dice";
    }
}
