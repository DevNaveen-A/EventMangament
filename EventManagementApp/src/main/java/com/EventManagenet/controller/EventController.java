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
import com.EventManagenet.Entity.Event;
import com.EventManagenet.dto.ResponseStructure;
import com.EventManagenet.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
@Autowired
private EventService eventService;
@PostMapping
public ResponseEntity<ResponseStructure<Event>> saveEvent(@RequestBody Event event){
	return eventService.saveEvent(event);
}
@GetMapping
public ResponseEntity<ResponseStructure<List<Event>>> getAllEvent(){
	return eventService.getAllEvent();
}
@GetMapping("/{id}")
public ResponseEntity<ResponseStructure<Optional<Event>>> getEventById(@PathVariable  Integer id){
	return eventService.getEventById(id);
}
@PutMapping
public ResponseEntity<ResponseStructure<Event>> updateEvent(@RequestBody Event event){
	return eventService.updateEvent(event);
}
@GetMapping("/org/{id}")
public ResponseEntity<ResponseStructure<List<Event>>> eventByOrganizerId(@PathVariable Integer id){
	return eventService.eventByOrganizerId(id);
}
@GetMapping("/attend/{id}")
public ResponseEntity<ResponseStructure<List<Attende>>> attendeByEventId(@PathVariable Integer id){
	return eventService.attendeByEventId(id);
}
@DeleteMapping("/{id}")
public ResponseEntity<ResponseStructure<String>> getDeleteEventById(@PathVariable Integer id){
	return eventService.getDeleteEventById(id);
}

@GetMapping("/page/{pageno}/{pagesize}/{field}")
public ResponseEntity<ResponseStructure<Page<Event>>> getEventByPageAndSort(@PathVariable  Integer pageno,@PathVariable Integer pagesize,@PathVariable String field)
{
	return eventService.getEventByPageAndSort(pageno, pagesize, field);
	}
@GetMapping("/organize/{id}")
public ResponseEntity<ResponseStructure<List<Event>>> getEventByOrganizerId(@PathVariable  Integer id){
	return eventService.eventByOrganizerId(id);
}

}
