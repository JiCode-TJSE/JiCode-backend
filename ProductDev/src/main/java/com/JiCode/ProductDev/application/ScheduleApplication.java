package com.JiCode.ProductDev.application;

import com.JiCode.ProductDev.adaptor.in.vo.ScheduleVo;
import com.JiCode.ProductDev.application.dto.SelectScheduleByIdDto;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
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
        selectScheduleByIdDto.setActual_workhour(sa.actual_workhour);
        selectScheduleByIdDto.setId(sa.id);
        selectScheduleByIdDto.setEstimated_workhour(sa.actual_workhour);
        selectScheduleByIdDto.setRemain_workhour(sa.remain_workhour);
        selectScheduleByIdDto.setProgress(sa.remain_workhour);
        return selectScheduleByIdDto;
    }
}
