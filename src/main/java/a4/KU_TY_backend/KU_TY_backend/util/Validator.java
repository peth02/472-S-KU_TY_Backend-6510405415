package a4.KU_TY_backend.KU_TY_backend.util;

import a4.KU_TY_backend.KU_TY_backend.entity.User;
import a4.KU_TY_backend.KU_TY_backend.exception.NotFoundException;
import a4.KU_TY_backend.KU_TY_backend.exception.SystemException;
import a4.KU_TY_backend.KU_TY_backend.repository.EventRepository;
import a4.KU_TY_backend.KU_TY_backend.repository.UserRepository;
import a4.KU_TY_backend.KU_TY_backend.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class Validator {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    public void userIdValidate(UUID userId){
        if(userId == null) throw new SystemException("User id must not be null");
        userRepository.findById(userId).orElseThrow(()->new NotFoundException("User not found"));
    }
    public void eventIdValidate(UUID eventId){
        if(eventId == null) throw new SystemException("Event id must not be null");
        eventRepository.findById(eventId).orElseThrow(()->new NotFoundException("Event not found"));
    }
}
