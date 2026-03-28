package com.example.queue_flow.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.queue_flow.project.dto.CreateUserDTO;
import com.example.queue_flow.project.enums.Role;
import com.example.queue_flow.project.mapper.UserResponse;
import com.example.queue_flow.project.model.UserModel;
import com.example.queue_flow.project.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserResponse createUser(CreateUserDTO dto) {

        // check if email exist
        if(repository.existsByEmail(dto.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }

        UserModel user = new UserModel();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(Role.CLIENT);

        UserModel saveModel = repository.save(user);

        return new UserResponse(
            saveModel.getId(),
            saveModel.getName(),
            saveModel.getEmail(),
            saveModel.getRole()
        );
    }

    public List<UserResponse> getAllUsers() {
        List<UserModel> users = repository.findAll();
        return users.stream().map(user -> new UserResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole()
        )).toList();
    }
}
