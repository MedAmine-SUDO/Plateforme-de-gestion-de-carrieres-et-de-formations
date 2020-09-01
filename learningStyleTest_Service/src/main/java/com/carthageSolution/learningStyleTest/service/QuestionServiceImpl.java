package com.carthageSolution.learningStyleTest.service;

import com.carthageSolution.learningStyleTest.model.Question;
import com.carthageSolution.learningStyleTest.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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

    @Override
    public List<Question> findTopByQuestionNbrExists(Pageable pageable) {
        return questionRepository.findTopByQuestionNbrExists(pageable);
    }
}
