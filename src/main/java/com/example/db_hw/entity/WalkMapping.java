package com.example.db_hw.entity;

import jakarta.persistence.*;

@Entity
public class WalkMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String region;
    private String walkTime;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getWalkTime() {
        return walkTime;
    }
    public void setWalkTime(String walkTime) {
        this.walkTime = walkTime;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }


}
