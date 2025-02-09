package com.example.basicschedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;
    private String task;

    public Schedule(String name, String password, String task) {
        this.name = name;
        this.password = password;
        this.task = task;
    }

    public void update(String name, String task) {
        this.name = name;
        this.task = task;
    }
}