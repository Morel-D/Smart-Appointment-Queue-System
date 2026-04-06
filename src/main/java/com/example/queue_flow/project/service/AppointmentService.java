package com.example.queue_flow.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        boolean exists = repository.existsByUserAndTimeSlotAndStatusIn(
            user,
            timeSlot,
            List.of(AppoitmentStatus.PENDING, AppoitmentStatus.CONFIRMED)
        );

        if (exists) {
            throw new IllegalArgumentException("You already booked this slot");
        }


        boolean timeSlotBooked = repository.existsByTimeSlotAndStatusIn(timeSlot, List.of(AppoitmentStatus.PENDING, AppoitmentStatus.CONFIRMED));

        if (timeSlotBooked) {
            throw new IllegalArgumentException("This slot has already been booked");
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


    public Map<String, Object> getAppointmentPosition(Long appointmentId) {
        AppointmentModel appointment = repository.findById(appointmentId)
            .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        List<AppointmentModel> queue = repository.findByTimeSlotOrderByCreatedAtAsc(appointment.getTimeSlot());

        int position = 0;

        for(int i = 0; i < queue.size(); i++){
            if(queue.get(i).getId().equals(appointmentId)) {
                position = i + 1;
                break;
            }
        }

        // Estimate the wait time
        int avgMinutesPerUser = 10;
        int estimateWait = (position - 1) * avgMinutesPerUser;
        Map<String, Object> result = new HashMap<>();
        result.put("position", position);
        result.put("Estimates Wait Time", estimateWait);

        return result;
    }

}
