package com.authentification.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

import com.authentification.springboot.models.ERole;
import com.authentification.springboot.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(ERole name);
}
