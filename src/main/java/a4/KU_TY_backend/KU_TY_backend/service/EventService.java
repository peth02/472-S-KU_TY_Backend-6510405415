package a4.KU_TY_backend.KU_TY_backend.service;

import a4.KU_TY_backend.KU_TY_backend.common.EventStatus;
import a4.KU_TY_backend.KU_TY_backend.entity.Event;
import a4.KU_TY_backend.KU_TY_backend.entity.EventType;
import a4.KU_TY_backend.KU_TY_backend.entity.EventUser;
import a4.KU_TY_backend.KU_TY_backend.entity.User;
import a4.KU_TY_backend.KU_TY_backend.exception.NotFoundException;
import a4.KU_TY_backend.KU_TY_backend.exception.SystemException;
import a4.KU_TY_backend.KU_TY_backend.repository.EventRepository;
import a4.KU_TY_backend.KU_TY_backend.repository.EventTypeRepository;
import a4.KU_TY_backend.KU_TY_backend.repository.UserRepository;
import a4.KU_TY_backend.KU_TY_backend.request.CreateEventRequest;
import a4.KU_TY_backend.KU_TY_backend.request.EditEventRequest;
import a4.KU_TY_backend.KU_TY_backend.request.JoinEventRequest;
import a4.KU_TY_backend.KU_TY_backend.response.EventResponse;
import a4.KU_TY_backend.KU_TY_backend.response.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    @Autowired
    private EventTypeRepository eventTypeRepository;
    public List<EventResponse> getAllEvent(){
        List<Event> eventList = eventRepository.getByStatus(EventStatus.OPEN);
        return eventList.stream().map(Event::toResponse).collect(Collectors.toList());
    }
    public EventResponse getEventById(UUID eventId){
        if(eventId == null){
            throw new SystemException("eventId must not be null");
        }
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Event not found"));
        return event.toResponse();
    }
    public EventResponse create(CreateEventRequest request){
       try{
           User user = userRepository.findById(request.getCreatedBy()).orElseThrow(()-> new NotFoundException("User not found"));
           Event event = new Event();

           String name = request.getName();
           String description = request.getDescription();
           String location = request.getLocation();
           LocalDate startDate = request.getStartDate();
           LocalTime startTime = request.getStartTime();

           int capacity = request.getCapacity();
           String imageUrl = request.getImageUrl();


           event.setCreatedBy(user);
           event.setName(name);
           event.setDescription(description);
           event.setLocation(location);
           if(startDate == null && startTime == null) event.setStartDate(null);
           else if(startDate == null || startTime == null) throw new SystemException("If want start date and time ะน null set both null");
           else event.setStartDate(LocalDateTime.of(startDate, startTime));
           event.setCapacity(capacity);
           event.setImageUrl(imageUrl);
           event.setCreatedAt(LocalDateTime.now());
           event.setUpdatedAt(event.getCreatedAt());
           event = eventRepository.save(event);

           userService.joinEvent(new JoinEventRequest(user.getUserId(), event.getEventId()));
           return event.toResponse();
       }
       catch (Exception e){
           throw new SystemException(e.getMessage());
       }
    }
    public List<UserResponse> getAllJoinedUser(UUID eventId){
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Event not found"));
        List<User> userList = event.getJoinedUserList().stream().map(EventUser::getUser).collect(Collectors.toList());
        return userList.stream().map(User::toResponse).collect(Collectors.toList());
    }
    public EventResponse updateEvent(EditEventRequest request){
        if(request == null) throw  new SystemException("Request must not be null");
        UUID eventId = request.getEventId();
        if(eventId == null) throw new SystemException("User id must not be null");
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new NotFoundException("Event not found"));
        if(request.getCapacity() < event.getAttendeeCount()) throw new SystemException("Capacity must greater or equal attendee count");
        EventType eventType = eventTypeRepository.findByTypeName(request.getTypeName());
        if(eventType == null) throw new NotFoundException("Type not found");
        event.setCapacity(request.getCapacity());
        event.setName(request.getName());
        event.setDescription(request.getDescription());
        event.setLocation(request.getLocation());
        event.setEventType(eventType);
        event.setImageUrl(request.getImageUrl());
        if(request.getStartDate() == null && request.getStartTime() == null) event.setStartDate(null);
        else if(request.getStartDate() == null || request.getStartTime() == null) throw new SystemException("If want start date and time ะน null set both null");
        else event.setStartDate(LocalDateTime.of(request.getStartDate(), request.getStartTime()));
        event.setStatus(request.getStatus());
        return eventRepository.save(event).toResponse();
    }
    public void deleteEvent(UUID eventId){
        if(eventId == null) throw new SystemException("Event id must not be null");
        Event event = eventRepository.findById(eventId).orElseThrow(()->new NotFoundException("Event not found"));
        eventRepository.delete(event);
    }
}
