package com.carthageSolution.learningStyleTest.service;

import com.carthageSolution.learningStyleTest.model.Question;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<Question> findAll();
    Optional<Question> findById(String id);
    Optional<Question> findByQuestionNbr(Integer nbr);
    void createQuestion(Question question);
    void deleteQuestion(String id);
    void deleteAll();
    void updateQuestion(Question question);
}
