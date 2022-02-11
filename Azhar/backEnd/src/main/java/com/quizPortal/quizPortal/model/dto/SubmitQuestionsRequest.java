package com.quizPortal.quizPortal.model.dto;

public class SubmitQuestionsRequest {

    private Integer id;
    private Boolean answer;

    public int getId() {
        return id;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }
}

