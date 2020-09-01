package com.carthageSolution.learningStyleTest.service;

import com.carthageSolution.learningStyleTest.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    public List<Question> findAll();
    public Optional<Question> findById(String id);
    public Optional<Question> findByQuestionNbr(Integer nbr);
    public void createQuestion(Question question);
    public void deleteQuestion(String id);
    public void deleteAll();
    public void updateQuestion(Question question);
}
