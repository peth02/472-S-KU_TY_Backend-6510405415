package a4.KU_TY_backend.KU_TY_backend.controller;
import a4.KU_TY_backend.KU_TY_backend.request.CreateEventRequest;
import a4.KU_TY_backend.KU_TY_backend.request.EditEventImageRequest;
import a4.KU_TY_backend.KU_TY_backend.request.EditEventRequest;
import a4.KU_TY_backend.KU_TY_backend.response.ResponseHandler;
import a4.KU_TY_backend.KU_TY_backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class EventController {
    @Autowired
    private EventService service;
    @GetMapping("/event")
    public ResponseEntity<Object> getAllEvent(){
        return ResponseHandler.responseBuilder("Get all event success", HttpStatus.OK, service.getAllEvent());
    }
    @GetMapping("/event/eventId/{eventId}")
    public ResponseEntity<Object> getEventById(@PathVariable("eventId") UUID eventId){
        return ResponseHandler.responseBuilder("Get event success", HttpStatus.OK, service.getEventById(eventId));
    }
    @GetMapping("/event/{eventId}/joined/user")
    public ResponseEntity<Object> getAllJoinedUser(@PathVariable("eventId") UUID eventId){
        return ResponseHandler.responseBuilder("Get joined user success", HttpStatus.OK, service.getAllJoinedUser(eventId));
    }
    @PutMapping("/event")
    public ResponseEntity<Object> editEvent(@RequestBody EditEventRequest request){
        return ResponseHandler.responseBuilder("Edit event success", HttpStatus.OK, service.updateEvent(request));
    }
    @PutMapping("/event/photo")
    public ResponseEntity<Object> editImageUrl(@RequestBody EditEventImageRequest request){
        return ResponseHandler.responseBuilder("Edit event image success", HttpStatus.OK, service.updateImageUrl(request));
    }
    @PostMapping("/event")
    public ResponseEntity<Object> create(@RequestBody CreateEventRequest request){
        return ResponseHandler.responseBuilder("Create event success", HttpStatus.OK, service.create(request));
    }
    @DeleteMapping("/event/{eventId}")
    public ResponseEntity<Object> deleteEvent(@PathVariable UUID eventId){
        service.deleteEvent(eventId);
        return ResponseHandler.responseBuilder("Delete event success", HttpStatus.OK, null);
    }
}
