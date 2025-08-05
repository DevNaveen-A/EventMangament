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

import com.EventManagenet.Entity.Event;
import com.EventManagenet.Entity.Venue;
import com.EventManagenet.dto.ResponseStructure;
import com.EventManagenet.service.VenueService;

@RestController
@RequestMapping("/event/venue")
public class VenueController {
	@Autowired
	private VenueService venueService;
	@PostMapping
	public ResponseEntity<ResponseStructure<Venue>> saveVenue(@RequestBody  Venue venue) {
		return venueService.saveVenue(venue);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Venue>>> getAllveVenue(){
		return venueService.getAllVenue();
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Optional<Venue>>> getByVenueId(@PathVariable Integer id){
		return venueService.getByVenueId(id);
	}
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Venue>> updateVenue(@RequestBody  Venue venue){
		return venueService.updateVenue(venue);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteVenueById(@PathVariable Integer id){
		return venueService.deleteVenueById(id);
	}
	@GetMapping("/loc/{location}")
	public ResponseEntity<ResponseStructure<List<Venue>>> getVenueByLocation(@PathVariable  String location){
		return venueService.getVenueByLocation(location);
	}
	@GetMapping("/event/{id}")
	public ResponseEntity<ResponseStructure<List<Event>>> getEventsByVenueId(@PathVariable  Integer id){
		return venueService.getEventsByVenueId(id);
	}
	@GetMapping("/page/{pageno}/{pagesize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Venue>>> getEventByPageAndSort(@PathVariable Integer pageno,@PathVariable Integer pagesize,@PathVariable String field)
	{
		return venueService.getEventByPageAndSort(pageno, pagesize, field);
	}
	

}
