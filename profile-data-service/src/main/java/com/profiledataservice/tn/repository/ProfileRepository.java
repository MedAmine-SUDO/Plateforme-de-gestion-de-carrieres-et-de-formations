package com.profiledataservice.tn.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.profiledataservice.tn.models.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String> {

	Optional<Profile> findByUserID(String id);

}
