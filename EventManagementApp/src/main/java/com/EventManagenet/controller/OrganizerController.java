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

import com.EventManagenet.Entity.Organizer;
import com.EventManagenet.dto.ResponseStructure;
import com.EventManagenet.service.OrganizerService;

@RestController
@RequestMapping("/event/organizer")
public class OrganizerController {
	@Autowired
	private OrganizerService organizerService;
@PostMapping
public ResponseEntity<ResponseStructure<Organizer>> saveOrganizer(@RequestBody  Organizer organizer){
	return organizerService.saveOrganizer(organizer);
}
@GetMapping
public ResponseEntity<ResponseStructure<List<Organizer>>> getAllOrganizer(){
	return organizerService.getAllOrganizer();
}
@PostMapping("/{id}")
public ResponseEntity<ResponseStructure<Optional<Organizer>>> getOranizerById(@PathVariable  Integer id){
	return organizerService.getOranizerById(id);
}
@PutMapping
public ResponseEntity<ResponseStructure<Organizer>> updateOrgainzer(@RequestBody Organizer organizer){
	return organizerService.updateOrganizer(organizer);
}
@DeleteMapping("/{id}")
public ResponseEntity<ResponseStructure<String>> deleteOrganizerById(@PathVariable  Integer id){
	return organizerService.deleteOrganizerById(id);
}
@GetMapping("/page/{pageno}/{pagesize}/{field}")
public ResponseEntity<ResponseStructure<Page<Organizer>>> getOrganizerByPageAndSort(@PathVariable  Integer pageno,@PathVariable Integer pagesize,@PathVariable String field){
	return organizerService.getOrganizerByPageAndSort(pageno, pagesize, field);
}

}

