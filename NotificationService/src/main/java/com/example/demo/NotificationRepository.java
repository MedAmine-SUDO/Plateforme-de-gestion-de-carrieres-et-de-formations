package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String>{

}
