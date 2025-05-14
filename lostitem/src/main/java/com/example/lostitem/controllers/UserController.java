package com.example.lostitem.controllers;

import com.example.lostitem.models.Users;
import com.example.lostitem.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.Optional;


@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "signup";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam("loginId") String loginId,
            @RequestParam("password") String password,
            @RequestParam("password2") String password2,  // password2를 별도로 받음
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            Model model) {
        // 비밀번호 확인
        if (!password.equals(password2)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "signup";  // 비밀번호가 일치하지 않으면 폼을 다시 보여줌
        }

        Optional<Users> already = userService.getUserByUserId(loginId);
        if (already.isPresent()) {
            model.addAttribute("error", "이미 존재하는 사용자 ID 입니다.");
            return "signup";
        }

        // 비밀번호 일치하면 Users 객체를 생성하고, 회원가입 로직 추가
        Users user = new Users();
        user.setLoginId(loginId);
        user.setPassword(password);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);

        userService.saveUser(user);

        return "redirect:/login";  // 회원가입 성공 시 로그인 페이지로 리다이렉트
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(
            @RequestParam String loginId,
            @RequestParam String password,
            Model model,
            HttpSession session) {
        Optional<Users> check = userService.getUserByUserId(loginId);

        if(check.isEmpty()){
            model.addAttribute("error", "존재하지 않는 사용자입니다.");
            return "login";
        }

        if(!check.get().getPassword().equals(password)){
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "login";
        }

        session.setAttribute("user", check.get());

        return "redirect:/";
    }
}

//데이터베이스 연결실험이라 지우셔도 돼요
/**
package com.example.lostitem.controllers;

import com.example.lostitem.models.Users;
import com.example.lostitem.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //테스트용 사용자 삽입
    @PostMapping("/test-insert")
    public Users testInsertUser() {
        Users user = new Users();
        user.setUserName("TestUser");
        user.setUserEmail("test@example.com");
        user.setUserPassword("1234");
        user.setIsAdmin(false);
        user.setCreatedAt(LocalDateTime.now());

        return userService.saveUser(user);
    }

    //모든 사용자 조회
    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }
}
*/
