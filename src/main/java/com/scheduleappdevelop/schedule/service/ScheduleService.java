package com.scheduleappdevelop.schedule.service;

import com.scheduleappdevelop.schedule.entity.Schedule;
import com.scheduleappdevelop.schedule.repository.ScheduleRepository;
import com.scheduleappdevelop.schedule.dto.*;
import com.scheduleappdevelop.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // 생성
    @Transactional
    public ScheduleCreateResponse save(User user, ScheduleCreateRequest request) {
        Schedule schedule = new Schedule(
                user,
                request.getTitle(),
                request.getContent()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleCreateResponse(
                savedSchedule.getId(),
                savedSchedule.getUser().getName(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public ScheduleListResponse findAll(Long userId) {
        List<Schedule> schedules = scheduleRepository.findByUserId(userId);

        List<ScheduleListResponse.Schedule> scheduleList = schedules.stream()
                .map(schedule -> new ScheduleListResponse.Schedule(
                        schedule.getId(),
                        schedule.getUser().getName(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                ))
                .toList();

        return new ScheduleListResponse(scheduleList);
    }

    // 단 건 조회
    @Transactional(readOnly = true)
    public ScheduleGetResponse findSchedule(Long userId, Long id) {
        Schedule schedule = getScheduleOrThrow(userId, id);

        return new ScheduleGetResponse(
                schedule.getId(),
                schedule.getUser().getName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 수정
    @Transactional
    public ScheduleUpdateResponse updateSchedule(Long userId, Long id, ScheduleUpdateRequest request) {
        Schedule schedule = getScheduleOrThrow(userId, id);

        schedule.update(request.getTitle(), request.getContent());

        return new ScheduleUpdateResponse(
                schedule.getId(),
                schedule.getUser().getName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 삭제
    @Transactional
    public void deleteSchedule(Long userId, Long id) {
        Schedule schedule = getScheduleOrThrow(userId, id);

        scheduleRepository.delete(schedule);
    }

    // 일정 id 및 유저 id 검증 메서드
    private Schedule getScheduleOrThrow(Long userId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );

        if (!schedule.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        return schedule;
    }
}
