package a4.KU_TY_backend.KU_TY_backend.controller;

import a4.KU_TY_backend.KU_TY_backend.entity.User;
import a4.KU_TY_backend.KU_TY_backend.request.UpdateUserDescriptionRequest;
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
    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") UUID userId){
        return ResponseHandler.responseBuilder("Get user success", HttpStatus.OK, service.getUserById(userId));
    }
    @PutMapping("/user/update/description")
    public ResponseEntity<Object> updateUserDescription(@RequestBody UpdateUserDescriptionRequest request){
        return ResponseHandler.responseBuilder("Update user description success", HttpStatus.OK, service.updateDescription(request));
    }
}
