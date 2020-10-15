package com.example.demo;

import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;

//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Collection;
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.gridfs.GridFsTemplate;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/ressource")
@Api(value = "RessourceUploadAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "Upload ressource ")

@RestController
public class RessourceController {
@Autowired
private RessourceRepository ressourceRepository;
@Autowired
private PhotoService photoService;
@Autowired
private VideoService videoService ;
//@Autowired
	//private GridFsTemplate gridFsTemplate;
//	@Autowired
//	private VideoRepository videoRepoSitory;
//@Autowired
//private PhotoRepository photorepository;
//@Autowired
//private StorageFileRepository storageFileRepository;
@Autowired
private StorageService storageFileService;

@CrossOrigin
@GetMapping("/all")
public List <Ressource> GetRessources(){
	return ressourceRepository.findAll();
	

}
@CrossOrigin

@ApiOperation("Get a Ressouce")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Get a Ressouce"),
		@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
		@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
		@ApiResponse(code = 404, message = "The resource  not found") })
@GetMapping("/{id}")
public Ressource GetRessources(@PathVariable String id){
	return ressourceRepository.findById(id).orElse(null);
	
}
@CrossOrigin
@ApiOperation("Add a Ressouce")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Add a Ressouce"),
		@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
		@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
		@ApiResponse(code = 404, message = "The resource  not found") })
@PostMapping("/add")
private Ressource addRessource( @RequestParam("image") MultipartFile image,  
		  @RequestParam("video") MultipartFile video, 
		 @RequestParam("title") String title,
		 @RequestParam("description") String description,
		 @RequestParam("file") MultipartFile file,Model model) throws IOException {
	Ressource ressource=new Ressource();
	ressource.setDescription(description);
	
	ressource.setTitle(title);
	String idphoto = photoService.addPhoto("Photo",image);
	ressource.addIdPhoto(idphoto);
	
	String idvideo = videoService.addVideo("Video", video);
	ressource.addIdVideo(idvideo);
	String idfile = storageFileService.addFile("file",file);
	ressource.addIdFile(idfile);
    
	return ressourceRepository.save(ressource);

}
@CrossOrigin
@ApiOperation("update a Ressouce")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully update a Ressouce"),
		@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
		@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
		@ApiResponse(code = 404, message = "The resource  not found") })
@PutMapping("/update")
private Ressource PutRessource(@RequestBody Ressource newRessource) {
	Ressource oldRessource = ressourceRepository.findById(newRessource.getId()).orElse(null);
	oldRessource.setTitle(newRessource.getTitle());
	oldRessource.setDescription(newRessource.getDescription());
	oldRessource.setIdFile(newRessource.getIdFile());
	oldRessource.setIdPhoto(newRessource.getIdPhoto());
	oldRessource.setIdVideo(newRessource.getIdVideo());
	return oldRessource;
}
@CrossOrigin
@ApiOperation("Delete a Ressouce")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Delete a Ressouce"),
		@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
		@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
		@ApiResponse(code = 404, message = "The resource  not found") })

@DeleteMapping("/{id}")
public String DeleteRessource(@PathVariable String id) {
	ressourceRepository.deleteById(id);
	return id;
}
//@DeleteMapping("/{idV}")
//public String DeleteVideo(@PathVariable String idV ) {
//	videoRepoSitory.deleteById(idV);;
//	return idV;
//}
//@DeleteMapping("/{idp}")
//public String DeletePhoto(@PathVariable String idp ) {
//	photorepository.deleteById(idp);;
//	return idp;
//}
//@GetMapping("/{id}/allVideos")
//public List<Video> GetVideosByRessource(@PathVariable String id) throws IllegalStateException, IOException{
//	Ressource res=ressourceRepository.findById(id).orElse(null);
//	List<String> rest=res.getIdVideo();
//	List <Video> lVideos = new ArrayList<Video>();
//	int i=0;
//	while (!rest.isEmpty()) {
//		String r= rest.get(i);
//		i++;
//		Video v= videoService.getVideo(r);
//		lVideos.add(v);
//		
//	}
//	return lVideos;
//	
//}
//
//@GetMapping("/{id}/allPhotos")
//public List<Photo> GetPhotosByRessource(@PathVariable String id){
//	Ressource res=ressourceRepository.findById(id).orElse(null);
//	List<String> rest=res.getIdPhoto();
//	List <Photo> lPhotos = new ArrayList<Photo>();
//	int i=0;
//	while (!rest.isEmpty()) {
//		String r= rest.get(i);
//		i++;
//		Photo v= photoService.getPhoto(r);
//		lPhotos.add(v);
//		
//	}
//	return lPhotos;
//	
//}

@CrossOrigin
@ApiOperation("get a photo")
@GetMapping("/photo/{idP}")
public Photo GetPhoto( @PathVariable String idP){
	
		Photo v= photoService.getPhoto(idP);
		
	return v;
	
}

@CrossOrigin
@ApiOperation("get a video")
@GetMapping("/video/{idV}")
public Video GetVideo( @PathVariable String idV) throws IllegalStateException, IOException{
	
		Video p= videoService.getVideo(idV);
		
	return p;
	
}

}