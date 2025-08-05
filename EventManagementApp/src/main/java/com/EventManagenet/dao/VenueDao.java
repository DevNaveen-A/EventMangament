package com.EventManagenet.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.EventManagenet.Entity.Event;
import com.EventManagenet.Entity.Venue;
import com.EventManagenet.respository.VenueRepository;
@Repository
public class VenueDao {
	@Autowired
	private VenueRepository venueReposiory;
	public Venue saveVenue(Venue venue) {
		return venueReposiory.save(venue);
	}
	public List<Venue> getAllVenue(){
		return venueReposiory.findAll();
	}
	public Optional<Venue> getByVenueId(Integer id){
		return venueReposiory.findById(id);
	}
	public Venue updateVenue(Venue venue) {
		return venueReposiory.save(venue);
	}
	public List<Venue> findVenueByLocation(String location){
		return venueReposiory.getByVenueLocation(location);
	}
	public List<Event> findEventsByVenue(Integer id){
		return venueReposiory.getByEventsFromVenue(id);
	}
	public Page<Venue> findEventByPageAndSorting(Integer pageno,Integer pagesize,String field){
		 return venueReposiory.findAll(PageRequest.of(pageno, pagesize,Sort.by(field).ascending()));
	}
	public void deleteVenueById(Integer id) {
		venueReposiory.deleteById(id);
	}
}
