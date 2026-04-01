package com.example.queue_flow.project.dto;
// import jakarta.validation.constraints.NotBlank;

public class AppointmentDTO {

    private Long user_id;
    private Long timeslot_id;

    
    public AppointmentDTO(Long user_id, Long timeslot_id) {
        this.user_id = user_id;
        this.timeslot_id = timeslot_id;
    }

    
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public Long getTimeslot_id() {
        return timeslot_id;
    }
    public void setTimeslot_id(Long timeslot_id) {
        this.timeslot_id = timeslot_id;
    }
}
