package com.EventManagenet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.EventManagenet.Entity.Registration;
import com.EventManagenet.dto.ResponseStructure;
import com.EventManagenet.service.RegisterService;
@RestController
@RequestMapping("/event/register")
public class RegisterController {
	@Autowired
	private RegisterService registerService;
	@PostMapping
	public ResponseEntity<ResponseStructure<Registration>> saveRegistration(@RequestBody  Registration register) {
		return registerService.saveRegistration(register);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Registration>>> getAllRegistration(){
		return registerService.getAllRegistration();
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Optional<Registration>>> getRegistrationById(@PathVariable Integer id){ 
		return registerService.getRegistrationById(id);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteAttendeById(@PathVariable Integer id){
		return registerService.deleteAttendeeRegistration(id);
	}
	@GetMapping("/event/{id}")
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByEventId(@PathVariable Integer id){
		return registerService.getRegistrationByEventId(id);
	}
	@GetMapping("/attende/{id}")
	public ResponseEntity<ResponseStructure<Optional<Registration>>> getRegistrationByAttendeId(@PathVariable Integer id){
		return registerService.getRegistrationByAttendeId(id);
	}

}
