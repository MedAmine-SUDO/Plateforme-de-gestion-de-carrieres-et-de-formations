package com.carthageSolution.testNiveau.controller;

import com.carthageSolution.testNiveau.model.Test;
import com.carthageSolution.testNiveau.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testNiveau")
public class TestController {
    private final Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private TestService testService;

    @GetMapping("")
    public List<Test> getAllTests(){
        return testService.findAll();
    }

    @GetMapping("/create")
    public ResponseEntity<Test> createTest() {
        Test test = testService.saveTest();
        return ResponseEntity.ok().body(test);
    }

    @GetMapping("/{id}")
    public Optional<Test> getTestById(@PathVariable("id") String id){
        return testService.findById(id);
    }

    @GetMapping("/nbr/{nbr}")
    public Test getTestByNbr(@PathVariable("nbr") Long nbr){
        return testService.findByNumTest(nbr);
    }

    @GetMapping("/user/{id}")
    public List<Test> getTestByUserId(@PathVariable("id") String id){
        return testService.findByUserId(id);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllTests(){
        testService.deleteAll();
        return new ResponseEntity<>("All tests deleted successfully!", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTestById(@PathVariable("id") String id){
        testService.deleteTest(id);
        return new ResponseEntity<>("Test deleted successfully!", HttpStatus.OK);
    }

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

    @GetMapping("/getResult/{id}")
    public HashMap<String, Integer> getResult(@PathVariable("id") String id){
        return testService.generateResult(id);
    }
}
