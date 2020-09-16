package com.example.demo;

import java.io.IOException;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service

public class PhotoService {
	 @Autowired
	 
	    private PhotoRepository photoRepo;
	 
	    public String addPhoto(String title, MultipartFile file) throws IOException { 
	        Photo photo = new Photo(title); 
	        photo.setImage(
	          new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
	        photo = photoRepo.insert(photo); return photo.getId(); 
	    }
	 
	    public Photo getPhoto(String id) { 
	        return photoRepo.findById(id).get(); 
	    }
	    public List<Photo> getAll() {
			return photoRepo.findAll();
		}
}
