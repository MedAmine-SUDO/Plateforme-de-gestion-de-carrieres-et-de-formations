package com.carthageSolution.testNiveau.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "results")
public class Result {

    @Id
    private String resultId;

    private String testId;
    private String userId;
    private Integer result;
    private SkillLevel skillLevel;

    public Result() {
    }

    public Result(String testId, String userId, Integer result, SkillLevel skillLevel) {
        this.testId = testId;
        this.userId = userId;
        this.result = result;
        this.skillLevel = skillLevel;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
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

    public SkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }
}
