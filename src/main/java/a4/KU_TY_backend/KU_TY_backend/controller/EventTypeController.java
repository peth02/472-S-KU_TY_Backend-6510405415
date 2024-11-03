package a4.KU_TY_backend.KU_TY_backend.controller;

import a4.KU_TY_backend.KU_TY_backend.entity.EventType;
import a4.KU_TY_backend.KU_TY_backend.request.CreateEventTypeRequest;
import a4.KU_TY_backend.KU_TY_backend.response.ResponseHandler;
import a4.KU_TY_backend.KU_TY_backend.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventTypeController {
    @Autowired
    private EventTypeService service;
    @PostMapping("/event-type")
    public ResponseEntity<Object> create(@RequestBody CreateEventTypeRequest request){
        EventType eventType = service.createEventType(request);
        return ResponseHandler.responseBuilder("Create type successful", HttpStatus.OK, eventType);
    }
}
