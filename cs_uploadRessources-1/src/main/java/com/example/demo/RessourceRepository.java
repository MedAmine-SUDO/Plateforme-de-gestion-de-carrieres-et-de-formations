package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RessourceRepository extends MongoRepository <Ressource,String>  {
	

}
