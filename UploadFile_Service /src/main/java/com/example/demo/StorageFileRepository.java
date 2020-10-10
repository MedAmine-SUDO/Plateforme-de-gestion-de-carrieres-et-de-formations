package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StorageFileRepository extends MongoRepository <StorageFile,String>  {
	

}

