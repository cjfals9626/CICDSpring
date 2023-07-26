package com.example.SpringSecurity.dto;

import com.example.SpringSecurity.domain.User;
import com.example.SpringSecurity.enums.UserRoleType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestSignupDto {
    private String username;
    private String password;
    private String roleType;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .roleType(UserRoleType.valueOf(roleType))
                .build();
    }
}
