package com.blog.test;

import com.blog.model.User;
import com.blog.model.RoleType;
import com.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/test")
@RestController
public class TestController {

    private final UserRepository userRepository;

    @PostMapping("/hello")
    public String hello(User user) {
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "<h1>hello</h1>";
    }

    @GetMapping("/search/{id}")
    public String Search(@PathVariable int id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 없습니다."));
        System.out.println("user = " + user.toString());
        return "<h1>check</h1>";
    }

    @GetMapping("/users/page")
    public Page<User> users(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }

    @Transactional
    @PutMapping("/hello/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
        System.out.println("id : " + id);
        System.out.println("password : " + requestUser.getPassword());
        System.out.println("Email : " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디를 찾을 수 없습니다."));
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        return user;
    }
    
    @Transactional
    @DeleteMapping("/hello/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "삭제 완료";
    }

}
