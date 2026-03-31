package com.example.queue_flow.project.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.queue_flow.project.dto.TimeSlotDTO;
import com.example.queue_flow.project.mapper.TimeSlotResponse;
import com.example.queue_flow.project.model.ApiResponse;
import com.example.queue_flow.project.service.TimeSlotService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/timeslot")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @PostMapping
    public ApiResponse<TimeSlotResponse> createTimeSlot(@Valid @RequestBody TimeSlotDTO dto) {
        TimeSlotResponse result = timeSlotService.creatTimeSlot(dto);
        return new ApiResponse<TimeSlotResponse>(
            true, 
            result,
            "done", 
            null,
            LocalDateTime.now()
        );
    }

    @GetMapping
    public ApiResponse<List<TimeSlotResponse>> getTimeSlots(){
        List<TimeSlotResponse> timeSlots = timeSlotService.getAllTimeSlot();
        return new ApiResponse<>(
            true, 
            timeSlots, 
            "done", 
            null, 
            LocalDateTime.now()
        );
    }

}
