package com.example.basic.service;

import com.example.basic.dto.MemoRequestDto;
import com.example.basic.dto.MemoResponseDto;
import com.example.basic.entity.Memo;
import com.example.basic.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto save(MemoRequestDto dto) {

        Memo memo = new Memo(dto.getContent());
        Memo savedMemo = memoRepository.save(memo);
        return new MemoResponseDto(savedMemo.getId(), savedMemo.getContent());
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> findAll() {
        List<Memo> memos = memoRepository.findAll();

        List<MemoResponseDto> lists = new ArrayList<>();
        for (Memo memo : memos) {
            lists.add(new MemoResponseDto(memo.getId(), memo.getContent()));
        }

        return lists;
    }

    @Transactional(readOnly = true)
    public MemoResponseDto findById(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 존재하지 않습니다.")
        );

        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    @Transactional
    public MemoResponseDto updateContent(Long id, MemoRequestDto dto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 존재하지 않습니다.")
        );

        Memo updatedMemo = memoRepository.updateContent(memo.getId(), dto.getContent());

        return new MemoResponseDto(updatedMemo.getId(), updatedMemo.getContent());
    }

    @Transactional
    public void deleteById(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 존재하지 않습니다.")
        );
        memoRepository.deleteById(memo.getId());
    }
}
