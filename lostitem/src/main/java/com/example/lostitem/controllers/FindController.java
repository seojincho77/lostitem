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
@RequestMapping("/found-items")
public class FindController {
    private final PostService postService;
    private final LostItemService lostItemService;

    public FindController(PostService postService, LostItemService lostItemService) {
        this.postService = postService;
        this.lostItemService = lostItemService;
    }

    /*@GetMapping
    public String foundItems(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);

        List<Post> foundPosts = postService.getFoundPosts();
        model.addAttribute("foundPosts", foundPosts);
        return "get_list";
    }*/

    @GetMapping("/new")
    public String CreateFoundItem(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);
        return "get_registeration";
    }

    @PostMapping("/new")
    public String CreateFoundItem(
            @RequestParam String title,
            @RequestParam CategoryType category,
            @RequestParam String foundPlace,
            @RequestParam StorageLocationType storageLocation,
            @RequestParam LocalDate foundDate,
            @RequestParam String description,
            HttpSession session,
            Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);

        Post post = new Post();
        post.setTitle(title);
        post.setFoundPlace(foundPlace);
        post.setStorageLocation(storageLocation);
        post.setDescription(description);
        post.setUser(user);
        post.setPostType(PostType.found);

        LostItem lostitem = new LostItem();
        lostitem.setCategory(category);
        lostitem.setLostDate(foundDate);

        lostitem.setPost(post);
        post.setLostItem(lostitem);

        postService.savePost(post);

        return "redirect:/found-items";
    }

    @GetMapping("/{id}")
    public String ShowFoundItem(HttpSession session, @PathVariable int id, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);

        Optional<Post> post = postService.getPostById(id);
        model.addAttribute("foundPost", post.orElse(null));

        return "get_detail";
    }

    @GetMapping
    public String showLostItems(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "NONE") DateType date,
            @RequestParam(defaultValue = "NONE") CategoryType category,
            @RequestParam(required = false) String place,
            HttpSession session,
            Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);

        List<Post> filteredPosts = postService.getFilteredLostPosts(date, category, keyword, place, PostType.found);

        model.addAttribute("foundPosts", filteredPosts);
        model.addAttribute("selectedDate", date.name());
        model.addAttribute("selectedCategory", category.name());

        return "get_list";
    }
}
