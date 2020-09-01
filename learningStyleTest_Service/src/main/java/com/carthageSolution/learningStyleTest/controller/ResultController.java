package com.carthageSolution.learningStyleTest.controller;

import com.carthageSolution.learningStyleTest.model.Answer;
import com.carthageSolution.learningStyleTest.model.Category;
import com.carthageSolution.learningStyleTest.model.Question;
import com.carthageSolution.learningStyleTest.model.Test;
import com.carthageSolution.learningStyleTest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/result")
public class ResultController {

    @Autowired
    private TestService testService;

    @GetMapping("/style/{id}")
    public String getlearningStyle(@PathVariable("id") String id){
        String style="Your learning style is " ;
        HashMap<String, Integer> map = getResult(id);
        for(Map.Entry<String, Integer> set: map.entrySet()){
            if(set.getValue() != 0){
                switch(set.getKey()){
                    case "VISUAL": style += set.getKey(); break;
                    case "AURAL": style += set.getKey(); break;
                    case "READWRITE": style += set.getKey(); break;
                    case "KINESTHETIC": style += set.getKey(); break;
                }
            }
        }

        return style;
    }

    @GetMapping("/{id}")
    public HashMap<String, Integer> getResult(@PathVariable("id") String id) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("VISUAL", 0);
        map.put("AURAL", 0);
        map.put("READWRITE", 0);
        map.put("KINESTHETIC", 0);
        Optional<Test> test = testService.findById(id);
        Test newTest = test.get();
        List<Question> questionList = newTest.getQuestionList();
        for (int i = 0; i < questionList.size(); i++) {
            Question question = questionList.get(i);
            for(int j=0;j<question.getAnswers().size();j++){
                if(question.getAnswers().get(j).isChecked()){
                    String categ = question.getAnswers().get(j).getAnswerCategory().getType();
                    if(map.containsKey(categ)){
                        Integer val = (Integer) map.get(categ);
                        map.replace(categ, val+1);
                    }
                }
            }
        }
        System.out.println(map);
        return map;
    }
}
