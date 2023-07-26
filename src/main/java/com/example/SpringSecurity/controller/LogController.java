package com.example.SpringSecurity.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class LogController {
    @GetMapping("/logTest")
    public String String(String str){
        try {
            str.toString();
        } catch (NullPointerException e){
            log.trace("가장 디테일한 로그={}",e.getMessage());
            log.warn("경고={}",e.getMessage());
            log.error("에러={}",e.getMessage());
            log.info("인포={}",e.getMessage());
            log.debug("디버그={}",e.getMessage());
        }
        return "log_test";
    }
}
