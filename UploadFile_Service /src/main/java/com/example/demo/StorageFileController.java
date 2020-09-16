package com.example.demo;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
public class StorageFileController {
	@Autowired
    private StorageService storageService;

	@PostMapping("/add")
	public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, Model model)
			throws IOException {
		String id = storageService.addFile(title, image);
		return "redirect:/photos/" + id;
	}
	@GetMapping("/")
	public List<StorageFile> test() {
		return storageService.getAll();
	}

	@GetMapping("/file/{id}")
	public String getPhoto(@PathVariable String id, Model model) {

		StorageFile photo = storageService.getFile(id);
		model.addAttribute("title", photo.getTitle());
		model.addAttribute("file", Base64.getEncoder().encodeToString(photo.getFile().getData()));
		return "photos";
	}

	
}
