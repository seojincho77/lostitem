package com.example.lostitem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lost-items")
public class LostController {
    @GetMapping
    public String lostItems() {
        return "lost_list";
    }

    @GetMapping("/new")
    public String CreateLostItem() {
        return "lost_registeration";
    }
}
