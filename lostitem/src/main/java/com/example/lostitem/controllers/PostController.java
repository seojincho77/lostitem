package com.example.lostitem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    @GetMapping("/lost-items")
    public String lostItems() {
        return "lost_list";
    }

    @GetMapping("/lost-items/new")
    public String CreateLostItem() {
        return "lost_registeration";
    }

    @GetMapping("/found-items")
    public String foundItems() {
        return "get_list";
    }

    @GetMapping("/found-items/new")
    public String CreateFoundItem() {
        return "get_registeration";
    }

}