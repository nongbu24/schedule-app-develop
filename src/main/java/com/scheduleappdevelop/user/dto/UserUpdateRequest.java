package com.scheduleappdevelop.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserUpdateRequest {
    @NotBlank(message = "이름을 입력하세요.")
    @Size(max = 4, message = "이름은 4자 이하로 설정해 주세요.")
    private final String name;

    public UserUpdateRequest(String name) {
        this.name = name;
    }
}
