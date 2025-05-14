package com.example.lostitem.controllers;

import com.example.lostitem.models.Users;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/found-items")
public class FindController {
    @GetMapping
    public String foundItems(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);
        return "get_list";
    }

    @GetMapping("/new")
    public String CreateFoundItem(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);
        return "get_registeration";
    }
}
