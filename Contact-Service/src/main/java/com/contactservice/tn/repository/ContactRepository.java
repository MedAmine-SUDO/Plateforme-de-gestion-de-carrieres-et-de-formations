package com.contactservice.tn.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.contactservice.tn.models.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {

}
