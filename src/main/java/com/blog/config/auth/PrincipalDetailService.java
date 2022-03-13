package com.blog.config.auth;

import com.blog.model.User;
import com.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // username DB에 있는지 확인
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 사용자를 찾을 수 없습니다. : " + username));
        System.out.println(new PrincipalDetail(user).getPassword());
        // 비밀번호 가져오는 로직 오타로 진짜 비밀번호가 나오는 문제 발생
        // -> encoded password does not look like bcrypt 오류가 발생  
        return new PrincipalDetail(user);
        // 시큐리티의 세션에 유저 정보 저장
    }
}
