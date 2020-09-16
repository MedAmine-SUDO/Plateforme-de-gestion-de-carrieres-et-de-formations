package com.carthageSolution.testNiveau.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
<<<<<<< HEAD
=======
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
>>>>>>> 8f133aef5598cab05f12a756f6194ce2b6f7551b
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="tests")
public class Test {

    @Transient
    public static final String SEQUENCE_NAME="test_sequence";

    @Id
<<<<<<< HEAD
    private String id;
=======
    private String id_test;
>>>>>>> 8f133aef5598cab05f12a756f6194ce2b6f7551b

    private Long numTest;
    private List<Question> questionList;
    private String userId;
    private Integer result;

    public Test() {
    }

    public Test(Long numTest, List<Question> questionList, String userId, Integer result) {
        this.numTest = numTest;
        this.questionList = questionList;
        this.userId = userId;
        this.result = result;
    }

<<<<<<< HEAD
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
=======
    public String getId_test() {
        return id_test;
    }

    public void setId_test(String id_test) {
        this.id_test = id_test;
>>>>>>> 8f133aef5598cab05f12a756f6194ce2b6f7551b
    }

    public Long getNumTest() {
        return numTest;
    }

    public void setNumTest(Long numTest) {
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

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Test{" +
<<<<<<< HEAD
                "id_test='" + id + '\'' +
=======
                "id_test='" + id_test + '\'' +
>>>>>>> 8f133aef5598cab05f12a756f6194ce2b6f7551b
                ", numTest=" + numTest +
                ", questionList=" + questionList +
                ", user_id='" + userId + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
