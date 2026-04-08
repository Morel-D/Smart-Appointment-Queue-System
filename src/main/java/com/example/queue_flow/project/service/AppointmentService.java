package com.example.queue_flow.project.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
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


        private long calculateEstimateWait(int position, List<AppointmentModel> sortedAppointment, LocalDateTime targetStart) {
            if(position <= 1) {
                return 0;
            }

            long averageServiceMinutes = 30;

            LocalDateTime now = LocalDateTime.now();
            Duration timeToYourSlot = Duration.between(now, targetStart);

            if(!timeToYourSlot.isNegative()) {
                return timeToYourSlot.toMinutes();
            }

            return (position - 1) * averageServiceMinutes;
    }


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
        AppointmentModel traget = repository.findById(appointmentId)
        .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        // Get only active appoitments (pending or continued)
        List<AppointmentModel> activeAppointments = repository.findAllByStatusIn(
            List.of(AppoitmentStatus.PENDING, AppoitmentStatus.CONFIRMED)
        );

        // Sort by starttime ascending -> earliest appointment first
        activeAppointments.sort(Comparator.comparing(a -> a.getTimeSlot().getStarTime()));

        // Find position
        int position = 0;
        LocalDateTime targStartTime = traget.getTimeSlot().getStarTime();

        for(int i = 0; i < activeAppointments.size(); i++){

            // Loop throught each position and incremant it
            if(activeAppointments.get(i).getId().equals(appointmentId)){
                position = i + 1;
                break;
            }
        }

        if(position == 0) {
            // This are the non active position (neither pending nor cancelled)
            throw new IllegalArgumentException("Appoitment is not active or expired");
        }

        // Simple estimator logic
        long estimatedWaitMinutes = calculateEstimateWait(position, activeAppointments, targStartTime);

        Map<String, Object> result = new HashMap<>();
        result.put("position", position);
        result.put("totalInQueue", activeAppointments.size());
        result.put("yourStartTime", targStartTime);
        result.put("estimatedWaitMinutes", estimatedWaitMinutes);

        return result;
    }

}
