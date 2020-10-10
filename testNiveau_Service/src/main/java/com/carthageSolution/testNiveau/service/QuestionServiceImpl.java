package com.carthageSolution.testNiveau.service;

import com.carthageSolution.testNiveau.model.Difficulty;
import com.carthageSolution.testNiveau.model.Question;
import com.carthageSolution.testNiveau.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> findById(String id) {
        return questionRepository.findById(id);
    }

    @Override
    public Optional<Question> findByQuestionNbr(Integer nbr) {
        return questionRepository.findByQuestionNbr(nbr);
    }

    @Override
    public List<Question> findByDifficulty(Difficulty difficulty) {
        return questionRepository.findByDifficulty(difficulty);
    }

    @Override
    public void createQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(String id) {
        questionRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        questionRepository.deleteAll();
    }

    @Override
    public void updateQuestion(Question question) {
        questionRepository.save(question);
    }
}
