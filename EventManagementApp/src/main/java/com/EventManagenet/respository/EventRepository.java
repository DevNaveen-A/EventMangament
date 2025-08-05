package com.EventManagenet.respository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.EventManagenet.Entity.Attende;
import com.EventManagenet.Entity.Event;
public interface EventRepository extends JpaRepository<Event, Integer>  {
@Query("select e from Event e where e.organizer.id=?1")
public List<Event> getEventByOrganizerId(Integer id);
@Query("SELECT r.attende FROM Event e JOIN e.registrations r WHERE e.id = ?1")
 public List<Attende> getAttendesByEventId(Integer id);
}
