package com.example.lostitem.controllers;

import com.example.lostitem.models.*;
import com.example.lostitem.services.CommentService;
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
@RequestMapping("/found-items")
public class FindController {
    private final PostService postService;
    private final LostItemService lostItemService;
    private final CommentService commentService;

    public FindController(PostService postService, LostItemService lostItemService,  CommentService commentService) {
        this.postService = postService;
        this.lostItemService = lostItemService;
        this.commentService = commentService;
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
            @RequestParam("image") MultipartFile imageFile,
            HttpSession session,
            Model model) throws IOException {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);

        String imagePath = null;

        if (imageFile != null && !imageFile.isEmpty()) {
            String originalFilename = imageFile.getOriginalFilename();
            String savedFilename = UUID.randomUUID() + "_" + originalFilename;

            System.out.println("originalFilename: " + imageFile.getOriginalFilename());
            System.out.println("UUID: " + UUID.randomUUID());

            // 절대 경로로 저장
            String uploadDir = System.getProperty("user.dir") + "/images/";
            Path uploadPath = Paths.get(uploadDir);

// 디렉토리가 없으면 생성
            Files.createDirectories(uploadPath);

// 파일 저장
            Path savePath = uploadPath.resolve(savedFilename);
            imageFile.transferTo(savePath.toFile());

            System.out.println("Save path: " + savePath.toString());
            // 웹에서 접근 가능한 경로로 설정
            imagePath = "/images/" + savedFilename;
        }

        Post post = new Post();
        post.setTitle(title);
        post.setFoundPlace(foundPlace);
        post.setStorageLocation(storageLocation);
        post.setDescription(description);
        post.setUser(user);
        post.setPostType(PostType.found);
        post.setImageUrl(imagePath);

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

        Optional<Post> postOpt = postService.getPostById(id);
        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            model.addAttribute("foundPost", post);

            List<Comment> comments = commentService.getCommentsByPost(post);
            model.addAttribute("comments", comments);  // 댓글 목록 추가
        }

        return "get_detail";
    }

    @GetMapping
    public String showLostItems(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "NONE") DateType date,
            @RequestParam(defaultValue = "NONE") CategoryType category,
            @RequestParam(required = false) String place,
            @RequestParam(defaultValue = "NONE") StatusType status,
            HttpSession session,
            Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);

        List<Post> filteredPosts = postService.getFilteredLostPosts(date, category, keyword, place, PostType.found, status);

        model.addAttribute("foundPosts", filteredPosts);
        model.addAttribute("keyword",keyword);
        model.addAttribute("selectedDate", date.name());
        model.addAttribute("selectedCategory", category.name());
        model.addAttribute("place", place);
        model.addAttribute("status", status.name());

        return "get_list";
    }

    @PostMapping("/{id}/status")
    public String updateFoundStatus(@PathVariable int id, HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);

        postService.toggleIsRecovered(id);
        return "redirect:/found-items/" + id;
    }

    @PostMapping("/{id}/comments")
    public String updateFoundComments(
            @PathVariable int id,
            @RequestParam("content") String content,
            HttpSession session,
            Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setPost(postService.getPostById(id).get());
        commentService.saveComment(comment);

        return "redirect:/found-items/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteFoundPost(@PathVariable int id, HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);

        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            postService.deletePost(id);
        }

        return "redirect:/found-items";
    }

    @GetMapping("/{id}/edit")
    public String editFoundPost(@PathVariable int id, HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);
        Optional<Post> post = postService.getPostById(id);
        model.addAttribute("foundPost", post);
        return "get_edit";
    }
}
