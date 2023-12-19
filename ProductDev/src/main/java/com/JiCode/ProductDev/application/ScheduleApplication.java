package com.JiCode.ProductDev.application;

import com.JiCode.ProductDev.adaptor.in.vo.ScheduleVo;
import com.JiCode.ProductDev.application.dto.SelectScheduleByIdDto;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleApplication {
    @Autowired
    ScheduleAggregation scheduleAggregation;

    @Autowired
    ScheduleRepository scheduleRepository;

    public SelectScheduleByIdDto selectById(String id){
        ScheduleAggregation sa = scheduleRepository.selectById(id);
        SelectScheduleByIdDto selectScheduleByIdDto = new SelectScheduleByIdDto();
        BeanUtils.copyProperties(sa, selectScheduleByIdDto);
        return selectScheduleByIdDto;
    }
}
