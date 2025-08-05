package com.EventManagenet.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.EventManagenet.Entity.Attende;
import com.EventManagenet.Entity.Registration;
import com.EventManagenet.ExceptionHandling.NoRecordFoundException;
import com.EventManagenet.dao.AttendeDao;
import com.EventManagenet.dto.ResponseStructure;
import com.sp.spring.demo2.ExceptionHandler.IdNotFoundException;

@Service
public class AttendeService {
	@Autowired
	private AttendeDao attendeDao;
	public ResponseEntity<ResponseStructure<Attende>> saveAttende(Attende attende){
		ResponseStructure<Attende> response=new ResponseStructure<Attende>();
		response.setMessage("Successfully Registered");
		response.setData(attendeDao.saveAttende(attende));
		return new ResponseEntity<ResponseStructure<Attende>>(response, HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<List<Attende>>> getAllAttende(){
		List<Attende> list=attendeDao.getAllAttende();
		ResponseStructure<List<Attende>> response=new ResponseStructure<>();
		if(list.size()>0) {
			response.setMessage("Attende Data are:");
		    response.setData(list);
		    return new ResponseEntity<ResponseStructure<List<Attende>>>(response, HttpStatus.OK);}
		 throw new NoRecordFoundException("No attednde data in database");
	} 
	public ResponseEntity<ResponseStructure<Optional<Attende>>> getAttendeById(Integer id){
		Optional<Attende> attend=attendeDao.getAttendeById(id);
		ResponseStructure<Optional<Attende>> response=new ResponseStructure<Optional<Attende>>();
		if(attend.isPresent()) {
			response.setMessage("Attende Details based on the id: "+id);
		    response.setData(attend);
		    return new ResponseEntity<ResponseStructure<Optional<Attende>>>(response, HttpStatus.OK);}
		throw new NoRecordFoundException("No attednde data in database based on the id: "+id);
	} 
	public ResponseEntity<ResponseStructure<Attende>> updateAttende(Attende attende){
		Optional<Attende> opt=attendeDao.getAttendeById(attende.getId());
		ResponseStructure<Attende> response=new ResponseStructure<Attende>();
		if(opt.isPresent()) 
			response.setMessage("Attende details are update");
			Attende attend=attendeDao.saveAttende(attende);
			response.setData(attend);
			return new ResponseEntity<ResponseStructure<Attende>>(response, HttpStatus.CREATED);	
	}
	public ResponseEntity<ResponseStructure<Optional<Attende>>> deleteAttendeById(Integer id){
		Optional<Attende> opt=attendeDao.getAttendeById(id);
		ResponseStructure<Optional<Attende>> response=new ResponseStructure<Optional<Attende>>();
		if(opt.isPresent()) {
			response.setMessage("Attende details deleted based on the id : "+id +" and their details are given below ") ;
			response.setData(opt);
			attendeDao.DeletedAttende(id);
			return new ResponseEntity<ResponseStructure<Optional<Attende>>>(response, HttpStatus.OK);
		}
		throw new NoRecordFoundException("No id found in the database "+id);
	}
	public ResponseEntity<ResponseStructure<Optional<Attende>>> getAttendeByContact(Long contact){
		Optional<Attende> opt=attendeDao.getByContact(contact);
		ResponseStructure<Optional<Attende>> response=new ResponseStructure<Optional<Attende>>();
		if(opt.isPresent()) {
			response.setMessage("Attende details based on the contact :"+contact);
			response.setData(opt);
			return new ResponseEntity<ResponseStructure<Optional<Attende>>>(response, HttpStatus.OK);
		}
		throw new NoRecordFoundException("NO attednde data in database");
	}
	public ResponseEntity<ResponseStructure<Page<Attende>>> getByAttendePageAndSort(Integer pageno,Integer pagesize,String field){
		Page<Attende> page=attendeDao.getByPageAndSort(pageno, pagesize, field);
		ResponseStructure<Page<Attende>> response=new ResponseStructure<Page<Attende>>();
		if(!page.isEmpty()) {
			response.setMessage("Pages are sorted based on the "+field);
			response.setData(page);
			return new ResponseEntity<ResponseStructure<Page<Attende>>>(response, HttpStatus.OK);
		}
		throw new IdNotFoundException("No data found");	
	}
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByAttendeId(Integer id){
		List<Registration> opt=attendeDao.getRegistrationByAttendeId(id);
		if(opt.size()>0) {
			ResponseStructure<List<Registration>> response=new ResponseStructure<List<Registration>>();
			response.setMessage("Registration details");
			response.setData(opt);
			return new ResponseEntity<ResponseStructure<List<Registration>>>(response, HttpStatus.OK);}
		throw new IdNotFoundException("Id not found in database");
	}
	}

