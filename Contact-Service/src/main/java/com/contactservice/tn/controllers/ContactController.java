package com.contactservice.tn.controllers;

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

import com.contactservice.tn.models.Contact;
import com.contactservice.tn.repository.ContactRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/Contact")
@Api(value = "ContactResourceAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "Contact Resource")
public class ContactController {
	@Autowired
	private ContactRepository ContactRepo;

	@ApiOperation("Get All Contact")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully get Contact"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@GetMapping("/all")
	public List<Contact> GetContacts() {
		return ContactRepo.findAll();
	}

	@ApiOperation("Get Contact")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully get Contact by ID"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@GetMapping("/{id}")
	public Contact GetContact(@PathVariable String id) {
		return ContactRepo.findById(id).orElse(null);
	}

	@ApiOperation("Add Contact")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully add Contact"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@PostMapping("/")
	public Contact postContact(@RequestBody Contact Contact) {
		return ContactRepo.save(Contact);
	}

	@ApiOperation("Update Contact")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully update Contact"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@PutMapping("/")
	public Contact putContact(@RequestBody Contact newContact) {
		Contact oldContact = ContactRepo.findById(newContact.getId()).orElse(null);
		oldContact.setId(newContact.getId());
		oldContact.setTitle(newContact.getTitle());
		oldContact.setDescription(newContact.getDescription());
		oldContact.setIdFormateurs(newContact.getIdFormateurs());
		oldContact.setNiveau(newContact.getNiveau());
		oldContact.setCompetence(newContact.getCompetence());
		oldContact.setBeginDate(newContact.getBeginDate());
		oldContact.setEndDate(newContact.getEndDate());
		oldContact.setNbrHours(newContact.getNbrHours());
		oldContact.setPrice(newContact.getPrice());
		oldContact.setType(newContact.getType());

		return ContactRepo.save(oldContact);
	}

	@ApiOperation("Delete Contact")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Contact"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@DeleteMapping("/{id}")
	public String deleteContact(@PathVariable String id) {
		ContactRepo.deleteById(id);
		return id;
	}

}
