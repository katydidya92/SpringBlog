package com.cos.blog.test;

import lombok.*;

@Getter
@Setter
@ToString
public class Member {

    private Long id;
    private String username;
    private String password;
    private String email;

    @Builder
    public Member(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
