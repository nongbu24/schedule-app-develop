package com.scheduleappdevelop.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleCreateRequest {
    private final String title;
    private final String content;

    public ScheduleCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
