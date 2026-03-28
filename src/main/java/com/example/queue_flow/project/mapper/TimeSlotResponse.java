package com.example.queue_flow.project.mapper;

import java.time.LocalDateTime;


public class TimeSlotResponse {

    private Long id;
    private LocalDateTime starTime;
    private LocalDateTime endTime;


    
    public TimeSlotResponse(Long id, LocalDateTime starTime, LocalDateTime endTime) {
        this.id = id;
        this.starTime = starTime;
        this.endTime = endTime;
    }

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
