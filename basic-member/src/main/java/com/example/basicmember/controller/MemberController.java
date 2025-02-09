package com.example.basicmember.controller;

import com.example.basicmember.dto.MemberRequestDto;
import com.example.basicmember.dto.MemberResponseDto;
import com.example.basicmember.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberservice;

    // 멤버 생성
    @PostMapping
    public ResponseEntity<MemberResponseDto> save(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(memberservice.save(requestDto.getName(), requestDto.getAge()));
    }

    // 멤버 전체 조회
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> findAll() {
        return ResponseEntity.ok(memberservice.findAll());
    }

    // 멤버 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(memberservice.findByid(id));
    }

    // 멤버 수정
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> update(@PathVariable Long id, @RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(memberservice.update(id, requestDto.getName(), requestDto.getAge()));
    }

    // 멤버 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        memberservice.delete(id);
    }

}