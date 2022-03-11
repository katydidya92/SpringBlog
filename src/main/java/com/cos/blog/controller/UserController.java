package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String loginForm() {

        return "/user/loginForm";
    }

    @PostMapping("/login")
    public String login() {

        return "";
    }

    @GetMapping("/join")
    public String joinForm() {

        return "/user/joinForm";
    }

    @PostMapping("/join")
    public String join() {

        return "";
    }
}
