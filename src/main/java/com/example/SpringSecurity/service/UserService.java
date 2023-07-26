package com.example.SpringSecurity.service;

import com.example.SpringSecurity.domain.User;
import com.example.SpringSecurity.dto.RequestLoginDto;
import com.example.SpringSecurity.dto.RequestSignupDto;
import com.example.SpringSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("MemberService.loadUserByEmail");
        log.info("LOGIN");
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    public void signUp(RequestSignupDto requestSignupDto) {
        log.info("MemberService.signUp={}", requestSignupDto.getUsername());
        User entity = requestSignupDto.toEntity();
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userRepository.save(entity);
    }

    public User getLoginInfo(RequestLoginDto requestLoginDto) {
        log.info("MemberService.getLoginInfo");
        Optional<User> byUsername = userRepository.findByUsername(requestLoginDto.getUsername());

        return byUsername.get();
    }
}
