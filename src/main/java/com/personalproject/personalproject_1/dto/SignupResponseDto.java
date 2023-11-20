package com.personalproject.personalproject_1.dto;

import com.personalproject.personalproject_1.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDto {
    private String username;
    private String password;

    public SignupResponseDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
