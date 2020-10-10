package com.carthageSolution.learningStyleTest.model;

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

    public Question(String id, Integer questionNbr, String questionContent, List<Answer> answers) {
        this.id = id;
        this.questionNbr = questionNbr;
        this.questionContent = questionContent;
        this.answers = answers;
    }

    public Question() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
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

    @Override
    public String toString() {
        return "Question{" +
                "id_question='" + id + '\'' +
                ", questionNbr=" + questionNbr +
                ", questionContent='" + questionContent + '\'' +
                ", answers=" + answers +
                '}';
    }
}
