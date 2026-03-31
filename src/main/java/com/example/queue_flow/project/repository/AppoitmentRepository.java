package com.example.queue_flow.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.queue_flow.project.model.AppointmentModel;

public interface AppoitmentRepository extends JpaRepository<AppointmentModel, Long> {
} 
