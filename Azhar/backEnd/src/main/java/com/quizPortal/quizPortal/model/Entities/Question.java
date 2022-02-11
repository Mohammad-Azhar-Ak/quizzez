package com.quizPortal.quizPortal.model.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quizPortal.quizPortal.model.BaseTime;

import javax.persistence.*;


@Entity
public class Question extends BaseTime {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(200)")
    private String statement;

    @JsonIgnore
    private Boolean answer;

    private Integer marks;


    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public Integer getId() {
        return id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Boolean isAnswer() {
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

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
