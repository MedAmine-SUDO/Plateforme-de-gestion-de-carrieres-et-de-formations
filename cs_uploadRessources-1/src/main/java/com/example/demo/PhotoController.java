package com.example.demo;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photos")
public class PhotoController {
	@Autowired
	private PhotoService photoService;
	
	private VideoService videoService;

	@PostMapping("/add")
	public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, Model model)
			throws IOException {
		String id = photoService.addPhoto(title, image);
		return "redirect:/photos/" + id;
	}
	@GetMapping("/")
	public List<Photo> test() {
		return photoService.getAll();
	}

	@GetMapping("/{id}")
	public String getPhoto(@PathVariable String id, Model model) {

		Photo photo = photoService.getPhoto(id);
		model.addAttribute("title", photo.getTitle());
		model.addAttribute("image", Base64.getEncoder().encodeToString(photo.getImage().getData()));
		return "photos";
	}

	@GetMapping("/videos/stream/{id}")
	public void streamVideo(@PathVariable String id, HttpServletResponse response) throws Exception {
		Video video = videoService.getVideo(id);
		FileCopyUtils.copy(video.getStream(), response.getOutputStream());
	}
}
