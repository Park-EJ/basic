package com.example.basicmemo.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {

    private String name;
    private String title;
    private String contents;
}