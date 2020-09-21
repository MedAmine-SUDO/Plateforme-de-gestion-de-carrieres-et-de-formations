package com.carthageSolution.testNiveau.controller;

import com.carthageSolution.testNiveau.model.Result;
import com.carthageSolution.testNiveau.model.SkillLevel;
import com.carthageSolution.testNiveau.service.ResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testNiveau/result")
@Api(value = "TestNiveauResultAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "TN Result Resource")
public class ResultController {
    @Autowired
    private ResultService resultService;

    @ApiOperation("Get All Results")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Results"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("")
    public List<Result> getAllResults(){
        return resultService.findAll();
    }

    @ApiOperation("Get Result By UserID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Result"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("/user/{id}")
    public List<Result> getAllResultsByUserId(@PathVariable("id") String id){
        return resultService.findByUserId(id);
    }

    @ApiOperation("Get All Results By SkillLevel")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Results"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("/{skillLevel}")
    public List<Result> getAllResultsBySkillLevel(@PathVariable("skillLevel") SkillLevel skillLevel){
        return resultService.findBySkillLevel(skillLevel);
    }

    @ApiOperation("Delete All Results")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully delete Results"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @DeleteMapping("")
    public void deleteAllResults(){
        resultService.deleteAll();
    }

    @ApiOperation("Delete Result By UserID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully delete Result"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @DeleteMapping("/user/{id}")
    public void deleteResultsUserId(@PathVariable("id") String id){
        resultService.deleteByUserId(id);
    }
}
