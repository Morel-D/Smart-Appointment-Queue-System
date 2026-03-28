package com.example.queue_flow.project.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.queue_flow.project.dto.CreateUserDTO;
import com.example.queue_flow.project.mapper.UserResponse;
import com.example.queue_flow.project.model.ApiResponse;
import com.example.queue_flow.project.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return new ApiResponse<>(
            true, 
            users, 
            "done",
            null,
            LocalDateTime.now()
        );
    }

    @PostMapping
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody CreateUserDTO dto) {
        UserResponse result = userService.createUser(dto);

        return new ApiResponse<UserResponse>(
            true, 
            result, 
            "done", 
            null,  
            LocalDateTime.now()
        );
    }

}
