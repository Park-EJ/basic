package com.example.basicmember.dto;

import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
public class MemberRequestDto {

    private String name;
    private Integer age;

}