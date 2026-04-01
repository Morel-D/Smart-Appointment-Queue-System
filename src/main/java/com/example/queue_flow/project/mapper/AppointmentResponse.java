package com.example.queue_flow.project.mapper;

import com.example.queue_flow.project.enums.AppoitmentStatus;

// import java.time.LocalDateTime;

public class AppointmentResponse {

    private Long id;
    private Long user_id;
    private Long appointment_id;
    private AppoitmentStatus status;


    
    public AppointmentResponse(Long id, Long user_id, Long appointment_id, AppoitmentStatus status) {
        this.id = id;
        this.user_id = user_id;
        this.appointment_id = appointment_id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public Long getAppointment_id() {
        return appointment_id;
    }
    public void setAppointment_id(Long appointment_id) {
        this.appointment_id = appointment_id;
    }
    public AppoitmentStatus getStatus() {
        return status;
    }
    public void setStatus(AppoitmentStatus status) {
        this.status = status;
    }


}
