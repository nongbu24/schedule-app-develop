package com.scheduleappdevelop.schedule.controller;

import com.scheduleappdevelop.schedule.dto.*;
import com.scheduleappdevelop.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 생성
    @PostMapping
    public ResponseEntity<ScheduleCreateResponse> create(
            @RequestBody ScheduleCreateRequest request
    ) {
        ScheduleCreateResponse response = scheduleService.saveSchedule(request);
        URI location = URI.create("/schedules" + response.getId());

        return ResponseEntity.created(location).body(response);
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<ScheduleGetResponse>> getAll() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    // 단 건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleGetResponse> getSchedule(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(scheduleService.findSchedule(id));
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleUpdateResponse> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequest request
    ) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id,request));
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);

        return ResponseEntity.noContent().build();
    }
}
