package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.dto.MsgDto;
import com.example.SpringSecurity.dto.RequestLoginDto;
import com.example.SpringSecurity.dto.RequestSignupDto;
import com.example.SpringSecurity.exception.DuplicateMemberException;
import com.example.SpringSecurity.service.AuthService;
import com.example.SpringSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<MsgDto> signup(@RequestBody RequestSignupDto requestSignupDto) {
        try{
            authService.signup(requestSignupDto);
        }catch (DuplicateMemberException e) {
            return ResponseEntity.ok(new MsgDto(false, "회원가입 실패", null));
        }
        return ResponseEntity.ok(new MsgDto(true, "회원가입 성공", null));
    }

    @PostMapping("/login")
    public ResponseEntity<MsgDto> login(@RequestBody RequestLoginDto requestLoginDto) {
        /*ResponseLoginDto responseLoginDto = authService.login(requestLoginDto);
        if(responseLoginDto.getAccessToken() != null){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(ACCESS_TOKEN, HEADER + responseLoginDto.getAccessToken());
            return new ResponseEntity<>(new MsgDto(true, "로그인 성공", memberService.getLoginInfo(requestLoginDto)), httpHeaders, HttpStatus.OK);
        }*/
        boolean loginSuccess = authService.login(requestLoginDto);
        if(loginSuccess){
            return new ResponseEntity<>(new MsgDto(true, "로그인 성공", userService.getLoginInfo(requestLoginDto)), HttpStatus.OK);
        }
        return ResponseEntity.ok(new MsgDto(false, "로그인 실패", null));
    }
}
