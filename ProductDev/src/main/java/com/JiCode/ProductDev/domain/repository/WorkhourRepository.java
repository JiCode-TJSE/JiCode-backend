package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.domain.model.WorkhourAggregation;
import com.JiCode.ProductDev.exceptions.WorkHour.DeleteFailureException;
import com.JiCode.ProductDev.exceptions.WorkHour.SelectFailureException;
import com.JiCode.ProductDev.exceptions.sprint.InsertFailureException;
import com.JiCode.ProductDev.exceptions.sprint.UpdateFaliureException;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface WorkhourRepository {
    public WorkhourAggregation selectById(String id) throws SelectFailureException;

    public List<WorkhourAggregation> selectAll();

    public PageInfo<WorkhourAggregation> getPage(int pageNum, int pageSize) throws SelectFailureException;

    public int insert(WorkhourAggregation workhourAggregation) throws InsertFailureException;

    public int update(WorkhourAggregation workhourAggregation) throws UpdateFaliureException;

    public int delete(String id) throws DeleteFailureException;

    public List<WorkhourAggregation> selectBySchedule(String scheduleId) throws SelectFailureException;
}
