package com.carthageSolution.learningStyleTest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="tests")
public class Test {

    @Id
    private String id;
    
    private Integer numTest;
    private List<Question> questionList;

    public Test(String id, Integer numTest, List<Question> questionList) {
        this.id = id;
        this.numTest = numTest;
        this.questionList = questionList;
    }

    public Test() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumTest() {
        return numTest;
    }

    public void setNumTest(Integer numTest) {
        this.numTest = numTest;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id_test='" + id + '\'' +
                ", numTest=" + numTest +
                ", questionList=" + questionList +
                '}';
    }
}
