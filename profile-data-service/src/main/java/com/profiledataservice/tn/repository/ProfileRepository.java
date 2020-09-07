package com.profiledataservice.tn.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.profiledataservice.tn.models.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String> {

}
