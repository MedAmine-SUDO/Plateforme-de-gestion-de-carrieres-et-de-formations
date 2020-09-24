package com.example.demo;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class StorageService {
@Autowired
StorageFileRepository storagefilerepo;
public String addFile(String title, MultipartFile file) throws IOException { 
    StorageFile photo = new StorageFile(title); 
    photo.setFile(
      new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
    photo = storagefilerepo.insert(photo); return photo.getId(); 
}

public StorageFile getFile(String id) { 
    return storagefilerepo.findById(id).get(); 
}
public List<StorageFile> getAll() {
	return storagefilerepo.findAll();
}
}