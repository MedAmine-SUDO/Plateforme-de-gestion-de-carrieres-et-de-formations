package com.carthageSolution.testNiveau.model;

public class Answer {

    private Integer answer_id;
    private String answerContent;
<<<<<<< HEAD
    private boolean checked;
    private boolean correct;
=======
    private boolean isChecked;
    private boolean isCorrect;
>>>>>>> 8f133aef5598cab05f12a756f6194ce2b6f7551b

    public Answer() {
    }

<<<<<<< HEAD
    public Answer(Integer answer_id, String answerContent, boolean checked, boolean correct) {
        this.answer_id = answer_id;
        this.answerContent = answerContent;
        this.checked = checked;
        this.correct = correct;
=======
    public Answer(Integer answer_id, String answerContent, boolean isChecked, boolean isCorrect) {
        this.answer_id = answer_id;
        this.answerContent = answerContent;
        this.isChecked = isChecked;
        this.isCorrect = isCorrect;
>>>>>>> 8f133aef5598cab05f12a756f6194ce2b6f7551b
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
<<<<<<< HEAD
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
=======
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
>>>>>>> 8f133aef5598cab05f12a756f6194ce2b6f7551b
    }

    @Override
    public String toString() {
        return "Answer{" +
<<<<<<< HEAD
                ", answerContent='" + answerContent + '\'' +
                ", isChecked=" + checked +
                ", isCorrect=" + correct +
=======
                "answer_id=" + answer_id +
                ", answerContent='" + answerContent + '\'' +
                ", isChecked=" + isChecked +
                ", isCorrect=" + isCorrect +
>>>>>>> 8f133aef5598cab05f12a756f6194ce2b6f7551b
                '}';
    }
}
