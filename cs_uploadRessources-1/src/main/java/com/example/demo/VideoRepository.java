package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository <Video, String> {

}
