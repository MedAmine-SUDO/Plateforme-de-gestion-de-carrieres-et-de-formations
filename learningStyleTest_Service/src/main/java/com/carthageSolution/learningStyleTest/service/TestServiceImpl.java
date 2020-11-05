package com.carthageSolution.learningStyleTest.service;

import com.carthageSolution.learningStyleTest.model.Question;
import com.carthageSolution.learningStyleTest.model.Test;
import com.carthageSolution.learningStyleTest.repository.QuestionRepository;
import com.carthageSolution.learningStyleTest.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.*;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Test> findAll() {
        return testRepository.findAll();
    }

    @Override
    public Test findByNumTest(Integer num) {
        return testRepository.findByNumTest(num);
    }

    @Override
    public void saveTest(Integer nbrQuestions) {
        List<Question> questionList = questionRepository.findAll();
        List<Question> newQuestionList = new ArrayList<>();

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<nbrQuestions; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);

        for(int i=0; i<nbrQuestions;i++){
            newQuestionList.add(questionList.get(list.get(i)));
        }
        Test test = new Test();
        test.setQuestionList(newQuestionList);
        test.setNumTest(sequenceGeneratorService.generateSequence(Test.SEQUENCE_NAME));
        testRepository.save(test);
    }

    @Override
    public void deleteTest(String id) {
        testRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        testRepository.deleteAll();
    }

    @Override
    public void save(Test test) {
        testRepository.save(test);
    }

    @Override
    public Optional<Test> findById(String id) {
        return testRepository.findById(id);
    }

    @Override
    public void updateTest(Test test) {
        testRepository.save(test);
    }
}
