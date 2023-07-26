package com.example.SpringSecurity.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestLoginDto {
    private String username;
    private String password;
}
