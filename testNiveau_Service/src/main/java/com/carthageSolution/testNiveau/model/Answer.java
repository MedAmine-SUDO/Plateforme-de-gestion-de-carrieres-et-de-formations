package com.carthageSolution.testNiveau.model;

public class Answer {

    private Integer answer_id;
    private String answerContent;
    private boolean isChecked;
    private boolean isCorrect;

    public Answer() {
    }

    public Answer(Integer answer_id, String answerContent, boolean isChecked, boolean isCorrect) {
        this.answer_id = answer_id;
        this.answerContent = answerContent;
        this.isChecked = isChecked;
        this.isCorrect = isCorrect;
    }

    public Integer getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Integer answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer_id=" + answer_id +
                ", answerContent='" + answerContent + '\'' +
                ", isChecked=" + isChecked +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
