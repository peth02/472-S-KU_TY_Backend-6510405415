package a4.KU_TY_backend.KU_TY_backend.service;

import a4.KU_TY_backend.KU_TY_backend.entity.Event;
import a4.KU_TY_backend.KU_TY_backend.entity.User;
import a4.KU_TY_backend.KU_TY_backend.exception.NotFoundException;
import a4.KU_TY_backend.KU_TY_backend.exception.SystemException;
import a4.KU_TY_backend.KU_TY_backend.repository.EventRepository;
import a4.KU_TY_backend.KU_TY_backend.repository.UserRepository;
import a4.KU_TY_backend.KU_TY_backend.request.CreateEventRequest;
import a4.KU_TY_backend.KU_TY_backend.request.JoinEventRequest;
import a4.KU_TY_backend.KU_TY_backend.response.EventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    public List<Event> getAllEvent(){
        List<Event> events = eventRepository.findAll();
        return events;
    }
    public Event getEventById(UUID eventId){
        if(eventId == null){
            throw new SystemException("userId must not be null");
        }
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Event not found"));
        return event;
    }
    public Event create(CreateEventRequest request){
       try{
           User user = userRepository.findById(request.getCreatedBy()).orElseThrow(()-> new NotFoundException("User not found"));
           Event event = new Event();

           String name = request.getName();
           String description = request.getDescription();
           String location = request.getLocation();
           LocalDateTime startDate = request.getStartDate();

           event.setCreatedBy(user);
           event.setName(name);
           event.setDescription(description);
           event.setLocation(location);
           event.setStartDate(startDate);
           event.setCreatedAt(LocalDateTime.now());
           event.setUpdatedAt(event.getCreatedAt());
           event = eventRepository.save(event);

           userService.joinEvent(new JoinEventRequest(user.getUserId(), event.getEventId()));
           return event;
       }
       catch (Exception e){
           throw new SystemException(e.getMessage());
       }
    }
}
