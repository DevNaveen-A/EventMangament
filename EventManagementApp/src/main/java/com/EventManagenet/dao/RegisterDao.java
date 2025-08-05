package com.EventManagenet.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.EventManagenet.Entity.Registration;
import com.EventManagenet.respository.RegisterRepository;

@Repository
public class RegisterDao {
	@Autowired
	private RegisterRepository registerRepository;

	public Registration saveRegister(Registration registration) {
		return registerRepository.save(registration);
	}

	public List<Registration> getAllRegistration() {
		return registerRepository.findAll();
	}

	public Optional<Registration> getRegistrationById(Integer id) {
		return registerRepository.findById(id);
	}

	public void deletedRegistration(Registration registration) {
		registerRepository.delete(registration);
	}

	public List<Registration> getRegistrationByEventId(Integer id) {
		return registerRepository.getRegistrationByEventId(id);
	}

	public Optional<Registration> getRegistrationByAttendeId(Integer id) {
		return registerRepository.getRegistrationByAttendeId(id);
	}
}
