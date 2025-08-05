package com.EventManagenet.respository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.EventManagenet.Entity.Attende;
import com.EventManagenet.Entity.Registration;
@Repository
public interface AttendeRepository  extends JpaRepository<Attende, Integer>{
@Query("select a from Attende a where a.contact=?1")
public Optional<Attende> getByContact(Long contact);
@Query("select a.registrations from Attende a where a.id=?1")
public List<Registration> getByRegistration(Integer id);
}

