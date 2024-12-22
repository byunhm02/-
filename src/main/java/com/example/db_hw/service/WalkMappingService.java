package com.example.db_hw.service;
import com.example.db_hw.entity.Member;
import com.example.db_hw.entity.WalkMapping;
import com.example.db_hw.repository.MemberRepository;
import com.example.db_hw.repository.WalkMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class WalkMappingService {
    @Autowired
    private WalkMappingRepository walkMappingRepository;
    @Autowired
    private MemberRepository memberRepository;

    public List<String> matchWalk(String region, String walkTime,Long memberId) {
        List<WalkMapping> matches = walkMappingRepository.findByRegionAndWalkTime(region,walkTime);

        // Exclude the current member from the matches
        List<String> matchedMembers = matches.stream()
                .filter(walkMapping -> !walkMapping.getMember().getId().equals(memberId))
                .map(walkMapping -> walkMapping.getMember().getUsername())
                .collect(Collectors.toList());
        return matchedMembers;
    }

    public void saveWalkMapping(Long memberId, String region,String walkTime) {
        WalkMapping walkMapping = new WalkMapping();
        walkMapping.setRegion(region);
        walkMapping.setWalkTime(walkTime);

        Member member=memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        walkMapping.setMember(member);
        walkMappingRepository.save(walkMapping);
    }
}
