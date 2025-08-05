package com.EventManagenet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.EventManagenet.Entity.Organizer;
import com.EventManagenet.ExceptionHandling.NoRecordFoundException;
import com.EventManagenet.dao.OrganizerDao;
import com.EventManagenet.ExceptionHandling.IdNotFoundException;
import com.EventManagenet.dto.ResponseStructure;

@Service
public class OrganizerService {
	@Autowired
	private OrganizerDao organizerDao;
	public ResponseEntity<ResponseStructure<Organizer>> saveOrganizer(Organizer organizer){
		ResponseStructure<Organizer> response=new ResponseStructure<Organizer>();
		response.setMessage("Orgainzer data is created");
		response.setData(organizerDao.saveOrganizer(organizer));
		return  new ResponseEntity<ResponseStructure<Organizer>>(response,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Organizer>>> getAllOrganizer(){
		List<Organizer> list=organizerDao.getAllOrganizer();
		ResponseStructure<List<Organizer>> response=new ResponseStructure<List<Organizer>>();
		if(!list.isEmpty()) {
			response.setMessage("Data found:");
		    response.setData(list);
		    return new ResponseEntity<ResponseStructure<List<Organizer>>>(response,HttpStatus.OK);}
		throw new NoRecordFoundException("No  record found in the database");
	}
	public ResponseEntity<ResponseStructure<Optional<Organizer>>> getOranizerById(Integer id){
		Optional<Organizer> opt=organizerDao.getOrganizerById(id);
		ResponseStructure<Optional<Organizer>> response=new ResponseStructure<Optional<Organizer>>();
		if(opt.isPresent()) {
			response.setMessage("Organzier based on the id: "+id);
            response.setData(opt);
            return new ResponseEntity<ResponseStructure<Optional<Organizer>>>(response,HttpStatus.FOUND);}
		throw new  IdNotFoundException("No  record found based on the id: "+id);
	}
	public ResponseEntity<ResponseStructure<Organizer>> updateOrganizer(Organizer organizer){
		Optional<Organizer> opt=organizerDao.getOrganizerById(organizer.getId());
		ResponseStructure<Organizer> response=new ResponseStructure<Organizer>();
		if(opt.isPresent()) {
			response.setMessage("organizer details are updated");
			Organizer data=organizerDao.saveOrganizer(organizer);
			response.setData(data);
			   return new ResponseEntity<ResponseStructure<Organizer>>(response,HttpStatus.OK);	}
		throw new NoRecordFoundException("No  record found");
	}
	public ResponseEntity<ResponseStructure<String>> deleteOrganizerById(Integer id){
		Optional<Organizer> opt=organizerDao.getOrganizerById(id);
		ResponseStructure<String> response=new ResponseStructure<String>();
		if(opt.isPresent()) {
			response.setMessage("Oranizer Id: "+id);
			response.setData("Organizer Details are deleted");organizerDao.deleteOrganizerById(id);
			return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
			}
		throw new  IdNotFoundException("No  record found based on the id: "+id);
		}
	public ResponseEntity<ResponseStructure<Page<Organizer>>> getOrganizerByPageAndSort(Integer pageno,Integer pagesize,String field){
		Page<Organizer> page=organizerDao.getOrganizerByPageAndSort(pageno, pagesize, field);
		ResponseStructure<Page<Organizer>> response=new ResponseStructure<Page<Organizer>>();
		if(!page.isEmpty()) {
			response.setMessage("Data are orderd based on :"+field);
			response.setData(page);
			return new ResponseEntity<ResponseStructure<Page<Organizer>>>(response,HttpStatus.OK);}
		else {
		 throw new NoRecordFoundException("No Supporting format check it properly");}
	}
	
}

