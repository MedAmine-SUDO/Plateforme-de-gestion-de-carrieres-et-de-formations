package com.carthageSolution.testNiveau.service;

import com.carthageSolution.testNiveau.model.*;
import com.carthageSolution.testNiveau.repository.QuestionRepository;
import com.carthageSolution.testNiveau.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TestServiceImpl implements TestService{
    @Autowired
    private ResultService resultService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private TestRepository testRepository;

    @Override
    public List<Test> findAll() {
        return testRepository.findAll();
    }

    @Override
    public Test findByNumTest(Long num) {
        return testRepository.findByNumTest(num);
    }

    @Override
    public List<Test> findByUserId(String userId) {
        return testRepository.findByUserId(userId);
    }

    @Override
    public Optional<Test> findById(String id)
    {
        return testRepository.findById(id);
    }

    @Override
    public Test saveTest() {
        List<Question> basicQuestions = get5RandomQuestionsByDifficulty(Difficulty.BASIC);
        List<Question> intermediateQuestions = get5RandomQuestionsByDifficulty(Difficulty.INTERMEDIATE);
        List<Question> hardQuestions = get5RandomQuestionsByDifficulty(Difficulty.HARD);
        List<Question> proQuestions = get5RandomQuestionsByDifficulty(Difficulty.PROFESSIONAL);

        List<Question> newList = Stream.of(basicQuestions, intermediateQuestions, hardQuestions,proQuestions)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        Test test = new Test();
        test.setQuestionList(newList);
        test.setNumTest(sequenceGeneratorService.generateSequence(Test.SEQUENCE_NAME));
        test.setResult(0);
        testRepository.save(test);
        return test;
    }

    @Override
    public void save(Test test) {
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
    public void updateTest(Test test) {
        testRepository.save(test);
    }

    @Override
    public HashMap<String, Integer> generateResult(String id) {
        SkillLevel skillLevel = null;
        HashMap<String, Integer> map = new HashMap<>();
        map.put(Difficulty.BASIC.toString(),0);
        map.put(Difficulty.INTERMEDIATE.toString(),0);
        map.put(Difficulty.HARD.toString(),0);
        map.put(Difficulty.PROFESSIONAL.toString(),0);

        Optional<Test> test = testRepository.findById(id);
        if(test.isPresent()){
            Test newTest = test.get();
            List<Question> questionList = newTest.getQuestionList();
            for (Question question:questionList) {
                for(Answer answer:question.getAnswers()){
                    if(answer.isChecked() & answer.isCorrect()){
                        map.replace(question.getDifficulty().toString(), map.get(question.getDifficulty().toString())+1);
                    }
                }
            }

            for(Map.Entry<String, Integer> entry: map.entrySet()){
                switch (entry.getKey()){
                    case "INTERMEDIATE":
                        map.replace(entry.getKey(), entry.getValue()*2);
                        break;
                    case "HARD":
                        map.replace(entry.getKey(), entry.getValue()*3);
                        break;
                    case "PROFESSIONAL":
                        map.replace(entry.getKey(), entry.getValue()*4);
                        break;
                }
            }


            Integer sum = 0;
            for(Integer nbr:map.values()){
                sum += nbr;
            }

            if(sum>0 & sum<=10)
                skillLevel = SkillLevel.NOVICE;
            else if(sum>10 || sum<=20)
                skillLevel = SkillLevel.INTERMEDIATE;
            else if(sum>20 & sum<=50)
                skillLevel = SkillLevel.EXPERT;
            
            Result result = new Result(newTest.getId(),newTest.getUserId(),sum, skillLevel);
            resultService.save(result);
        }
        return map;
    }

    public List<Question> get5RandomQuestionsByDifficulty(Difficulty difficulty){
        List<Question> questions = questionRepository.findByDifficulty(difficulty);
        List<Question> newQuestionList = new ArrayList<>();

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<5; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);

        for(int i=0; i<5;i++){
            try{
                newQuestionList.add(questions.get(list.get(i)));
            } catch (Exception e) {
                break;
            }

        }
        return newQuestionList;
    }
}
