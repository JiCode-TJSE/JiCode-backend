package com.JiCode.ProductDev.adaptor.in;

import com.JiCode.ProductDev.application.ScheduleApplication;
import com.JiCode.ProductDev.application.dto.SelectScheduleByIdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JiCode.ProductDev.adaptor.in.vo.ScheduleVo;

@RestController
@RequestMapping("/ProductDev")
public class WebController {

    @Autowired
    ScheduleApplication scheduleApplication;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/test")
    public ScheduleVo getTestSchedule(@RequestParam("id") String id) {
        // 使用 id 参数来获取和返回 Schedule
        return new ScheduleVo();
    }

    @GetMapping("/selectById")
    public SelectScheduleByIdDto selectScheduleById(@RequestParam("id") String id){
        SelectScheduleByIdDto selectScheduleByIdDto = scheduleApplication.selectById(id);
        return selectScheduleByIdDto;
    }
}