package com.example.queue_flow.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.queue_flow.project.model.AppointmentModel;

@Repository
public interface AppoitmentRepository extends JpaRepository<AppointmentModel, Long> {
} 
