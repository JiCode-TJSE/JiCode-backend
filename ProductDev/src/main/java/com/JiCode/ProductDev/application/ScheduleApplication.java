package com.JiCode.ProductDev.application;

import com.JiCode.ProductDev.adaptor.in.vo.ScheduleVo;
import com.JiCode.ProductDev.application.dto.ScheduleDto;
import com.JiCode.ProductDev.application.dto.SelectScheduleByIdDto;
import com.JiCode.ProductDev.domain.factory.ScheduleFactory;
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

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ScheduleFactory scheduleFactory;

    @Transactional(readOnly = true)
    public SelectScheduleByIdDto selectById(String id) throws SelectFailureException {
        ScheduleAggregation sa = scheduleRepository.selectById(id);
        SelectScheduleByIdDto selectScheduleByIdDto = new SelectScheduleByIdDto();
        BeanUtils.copyProperties(sa, selectScheduleByIdDto);
        return selectScheduleByIdDto;
    }

    public int update(ScheduleDto scheduleDto) throws SelectFailureException {
        Integer estimatedWorkhour = scheduleDto.getEstimatedWorkhour();
        Integer actualWorkhour = scheduleDto.getActualWorkhour();
        scheduleDto.setProgress((float)estimatedWorkhour/(float)actualWorkhour);
        ScheduleAggregation scheduleAggregation = scheduleFactory.createSchedule(scheduleDto.getId(), scheduleDto.getEstimatedWorkhour(), scheduleDto.getActualWorkhour(), scheduleDto.getRemainWorkhour(), scheduleDto.getProgress());
        return scheduleRepository.update(scheduleAggregation);
    }
}
