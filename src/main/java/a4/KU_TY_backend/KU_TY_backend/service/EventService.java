package a4.KU_TY_backend.KU_TY_backend.service;

import a4.KU_TY_backend.KU_TY_backend.entity.Event;
import a4.KU_TY_backend.KU_TY_backend.entity.User;
import a4.KU_TY_backend.KU_TY_backend.exception.CreateEventException;
import a4.KU_TY_backend.KU_TY_backend.exception.UserNotFoundException;
import a4.KU_TY_backend.KU_TY_backend.repository.EventRepository;
import a4.KU_TY_backend.KU_TY_backend.repository.UserRepository;
import a4.KU_TY_backend.KU_TY_backend.request.CreateEventRequest;
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
    public List<EventResponse> getAllEvent(){
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    public EventResponse create(CreateEventRequest request){
       try{
           User user = userRepository.findById(request.getCreatedBy()).orElseThrow(()-> new UserNotFoundException("User not found"));
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
           return convertToResponse(event);
       }
       catch (Exception e){
           throw new CreateEventException(e.getMessage());
       }
    }
    private EventResponse convertToResponse(Event event) {
        EventResponse dto = new EventResponse();
        dto.setEventId(event.getEventId());
        dto.setCreatedBy(event.getCreatedBy().getUserId());  // แปลง User เป็น UUID
        dto.setName(event.getName());
        dto.setDescription(event.getDescription());
        dto.setCreatedAt(event.getCreatedAt());
        dto.setUpdatedAt(event.getUpdatedAt());
        dto.setStatus(event.getStatus());
        dto.setStartDate(event.getStartDate());
        dto.setLocation(event.getLocation());
        return dto;
    }
}
