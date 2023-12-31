package com.JiCode.ProductDev.application;

import com.JiCode.ProductDev.adaptor.in.vo.ScheduleVo;
import com.JiCode.ProductDev.application.dto.SelectScheduleByIdDto;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.domain.repository.Impl.ScheduleRepositoryImpl;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
import com.JiCode.ProductDev.exceptions.WorkHour.SelectFailureException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleApplication {
//    @Autowired
//    ScheduleAggregation scheduleAggregation;

    @Autowired
    ScheduleRepositoryImpl scheduleRepositoryImpl;

    @Transactional(readOnly = true)
    public SelectScheduleByIdDto selectById(String id) throws SelectFailureException {
        ScheduleAggregation sa = scheduleRepositoryImpl.selectById(id);
        SelectScheduleByIdDto selectScheduleByIdDto = new SelectScheduleByIdDto();
        BeanUtils.copyProperties(sa, selectScheduleByIdDto);
        return selectScheduleByIdDto;
    }
}
