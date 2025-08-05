package com.EventManagenet.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.EventManagenet.Entity.Event;
import com.EventManagenet.Entity.Venue;
import com.EventManagenet.ExceptionHandling.IdNotFoundException;
import com.EventManagenet.ExceptionHandling.NoRecordFoundException;
import com.EventManagenet.dao.VenueDao;
import com.EventManagenet.dto.ResponseStructure;

@Service
public class VenueService {
	@Autowired
	private VenueDao venueDao;
	public ResponseEntity<ResponseStructure<Venue>> saveVenue(Venue venue) {
		ResponseStructure<Venue> response=new ResponseStructure<Venue>();
		response.setMessage("Data is inserted");
		response.setData(venueDao.saveVenue(venue));
		return new ResponseEntity<ResponseStructure<Venue>>(response,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<List<Venue>>> getAllVenue(){
		List<Venue> list=venueDao.getAllVenue();
		ResponseStructure<List<Venue>> response=new ResponseStructure<List<Venue>>();
		if(!list.isEmpty()) {
			response.setMessage("Venue Details");
	        response.setData(list);
	        return new ResponseEntity<ResponseStructure<List<Venue>>>(response, HttpStatus.FOUND);}
		throw new NoRecordFoundException("No  record found in the database");     
	}
	public ResponseEntity<ResponseStructure<Optional<Venue>>> getByVenueId(Integer id){
		Optional<Venue> opt=venueDao.getByVenueId(id);
		ResponseStructure<Optional<Venue>> response=new ResponseStructure<Optional<Venue>>();
		if(opt.isPresent()) {
			response.setMessage("Data is displayed based on the id:"+id);
		    response.setData(opt);
		    return new ResponseEntity<ResponseStructure<Optional<Venue>>>(response, HttpStatus.FOUND);}
		throw new IdNotFoundException("Id is not present in database");
	}
	public ResponseEntity<ResponseStructure<Venue>> updateVenue(Venue venue){
		Optional<Venue> opt=venueDao.getByVenueId(venue.getId());
		ResponseStructure<Venue> response=new ResponseStructure<Venue>();
		if(opt.isPresent()) {
			response.setMessage("Event details is upadted:");
		Venue venues=venueDao.saveVenue(venue);
		    response.setData(venues);
		    return new  ResponseEntity<ResponseStructure<Venue>>(response,HttpStatus.OK);}
		throw new NoRecordFoundException("No  record found in the database");     	    
	}
	public ResponseEntity<ResponseStructure<String>> deleteVenueById(Integer id){
		Optional<Venue> opt=venueDao.getByVenueId(id);
		if(opt.isPresent()) {
			ResponseStructure<String> response=new ResponseStructure<String>();
			venueDao.deleteVenueById(id);
			response.setMessage("Venu details based on the id: "+id);
			response.setData("Data is deleted sucessfully!!");
			return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
		}
		throw new IdNotFoundException("Id is not found.Try again!!");
	}
	
	
	
	
	
	
	
	
	
	
	public ResponseEntity<ResponseStructure<List<Venue>>> getVenueByLocation(String location){
		List<Venue> list=venueDao.findVenueByLocation(location);
		ResponseStructure<List<Venue>> response=new ResponseStructure<List<Venue>>();
		if(!list.isEmpty()) {
			response.setMessage("Venue detail based on the location:"+location);
		    response.setData(list);
		    return new ResponseEntity<ResponseStructure<List<Venue>>>(response,HttpStatus.OK);}
		  throw new NoRecordFoundException("No  record found in the database");
	}
	public ResponseEntity<ResponseStructure<List<Event>>> getEventsByVenueId(Integer id){
		List<Event> list=venueDao.findEventsByVenue(id);
		ResponseStructure<List<Event>> response=new ResponseStructure<List<Event>>();
		if(!list.isEmpty()) {
			response.setMessage("Event details based on the venue id: "+id);
		    response.setData(list);
		    return new ResponseEntity<ResponseStructure<List<Event>>>(response, HttpStatus.OK);}
		   throw new NoRecordFoundException("No Events are bind with the venue Id:"+id); 
	}
	public ResponseEntity<ResponseStructure<Page<Venue>>> getEventByPageAndSort(Integer pageno,Integer pagesize,String field)
	{
		Page<Venue> pages=venueDao.findEventByPageAndSorting(pageno, pagesize, field);
		ResponseStructure<Page<Venue>> response=new ResponseStructure<Page<Venue>>();
		if(!pages.isEmpty()) {
			response.setMessage("Pages are sorted based on the fields :"+field);
			response.setData(pages);
			return new ResponseEntity<ResponseStructure<Page<Venue>>>(response, HttpStatus.OK);
		}
		throw new NoRecordFoundException("No record in the database");
	}
}
