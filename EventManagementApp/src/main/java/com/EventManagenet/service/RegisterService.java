package com.EventManagenet.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.EventManagenet.Entity.Attende;
import com.EventManagenet.Entity.Event;
import com.EventManagenet.Entity.Registration;
import com.EventManagenet.ExceptionHandling.IdNotFoundException;
import com.EventManagenet.ExceptionHandling.NoRecordFoundException;
import com.EventManagenet.dao.RegisterDao;
import com.EventManagenet.dto.ResponseStructure;
import com.EventManagenet.respository.AttendeRepository;
import com.EventManagenet.respository.EventRepository;
import com.EventManagenet.respository.RegisterRepository;

import jakarta.transaction.Transactional;

@Service
public class RegisterService {
	@Autowired
	private RegisterDao registerDao;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private AttendeRepository attendeRepository;

	@Autowired
	private RegisterRepository registerRepository;

	public ResponseEntity<ResponseStructure<Registration>> saveRegistration(Registration register) {
		Optional<Attende> opt = attendeRepository.findById(register.getAttende().getId());
		Optional<Event> opt1 = eventRepository.findById(register.getEvent().getId());
		if (opt.isPresent() && opt1.isPresent()) {
			register.setEvent(opt1.get());
			register.setAttende(opt.get());
		}
		ResponseStructure<Registration> response = new ResponseStructure<Registration>();
		response.setMessage("Registeration sucessfull");
		response.setData(registerDao.saveRegister(register));
		return new ResponseEntity<ResponseStructure<Registration>>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Registration>>> getAllRegistration() {
		List<Registration> list = registerDao.getAllRegistration();
		ResponseStructure<List<Registration>> response = new ResponseStructure<List<Registration>>();
		if (list.size() > 0) {
			response.setMessage("Registration details :");
			response.setData(list);
			return new ResponseEntity<ResponseStructure<List<Registration>>>(response, HttpStatus.OK);
		}
		throw new NoRecordFoundException("No details about the registration");
	}

	public ResponseEntity<ResponseStructure<Optional<Registration>>> getRegistrationById(Integer id) {
		Optional<Registration> opt = registerDao.getRegistrationById(id);
		ResponseStructure<Optional<Registration>> response = new ResponseStructure<Optional<Registration>>();
		if (opt.isPresent()) {
			response.setMessage("Registration details based on the id: " + id);
			response.setData(opt);
			return new ResponseEntity<ResponseStructure<Optional<Registration>>>(response, HttpStatus.FOUND);
		}
		throw new IdNotFoundException("No data found in the database based on the id: " + id);
	}

	public ResponseEntity<ResponseStructure<String>> deleteAttendeeRegistration(Integer id) {

		Optional<Registration> opt = registerRepository.findById(id);
		ResponseStructure<String> response = new ResponseStructure<String>();
		if (opt.isPresent()) {
			registerDao.deletedRegistration(opt.get());
			response.setMessage("Attendee registration deleted for ID: " + id + ". Details below.");
			response.setData("Deleted sucessfully");
			return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
		} else
			throw new IdNotFoundException("No registration found for ID: " + id);
	}

	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByEventId(Integer id) {
		List<Registration> opt = registerDao.getRegistrationByEventId(id);
		if (opt.size() > 0) {
			ResponseStructure<List<Registration>> response = new ResponseStructure<List<Registration>>();
			response.setMessage("Registration detail based on the event id:" + id);
			response.setData(opt);
			return new ResponseEntity<ResponseStructure<List<Registration>>>(response, HttpStatus.OK);
		}
		throw new IdNotFoundException("Registration  not done by the event Id: " + id);
	}

	public ResponseEntity<ResponseStructure<Optional<Registration>>> getRegistrationByAttendeId(Integer id) {
		Optional<Registration> opt = registerDao.getRegistrationByAttendeId(id);
		if (opt.isPresent()) {
			ResponseStructure<Optional<Registration>> response = new ResponseStructure<Optional<Registration>>();
			response.setMessage("Registration detail based on the attende id:" + id);
			response.setData(opt);
			return new ResponseEntity<ResponseStructure<Optional<Registration>>>(response, HttpStatus.OK);
		}
		throw new IdNotFoundException("Registration  not done by the attende Id: " + id);
	}
}
