package com.example.demo;

import java.util.ArrayList;
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


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/notification")
@Api(value = "NotificationAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "Notification Service ressources ")
public class NotificationController {
	@Autowired
	private NotificationRepository notificationRepo ;


	// Get a all notifications by ID receiver 
	//--------------------------------------------------------
	
	@ApiOperation("Get all Notification of a receiver")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully  Get all Notification of a receiver"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@GetMapping("/{id}")
	public List<Notification> GetNotBySender(@PathVariable String idR) {
		List<Notification> l= new ArrayList();
		int i =0;
		List<Notification> listall = GetNotifications();
		while(!listall.isEmpty()) {
			if (listall.get(i).getIdReceiver()==idR)	{
				l.add(listall.get(i));
			}
		}
		return l;
	}

	
// Get all !notification APi
//--------------------------------------------------------
	@ApiOperation("Get All Notifications")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully get All Notifications"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@GetMapping("/all")
	
	public List<Notification> GetNotifications() {
		return notificationRepo.findAll();
	}
	
	

	// Get a notification by ID 
	//--------------------------------------------------------
	
	@ApiOperation("Get a Notification")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully get a nNotification by ID"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@GetMapping("/{id}")
	public Notification GetNotification(@PathVariable String id) {
		return notificationRepo.findById(id).orElse(null);
	}
	
		// Get add !notification APi
	//--------------------------------------------------------
	@ApiOperation("Add Notification")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added a Notification"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") 
	})
	@PostMapping("/")
	public Notification postFormation(@RequestBody Notification Formation) {
		return notificationRepo.save(Formation);
	}

	// Get update !notification APi
	//--------------------------------------------------------


	@ApiOperation("Update Formation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully update Formation"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@PutMapping("/update")
	public Notification putFormation(@RequestBody Notification newNotifcation) {
		Notification oldNotifition = notificationRepo.findById(newNotifcation.getId()).orElse(null);
		oldNotifition.setId(newNotifcation.getId());
		oldNotifition.setTitle(newNotifcation.getTitle());
		oldNotifition.setDescription(newNotifcation.getDescription());
		oldNotifition.setBody(newNotifcation.getBody());
		oldNotifition.setDate(newNotifcation.getDate());
		oldNotifition.setIdSender(newNotifcation.getIdSender());
		oldNotifition.setIdReceiver(newNotifcation.getIdReceiver());;
		oldNotifition.setObject(newNotifcation.getObject());;
		oldNotifition.setType(newNotifcation.getType());
		oldNotifition.setSeen(newNotifcation.getSeen());
		return notificationRepo.save(oldNotifition);
	}

	// Get delete !notification APi
	//--------------------------------------------------------

	@ApiOperation("Delete Formation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved the Notification"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found") })
	@DeleteMapping("/{id}")
	public String deleteFormation(@PathVariable String id) {
		notificationRepo.deleteById(id);
		return id;
	}
	


}
