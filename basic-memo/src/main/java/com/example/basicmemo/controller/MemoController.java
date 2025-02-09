package com.example.basicmemo.controller;

import com.example.basicmemo.dto.MemoRequestDto;
import com.example.basicmemo.dto.MemoResponseDto;
import com.example.basicmemo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memos")
public class MemoController {

    private final MemoService memoService;

    // 메모 생성
    @PostMapping
    public ResponseEntity<MemoResponseDto> save(@RequestBody MemoRequestDto requestDto) {
        return ResponseEntity.ok(memoService.save(requestDto.getName(), requestDto.getTitle(), requestDto.getContents()));
    }

    // 메모 전체 조회
    @GetMapping
    public ResponseEntity<List<MemoResponseDto>> findAll() {
        return ResponseEntity.ok(memoService.findAll());
    }

    // 메모 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(memoService.findById(id));
    }

    // 메모 수정
    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDto> update(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return ResponseEntity.ok(memoService.update(id, requestDto.getName(), requestDto.getTitle(), requestDto.getContents()));
    }

    // 메모 삭제
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        memoService.deleteById(id);
    }
}