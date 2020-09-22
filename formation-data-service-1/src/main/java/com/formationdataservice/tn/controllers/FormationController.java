package com.formationdataservice.tn.controllers;

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

import com.formationdataservice.tn.models.Formation;
import com.formationdataservice.tn.repository.FormationRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/formation")
@Api(value = "FormationResourceAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "Formation Resource")
public class FormationController {
	@Autowired
	private FormationRepository formationRepo;

	@ApiOperation("Get All Formation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully get Formation"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@GetMapping("/all")
	public List<Formation> GetFormations() {
		return formationRepo.findAll();
	}

	@ApiOperation("Get Formation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully get Formation by ID"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@GetMapping("/{id}")
	public Formation GetFormation(@PathVariable String id) {
		return formationRepo.findById(id).orElse(null);
	}

	@ApiOperation("Add Formation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully add Formation"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@PostMapping("/")
	public Formation postFormation(@RequestBody Formation Formation) {
		return formationRepo.save(Formation);
	}

	@ApiOperation("Update Formation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully update Formation"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@PutMapping("/")
	public Formation putFormation(@RequestBody Formation newFormation) {
		Formation oldFormation = formationRepo.findById(newFormation.getId()).orElse(null);
		oldFormation.setId(newFormation.getId());
		oldFormation.setTitle(newFormation.getTitle());
		oldFormation.setDescription(newFormation.getDescription());
		oldFormation.setIdFormateurs(newFormation.getIdFormateurs());
		oldFormation.setNiveau(newFormation.getNiveau());
		oldFormation.setCompetence(newFormation.getCompetence());
		oldFormation.setBeginDate(newFormation.getBeginDate());
		oldFormation.setEndDate(newFormation.getEndDate());
		oldFormation.setNbrHours(newFormation.getNbrHours());
		oldFormation.setPrice(newFormation.getPrice());
		oldFormation.setType(newFormation.getType());

		return formationRepo.save(oldFormation);
	}

	@ApiOperation("Delete Formation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Formation"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@DeleteMapping("/{id}")
	public String deleteFormation(@PathVariable String id) {
		formationRepo.deleteById(id);
		return id;
	}

}
