package com.example.basicmemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String title;
    private String contents;

    public Memo(String name, String title, String contents) {
        this.name = name;
        this.title = title;
        this.contents = contents;
    }

    public void update(String name, String title, String contents) {
        this.name = name;
        this.title = title;
        this.contents = contents;
    }
}