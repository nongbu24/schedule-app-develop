package com.scheduleappdevelop.schedule.dto;

import com.scheduleappdevelop.user.entity.User;
import lombok.Getter;

@Getter
public class ScheduleCreateRequest {
    private final User user;
    private final String title;
    private final String content;

    public ScheduleCreateRequest(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }
}
