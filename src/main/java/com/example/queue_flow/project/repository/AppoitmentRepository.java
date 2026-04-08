package com.example.queue_flow.project.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.queue_flow.project.enums.AppoitmentStatus;
import com.example.queue_flow.project.model.AppointmentModel;
import com.example.queue_flow.project.model.TimeSlotModel;
import com.example.queue_flow.project.model.UserModel;

@Repository
public interface AppoitmentRepository extends JpaRepository<AppointmentModel, Long> {
    boolean existsByUserAndTimeSlotAndStatusIn(UserModel user, TimeSlotModel timeSlot, Collection<AppoitmentStatus> statuses);

    boolean existsByTimeSlotAndStatusIn(TimeSlotModel timeSlot, Collection<AppoitmentStatus> statuses);
    List<AppointmentModel> findAllByStatusIn(List<AppoitmentStatus> statuses);

    Page<AppointmentModel> findByStatusContaining(AppoitmentStatus status, Pageable pageable);
} 
