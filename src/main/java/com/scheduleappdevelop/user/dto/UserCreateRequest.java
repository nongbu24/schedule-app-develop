package com.scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class UserCreateRequest {
    private final String name;
    private final String email;

    public UserCreateRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
