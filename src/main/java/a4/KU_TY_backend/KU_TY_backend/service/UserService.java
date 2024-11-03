package a4.KU_TY_backend.KU_TY_backend.service;

import a4.KU_TY_backend.KU_TY_backend.entity.Event;
import a4.KU_TY_backend.KU_TY_backend.entity.EventUser;
import a4.KU_TY_backend.KU_TY_backend.entity.EventUserKey;
import a4.KU_TY_backend.KU_TY_backend.entity.User;
import a4.KU_TY_backend.KU_TY_backend.exception.ConflictException;
import a4.KU_TY_backend.KU_TY_backend.exception.NotFoundException;
import a4.KU_TY_backend.KU_TY_backend.exception.SystemException;
import a4.KU_TY_backend.KU_TY_backend.repository.EventRepository;
import a4.KU_TY_backend.KU_TY_backend.repository.EventUserRepository;
import a4.KU_TY_backend.KU_TY_backend.repository.UserRepository;
import a4.KU_TY_backend.KU_TY_backend.request.*;
import a4.KU_TY_backend.KU_TY_backend.response.EventResponse;
import a4.KU_TY_backend.KU_TY_backend.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventUserRepository eventUserRepository;
    public List<UserResponse> getAllUser(){
        return userRepository.findAll().stream().map(User::toResponse).collect(Collectors.toList());
    }
    public UserResponse getUserById(UUID userId){
        if(userId == null){
            throw new SystemException("userId must not be null");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return user.toResponse();
    }
    public UserResponse getUserByUsername(String username){
        if(username == null) throw new SystemException("username must not be null");
        User user = userRepository.findByUsername(username);
        if(user == null) throw new NotFoundException("User not found");
        return user.toResponse();
    }
    public UserResponse updateDescription(UpdateUserDescriptionRequest request){
        if(request.getUserId() == null){
            throw new SystemException("userId must not be null");
        }
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        user.setDescription(request.getDescription());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user).toResponse();
    }
    public UserResponse updateEmail(UpdateUserEmailRequest request){
        if(request.getUserId() == null){
            throw new SystemException("userId must not be null");
        }
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        user.setEmail(request.getEmail());
        return userRepository.save(user).toResponse();
    }
    public UserResponse updateName(UpdateUserNameRequest request){
        if(request.getUserId() == null){
            throw new SystemException("userId must not be null");
        }
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        return userRepository.save(user).toResponse();
    }
    public EventResponse joinEvent(JoinEventRequest request){
        UUID eventId = request.getEventId();
        UUID userId = request.getUserId();

        if(eventId == null || userId == null) throw new NotFoundException("User id and event id must not be null");
        Event event =  eventRepository.findById(eventId).orElseThrow(()-> new NotFoundException("Event not found"));
        User user =  userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found"));

        EventUserKey eventUserKey = new EventUserKey(eventId, userId);
        if(eventUserRepository.findById(eventUserKey).isPresent()) throw new ConflictException("User has already joined this event");

        event.setAttendeeCount(event.getAttendeeCount() + 1);

        EventUser eventUser = new EventUser();
        eventUser.setKey(eventUserKey);
        eventUser.setEvent(event);
        eventUser.setUser(user);

        eventUserRepository.save(eventUser);

        return event.toResponse();
    }
    public List<EventResponse> getAllJoinedEvent(UUID userId){
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("User not found"));
        return user.getJoinedEventList().stream().map(EventUser::getEvent).map(Event::toResponse).collect(Collectors.toList());
    }
    public UserResponse updateUser(UpdateUserRequest request){
        if(request == null) throw  new SystemException("Request must not be null");
        UUID userId = request.getUserId();
        if(userId == null) throw new SystemException("User id must not be null");
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setDescription(request.getDescription());
        return userRepository.save(user).toResponse();
    }
    public List<EventResponse> getAllCreatedEvent(UUID userId){
        if(userId == null) throw new SystemException("user Id must not be null");
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        if(user == null) throw new NotFoundException("User not found");
        return user.getCreatedEventList().stream().map(Event::toResponse).collect(Collectors.toList());
    }
}
