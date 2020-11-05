package com.carthageSolution.learningStyleTest.controller;

import com.carthageSolution.learningStyleTest.model.Test;
import com.carthageSolution.learningStyleTest.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testProfile")
@Api(value = "TestProfileTestAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "TP Test Resource")
public class TestController {

    @Autowired
    private TestService testService;

    //Get all tests
    @ApiOperation("Get All Tests")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Tests"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("")
    public List<Test> getAllTests(){
        return testService.findAll();
    }

    //Automatically create a test by specifying number of questions in that test
    @ApiOperation("Create Test By Specifying nbr of questions")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully Create Test"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("/create/{nbr}")
    public ResponseEntity<?> saveTest(@PathVariable("nbr") Integer nbrQuestions){
        testService.saveTest(nbrQuestions);
        return new ResponseEntity("Test created successfully", HttpStatus.OK);
    }

    //Get a test by id
    @ApiOperation("Get Test By it's Id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Test"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("/{id}")
    public Optional<Test> getTestById(@PathVariable("id") String id){
        return testService.findById(id);
    }

    //Delete all tests
    @ApiOperation("Delete All Tests")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully delete Tests"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllTests(){
        testService.deleteAll();
        return new ResponseEntity("All tests deleted successfully", HttpStatus.OK);
    }

    //Update a test
    @ApiOperation("Update Test")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully update test"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @PutMapping("/{id}")
    public ResponseEntity<Test> updateTest(@PathVariable("id") String id, @RequestBody Test test){
        Optional<Test> newTest = testService.findById(id);

        if (newTest.isPresent()) {
            Test _test = newTest.get();
            _test.setNumTest(test.getNumTest());
            _test.setQuestionList(test.getQuestionList());
            _test.setUser_id(test.getUser_id());
            _test.setResult(test.getResult());
            testService.save(_test);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
