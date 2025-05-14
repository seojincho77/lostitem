package com.example.lostitem.controllers;

import com.example.lostitem.models.Users;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user); // 세션에서 사용자 정보를 model에 추가
        return "main"; // home.html 파일 반환
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/"; // 홈 화면으로 리다이렉트
    }
}
