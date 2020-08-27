package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RessourceController {
@Autowired
private RessourceRepository ressourceRepository;
@GetMapping("/")
public List <Ressource> GetRessources(){
	return ressourceRepository.findAll();
	

}
@GetMapping("/{id}")
public Ressource GetRessources(@PathVariable String id){
	return ressourceRepository.findById(id).orElse(null);
	
}
@PostMapping("/")
private Ressource addRessource(@RequestBody Ressource ressource) {
	return ressourceRepository.save(ressource);

}
@PutMapping("/")
private Ressource PutRessource(@RequestBody Ressource newRessource) {
	Ressource oldRessource = ressourceRepository.findById(newRessource.getId()).orElse(null);
	oldRessource.setTitle(newRessource.getTitle());
	oldRessource.setDescription(newRessource.getDescription());
	oldRessource.setFile(newRessource.getFile());
	return oldRessource;
}
@DeleteMapping
public String DeleteRessource(@PathVariable String id) {
	ressourceRepository.deleteById(id);
	return id;
}
}