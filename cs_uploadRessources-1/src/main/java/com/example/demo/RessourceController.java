package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class RessourceController {
@Autowired
private RessourceRepository ressourceRepository;
@Autowired
private PhotoService photoService;
private VideoService videoService ;
private   GridFsTemplate gridFsTemplate;


@GetMapping("/")
public List <Ressource> GetRessources(){
	return ressourceRepository.findAll();
	

}
@GetMapping("/{id}")
public Ressource GetRessources(@PathVariable String id){
	return ressourceRepository.findById(id).orElse(null);
	
}
@PostMapping("/add")
private Ressource addRessource( @RequestParam("image") MultipartFile image,  
		  @RequestParam("video") InputStream video, 
		 @RequestParam("title") String title,
		 @RequestParam("description") String description,
		 @RequestParam("file") String file) throws IOException {
	Ressource ressource=new Ressource();
	ressource.setDescription(description);
	ressource.setFile(file);
	ressource.setTitle(title);
	String idphoto = photoService.addPhoto("Photo",image);
	ressource.setIdPhoto(idphoto);
	
	String idvideo = videoService.addVideo("Video",video);
	ressource.setIdVideo(idvideo);
    
	return ressourceRepository.save(ressource);

}
@PutMapping("/update")
private Ressource PutRessource(@RequestBody Ressource newRessource) {
	Ressource oldRessource = ressourceRepository.findById(newRessource.getId()).orElse(null);
	oldRessource.setTitle(newRessource.getTitle());
	oldRessource.setDescription(newRessource.getDescription());
	oldRessource.setFile(newRessource.getFile());
	return oldRessource;
}
@DeleteMapping
public String DeleteRessource(@PathVariable String id) {
	ressourceRepository.deleteById(id);
	return id;
}
}