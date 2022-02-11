package com.quizPortal.quizPortal.model.dto;

import com.quizPortal.quizPortal.model.Gender;

public class UpdateUserRequest {

    private String name;
    private Gender gender;
    private String linkedIn;
    private String hobbies;
    private String mobile;

    public UpdateUserRequest(String name, Gender gender, String linkedIn, String hobbies, String mobile) {
        this.name = name;
        this.gender = gender;
        this.linkedIn = linkedIn;
        this.hobbies = hobbies;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
