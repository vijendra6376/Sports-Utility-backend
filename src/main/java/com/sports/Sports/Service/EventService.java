package com.sports.Sports.Service;

import com.sports.Sports.model.Events;

import java.util.List;
import java.util.Optional;

public interface EventService {
List<Events> getAllEvents();
Optional<Events> getEventsById(long id);
Events saveEvents(Events events);
Events updateEvent(Long id , Events updatedEvents);
void deleteEvent(Long id);

}
