package a4.KU_TY_backend.KU_TY_backend.controller;

import a4.KU_TY_backend.KU_TY_backend.entity.Event;
import a4.KU_TY_backend.KU_TY_backend.entity.User;
import a4.KU_TY_backend.KU_TY_backend.request.CreateEventRequest;
import a4.KU_TY_backend.KU_TY_backend.request.LoginRequest;
import a4.KU_TY_backend.KU_TY_backend.response.ResponseHandler;
import a4.KU_TY_backend.KU_TY_backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    @Autowired
    private EventService service;
    @PostMapping("/event")
    public ResponseEntity<Object> create(@RequestBody CreateEventRequest request){
        Event event = service.create(request);
        return ResponseHandler.responseBuilder("Create event successful", HttpStatus.OK, event);
    }
}
