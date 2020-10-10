package com.carthageSolution.testNiveau.repository;

import com.carthageSolution.testNiveau.model.Difficulty;
import com.carthageSolution.testNiveau.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends MongoRepository<Question, String> {
    Optional<Question> findByQuestionNbr(Integer nbr);
    List<Question> findByDifficulty(Difficulty difficulty);
    List<Question> findAll();
}
