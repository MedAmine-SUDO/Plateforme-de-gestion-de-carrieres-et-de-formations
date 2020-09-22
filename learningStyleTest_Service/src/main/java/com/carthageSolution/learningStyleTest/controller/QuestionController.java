package com.carthageSolution.learningStyleTest.controller;

import com.carthageSolution.learningStyleTest.model.Question;
import com.carthageSolution.learningStyleTest.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testProfile/question")
@Api(value = "TestProfileQuestionAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "TP Question Resource")

public class QuestionController {

    @Autowired
    private QuestionService questionService;

    //Get all questions
    @ApiOperation("Get All Questions")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Questions"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("")
    public List<Question> getAllQuestions(){
        return questionService.findAll();
    }

    //Get specefic question by its number
    @ApiOperation("Get Question By QuestionNbr")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Question"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("/{nbr}")
    public Optional<Question> getQuestionByNbr(@PathVariable("nbr") Integer nbr){
        return questionService.findByQuestionNbr(nbr);
    }

    //Create question
    @ApiOperation("Create Question")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully create Question"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @PostMapping("/createQuestion")
    public ResponseEntity<?> createQuestion(@RequestBody Question question){
        questionService.createQuestion(question);
        return new ResponseEntity("Question added successfully", HttpStatus.OK);
    }

    //Delete a question by it's id
    @ApiOperation("Delete Question By ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully delete Question"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @DeleteMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable("id") String id){
        questionService.deleteQuestion(id);
    }

    //Delete all questions
    @ApiOperation("Delete All Questions")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully delete all Questions"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllQuestions(){
        questionService.deleteAll();
        return new ResponseEntity<>("All questions deleted successfully", HttpStatus.OK);
    }

    //Update a question
    @ApiOperation("Update Question")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully update Question"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
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
