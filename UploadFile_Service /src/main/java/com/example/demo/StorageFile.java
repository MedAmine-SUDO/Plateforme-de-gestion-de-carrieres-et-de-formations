package com.example.demo;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Document(collection = "files")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorageFile {
	@Id
private String id;
private Binary file ;
private String title;

public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public StorageFile(String title) {
	super();
	this.title = title;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public Binary getFile() {
	return file;
}
public void setFile(Binary image) {
	this.file = image;
}


}
