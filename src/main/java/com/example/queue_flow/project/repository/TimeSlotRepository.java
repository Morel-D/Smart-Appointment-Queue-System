package com.example.queue_flow.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.queue_flow.project.model.TimeSlotModel;
@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlotModel, Long> {

}
