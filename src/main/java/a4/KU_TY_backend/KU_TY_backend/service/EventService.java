package a4.KU_TY_backend.KU_TY_backend.service;

import a4.KU_TY_backend.KU_TY_backend.entity.Event;
import a4.KU_TY_backend.KU_TY_backend.entity.User;
import a4.KU_TY_backend.KU_TY_backend.exception.CreateEventException;
import a4.KU_TY_backend.KU_TY_backend.repository.EventRepository;
import a4.KU_TY_backend.KU_TY_backend.request.CreateEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;
    public Event create(CreateEventRequest request){
       try{
           Event event = request.getEvent();

           event.setCreatedAt(LocalDateTime.now());
           event.setUpdatedAt(event.getCreatedAt());
           return repository.save(event);
       }
       catch (Exception e){
           throw new CreateEventException(e.getMessage());
       }
    }
}
