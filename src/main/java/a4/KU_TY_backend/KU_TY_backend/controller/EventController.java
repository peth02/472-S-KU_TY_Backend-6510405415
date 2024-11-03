package a4.KU_TY_backend.KU_TY_backend.controller;
import a4.KU_TY_backend.KU_TY_backend.exception.SystemException;
import a4.KU_TY_backend.KU_TY_backend.request.CreateEventRequest;
import a4.KU_TY_backend.KU_TY_backend.request.EditEventImageRequest;
import a4.KU_TY_backend.KU_TY_backend.request.EditEventRequest;
import a4.KU_TY_backend.KU_TY_backend.response.ResponseHandler;
import a4.KU_TY_backend.KU_TY_backend.service.CloudinaryService;
import a4.KU_TY_backend.KU_TY_backend.service.EventService;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private CloudinaryService cloudinaryService;
    @GetMapping("/event")
    public ResponseEntity<Object> getAllEvent(){
        return ResponseHandler.responseBuilder("Get all event success", HttpStatus.OK, eventService.getAllEvent());
    }
    @GetMapping("/event/eventId/{eventId}")
    public ResponseEntity<Object> getEventById(@PathVariable("eventId") UUID eventId){
        return ResponseHandler.responseBuilder("Get event success", HttpStatus.OK, eventService.getEventById(eventId));
    }
    @GetMapping("/event/{eventId}/joined/user")
    public ResponseEntity<Object> getAllJoinedUser(@PathVariable("eventId") UUID eventId){
        return ResponseHandler.responseBuilder("Get joined user success", HttpStatus.OK, eventService.getAllJoinedUser(eventId));
    }
    @PutMapping("/event")
    public ResponseEntity<Object> editEvent(@RequestBody EditEventRequest request){
        return ResponseHandler.responseBuilder("Edit event success", HttpStatus.OK, eventService.updateEvent(request));
    }
    @PutMapping("/event/{eventId}/image")
    public ResponseEntity<Object> editImageUrl(@PathVariable UUID eventId, @RequestParam("image") MultipartFile imageFile){
        try {
            String imageUrl = cloudinaryService.uploadImage(imageFile);
            return ResponseHandler.responseBuilder("Edit event image success", HttpStatus.OK, eventService.updateImage(eventId, imageUrl));
        } catch (IOException e) {
            throw new SystemException(e.getMessage());
        }
    }
    @PostMapping("/event")
    public ResponseEntity<Object> create(@RequestBody CreateEventRequest request){
        return ResponseHandler.responseBuilder("Create event success", HttpStatus.OK, eventService.create(request));
    }
    @DeleteMapping("/event/{eventId}")
    public ResponseEntity<Object> deleteEvent(@PathVariable UUID eventId){
        eventService.deleteEvent(eventId);
        return ResponseHandler.responseBuilder("Delete event success", HttpStatus.OK, null);
    }
}
