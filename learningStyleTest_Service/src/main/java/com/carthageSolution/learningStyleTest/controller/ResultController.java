package com.carthageSolution.learningStyleTest.controller;

import com.carthageSolution.learningStyleTest.model.Question;
import com.carthageSolution.learningStyleTest.model.Test;
import com.carthageSolution.learningStyleTest.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/testProfile/result")
@Api(value = "TestProfileResultAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "TP Result Resource")

public class ResultController {

    @Autowired
    private TestService testService;

    //Get your learning style by specifying test id
    @ApiOperation("Get Your LearningStyle by Test ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get LearningStyle"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("/style/{id}")
    public String getLearningStyle(@PathVariable("id") String id){
        StringBuilder style= new StringBuilder("Your learning style is ");
        HashMap<String, Integer> map = getResult(id);
        for(Map.Entry<String, Integer> set: map.entrySet()){
            if(set.getValue() != 0){
                switch(set.getKey()){
                    case "VISUAL":
                    case "AURAL":
                    case "READWRITE":
                    case "KINESTHETIC":
                        style.append(set.getKey()); break;
                }
            }
        }
        return style.toString();
    }

    //Get test result as a HashMap
    @ApiOperation("Get Test Result")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get Result"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
            @ApiResponse(code = 404, message = "The resource  not found")})
    @GetMapping("/{id}")
    public HashMap<String, Integer> getResult(@PathVariable("id") String id) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("VISUAL", 0);
        map.put("AURAL", 0);
        map.put("READWRITE", 0);
        map.put("KINESTHETIC", 0);
        Optional<Test> test = testService.findById(id);
        Test newTest = test.get();
        List<Question> questionList = newTest.getQuestionList();
        for (Question question : questionList) {
            for (int j = 0; j < question.getAnswers().size(); j++) {
                if (question.getAnswers().get(j).isChecked()) {
                    String categ = question.getAnswers().get(j).getAnswerCategory().toString();
                    if (map.containsKey(categ)) {
                        Integer val = map.get(categ);
                        map.replace(categ, val + 1);
                    }
                }
            }
        }
        System.out.println(map);
        return map;
    }
}
