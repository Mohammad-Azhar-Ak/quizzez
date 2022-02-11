package com.quizPortal.quizPortal.model.dto;

public class AfterSubmitResponse {
    Integer rightCount;
    Integer wrongCount;
    Integer totalScore;
    Integer totalMarks;

    public AfterSubmitResponse(Integer rightCount, Integer wrongCount, Integer totalScore, Integer totalMarks) {
        this.rightCount = rightCount;
        this.wrongCount = wrongCount;
        this.totalScore = totalScore;
        this.totalMarks = totalMarks;
    }

    public Integer getRightCount() {
        return rightCount;
    }

    public void setRightCount(Integer rightCount) {
        this.rightCount = rightCount;
    }

    public Integer getWrongCount() {
        return wrongCount;
    }

    public void setWrongCount(Integer wrongCount) {
        this.wrongCount = wrongCount;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
    }
}
