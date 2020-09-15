package com.carthageSolution.testNiveau.controller;

import com.carthageSolution.testNiveau.model.Difficulty;
import com.carthageSolution.testNiveau.model.Question;
import com.carthageSolution.testNiveau.service.QuestionService;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
=======
>>>>>>> 8f133aef5598cab05f12a756f6194ce2b6f7551b
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testNiveau/question")
public class QuestionController {
<<<<<<< HEAD
    private final Logger log = LoggerFactory.getLogger(QuestionController.class);
=======
>>>>>>> 8f133aef5598cab05f12a756f6194ce2b6f7551b

    @Autowired
    private QuestionService questionService;

    @GetMapping("")
    public List<Question> getAllQuestions(){
        return questionService.findAll();
    }

<<<<<<< HEAD
    @GetMapping("/byNumber/{nbr}")
=======
    @GetMapping("/{nbr}")
>>>>>>> 8f133aef5598cab05f12a756f6194ce2b6f7551b
    public Optional<Question> getQuestionByNumber(@PathVariable("nbr") Integer nbr){
        return questionService.findByQuestionNbr(nbr);
    }

<<<<<<< HEAD
    @GetMapping("/{id}")
    public Optional<Question> getQuestionById(@PathVariable("id") String id){
        log.info("Request to get question");
        return questionService.findById(id);
    }

=======
>>>>>>> 8f133aef5598cab05f12a756f6194ce2b6f7551b
    @GetMapping("/byDifficulty/{difficulty}")
    public List<Question> getQuestionByDifficulty(@PathVariable("difficulty") Difficulty difficulty){
        return questionService.findByDifficulty(difficulty);
    }

<<<<<<< HEAD
    @PostMapping("")
=======
    @PostMapping("/createQuestion")
>>>>>>> 8f133aef5598cab05f12a756f6194ce2b6f7551b
    public ResponseEntity<?> createQuestion(@RequestBody Question question){
        questionService.createQuestion(question);
        return new ResponseEntity("Question added successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable("id") String id, @RequestBody Question question){
<<<<<<< HEAD
        log.info("Request to update group: {}", question);
        Optional<Question> newQuestion = questionService.findById(id);
        if (newQuestion.isPresent()) {
            Question _question = newQuestion.get();
            _question.setId(question.getId());
=======
        Optional<Question> newQuestion = questionService.findById(id);
        if (newQuestion.isPresent()) {
            Question _question = newQuestion.get();
>>>>>>> 8f133aef5598cab05f12a756f6194ce2b6f7551b
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
<<<<<<< HEAD

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") String id){
        questionService.deleteQuestion(id);
        return new ResponseEntity<>("Question deleted successfully!", HttpStatus.OK);
    }
=======
>>>>>>> 8f133aef5598cab05f12a756f6194ce2b6f7551b
}
