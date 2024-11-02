package a4.KU_TY_backend.KU_TY_backend.service;

import a4.KU_TY_backend.KU_TY_backend.entity.Event;
import a4.KU_TY_backend.KU_TY_backend.entity.EventUser;
import a4.KU_TY_backend.KU_TY_backend.entity.EventUserKey;
import a4.KU_TY_backend.KU_TY_backend.entity.User;
import a4.KU_TY_backend.KU_TY_backend.exception.NotFoundException;
import a4.KU_TY_backend.KU_TY_backend.exception.SystemException;
import a4.KU_TY_backend.KU_TY_backend.repository.EventRepository;
import a4.KU_TY_backend.KU_TY_backend.repository.EventUserRepository;
import a4.KU_TY_backend.KU_TY_backend.repository.UserRepository;
import a4.KU_TY_backend.KU_TY_backend.request.JoinEventRequest;
import a4.KU_TY_backend.KU_TY_backend.request.UpdateUserDescriptionRequest;
import a4.KU_TY_backend.KU_TY_backend.request.UpdateUserEmailRequest;
import a4.KU_TY_backend.KU_TY_backend.request.UpdateUserNameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventUserRepository eventUserRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public User getUserById(UUID userId){
        if(userId == null){
            throw new SystemException("userId must not be null");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return user;
    }
    public User getUserByUsername(String username){
        if(username == null) throw new SystemException("username must not be null");
        User user = userRepository.findByUsername(username);
        if(user == null) throw new NotFoundException("User not found");
        return user;
    }
    public User updateDescription(UpdateUserDescriptionRequest request){
        if(request.getUserId() == null){
            throw new SystemException("userId must not be null");
        }
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        user.setDescription(request.getDescription());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }
    public User updateEmail(UpdateUserEmailRequest request){
        if(request.getUserId() == null){
            throw new SystemException("userId must not be null");
        }
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }
    public User updateName(UpdateUserNameRequest request){
        if(request.getUserId() == null){
            throw new SystemException("userId must not be null");
        }
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        return userRepository.save(user);
    }
    public Event joinEvent(JoinEventRequest request){
        UUID eventId = request.getEventId();
        UUID userId = request.getUserId();

        if(eventId == null || userId == null) throw new NotFoundException("User id and event id must not be null");
        Event event =  eventRepository.findById(eventId).orElseThrow(()-> new NotFoundException("Event not found"));
        User user =  userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found"));

        EventUserKey eventUserKey = new EventUserKey(eventId, userId);

        EventUser eventUser = new EventUser();
        eventUser.setKey(eventUserKey);
        eventUser.setEvent(event);
        eventUser.setUser(user);

        eventUserRepository.save(eventUser);

        return event;
    }
}
