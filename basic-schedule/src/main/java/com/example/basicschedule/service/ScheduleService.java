package com.example.basicschedule.service;

import com.example.basicschedule.dto.ScheduleResponseDto;
import com.example.basicschedule.entity.Schedule;
import com.example.basicschedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto save(String name, String password, String task) {
        Schedule schedule = new Schedule(name, password, task);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getName(), savedSchedule.getTask());
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        List<ScheduleResponseDto> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            dtos.add(new ScheduleResponseDto(schedule.getId(), schedule.getName(), schedule.getTask()));
        }

        return dtos;
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다.")
        );

        return new ScheduleResponseDto(schedule.getId(), schedule.getName(), schedule.getTask());
    }

    @Transactional
    public ScheduleResponseDto update(Long id, String name, String password, String task) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다.")
        );

        if (!schedule.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "비밀번호가 일치하지 않습니다.");
        }

        schedule.update(name, task);

        return new ScheduleResponseDto(schedule.getId(), schedule.getName(), schedule.getTask());
    }

    @Transactional
    public void deleteById(Long id, String password) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다.")
        );

        if (!schedule.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.deleteById(id);
    }
}