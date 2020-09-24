package com.example.demo;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.MediaType;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RequestMapping("/api/photos")
@Api(value = "VideoUploadAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "Upload ressource ")

@RestController
public class PhotoController {
	@Autowired
	private PhotoService photoService;
	@Autowired
	private PhotoRepository photoRepository;
	
	//private VideoService videoService;
	@ApiOperation("Add a Photo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Add a Photo"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@PostMapping("/add")
	public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, Model model)
			throws IOException {
		String id = photoService.addPhoto(title, image);
		return "redirect:/photos/" + id;
	}
	@ApiOperation("Get all Photos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Get all Photos"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@GetMapping("/")
	public List<Photo> test() {
		return photoService.getAll();
	}
	@ApiOperation("Get a Photo ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Get a Photo"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@GetMapping("/{id}")
	public String getPhoto(@PathVariable String id, Model model) {

		Photo photo = photoService.getPhoto(id);
		model.addAttribute("title", photo.getTitle());
		model.addAttribute("image", Base64.getEncoder().encodeToString(photo.getImage().getData()));
		return "photos";
	}
	@ApiOperation("Delete a Photo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Delete a Photo"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@DeleteMapping("/{idp}")
	public String DeleteFile(@PathVariable String idp) {
		photoRepository.deleteById(idp);
		;
		return idp;
	}
//	@GetMapping("/videos/stream/{id}")
//	public void streamVideo(@PathVariable String id, HttpServletResponse response) throws Exception {
//		Video video = videoService.getVideo(id);
//		FileCopyUtils.copy(video.getStream(), response.getOutputStream());
//	}
}
