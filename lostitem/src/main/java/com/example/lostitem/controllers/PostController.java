package com.example.lostitem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    @GetMapping("/lost-items")
    public String lostItems() {
        return "form";
    }

    @GetMapping("/found-items")
    public String foundItems() {
        return "form";
    }

}
