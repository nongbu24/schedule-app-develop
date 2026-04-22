package com.scheduleappdevelop.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleListResponse {
    private List<Schedule> scheduleList;

    public ScheduleListResponse(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    // 내부 클래스 구현
    @Getter
    public static class Schedule {
        private Long id;
        private String user;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public Schedule(Long id, String user, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
            this.id = id;
            this.user = user;
            this.title = title;
            this.content = content;
            this.createdAt = createdAt;
            this.modifiedAt = modifiedAt;
        }
    }
}
