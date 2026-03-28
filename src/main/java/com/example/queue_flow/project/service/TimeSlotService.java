package com.example.queue_flow.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.queue_flow.project.mapper.TimeSlotResponse;
import com.example.queue_flow.project.model.TimeSlotModel;
import com.example.queue_flow.project.repository.TimeSlotRepository;

@Service
public class TimeSlotService {

    @Autowired
    private TimeSlotRepository repository;

    public TimeSlotResponse creatTimeSlot(TimeSlotModel dto){

        TimeSlotModel timeSlot = new TimeSlotModel();
        timeSlot.setStarTime(dto.getStarTime());
        timeSlot.setEndTime(dto.getEndTime());

        TimeSlotModel timeSlotModel = repository.save(timeSlot);

        return new TimeSlotResponse(
            timeSlotModel.getId(), 
            timeSlotModel.getStarTime(), 
            timeSlotModel.getEndTime()
        );
    }


    public List<TimeSlotResponse> getAllTimeSlot() {
        List<TimeSlotModel> timeSlotModel = repository.findAll();
        return timeSlotModel.stream().map(timeSlot -> new TimeSlotResponse(
            timeSlot.getId(),
             timeSlot.getStarTime(),
              timeSlot.getEndTime()
            )).toList();
    }

}
