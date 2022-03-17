package com.blog.service;

import com.blog.model.User;
import com.blog.model.RoleType;
import com.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public void join(User requestUser) {
        String password = requestUser.getPassword();
        String encPassword = encoder.encode(password);

        System.out.println("join : encPassword = " + encPassword);

        User user = User.builder()
                .username(requestUser.getUsername())
                .password(encPassword)
                .role(RoleType.USER)
                .email(requestUser.getEmail())
                .build();
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원 찾기 실패"));
        return user;
    }

    @Transactional(readOnly = true)
    public User findUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseGet(() -> new User());
        return user;
    }

    public void update(User user) {
        User findUser = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("회원 찾기 실패"));
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        findUser.setPassword(encPassword);
        findUser.setEmail(user.getEmail());
    }

//    @Transactional(readOnly = true)
//    public User login(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
