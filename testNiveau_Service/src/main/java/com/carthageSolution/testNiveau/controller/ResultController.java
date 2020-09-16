package com.carthageSolution.testNiveau.controller;

import com.carthageSolution.testNiveau.model.Result;
import com.carthageSolution.testNiveau.model.SkillLevel;
import com.carthageSolution.testNiveau.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testNiveau/result")
public class ResultController {
    @Autowired
    private ResultService resultService;

    @GetMapping("")
    public List<Result> getAllResults(){
        return resultService.findAll();
    }

    @GetMapping("/user/{id}")
    public List<Result> getAllResultsByUserId(@PathVariable("id") String id){
        return resultService.findByUserId(id);
    }

    @GetMapping("/{skillLevel}")
    public List<Result> getAllResultsBySkillLevel(@PathVariable("skillLevel") SkillLevel skillLevel){
        return resultService.findBySkillLevel(skillLevel);
    }

    @DeleteMapping("")
    public void deleteAllResults(){
        resultService.deleteAll();
    }

    @DeleteMapping("/user/{id}")
    public void deleteResultsUserId(@PathVariable("id") String id){
        resultService.deleteByUserId(id);
    }
}
