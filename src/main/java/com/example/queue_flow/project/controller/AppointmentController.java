package com.example.queue_flow.project.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.queue_flow.project.dto.AppointmentDTO;
import com.example.queue_flow.project.mapper.AppointmentResponse;
import com.example.queue_flow.project.model.ApiResponse;
import com.example.queue_flow.project.service.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ApiResponse<AppointmentResponse> createAppointments(@Valid @RequestBody AppointmentDTO dto) {
        AppointmentResponse result = appointmentService.assignAppointment(dto);

        return new ApiResponse<AppointmentResponse>(
            true, 
            result, 
            "done",
             null, 
             LocalDateTime.now());
    }
}
