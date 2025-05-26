package com.example.lostitem.controllers;

import com.example.lostitem.models.Post;
import com.example.lostitem.models.PostType;
import com.example.lostitem.models.Users;
import com.example.lostitem.services.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final PostService postService;

    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user); // 세션에서 사용자 정보를 model에 추가

        List<Post> posts = postService.getRecentPosts();
        model.addAttribute("posts", posts);
        model.addAttribute("found", PostType.found);
        return "main"; // home.html 파일 반환
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/"; // 홈 화면으로 리다이렉트
    }
}
