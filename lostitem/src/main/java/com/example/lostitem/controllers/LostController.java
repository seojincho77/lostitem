package com.example.lostitem.controllers;

import com.example.lostitem.models.Users;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lost-items")
public class LostController {
    @GetMapping
    public String lostItems(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);
        return "lost_list";
    }

    @GetMapping("/new")
    public String CreateLostItem(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);
        return "lost_registeration";
    }

    @PostMapping("/new")
    public String CreateLostItem(@ModelAttribute("user") Users user, Model model) {
        return "lost_list";
    }
}
