package com.carthageSolution.learningStyleTest.repository;

import com.carthageSolution.learningStyleTest.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends MongoRepository<Question, String> {
    Optional<Question> findByQuestionNbr(Integer nbr);
    List<Question> findAll();
}
