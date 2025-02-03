package com.example.basic.repository;

import com.example.basic.entity.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {

    Memo save(Memo memo);

    List<Memo> findAll();

    Optional<Memo> findById(Long id);

    Memo updateContent(Long id, String content);

    void deleteById(Long id);
}
