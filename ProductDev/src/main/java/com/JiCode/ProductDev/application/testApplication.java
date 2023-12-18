package com.JiCode.ProductDev.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;

@Service
public class testApplication {
    private final ScheduleRepository scheduleRepository;

    public testApplication(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.getAllSchedules();
    }
}
