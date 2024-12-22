package com.example.db_hw.controller;

import com.example.db_hw.dto.MemberDTO;
import com.example.db_hw.entity.Member;
import com.example.db_hw.service.CustomUserDetailsService;
import com.example.db_hw.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthController {
    private final MemberService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }
    @Autowired
    public CustomUserDetailsService customUserDetailsService;
    @GetMapping("auth/save")
    public String saveFrom(){
        return "save";
    }
    @GetMapping("auth/register")
    public String registerForm() {
        return "register"; // 회원가입 폼 페이지(register.html)를 반환
    }
    /*
    @PostMapping("auth/register")
    public String register(@RequestParam("username") String username,@RequestParam("password")String passwoprd){

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUsername(username);
        memberDTO.setPassword(passwoprd);
        memberService.register(memberDTO);


        return "loginForm";
    }

     */
    @PostMapping("auth/register")
    public String register(MemberDTO memberDTO) {
        Member member = new Member();
        member.setUsername(memberDTO.getUsername());
        member.setPassword(memberDTO.getPassword());
        customUserDetailsService.saveUser(member);
        return "login";
    }
    @GetMapping("auth/login")
    public String loginForm(){
        return "login";
    }
    @PostMapping("auth/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUsername(username);
        memberDTO.setPassword(password);

        boolean isAuthenticated = memberService.authenticate(memberDTO);

        if (isAuthenticated) {
            return "main"; // 메인 페이지로 이동
        } else {
            return "login"; // 로그인 폼으로 다시 이동
        }
    }

}

