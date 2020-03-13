package com.codeup.springtest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public String add(@PathVariable double num1, @PathVariable double num2) {
        return String.format("%.2f + %.2f = %.2f", num1, num2, num1 + num2);
    }

    @RequestMapping(path="/subtract/{num1}/from/{num2}" , method = RequestMethod.GET)
    @ResponseBody
    public String subtract(@PathVariable double num1, @PathVariable double num2) {
        return String.format("%.2f - %.2f = %.2f", num2, num1, num2 - num1);
    }

    @RequestMapping(path="/multiply/{num1}/and/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String multiply(@PathVariable double num1, @PathVariable double num2) {
        return String.format("%.2f * %.2f = %.2f", num1, num2, num1 * num2);
    }

    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody
    public String divide(@PathVariable double num1, @PathVariable double num2) {
        return String.format("%.2f / %.2f = %.2f", num1, num2, num1 / num2);
    }

}
