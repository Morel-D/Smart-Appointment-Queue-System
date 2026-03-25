package com.example.queue_flow.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.queue_flow.project.dto.CreateUserDTO;
import com.example.queue_flow.project.mapper.UserResponse;
import com.example.queue_flow.project.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserDTO dto) {
        return userService.createUser(dto);
    }

}
