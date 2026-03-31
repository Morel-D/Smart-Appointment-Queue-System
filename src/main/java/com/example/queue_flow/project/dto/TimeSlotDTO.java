package com.example.queue_flow.project.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class TimeSlotDTO {


    private LocalDateTime starTime;
    private LocalDateTime endTime;

    public TimeSlotDTO(LocalDateTime starTime, LocalDateTime endTime) {
        this.starTime = starTime;
        this.endTime = endTime;
    }


    public LocalDateTime getStarTime() {
        return starTime;
    }
    public void setStarTime(LocalDateTime starTime) {
        this.starTime = starTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }


}
