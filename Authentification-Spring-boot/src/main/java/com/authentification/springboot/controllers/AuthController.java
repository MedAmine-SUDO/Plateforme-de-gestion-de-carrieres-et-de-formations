package com.authentification.springboot.controllers;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentification.springboot.models.ERole;
import com.authentification.springboot.models.Role;
import com.authentification.springboot.models.User;
import com.authentification.springboot.payload.request.LoginRequest;
import com.authentification.springboot.payload.request.SignupRequest;
import com.authentification.springboot.payload.response.JwtResponse;
import com.authentification.springboot.payload.response.MessageResponse;
import com.authentification.springboot.repository.RoleRepository;
import com.authentification.springboot.repository.UserRepository;
import com.authentification.springboot.security.jwt.JwtUtils;
import com.authentification.springboot.security.services.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role candidatRole = roleRepository.findByName(ERole.ROLE_CANDIDAT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(candidatRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "instructor":
					Role instructorRole = roleRepository.findByName(ERole.ROLE_INSTRUCTOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(instructorRole);

					break;
				case "mentor":
					Role mentorRole = roleRepository.findByName(ERole.ROLE_MENTOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(mentorRole);

					break;
				default:
					Role candidatRole = roleRepository.findByName(ERole.ROLE_CANDIDAT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(candidatRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	@GetMapping("/all")
	public List<User> GetUsers() {
		return userRepository.findAll();
	}
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable String id) {
		userRepository.deleteById(id);
		return id;
	}

}
