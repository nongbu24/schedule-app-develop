package com.scheduleappdevelop.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UserListResponse {
    private final List<User> userList;

    public UserListResponse(List<User> userList) {
        this.userList = userList;
    }

    @Getter
    public static class User {
        private final Long id;
        private final String name;
        private final String email;
        private final LocalDateTime createdAt;
        private final LocalDateTime modifiedAt;

        public User(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.createdAt = createdAt;
            this.modifiedAt = modifiedAt;
        }
    }
}
