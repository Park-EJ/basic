package com.example.basicmember.service;

import com.example.basicmember.dto.MemberResponseDto;
import com.example.basicmember.entity.Member;
import com.example.basicmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto save(String name, Integer age) {
        Member member = new Member(name, age);
        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(savedMember.getId(), savedMember.getName(), savedMember.getAge());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();

        List<MemberResponseDto> dtos = new ArrayList<>();
        for (Member member : members) {
            dtos.add(new MemberResponseDto(member.getId(), member.getName(), member.getAge()));
        }

        return dtos;
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findByid(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다."));

        return new MemberResponseDto(member.getId(), member.getName(), member.getAge());

    }

    @Transactional
    public MemberResponseDto update(Long id, String name, Integer age) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다."));

        member.update(name, age);

        return new MemberResponseDto(member.getId(), member.getName(), member.getAge());
    }

    @Transactional
    public void delete(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 존재하지 않습니다.");
        }

        memberRepository.deleteById(id);
    }

}