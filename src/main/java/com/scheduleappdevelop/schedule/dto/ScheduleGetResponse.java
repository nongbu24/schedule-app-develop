package com.scheduleappdevelop.schedule.dto;

import com.scheduleappdevelop.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleGetResponse {
    private final Long id;
    private final User user;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ScheduleGetResponse(Long id, User user, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
