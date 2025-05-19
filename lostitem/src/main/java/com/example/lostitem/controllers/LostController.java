package com.example.lostitem.controllers;

import com.example.lostitem.models.*;
import com.example.lostitem.services.LostItemService;
import com.example.lostitem.services.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/lost-items")
public class LostController {
    private final PostService postService;
    private final LostItemService lostItemService;

    public LostController(PostService postService, LostItemService lostItemService) {
        this.postService = postService;
        this.lostItemService = lostItemService;
    }

    /*@GetMapping
    public String lostItems(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);

        List<Post> lostPosts = postService.getLostPosts();
        model.addAttribute("lostPosts", lostPosts);
        return "lost_list";
    }*/

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
            @RequestParam("image") MultipartFile imageFile,
            HttpSession session,
            Model model) throws IOException {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);

        String uploadDir = "uploads/images/";
        String originalFilename = imageFile.getOriginalFilename();
        String imagePath = null;

        if (imageFile != null && !imageFile.isEmpty()) {
            // 파일명 중복 방지를 위해 UUID 사용
            String savedFilename = UUID.randomUUID() + "_" + originalFilename;
            Path savePath = Paths.get(uploadDir + savedFilename);

            // 디렉토리 없으면 생성
            Files.createDirectories(savePath.getParent());
            imageFile.transferTo(savePath.toFile());

            imagePath = "/uploads/images/" + savedFilename; // 이 경로는 나중에 static 경로와 연결됨
        }

        Post post = new Post();
        post.setTitle(title);
        post.setDescription(description);
        post.setUser(user);
        post.setPostType(PostType.lost);
        post.setImageUrl(imagePath);

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

        List<Post> filteredPosts = postService.getFilteredLostPosts(date, category, keyword, place, PostType.lost);

        model.addAttribute("lostPosts", filteredPosts);
        model.addAttribute("selectedDate", date.name());
        model.addAttribute("selectedCategory", category.name());

        return "lost_list";
    }
}
