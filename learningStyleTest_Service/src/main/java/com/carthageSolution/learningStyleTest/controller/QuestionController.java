package com.carthageSolution.learningStyleTest.controller;

import com.carthageSolution.learningStyleTest.model.Question;
import com.carthageSolution.learningStyleTest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("")
    public List<Question> getAllQuestions(){
        return questionService.findAll();
    }

    @GetMapping("/{nbr}")
    public Optional<Question> getQuestionByNbr(@PathVariable("nbr") Integer nbr){
        return questionService.findByQuestionNbr(nbr);
    }

    @PostMapping("/createQuestion")
    public ResponseEntity<?> createQuestion(@RequestBody Question question){
        questionService.createQuestion(question);
        return new ResponseEntity("Question added successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable("id") String id){
        questionService.deleteQuestion(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllQuestions(){
        questionService.deleteAll();
    }

    @PutMapping("/{id_question}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("id_question") String id_question, @RequestBody Question question){
        Optional<Question> newQuestion = questionService.findById(id_question);
        if (newQuestion.isPresent()) {
            Question _question = newQuestion.get();
            _question.setQuestionNbr(question.getQuestionNbr());
            _question.setQuestionContent(question.getQuestionContent());
            _question.setAnswers(question.getAnswers());
            questionService.createQuestion(_question);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
