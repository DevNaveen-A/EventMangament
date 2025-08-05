package com.EventManagenet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.EventManagenet.Entity.Attende;
import com.EventManagenet.Entity.Registration;
import com.EventManagenet.dto.ResponseStructure;
import com.EventManagenet.service.AttendeService;

@RestController
@RequestMapping("/event/attende")
public class AttendeController {
	@Autowired
	private AttendeService attendService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Attende>> saveAttende(@RequestBody Attende attende) {
		return attendService.saveAttende(attende);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Attende>>> getAllAttende() {
		return attendService.getAllAttende();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Optional<Attende>>> getAttendeById(@PathVariable Integer id) {
		return attendService.getAttendeById(id);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Attende>> updateAttende(@RequestBody Attende attende) {
		return attendService.updateAttende(attende);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Optional<Attende>>> deleteAttendeById(@PathVariable Integer id) {
		return attendService.deleteAttendeById(id);
	}

	@GetMapping("/contact/{contact}")
	public ResponseEntity<ResponseStructure<Optional<Attende>>> getAttendeByContact(@PathVariable Long contact) {
		return attendService.getAttendeByContact(contact);
	}

	@GetMapping("/page/{pageno}/{pagesize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Attende>>> getByAttendePageAndSort(@PathVariable Integer pageno,
			@PathVariable Integer pagesize, @PathVariable String field) {
		return attendService.getByAttendePageAndSort(pageno, pagesize, field);
	}

	@GetMapping("/reg/{id}")
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByAttendeId(@PathVariable Integer id) {
		return attendService.getRegistrationByAttendeId(id);
	}
}
