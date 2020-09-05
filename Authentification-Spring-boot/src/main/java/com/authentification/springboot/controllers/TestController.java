package com.authentification.springboot.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/candidates")
	@PreAuthorize("hasRole('CANDIDAT') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
	public String candidatesAccess() {
		return "User Content.";
	}

	@GetMapping("/instructor")
	@PreAuthorize("hasRole('INSTRUCTOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	@GetMapping("/candidat")
	@PreAuthorize("hasRole('CANDIDAT')")
	public String candidatAccess() {
		return "Candidate Board.";
	}
	@GetMapping("/mentor")
	@PreAuthorize("hasRole('MENTOR')")
	public String mentorAccess() {
		return "MENTOR Board.";
	}
}
