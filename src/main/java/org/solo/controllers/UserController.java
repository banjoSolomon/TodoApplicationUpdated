package org.solo.controllers;

import org.solo.dto.LoginRequest;
import org.solo.dto.RegisterRequest;
import org.solo.dto.TaskRequest;
import org.solo.response.ApiResponse;
import org.solo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")

    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request){
        try{
            var result = userService.register(request);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request){
        try{
            var result = userService.login(request);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/create-Task")
    public ResponseEntity<?> createTask(@RequestBody TaskRequest request){
        try{
            var result = userService.createTask(request);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}
