package com.scheduleappdevelop.schedule.controller;

import com.scheduleappdevelop.schedule.dto.*;
import com.scheduleappdevelop.schedule.service.ScheduleService;
import com.scheduleappdevelop.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 생성
    @PostMapping
    public ResponseEntity<ScheduleCreateResponse> create(
            @SessionAttribute(name = "loginUser", required = false) User user,
            @RequestBody ScheduleCreateRequest request
    ) {
        validateLogin(user);

        ScheduleCreateResponse response = scheduleService.save(user, request);
        URI location = URI.create("/schedules/" + response.getId());

        return ResponseEntity.created(location).body(response);
    }

    // 전체 조회 (로그인한 유저의 일정만)
    @GetMapping
    public ResponseEntity<ScheduleListResponse> getAll(
            @SessionAttribute(name = "loginUser", required = false) User user
    ) {
        validateLogin(user);

        return ResponseEntity.ok(scheduleService.findAll(user.getId()));
    }

    // 단 건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleGetResponse> getSchedule(
            @SessionAttribute(name = "loginUser", required = false) User user,
            @PathVariable Long id
    ) {
        validateLogin(user);

        return ResponseEntity.ok(scheduleService.findSchedule(user.getId(), id));
    }

    // 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleUpdateResponse> updateSchedule(
            @SessionAttribute(name = "loginUser", required = false) User user,
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequest request
    ) {
        validateLogin(user);

        return ResponseEntity.ok(scheduleService.updateSchedule(user.getId(), id, request));
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @SessionAttribute(name = "loginUser", required = false) User user,
            @PathVariable Long id
    ) {
        validateLogin(user);

        scheduleService.deleteSchedule(user.getId(), id);

        return ResponseEntity.noContent().build();
    }

    // 로그인 검증 메서드
    private void validateLogin(User user) {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
    }
}
