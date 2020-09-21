package com.carthageSolution.testNiveau.model;

public class Answer {

    private Integer answer_id;
    private String answerContent;
    private boolean checked;
    private boolean correct;

    public Answer() {
    }

    public Answer(Integer answer_id, String answerContent, boolean checked, boolean correct) {
        this.answer_id = answer_id;
        this.answerContent = answerContent;
        this.checked = checked;
        this.correct = correct;
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
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                ", answerContent='" + answerContent + '\'' +
                ", isChecked=" + checked +
                ", isCorrect=" + correct +
                '}';
    }
}
