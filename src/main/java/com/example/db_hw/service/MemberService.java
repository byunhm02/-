package com.example.db_hw.service;

import com.example.db_hw.dto.MemberDTO;
import com.example.db_hw.entity.Member;
import com.example.db_hw.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.atn.ActionTransition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void save(MemberDTO memberdto) {
        Member member = new Member();
        member.setUsername(memberdto.getUsername());
        member.setPassword(memberdto.getPassword());
        memberRepository.save(member);
    }
    public Member register(MemberDTO memberdto) {
        Member member = new Member();
        member.setUsername(memberdto.getUsername());
        member.setPassword(memberdto.getPassword());
        return memberRepository.save(member);
    }
    public boolean authenticate(MemberDTO memberdto) {
        Member member = memberRepository.findByUsername(memberdto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        //return member != null && Objects.equals(memberdto.getPassword(), member.getPassword());
        // 암호화된 비밀번호 비교
        return passwordEncoder.matches(memberdto.getPassword(), member.getPassword());
    }
}
