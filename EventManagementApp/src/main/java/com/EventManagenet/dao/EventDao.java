package com.EventManagenet.dao;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.EventManagenet.Entity.Attende;
import com.EventManagenet.Entity.Event;
import com.EventManagenet.respository.EventRepository;

@Repository
public class EventDao {
@Autowired
private EventRepository eventRepository;
public Event saveEnvent(Event event) {
	 return eventRepository.save(event);	
}
public List<Event> getAllEvent(){
	return eventRepository.findAll();
}
public Optional<Event> getEventById(Integer id){
	return eventRepository.findById(id);
}
public Event updateEvent(Event event) {
	return eventRepository.save(event);
}
public void DeleteEvent(Integer id) {
	eventRepository.deleteById(id);
}
public List<Event> getEventByRegistrationId(Integer id){
	return eventRepository.getEventByOrganizerId(id);
}
public List<Attende> getAttendeByEventId(Integer id){
	return eventRepository.getAttendesByEventId(id);
}

public Page<Event> findEventByPageAndSorting(Integer pageno,Integer pagesize,String field){
	 return eventRepository.findAll(PageRequest.of(pageno, pagesize,Sort.by(field).ascending()));
	
}
}

