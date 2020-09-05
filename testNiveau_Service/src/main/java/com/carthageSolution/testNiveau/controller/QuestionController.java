package com.carthageSolution.testNiveau.controller;

import com.carthageSolution.testNiveau.model.Difficulty;
import com.carthageSolution.testNiveau.model.Question;
import com.carthageSolution.testNiveau.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testNiveau/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("")
    public List<Question> getAllQuestions(){
        return questionService.findAll();
    }

    @GetMapping("/{nbr}")
    public Optional<Question> getQuestionByNumber(@PathVariable("nbr") Integer nbr){
        return questionService.findByQuestionNbr(nbr);
    }

    @GetMapping("/byDifficulty/{difficulty}")
    public List<Question> getQuestionByDifficulty(@PathVariable("difficulty") Difficulty difficulty){
        return questionService.findByDifficulty(difficulty);
    }

    @PostMapping("/createQuestion")
    public ResponseEntity<?> createQuestion(@RequestBody Question question){
        questionService.createQuestion(question);
        return new ResponseEntity("Question added successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable("id") String id, @RequestBody Question question){
        Optional<Question> newQuestion = questionService.findById(id);
        if (newQuestion.isPresent()) {
            Question _question = newQuestion.get();
            _question.setQuestionNbr(question.getQuestionNbr());
            _question.setQuestionContent(question.getQuestionContent());
            _question.setAnswers(question.getAnswers());
            _question.setDifficulty(question.getDifficulty());
            questionService.createQuestion(_question);
            return new ResponseEntity<>("Question updated successfully",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Can't update question!", HttpStatus.NOT_FOUND);
        }
    }
}
