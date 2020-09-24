package com.example.demo;

import java.io.IOException;

import java.util.Base64;
import java.util.List;

//import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
//import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RequestMapping("/api/file")
@Api(value = "FileUploadAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "Upload File ")

@RestController
public class StorageFileController {
	@Autowired
	private StorageService storageService;
	@Autowired
	private StorageFileRepository storageFileRepository;

	@ApiOperation("Add a file")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Add a file"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@PostMapping("/add")
	public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, Model model)
			throws IOException {
		String id = storageService.addFile(title, image);
		return "redirect:/photos/" + id;
	}

	@ApiOperation("Get all files")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully getted All files"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@GetMapping("/")
	public List<StorageFile> test() {
		return storageService.getAll();
	}

	@ApiOperation("Get a file")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Geta file"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@GetMapping("/file/{id}")
	public String getPhoto(@PathVariable String id, Model model) {

		StorageFile photo = storageService.getFile(id);
		model.addAttribute("title", photo.getTitle());
		model.addAttribute("file", Base64.getEncoder().encodeToString(photo.getFile().getData()));
		return "photos";
	}

	@ApiOperation("Delete a file")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Delete a file"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@DeleteMapping("/{idp}")
	public String DeleteFile(@PathVariable String idp) {
		storageFileRepository.deleteById(idp);
		;
		return idp;
	}

}
