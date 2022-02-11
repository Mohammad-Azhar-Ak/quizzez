package com.quizPortal.quizPortal.model.dto;


public class CreateAndUpdateQuestionRequest{

    private String statement;
    private Boolean answer;
    private Integer marks;


    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }
}
