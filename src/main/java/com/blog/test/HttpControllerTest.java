package com.blog.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {

    @GetMapping("/http/get")
    private String getTest(Member m) {
        return "get요청 : " + m.toString();
    }

    @PostMapping("/http/post")
    private String postTest(@RequestBody Member m) {
        return "post요청 : " + m.toString();
    }

    @PutMapping("/http/put")
    private String putTest(@RequestBody Member m) {
        return "put요청 : " + m.toString();
    }

    @DeleteMapping("/http/delete")
    private String deleteTest() {
        return "delete요청";
    }
}
