package com.EventManagenet.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.EventManagenet.Entity.Attende;
import com.EventManagenet.Entity.Event;
import com.EventManagenet.Entity.Organizer;
import com.EventManagenet.Entity.Venue;
import com.EventManagenet.ExceptionHandling.IdNotFoundException;
import com.EventManagenet.ExceptionHandling.NoRecordFoundException;
import com.EventManagenet.dao.EventDao;
import com.EventManagenet.dto.ResponseStructure;
import com.EventManagenet.respository.OrganizerRepostiory;
import com.EventManagenet.respository.VenueRepository;

@Service
public class EventService {
@Autowired
private EventDao eventDao;
@Autowired
private VenueRepository venuedao;
@Autowired
private OrganizerRepostiory organizerReposiory;

public ResponseEntity<ResponseStructure<Event>> saveEvent(Event event){
	Optional<Venue> opt1=venuedao.findById(event.getVenue().getId());
	Optional<Organizer> opt2=organizerReposiory.findById(event.getOrganizer().getId());
	if(opt1.isPresent()&&opt2.isPresent()) {
		event.setOrganizer(opt2.get());
		event.setVenue(opt1.get());
	}
	ResponseStructure<Event> response=new ResponseStructure<Event>();
	response.setMessage(" Event Data are inseret");
	response.setData(eventDao.saveEnvent(event));
	return new ResponseEntity<ResponseStructure<Event>>(response,HttpStatus.CREATED);
}
public ResponseEntity<ResponseStructure<List<Event>>> getAllEvent(){
	List<Event> events=eventDao.getAllEvent();
	ResponseStructure<List<Event>> response=new ResponseStructure<List<Event>>();
	if(events.isEmpty()) {
		throw new NoRecordFoundException(" No records available in database table");
	}
	response.setMessage("Event details");
	response.setData(events);
	return new ResponseEntity<ResponseStructure<List<Event>>>(response,HttpStatus.FOUND);
	}
public ResponseEntity<ResponseStructure<Optional<Event>>> getEventById(Integer id){
	Optional<Event> events=eventDao.getEventById(id);
	ResponseStructure<Optional<Event>> response=new ResponseStructure<Optional<Event>>();
	if(events.isPresent()) {
		response.setMessage("Event Data base on the id: "+id);
        response.setData(events);
        return new ResponseEntity<ResponseStructure<Optional<Event>>>(response,HttpStatus.FOUND);}
        throw new NoRecordFoundException(" No records available in database table"); 
}
public ResponseEntity<ResponseStructure<Event>> updateEvent(Event event){
	Optional<Event> events=eventDao.getEventById(event.getId());
	ResponseStructure<Event> response=new ResponseStructure<Event>();
	if(events.isPresent()) {
		response.setMessage("Event Data's are upadted");
	    Event updatedEvent=eventDao.saveEnvent(event);
	    response.setData(updatedEvent);
	    return new ResponseEntity<ResponseStructure<Event>>(response,HttpStatus.OK);}
	    throw new IdNotFoundException(" No records available in database table");   
}
public ResponseEntity<ResponseStructure<String>> getDeleteEventById(Integer id){
	Optional<Event> events=eventDao.getEventById(id);
	ResponseStructure<String> response=new ResponseStructure<String>();
	if(events.isPresent()) {
	response.setData("Event is deleted");
	response.setMessage("Event data are deleted based on the id: "+id);
	eventDao.DeleteEvent(id);
	return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);}
	throw new IdNotFoundException("Enter the coorect id");
}
public ResponseEntity<ResponseStructure<List<Event>>> eventByOrganizerId(Integer id){
	List<Event> list=eventDao.getEventByRegistrationId(id);
	ResponseStructure<List<Event>> response=new ResponseStructure<List<Event>>();
	if(list.size()>0) {
		response.setMessage("Event details");
		response.setData(list);
		return new ResponseEntity<ResponseStructure<List<Event>>>(response, HttpStatus.OK);
	}
	throw new IdNotFoundException("Event details not found");
}
public ResponseEntity<ResponseStructure<List<Attende>>> attendeByEventId(Integer id){
	List<Attende> li=eventDao.getAttendeByEventId(id);
	if(li.isEmpty()) {
		
		throw new IdNotFoundException("No  attende details found based on the event id: "+id);
	}
	ResponseStructure<List<Attende>> response=new ResponseStructure<List<Attende>>();
	response.setMessage("Attende deatils based on the events id: "+id);
	response.setData(li);
	return new ResponseEntity<ResponseStructure<List<Attende>>>(response, HttpStatus.FOUND);
	
	
}
public ResponseEntity<ResponseStructure<Page<Event>>> getEventByPageAndSort(Integer pageno,Integer pagesize,String field)
{
	Page<Event> li=eventDao.findEventByPageAndSorting(pageno, pagesize, field);
	ResponseStructure<Page<Event>> pages=new ResponseStructure<Page<Event>>();
	if(!li.isEmpty()) {
		pages.setMessage("Events Details");
	    pages.setData(li);
	    return new ResponseEntity<ResponseStructure<Page<Event>>>(pages,HttpStatus.OK);}
	throw new NoRecordFoundException(" No records available in database table");	
}
	}

