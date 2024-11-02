package a4.KU_TY_backend.KU_TY_backend.service;

import a4.KU_TY_backend.KU_TY_backend.entity.User;
import a4.KU_TY_backend.KU_TY_backend.exception.SystemException;
import a4.KU_TY_backend.KU_TY_backend.exception.UserNotFoundException;
import a4.KU_TY_backend.KU_TY_backend.repository.UserRepository;
import a4.KU_TY_backend.KU_TY_backend.request.UpdateUserDescriptionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public List<User> getAllUser(){
        return repository.findAll();
    }
    public User getUserById(UUID userId){
        if(userId == null){
            throw new SystemException("userId must not be null");
        }
        User user = repository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return user;
    }
    public User updateDescription(UpdateUserDescriptionRequest request){
        if(request.getUserId() == null){
            throw new SystemException("userId must not be null");
        }
        User user = repository.findById(request.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setDescription(request.getDescription());
        return repository.save(user);
    }
}
