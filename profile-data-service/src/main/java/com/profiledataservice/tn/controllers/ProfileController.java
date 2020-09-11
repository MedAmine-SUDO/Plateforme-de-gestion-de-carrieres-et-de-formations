package com.profiledataservice.tn.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profiledataservice.tn.models.Profile;
import com.profiledataservice.tn.repository.ProfileRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/profile")
@Api(value = "ProfileResourceAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "Profile Resource")
public class ProfileController {
	@Autowired
	private ProfileRepository profileRepo;

	@ApiOperation("Get All Profile")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully get Profile"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@GetMapping("/all")
	public List<Profile> GetProfiles() {
		return profileRepo.findAll();
	}

	@ApiOperation("Get Profile")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully get Profile by ID"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@GetMapping("/{id}")
	public Profile GetProfile(@PathVariable String id) {
		return profileRepo.findByUserID(id).orElse(null);
	}

	@ApiOperation("Add Profile")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully add Profile"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@PostMapping("/")
	public Profile postProfile(@RequestBody Profile Profile) {
		return profileRepo.save(Profile);
	}

	@ApiOperation("Update Profile")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully update Profile"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@PutMapping("/")
	public Profile putProfile(@RequestBody Profile newProfile) {
		Profile oldProfile = profileRepo.findById(newProfile.getId()).orElse(null);
		oldProfile.setFirstName(newProfile.getFirstName());
		oldProfile.setLastName(newProfile.getLastName());
		oldProfile.setBirthDate(newProfile.getBirthDate());
		oldProfile.setAddress(newProfile.getAddress());
		oldProfile.setPostCode(newProfile.getPostCode());
		oldProfile.setRegion(newProfile.getRegion());
		oldProfile.setCountry(newProfile.getCountry());
		oldProfile.setTelephone(newProfile.getTelephone());

		return profileRepo.save(oldProfile);
	}

	@ApiOperation("Delete Profile")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Profile"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@DeleteMapping("/{id}")
	public String deleteProfile(@PathVariable String id) {
		profileRepo.deleteById(id);
		return id;
	}

}
