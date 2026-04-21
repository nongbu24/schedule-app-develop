package com.scheduleappdevelop.schedule.service;

import com.scheduleappdevelop.schedule.entity.Schedule;
import com.scheduleappdevelop.schedule.repository.ScheduleRepository;
import com.scheduleappdevelop.schedule.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // 생성
    @Transactional
    public ScheduleCreateResponse saveSchedule(ScheduleCreateRequest request) {
        Schedule schedule = new Schedule(
                request.getUser(),
                request.getTitle(),
                request.getContent()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleCreateResponse(
                savedSchedule.getId(),
                savedSchedule.getUser(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<ScheduleGetResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleGetResponse> dtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            dtos.add(new ScheduleGetResponse(
                    schedule.getId(),
                    schedule.getUser(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            ));
        }

        return dtos;
    }

    // 단 건 조회
    @Transactional(readOnly = true)
    public ScheduleGetResponse findSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );

        return new ScheduleGetResponse(
                schedule.getId(),
                schedule.getUser(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 수정
    @Transactional
    public ScheduleUpdateResponse updateSchedule(Long id, ScheduleUpdateRequest request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );

        schedule.update(request.getTitle(), request.getContent());

        return new ScheduleUpdateResponse(
                schedule.getId(),
                schedule.getUser(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 삭제
    @Transactional
    public void deleteSchedule(Long id) {
        boolean existence = scheduleRepository.existsById(id);
        if (!existence) {
            throw new IllegalArgumentException("존재하지 않는 일정입니다.");
        }

        scheduleRepository.deleteById(id);
    }
}
