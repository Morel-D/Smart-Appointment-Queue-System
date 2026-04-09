package com.example.queue_flow.project.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.queue_flow.project.dto.AppointmentDTO;
import com.example.queue_flow.project.enums.AppoitmentStatus;
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

    @GetMapping("/{id}/position")
    public ApiResponse<?> getPosition(@PathVariable Long id) {
        Map<String, Object> result = appointmentService.getAppointmentPosition(id);

        return new ApiResponse<>(
            true, 
            result, 
            "done", 
            null, 
            LocalDateTime.now()
        );
    }

    @GetMapping
    public ApiResponse<Page<AppointmentDTO>> getAppointments(@RequestParam(required = false) AppoitmentStatus status, Pageable pageable) {
        Page<AppointmentDTO> appoitments = appointmentService.getAllAppointments(status, pageable);

        return new ApiResponse<>(
            true, 
            appoitments, 
            "done", 
            null, 
            LocalDateTime.now());
    }

    @PutMapping("/{id}")
    public ApiResponse<AppointmentResponse> updateAppointmentStatus(@PathVariable Long id, @RequestParam AppoitmentStatus status) {

        AppointmentResponse result = appointmentService.updatesAppoitmentStatus(status, id);

        return new ApiResponse<AppointmentResponse>(
            false, 
            result, 
            "Done", 
            null, 
            LocalDateTime.now()
        );
    }
}
