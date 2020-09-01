package com.carthageSolution.learningStyleTest.controller;

import com.carthageSolution.learningStyleTest.model.Question;
import com.carthageSolution.learningStyleTest.model.Test;
import com.carthageSolution.learningStyleTest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("")
    public List<Test> getAllTests(){
        return testService.findAll();
    }

    @GetMapping("/create/{nbr}")
    public ResponseEntity<?> saveTest(@PathVariable("nbr") Integer nbr){
        testService.saveTest(nbr);
        return new ResponseEntity("Test added successfully", HttpStatus.OK);
    }

    @GetMapping("/{nbr}")
    public Test getTestByNbr(@PathVariable("nbr") Integer nbr){
        return testService.findByNumTest(nbr);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllTests(){
        testService.deleteAll();
        return new ResponseEntity("All tests deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Test> updateTest(@PathVariable("id") String id, @RequestBody Test test){
        Optional<Test> newTest = testService.findById(id);

        if (newTest.isPresent()) {
            Test _test = newTest.get();
            _test.setNumTest(test.getNumTest());
            _test.setQuestionList(test.getQuestionList());
            testService.save(_test);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
