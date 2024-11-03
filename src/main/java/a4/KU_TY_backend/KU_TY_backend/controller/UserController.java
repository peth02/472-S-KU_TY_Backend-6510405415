package a4.KU_TY_backend.KU_TY_backend.controller;

import a4.KU_TY_backend.KU_TY_backend.entity.User;
import a4.KU_TY_backend.KU_TY_backend.request.*;
import a4.KU_TY_backend.KU_TY_backend.response.ResponseHandler;
import a4.KU_TY_backend.KU_TY_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @GetMapping("/user")
    public ResponseEntity<Object> getAllUser(){
        return ResponseHandler.responseBuilder("Get all user success", HttpStatus.OK, service.getAllUser());
    }
    @GetMapping("/user/userId/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") UUID userId){
        return ResponseHandler.responseBuilder("Get user success", HttpStatus.OK, service.getUserById(userId));
    }
    @GetMapping("/user/username/{username}")
    public ResponseEntity<Object> getUserById(@PathVariable("username") String username){
        return ResponseHandler.responseBuilder("Get user success", HttpStatus.OK, service.getUserByUsername(username));
    }
    @GetMapping("/user/userId/{userId}/joined/event")
    public ResponseEntity<Object> getAllJoinedEvent(@PathVariable UUID userId){
        return ResponseHandler.responseBuilder("Get joined event success", HttpStatus.OK, service.getAllJoinedEvent(userId));
    }
    @GetMapping("/user/userId/{userId}/created/event")
    public ResponseEntity<Object> getAllCreatedEvent(@PathVariable UUID userId){
        return ResponseHandler.responseBuilder("Get created event success", HttpStatus.OK, service.getAllCreatedEvent(userId));
    }
    @PutMapping("/user/update/description")
    public ResponseEntity<Object> updateUserDescription(@RequestBody UpdateUserDescriptionRequest request){
        return ResponseHandler.responseBuilder("Update user description success", HttpStatus.OK, service.updateDescription(request));
    }
    @PutMapping("/user/update/email")
    public ResponseEntity<Object> updateUserEmail(@RequestBody UpdateUserEmailRequest request){
        return ResponseHandler.responseBuilder("Update user email success", HttpStatus.OK, service.updateEmail(request));
    }
    @PutMapping("/user/update/name")
    public ResponseEntity<Object> updateUserName(@RequestBody UpdateUserNameRequest request){
        return ResponseHandler.responseBuilder("Update user name success", HttpStatus.OK, service.updateName(request));
    }
    @PutMapping("/user")
    public ResponseEntity<Object> updateUser(@RequestBody UpdateUserRequest request){
        return ResponseHandler.responseBuilder("Update user success", HttpStatus.OK, service.updateUser(request));
    }
    @PostMapping("/user/join/event")
    public ResponseEntity<Object> joinEvent(@RequestBody JoinEventRequest request){
        return ResponseHandler.responseBuilder("Join event success", HttpStatus.OK, service.joinEvent(request));
    }
}
