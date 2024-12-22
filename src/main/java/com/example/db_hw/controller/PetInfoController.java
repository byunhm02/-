package com.example.db_hw.controller;
import com.example.db_hw.dto.PetInfoDTO;
import com.example.db_hw.entity.Member;
import com.example.db_hw.repository.MemberRepository;
import com.example.db_hw.service.PetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class PetInfoController {
    @Autowired
    private PetInfoService petInfoService;
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/petInfo")
    public String petInfoForm(Model model) {
        Long memberId=1L;
        model.addAttribute("memberId",memberId);
        return "petInfoForm"; // petInfoForm.html
    }
    @PostMapping("/petInfo")
    public String savePetInfo(@RequestParam("petType") String petType,
                              @RequestParam("age") int age,
                              @RequestParam("region") String region,
                              @RequestParam("walkTime") String walkTime,
                              @AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        // 현재 로그인된 사용자의 정보를 가져옴
        String username = user.getUsername();

        // Member 정보 가져오기
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // PetInfo 저장 로직
        PetInfoDTO petInfoDTO = new PetInfoDTO();
        petInfoDTO.setPetType(petType);
        petInfoDTO.setAge(age);
        petInfoDTO.setRegion(region);
        petInfoDTO.setWalkTime(walkTime);

        petInfoService.savePetInfo(petInfoDTO, member.getId());
        return "redirect:/main"; // 메인 페이지로 리다이렉트
    }

}
