package com.scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class UserUpdateRequest {
    private final String name;
    private final String password;

    public UserUpdateRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
