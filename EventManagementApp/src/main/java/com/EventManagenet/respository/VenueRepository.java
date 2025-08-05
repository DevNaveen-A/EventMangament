package com.EventManagenet.respository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.EventManagenet.Entity.Event;
import com.EventManagenet.Entity.Venue;

public interface VenueRepository extends JpaRepository<Venue, Integer> {
@Query("select v from Venue v where v.location=?1")
public List<Venue> getByVenueLocation(String location);

@Query("select v.events from Venue v where v.id=?1")
public List<Event> getByEventsFromVenue(Integer id);
}