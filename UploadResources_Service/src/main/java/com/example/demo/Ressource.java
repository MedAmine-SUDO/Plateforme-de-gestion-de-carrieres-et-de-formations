package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Document(collection = "ressources")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ressource {
	
	@Id
	private String id;
	private String title;
	private String description;
	private List <String> idFile= new ArrayList<String>();
	private List <String> idPhoto= new ArrayList<String>();
	private List <String> idVideo= new ArrayList<String>();
	
public List<String> getIdPhoto() {
		return idPhoto;
	}
	public void setIdPhoto(List<String> idPhoto) {
		this.idPhoto = idPhoto;
	}
	public List<String> addIdPhoto(String idpho) {
		idPhoto.add(idpho);

		return idPhoto;	}
	
	//	public String getIdPhoto() {
//		return idPhoto;
//	}
//	public void setIdPhoto(String idPhoto) {
//		this.idPhoto = idPhoto;
//	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<String> getIdVideo() {
		return idVideo;
	}
	public void setIdVideo(List<String> idVideo) {
		this.idVideo = idVideo;
	}
	public List<String> addIdVideo(String idv) {
		idVideo.add(idv);

		return idVideo;	}
	
	public List<String> getIdFile() {
		return idFile;
	}
	public void setIdFile(List<String> idFile) {
		this.idFile = idFile;
	}
	public List<String> addIdFile(String file) {
		idFile.add(file);
		return idFile;
	}

	
	

		
}
