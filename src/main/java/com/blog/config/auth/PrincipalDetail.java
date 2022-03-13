package com.blog.config.auth;

import com.blog.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class PrincipalDetail implements UserDetails {
    private User user;

    public PrincipalDetail(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정 만료 (true : 사용 가능)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 (true: 사용 가능)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 (true : 사용 가능)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 (true : 사용 가능)
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 계정 권한 목록( [ROLE_] + 권한명 형태로 사용한다.)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections = new ArrayList<>();
        collections.add(() -> "ROLE_"+ user.getRole());
        return collections;
    }
}
