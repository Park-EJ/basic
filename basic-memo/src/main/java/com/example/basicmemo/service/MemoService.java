package com.example.basicmemo.service;

import com.example.basicmemo.dto.MemoResponseDto;
import com.example.basicmemo.entity.Memo;
import com.example.basicmemo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto save(String name, String title, String contents) {
        Memo memo = new Memo(name, title, contents);
        Memo savedMemo = memoRepository.save(memo);

        return new MemoResponseDto(savedMemo.getId(), savedMemo.getName(), savedMemo.getTitle(), savedMemo.getContents());
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> findAll() {
        List<Memo> memos = memoRepository.findAll();

        List<MemoResponseDto> dtos = new ArrayList<>();
        for (Memo memo : memos) {
            dtos.add(new MemoResponseDto(memo.getId(), memo.getName(), memo.getTitle(), memo.getContents()));
        }

        return dtos;
    }

    @Transactional(readOnly = true)
    public MemoResponseDto findById(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다.")
        );

        return new MemoResponseDto(memo.getId(), memo.getName(), memo.getTitle(), memo.getContents());
    }

    @Transactional
    public MemoResponseDto update(Long id, String name, String title, String contents) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다.")
        );

        memo.update(name, title, contents);

        return new MemoResponseDto(memo.getId(), memo.getName(), memo.getTitle(), memo.getContents());
    }

    @Transactional
    public void deleteById(Long id) {
        if (!memoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다.");
        }

        memoRepository.deleteById(id);
    }
}