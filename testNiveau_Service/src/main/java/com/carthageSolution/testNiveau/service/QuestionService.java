package com.carthageSolution.testNiveau.service;

import com.carthageSolution.testNiveau.model.Difficulty;
import com.carthageSolution.testNiveau.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<Question> findAll();
    Optional<Question> findById(String id);
    Optional<Question> findByQuestionNbr(Integer nbr);
    List<Question> findByDifficulty(Difficulty difficulty);
    void createQuestion(Question question);
    void deleteQuestion(String id);
    void deleteAll();
    void updateQuestion(Question question);
}
