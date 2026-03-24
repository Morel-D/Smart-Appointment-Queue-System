package com.example.queue_flow.project.model;

import com.example.queue_flow.project.enums.AppoitmentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class AppointmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @OneToOne
    @JoinColumn(name = "timeslot_id")
    private TimeSlotModel timeSlot;
    private AppoitmentStatus status;


    public AppointmentModel(Long id, UserModel user, TimeSlotModel timeSlot, AppoitmentStatus status) {
        this.id = id;
        this.user = user;
        this.timeSlot = timeSlot;
        this.status = status;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public UserModel getUser() {
        return user;
    }
    public void setUser(UserModel user) {
        this.user = user;
    }
    public TimeSlotModel getTimeSlot() {
        return timeSlot;
    }
    public void setTimeSlot(TimeSlotModel timeSlot) {
        this.timeSlot = timeSlot;
    }
    public AppoitmentStatus getStatus() {
        return status;
    }
    public void setStatus(AppoitmentStatus status) {
        this.status = status;
    }


}
