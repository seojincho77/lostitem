package com.example.lostitem.controllers;

import com.example.lostitem.models.*;
import com.example.lostitem.services.LostItemService;
import com.example.lostitem.services.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lost-items")
public class LostController {
    private final PostService postService;
    private final LostItemService lostItemService;

    public LostController(PostService postService, LostItemService lostItemService) {
        this.postService = postService;
        this.lostItemService = lostItemService;
    }

    @GetMapping
    public String lostItems(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);

        List<Post> lostPosts = postService.getLostPosts();
        model.addAttribute("lostPosts", lostPosts);
        return "lost_list";
    }

    @GetMapping("/new")
    public String CreateLostItem(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);
        return "lost_registeration";
    }

    @PostMapping("/new")
    public String CreateLostItem(
            @RequestParam("title") String title,
            @RequestParam("lostPlace") String lostPlace,
            @RequestParam("lostDate") LocalDate lostDate,
            @RequestParam("description") String description,
            @RequestParam("category") CategoryType category,
            HttpSession session,
            Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);

        Post post = new Post();
        post.setTitle(title);
        post.setDescription(description);
        post.setUser(user);
        post.setPostType(PostType.lost);

        LostItem lostitem = new LostItem();
        lostitem.setLostPlace(lostPlace);
        lostitem.setLostDate(lostDate);
        lostitem.setCategory(category);

        lostitem.setPost(post);
        post.setLostItem(lostitem);

        postService.savePost(post);

        return "redirect:/lost-items";
    }

    @GetMapping("/{id}")
    public String showLostItem(HttpSession session, @PathVariable int id, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);

        Optional<Post> post = postService.getPostById(id);
        model.addAttribute("lostPost", post.orElse(null));

        return "lost_detail";
    }
}
