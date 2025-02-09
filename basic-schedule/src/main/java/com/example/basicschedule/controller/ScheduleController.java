package com.example.basicschedule.controller;

import com.example.basicschedule.dto.ScheduleRequestDto;
import com.example.basicschedule.dto.ScheduleResponseDto;
import com.example.basicschedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 스케줄 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.save(requestDto.getName(), requestDto.getPassword(), requestDto.getTask()));
    }

    // 스케줄 전체 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    // 스케줄 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    // 스케줄 수정(비밀번호 확인 필요, name & task 두가지만 수정 가능)
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.update(id, requestDto.getName(), requestDto.getPassword(), requestDto.getTask()));
    }

    // 스케줄 삭제(비밀번호 확인 필요)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        scheduleService.deleteById(id, requestDto.getPassword());
    }
}