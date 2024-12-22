package com.example.db_hw.service;
import com.example.db_hw.dto.PetInfoDTO;
import com.example.db_hw.entity.Member;
import com.example.db_hw.entity.PetInfo;
import com.example.db_hw.repository.MemberRepository;
import com.example.db_hw.repository.PetInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PetInfoService {
    @Autowired
    private PetInfoRepository petInfoRepository;
    @Autowired
    private MemberRepository memberRepository;

    public void savePetInfo(PetInfoDTO petInfoDTO,Long memberId) {
        PetInfo petInfo = new PetInfo();
        petInfo.setPetType(petInfoDTO.getPetType());
        petInfo.setAge(petInfoDTO.getAge());
        petInfo.setRegion(petInfoDTO.getRegion());
        petInfo.setWalkTime(petInfoDTO.getWalkTime());
        Member member= memberRepository.findById(memberId).orElseThrow();
        petInfo.setUser(member);
        petInfoRepository.save(petInfo);
    }
}
