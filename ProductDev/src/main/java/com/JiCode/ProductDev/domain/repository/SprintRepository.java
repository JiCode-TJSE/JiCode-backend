package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.domain.model.SprintAggregation;
import com.JiCode.ProductDev.exceptions.sprint.DeleteFailureException;
import com.JiCode.ProductDev.exceptions.sprint.InsertFailureException;
import com.JiCode.ProductDev.exceptions.sprint.SetReleaseException;
import com.JiCode.ProductDev.exceptions.sprint.UpdateFaliureException;
import com.github.pagehelper.PageInfo;
import org.springframework.boot.SpringApplication;

/**
 * @author Laurent Wu
 * @date 2023/12/26
 */
public interface SprintRepository {
    public SprintAggregation selectById(String id);
    public PageInfo<SprintAggregation> getPage(int pageNum, int pageSize);
    public int insert(SprintAggregation sprintAggregation) throws InsertFailureException;
    public int updateById(SprintAggregation sprintAggregation) throws UpdateFaliureException;
    public int deleteById(String id) throws DeleteFailureException;
    public int setRelease(String sprintId, String releaseId) throws SetReleaseException;

    public int associateWithRelease(String sprintId, String releaseId);
}
