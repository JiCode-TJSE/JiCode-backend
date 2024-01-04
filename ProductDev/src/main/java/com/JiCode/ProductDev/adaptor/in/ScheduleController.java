package com.JiCode.ProductDev.adaptor.in;

import com.JiCode.ProductDev.application.ScheduleApplication;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.domain.model.WorkhourAggregation;
import com.JiCode.ProductDev.exceptions.WorkHour.SelectFailureException;
import com.JiCode.ProductDev.util.ComResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productdev")
public class ScheduleController {
    @Autowired
    ScheduleApplication scheduleApplication;

    @GetMapping("/schedule")
    public ComResponse<ScheduleAggregation> selectById(@RequestParam("id")String id) throws SelectFailureException {
        return ComResponse.success(scheduleApplication.selectById(id));
    }

    @GetMapping("/workhours")
    public ComResponse<List<WorkhourAggregation>> selectAllWorkhour() throws SelectFailureException {
        return ComResponse.success(scheduleApplication.selectAllWorkhour());
    }
}
