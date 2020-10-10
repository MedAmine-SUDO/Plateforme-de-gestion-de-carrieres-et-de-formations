package com.carthageSolution.testNiveau.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="questions")
public class Question {
    @Id
    private String id;

    private Integer questionNbr;
    private String questionContent;
    private List<Answer> answers;
    private Difficulty difficulty;

    public Question() {
    }

    public Question(String id, Integer questionNbr, List<Answer> answers, String questionContent, Difficulty difficulty) {
        this.id = id;
        this.questionNbr = questionNbr;
        this.answers = answers;
        this.questionContent = questionContent;
        this.difficulty = difficulty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuestionNbr() {
        return questionNbr;
    }

    public void setQuestionNbr(Integer questionNbr) {
        this.questionNbr = questionNbr;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", questionNbr=" + questionNbr +
                ", answers=" + answers +
                ", questionContent='" + questionContent + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }
}
