package com.example.Competence_service.Controllers;

import com.example.Competence_service.CvReader.CvReader;
import com.example.Competence_service.Models.Competence;
import com.example.Competence_service.Repositories.CompetenceRepository;
import com.example.Competence_service.convertCsv.CsvToList;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/competence")
@Api(value = "CompetenceResourceAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "Competence Resource")
public class CompetenceController {

	@Autowired
	CompetenceRepository competenceRepository;

	// add new Competence
	@PostMapping("/")
	public ResponseEntity<Competence> add(@RequestBody Competence competence) {
		try {
			Competence c_ = competenceRepository.save(competence);
			return new ResponseEntity<>(c_, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/cv")
	public ResponseEntity<Competence> addfromcv(@RequestParam("idCandidat") String idCandidat,
			@RequestParam("file") MultipartFile file) {
		try {
			Competence c = new Competence();
			c.setIdCandidat(idCandidat);

			// call the detection algorithm
			CvReader cvReader = new CvReader();
			List<String> list = cvReader.readCV(file);

			c.setList(list);
			Competence c_ = competenceRepository.save(c);
			return new ResponseEntity<>(c_, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// list all the Competences in the database

	@GetMapping("/all")
	public List<Competence> getall() {
		List<Competence> list = new ArrayList<>();
		competenceRepository.findAll().forEach(list::add);
		return list;
	}

	// Get one candidate's competence from his id
	@GetMapping("/{idCandidat}")
	public Competence getone(@PathVariable("idCandidat") String idCandidat) {
		return competenceRepository.findByIdCandidat(idCandidat);
	}

	// get one candidate's competence by the competence id
	@GetMapping("/{id}")
	public Optional<Competence> getOneByid(@PathVariable("id") String id) {
		return competenceRepository.findById(id);
	}

	// delete by id

	@DeleteMapping("/{id}")
	public void deleteCompetence(@PathVariable("id") String id) {
		competenceRepository.deleteById(id);
	}

	// delete all data

	@DeleteMapping("/")
	public void deleteAll() {
		competenceRepository.deleteAll();
	}

}
