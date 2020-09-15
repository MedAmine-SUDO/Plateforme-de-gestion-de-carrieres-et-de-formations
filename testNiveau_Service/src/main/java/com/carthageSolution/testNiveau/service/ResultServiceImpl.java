package com.carthageSolution.testNiveau.service;

import com.carthageSolution.testNiveau.model.Result;
import com.carthageSolution.testNiveau.model.SkillLevel;
import com.carthageSolution.testNiveau.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService{
    @Autowired
    private ResultRepository resultRepository;

    @Override
    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    @Override
    public List<Result> findByUserId(String userId) {
        return resultRepository.findByUserId(userId);
    }

    @Override
    public List<Result> findBySkillLevel(SkillLevel skillLevel) {
        return resultRepository.findBySkillLevel(skillLevel);
    }

    @Override
    public Optional<Result> findById(String id) {
        return resultRepository.findById(id);
    }

    @Override
    public void save(Result result) {
        resultRepository.save(result);
    }

    @Override
    public void deleteAll() {
        resultRepository.deleteAll();
    }

    @Override
    public void deleteByUserId(String userId) {
        resultRepository.deleteByUserId(userId);
    }
}
