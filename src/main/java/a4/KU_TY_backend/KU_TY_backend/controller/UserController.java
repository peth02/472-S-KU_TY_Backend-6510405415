package a4.KU_TY_backend.KU_TY_backend.controller;

import a4.KU_TY_backend.KU_TY_backend.entity.User;
import a4.KU_TY_backend.KU_TY_backend.request.UpdateUserDescriptionRequest;
import a4.KU_TY_backend.KU_TY_backend.response.ResponseHandler;
import a4.KU_TY_backend.KU_TY_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @GetMapping("/user")
    public ResponseEntity<Object> getAllUser(){
        return ResponseHandler.responseBuilder("Get all user successful", HttpStatus.OK, service.getAllUser());
    }
    @PutMapping("/user/update/description")
    public ResponseEntity<Object> updateUserDescription(@RequestBody UpdateUserDescriptionRequest request){
        return ResponseHandler.responseBuilder("update user description successful", HttpStatus.OK, service.updateDescription(request));
    }
}
