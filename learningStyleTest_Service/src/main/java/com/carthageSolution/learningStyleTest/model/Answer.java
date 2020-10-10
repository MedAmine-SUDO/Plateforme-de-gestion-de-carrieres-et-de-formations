package com.carthageSolution.learningStyleTest.model;

public class Answer {

    private String id_answer;
    private String answerContent;
    private Category answerCategory;
    private Boolean checked;

    public Answer() {
    }

    public Answer(String id_answer, String answerContent, Category answerCategory, Boolean checked) {
        this.id_answer = id_answer;
        this.answerContent = answerContent;
        this.answerCategory = answerCategory;
        this.checked = checked;
    }

    public String getId_answer() {
        return id_answer;
    }

    public void setId_answer(String id_answer) {
        this.id_answer = id_answer;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Category getAnswerCategory() {
        return answerCategory;
    }

    public void setAnswerCategory(Category answerCategory) {
        this.answerCategory = answerCategory;
    }

    public Boolean isChecked() {
        if(this.checked)
            return true;
        else
            return false;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id_answer='" + id_answer + '\'' +
                ", answerContent='" + answerContent + '\'' +
                ", answerCategory=" + answerCategory +
                ", checked=" + checked +
                '}';
    }
}
