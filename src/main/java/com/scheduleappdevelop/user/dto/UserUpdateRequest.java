package com.scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class UserUpdateRequest {
    private final String name;

    public UserUpdateRequest(String name) {
        this.name = name;
    }
}
