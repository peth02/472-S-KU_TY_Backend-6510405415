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
import a4.KU_TY_backend.KU_TY_backend.request.EditEventImageRequest;
import a4.KU_TY_backend.KU_TY_backend.request.EditEventRequest;
import a4.KU_TY_backend.KU_TY_backend.request.JoinEventRequest;
import a4.KU_TY_backend.KU_TY_backend.response.EventResponse;
import a4.KU_TY_backend.KU_TY_backend.response.UserResponse;

import a4.KU_TY_backend.KU_TY_backend.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private EventTypeRepository eventTypeRepository;
    @Autowired
    private Validator validator;
    public List<EventResponse> getAllEvent(){
        List<Event> eventList = eventRepository.getByStatus(EventStatus.OPEN);
        return eventList.stream().map(Event::toResponse).collect(Collectors.toList());
    }
    public EventResponse getEventById(UUID eventId){
        validator.eventIdValidate(eventId);
        Event event = eventRepository.findById(eventId).get();
        return event.toResponse();
    }
    public EventResponse create(CreateEventRequest request){
       try{
           validator.userIdValidate(request.getCreatedBy());
           User user = userRepository.findById(request.getCreatedBy()).get();
           Event event = new Event();

           String name = request.getName();
           String description = request.getDescription();
           String location = request.getLocation();
           LocalDate startDate = request.getStartDate();
           LocalTime startTime = request.getStartTime();
           String typeName = request.getTypeName();
           int capacity = request.getCapacity();


           event.setCreatedBy(user);
           event.setName(name);
           event.setDescription(description);
           event.setLocation(location);
           if(startDate == null && startTime == null) event.setStartDate(null);
           else if(startDate == null || startTime == null) throw new SystemException("If want start date and time to null.Please set both null");
           else event.setStartDate(LocalDateTime.of(startDate, startTime));
           event.setCapacity(capacity);
           event.setCreatedAt(LocalDateTime.now());
           event.setUpdatedAt(event.getCreatedAt());
           event.setEventType(getEventType(typeName));
           event = eventRepository.save(event);
           return event.toResponse();
       }
       catch (Exception e){
           throw new SystemException(e.getMessage());
       }
    }
    public List<UserResponse> getAllJoinedUser(UUID eventId){
        validator.eventIdValidate(eventId);
        Event event = eventRepository.findById(eventId).get();
        List<User> userList = event.getJoinedUserList().stream().map(EventUser::getUser).toList();
        return userList.stream().map(User::toResponse).collect(Collectors.toList());
    }
    private EventType getEventType(String typeName){
        if(typeName == null) return null;
        else{
            EventType eventType = eventTypeRepository.findByTypeName(typeName);
            if(eventType == null) {
                eventType = new EventType();
                eventType.setTypeName(typeName);
                eventType = eventTypeRepository.save(eventType);
            }
            return eventType;
        }
    }
    public EventResponse updateEvent(EditEventRequest request){
        UUID eventId = request.getEventId();
        validator.eventIdValidate(eventId);
        Event event = eventRepository.findById(eventId).get();
        if(request.getCapacity() < event.getAttendeeCount()) throw new SystemException("Capacity must greater or equal attendee count");
        String typeName = request.getTypeName();

        event.setEventType(getEventType(typeName));
        event.setCapacity(request.getCapacity());
        event.setName(request.getName());
        event.setDescription(request.getDescription());
        event.setLocation(request.getLocation());
        if(request.getStartDate() == null && request.getStartTime() == null) event.setStartDate(null);
        else if(request.getStartDate() == null || request.getStartTime() == null) throw new SystemException("If want start date and time ะน null set both null");
        else event.setStartDate(LocalDateTime.of(request.getStartDate(), request.getStartTime()));
        event.setStatus(request.getStatus());
        return eventRepository.save(event).toResponse();
    }
    public void deleteEvent(UUID eventId){
        validator.eventIdValidate(eventId);
        Event event = eventRepository.findById(eventId).get();
        eventRepository.delete(event);
    }
    public EventResponse updateImage(UUID eventId, String imageUrl){;
        validator.eventIdValidate(eventId);
        Event event = eventRepository.findById(eventId).get();
        event.setImageUrl(imageUrl);
        return eventRepository.save(event).toResponse();
    }
}
