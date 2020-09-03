package com.example.demo;

import java.io.InputStream;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Document(collection = "videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
	 private String title;
	    
		public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public InputStream getStream() {
		return stream;
	}
	public void setStream(InputStream stream) {
		this.stream = stream;
	}
		private InputStream stream;
}
