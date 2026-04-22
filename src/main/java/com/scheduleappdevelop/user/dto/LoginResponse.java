package com.scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final Long id;
    private final String name;

    public LoginResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
