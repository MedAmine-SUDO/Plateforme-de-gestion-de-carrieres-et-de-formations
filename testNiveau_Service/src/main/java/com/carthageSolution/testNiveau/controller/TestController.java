package com.carthageSolution.testNiveau.controller;

import com.carthageSolution.testNiveau.model.Test;
import com.carthageSolution.testNiveau.service.TestService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testNiveau")
@Api(value = "TestNiveauTestAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "TN Test Resource")
public class TestController {
    private final Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private TestService testService;

    @ApiOperation("Get All Tests")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Tests"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("")
    public List<Test> getAllTests(){
        return testService.findAll();
    }

    @ApiOperation("Create Test")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully create Test"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("/create")
    public ResponseEntity<Test> createTest() {
        Test test = testService.saveTest();
        return ResponseEntity.ok().body(test);
    }

    @ApiOperation("Get Test By ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Test"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("/{id}")
    public Optional<Test> getTestById(@PathVariable("id") String id){
        return testService.findById(id);
    }

    @ApiOperation("Get Test By Nbr")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Test"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("/nbr/{nbr}")
    public Test getTestByNbr(@PathVariable("nbr") Long nbr){
        return testService.findByNumTest(nbr);
    }

    @ApiOperation("Get Test By UserID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Test"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("/user/{id}")
    public List<Test> getTestByUserId(@PathVariable("id") String id){
        return testService.findByUserId(id);
    }

    @ApiOperation("Delete All Tests")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully delete Tests"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllTests(){
        testService.deleteAll();
        return new ResponseEntity<>("All tests deleted successfully!", HttpStatus.OK);
    }

    @ApiOperation("Delete Test By ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully delete Test"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTestById(@PathVariable("id") String id){
        testService.deleteTest(id);
        return new ResponseEntity<>("Test deleted successfully!", HttpStatus.OK);
    }

    @ApiOperation("Update Test")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully update Test"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @PutMapping("/{id}")
    public ResponseEntity<Test> updateTest(@PathVariable("id") String id, @RequestBody Test test){
        log.info("Request to update test");

        Optional<Test> newTest = testService.findById(id);

        if (newTest.isPresent()) {
            Test _test = newTest.get();
            _test.setNumTest(test.getNumTest());
            _test.setQuestionList(test.getQuestionList());
            _test.setUserId(test.getUserId());
            _test.setResult(test.getResult());
            testService.save(_test);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Get Result of Specefic Test")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Result"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("/getResult/{id}")
    public HashMap<String, Integer> getResult(@PathVariable("id") String id){
        return testService.generateResult(id);
    }
}
