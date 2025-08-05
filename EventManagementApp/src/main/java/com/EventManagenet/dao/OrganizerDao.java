package com.EventManagenet.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.EventManagenet.Entity.Organizer;
import com.EventManagenet.respository.OrganizerRepostiory;
@Repository
public class OrganizerDao {
	@Autowired
	private OrganizerRepostiory organizerRepository;
	public Organizer saveOrganizer(Organizer organizer) {
		return organizerRepository.save(organizer);
	}
	public List<Organizer> getAllOrganizer(){
		return organizerRepository.findAll();
	}
	public Optional<Organizer> getOrganizerById(Integer id){
		return organizerRepository.findById(id);
	}
	public Organizer updateOrgainizer(Organizer organizer) {
		return organizerRepository.saveAndFlush(organizer);
	}
	public void deleteOrganizerById(Integer id){
		 organizerRepository.deleteById(id);
	}
	public Page<Organizer> getOrganizerByPageAndSort(Integer pageno,Integer pagesize, String field){
		return organizerRepository.findAll(PageRequest.of(pageno, pagesize,Sort.by(field).ascending()));
	}
}
