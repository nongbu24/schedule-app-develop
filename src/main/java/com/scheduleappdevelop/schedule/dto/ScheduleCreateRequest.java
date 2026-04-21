package com.scheduleappdevelop.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleCreateRequest {
    private final String user;
    private final String title;
    private final String content;

    public ScheduleCreateRequest(String user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }
}
