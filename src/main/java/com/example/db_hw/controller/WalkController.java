package com.example.db_hw.controller;

import com.example.db_hw.entity.Member;
import com.example.db_hw.entity.WalkMapping;
import com.example.db_hw.repository.MemberRepository;
import com.example.db_hw.service.WalkMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class WalkController {
    @Autowired
    public WalkMappingService walkMappingService;
    @Autowired
    public MemberRepository memberRepository;
    @GetMapping("/walk/mapping")
    public String walkMapping() {
        return "walkMapping"; // walkMapping.html 페이지 반환
    }
    /*
    @PostMapping("/walk/mapping")
    public String saveWalkMapping(@RequestParam("region") String region,
                                  @RequestParam("walkTime") String walkTime,
                                  @RequestParam("memberId") Long memberId,
                                  Model model) {
        walkMappingService.saveWalkMapping(memberId, region, walkTime);
        // 매칭 로직
        List<String> matchedMembers= walkMappingService.matchWalk(region, walkTime,memberId);
        model.addAttribute("region", region);
        model.addAttribute("walkTime", walkTime);
        //model.addAttribute("matches", matches);

        if (matchedMembers.isEmpty()) {
            model.addAttribute("noMatches", true);
        } else {
            model.addAttribute("matchedMembers", matchedMembers);
        }
        return "walkmatched";
    }

     */
    @PostMapping("/walk/mapping")
    public String saveWalkMapping(@RequestParam("region") String region,
                                  @RequestParam("walkTime") String walkTime,
                                  Model model,
                                  @AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        // 현재 로그인된 사용자의 정보를 통해 ID 가져오기
        Member loggedInMember = memberRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Long memberId = loggedInMember.getId();

        // 저장 로직
        walkMappingService.saveWalkMapping(memberId, region, walkTime);

        // 매칭 로직
        List<String> matchedMembers = walkMappingService.matchWalk(region, walkTime, memberId);
        model.addAttribute("region", region);
        model.addAttribute("walkTime", walkTime);
        model.addAttribute("matchedMembers", matchedMembers);

        return "walkMatched";
    }

}
