package com.scheduleappdevelop.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleUpdateRequest {
    @NotBlank(message = "제목은 비워둘 수 없습니다.")
    @Size(max = 20, message = "제목은 20자를 넘을 수 없습니다.")
    private final String title;

    private final String content;

    public ScheduleUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
