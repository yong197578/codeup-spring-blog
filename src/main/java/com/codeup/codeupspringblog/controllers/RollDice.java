package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class RollDice {
    @GetMapping("/roll-dice")
    public String RollDiceHome() {
        return "rollDice";
    }
    @GetMapping("/roll-dice/{guess}")
    public String rollDice(@PathVariable int guess, Model model){
        Random random = new Random();
        int randomNum = random.nextInt(6) + 1;

        model.addAttribute("randomNum", randomNum);
        model.addAttribute("guess", guess);
//        Model result = model.addAttribute("result", randomNum == guess);

        return "/roll-dice-result";
    }

}
