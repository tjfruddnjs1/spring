package com.spring.socket.controller;

import com.spring.socket.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @NonNull
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

}

