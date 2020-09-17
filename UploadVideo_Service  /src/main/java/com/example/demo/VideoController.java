package com.example.demo;

import java.io.IOException;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.MediaType;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RequestMapping("/api/video")
@Api(value = "VideoUploadAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "Upload ressource ")

@RestController

public class VideoController {

	@Autowired
	private VideoRepository videoRepoSitory;
	@Autowired
	private GridFsTemplate gridFsTemplate;

	private VideoService videoService;

	@ApiOperation("Add a Video")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully ADd a Video"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@PostMapping("/add")
	public String addVideo(@RequestParam("title") String title, @RequestParam("video") MultipartFile video, Model model)
			throws IOException {

		String id = videoService.addVideo(title, video);

		return "redirect:/videos/" + id;
	}

	@ApiOperation("Get a Video")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Get a Video"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@GetMapping("/videos/{id}")
	public String getVideo(@PathVariable String id, Model model) throws Exception {
		Video video = videoService.getVideo(id);
		model.addAttribute("title", video.getTitle());
		model.addAttribute("url", "/videos/stream/" + id);
		return "videos";
	}

	@ApiOperation("Stream a Video")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Stream a Video"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@GetMapping("/videos/stream/{id}")
	public void streamVideo(@PathVariable String id, HttpServletResponse response) throws Exception {
		Video video = videoService.getVideo(id);
		FileCopyUtils.copy(video.getStream(), response.getOutputStream());
	}

	@ApiOperation("Delete a Video")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Delete a Video"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@DeleteMapping("/{idV}")
	public String DeleteVideo(@PathVariable String idV) {
		videoRepoSitory.deleteById(idV);
		;
		return idV;
	}

}
