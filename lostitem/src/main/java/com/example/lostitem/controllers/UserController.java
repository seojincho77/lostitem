package com.example.lostitem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
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
