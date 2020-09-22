package com.carthageSolution.testNiveau.controller;

import com.carthageSolution.testNiveau.model.Difficulty;
import com.carthageSolution.testNiveau.model.Question;
import com.carthageSolution.testNiveau.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/testNiveau/question")
@Api(value = "TestNiveauQuestionAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "TN Question Resource")
public class QuestionController {
    private final Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    @ApiOperation("Get All Questions")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Questions"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("")
    public List<Question> getAllQuestions() {
        return questionService.findAll();
    }


    @ApiOperation("Get Question By Question Number")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Question"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("/byNumber/{nbr}")
    public Optional<Question> getQuestionByNumber(@PathVariable("nbr") Integer nbr) {
        return questionService.findByQuestionNbr(nbr);
    }

    @ApiOperation("Get Question By ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Question"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("/{id}")
    public Optional<Question> getQuestionById(@PathVariable("id") String id) {
        log.info("Request to get question");
        return questionService.findById(id);
    }

    @ApiOperation("Get Question By Difficulty")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Formation"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("/byDifficulty/{difficulty}")
    public List<Question> getQuestionByDifficulty(@PathVariable("difficulty") Difficulty difficulty) {
        return questionService.findByDifficulty(difficulty);
    }

    @ApiOperation("Add Question")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully add Question"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @PostMapping("")
    public ResponseEntity<?> createQuestion(@RequestBody Question question) {
        questionService.createQuestion(question);
        return new ResponseEntity("Question added successfully", HttpStatus.OK);
    }

    @ApiOperation("Update Question")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully update question"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable("id") String id, @RequestBody Question question) {
        log.info("Request to update group: {}", question);
        Optional<Question> newQuestion = questionService.findById(id);
        if (newQuestion.isPresent()) {
            Question _question = newQuestion.get();
            _question.setId(question.getId());

            _question.setQuestionNbr(question.getQuestionNbr());
            _question.setQuestionContent(question.getQuestionContent());
            _question.setAnswers(question.getAnswers());
            _question.setDifficulty(question.getDifficulty());
            questionService.createQuestion(_question);
            return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Can't update question!", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Delete Question")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully delete question"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") String id) {
        questionService.deleteQuestion(id);
        return new ResponseEntity<>("Question deleted successfully!", HttpStatus.OK);
    }

}
