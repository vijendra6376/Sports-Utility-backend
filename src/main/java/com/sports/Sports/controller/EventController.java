package com.sports.Sports.controller;

import com.sports.Sports.Service.EventService;
import com.sports.Sports.model.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * GET /events
     * Accessible by USER and ADMIN: list all events.
     */
    @GetMapping
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Events>> getAllEvents() {
        List<Events> list = eventService.getAllEvents();
        return ResponseEntity.ok(list);
    }

    /**
     * GET /events/{id}
     * Accessible by USER and ADMIN: view a single event by ID.
     */
    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        Optional<Events> opt = eventService.getEventsById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        } else {
            return ResponseEntity.status(404).body("Event not found with id: " + id);
        }
    }

    /**
     * POST /events
     * Accessible only by ADMIN: create a new event.
     */
    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Events> createEvent(@RequestBody Events events) {
        Events saved = eventService.saveEvents(events);
        return ResponseEntity.status(201).body(saved);
    }

    /**
     * PUT /events/{id}
     * Accessible only by ADMIN: update existing event or create if not exists.
     */
    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateEvent(
            @PathVariable Long id,
            @RequestBody Events updatedEvents) {
        try {
            Events saved = eventService.updateEvent(id, updatedEvents);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to update event: " + e.getMessage());
        }
    }

    /**
     * DELETE /events/{id}
     * Accessible only by ADMIN: delete event by ID.
     */
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        Optional<Events> opt = eventService.getEventsById(id);
        if (opt.isPresent()) {
            eventService.deleteEvent(id);
            return ResponseEntity.ok("Deleted event with id: " + id);
        } else {
            return ResponseEntity.status(404).body("Event not found with id: " + id);
        }
    }
}
