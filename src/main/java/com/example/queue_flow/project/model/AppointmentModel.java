package com.example.queue_flow.project.model;

import com.example.queue_flow.project.enums.AppoitmentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointment_model")
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

    @Enumerated(EnumType.STRING)
    private AppoitmentStatus status;


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
