package com.scheduleappdevelop.user.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreateRequest {
    private final String name;
    private final String email;

    @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
    private final String password;

    public UserCreateRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
