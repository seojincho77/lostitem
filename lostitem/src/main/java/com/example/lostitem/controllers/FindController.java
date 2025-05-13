package com.example.lostitem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/found-items")
public class FindController {
    @GetMapping
    public String foundItems() {
        return "get_list";
    }

    @GetMapping("/new")
    public String CreateFoundItem() {
        return "get_registeration";
    }
}
