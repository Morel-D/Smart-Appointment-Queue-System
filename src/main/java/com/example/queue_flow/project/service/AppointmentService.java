package com.example.queue_flow.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.queue_flow.project.dto.AppointmentDTO;
import com.example.queue_flow.project.enums.AppoitmentStatus;
import com.example.queue_flow.project.mapper.AppointmentResponse;
import com.example.queue_flow.project.model.AppointmentModel;
import com.example.queue_flow.project.model.TimeSlotModel;
import com.example.queue_flow.project.model.UserModel;
import com.example.queue_flow.project.repository.AppoitmentRepository;
import com.example.queue_flow.project.repository.TimeSlotRepository;
import com.example.queue_flow.project.repository.UserRepository;


@Service
public class AppointmentService {


    @Autowired
    private AppoitmentRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    public AppointmentResponse assignAppointment(AppointmentDTO dto){

        UserModel user = userRepository.findById(dto.getUser_id())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        TimeSlotModel timeSlot = timeSlotRepository.findById(dto.getTimeslot_id())
            .orElseThrow(() -> new IllegalArgumentException("Time slot not found"));

        // Prevent over booking
        boolean exists = repository.existsByUserAndTimeSlotAndStatusIn(user, timeSlot, List.of(AppoitmentStatus.PENDING, AppoitmentStatus.CONFIRMED));

        if(exists){
            throw new IllegalArgumentException("User already has an active booking for this slot");
        }

        AppointmentModel appointment = new AppointmentModel();
        appointment.setUser(user);
        appointment.setTimeSlot(timeSlot);
        appointment.setStatus(AppoitmentStatus.PENDING);

        AppointmentModel appointmentModel = repository.save(appointment);

        return new AppointmentResponse(
            appointmentModel.getId(), 
            appointment.getUser().getId(),
            appointment.getTimeSlot().getId(),
            appointment.getStatus()
        );
    }

}
