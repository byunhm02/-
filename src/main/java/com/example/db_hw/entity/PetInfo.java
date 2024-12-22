package com.example.db_hw.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
//import org.springframework.data.annotation.Id;

@Entity
public class PetInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String petType;
    private int age;
    private String region;
    private String walkTime;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // Getters and Setters for user
    public Member getMember() {
        return member;
    }

    public void setUser(Member member) {
        this.member = member;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
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
}
