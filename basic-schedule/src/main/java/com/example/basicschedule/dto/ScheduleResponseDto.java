package com.example.basicschedule.dto;

import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private final Long id;
    private final String name;
    private final String task;

    public ScheduleResponseDto(Long id, String name, String task) {
        this.id = id;
        this.name = name;
        this.task = task;
    }
}