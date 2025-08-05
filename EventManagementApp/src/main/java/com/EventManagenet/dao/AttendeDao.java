package com.EventManagenet.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.EventManagenet.Entity.Attende;
import com.EventManagenet.Entity.Registration;
import com.EventManagenet.respository.AttendeRepository;
import com.EventManagenet.respository.RegisterRepository;
@Repository
public class AttendeDao {
@Autowired
private AttendeRepository attendeRepository;
@SuppressWarnings("unused")
@Autowired
private RegisterRepository registerRepository;
public Attende saveAttende(Attende attende) {
	return attendeRepository.save(attende);
}
public List<Attende> getAllAttende(){
	return attendeRepository.findAll();
}
public Optional<Attende> getAttendeById(Integer id){
	return attendeRepository.findById(id);
}
public Attende updateAttende(Attende attende) {
	return attendeRepository.save(attende);
}
public void DeletedAttende(Integer id) {
	attendeRepository.deleteById(id);
	
}
public Optional<Attende> getByContact(Long cotact){
	return attendeRepository.getByContact(cotact);
}
public Page<Attende> getByPageAndSort(Integer pageno,Integer pagesize,String field){
	return attendeRepository.findAll(PageRequest.of(pageno, pagesize,Sort.by(field).ascending()));
}
public List<Registration>  getRegistrationByAttendeId(Integer id){
	return attendeRepository.getByRegistration(id);
}
}
