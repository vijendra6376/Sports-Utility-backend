package com.sports.Sports.Service;

import com.sports.Sports.Repository.EventRepository;
import com.sports.Sports.model.Events;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EventServiceImp implements EventService{
     private final EventRepository eventRepository;

    public EventServiceImp(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Events> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Events> getEventsById(long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Events saveEvents(Events events) {
        return eventRepository.save(events);
    }

    @Override
    public Events updateEvent(Long id, Events updatedEvents) {
        return eventRepository.findById(id)
                .map(existingEvent -> {
                    existingEvent.setName(updatedEvents.getName());
                    existingEvent.setMatchTitle(updatedEvents.getMatchTitle());
                    existingEvent.setMatchDate(updatedEvents.getMatchDate());
                    existingEvent.setVenue(updatedEvents.getVenue());
                    existingEvent.setStatus(updatedEvents.getStatus());
                    return eventRepository.save(existingEvent);
                })
                .orElseGet(() -> {
                    updatedEvents.setId(id);
                    return eventRepository.save(updatedEvents);
                });

    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
