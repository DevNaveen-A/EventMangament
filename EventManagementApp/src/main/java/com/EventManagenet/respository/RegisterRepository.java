package com.EventManagenet.respository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.EventManagenet.Entity.Registration;

public interface RegisterRepository extends JpaRepository<Registration, Integer> {
	@Query("SELECT r FROM Registration r WHERE r.event.id = ?1")
	List<Registration> getRegistrationByEventId(Integer id);  
	@Query("SELECT r FROM Registration r WHERE r.attende.id = ?1")
	Optional<Registration> getRegistrationByAttendeId(Integer id);
}
