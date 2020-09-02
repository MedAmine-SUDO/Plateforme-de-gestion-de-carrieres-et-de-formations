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
    private String user_id;
    private String result;

    public Test(String id, Integer numTest, List<Question> questionList, String user_id, String result) {
        this.id = id;
        this.numTest = numTest;
        this.questionList = questionList;
        this.user_id = user_id;
        this.result = result;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id='" + id + '\'' +
                ", numTest=" + numTest +
                ", questionList=" + questionList +
                ", user_id='" + user_id + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
