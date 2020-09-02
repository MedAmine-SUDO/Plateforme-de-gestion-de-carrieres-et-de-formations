package com.carthageSolution.testNiveau.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="tests")
public class Test {

    @Transient
    public static final String SEQUENCE_NAME="test_sequence";

    @Id
    private String id_test;

    private Integer numTest;
    private List<Question> questionList;
    private String userId;
    private String result;

    public Test() {
    }

    public Test(String id_test, Integer numTest, List<Question> questionList, String userId, String result) {
        this.id_test = id_test;
        this.numTest = numTest;
        this.questionList = questionList;
        this.userId = userId;
        this.result = result;
    }

    public String getId_test() {
        return id_test;
    }

    public void setId_test(String id_test) {
        this.id_test = id_test;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
                "id_test='" + id_test + '\'' +
                ", numTest=" + numTest +
                ", questionList=" + questionList +
                ", user_id='" + userId + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
