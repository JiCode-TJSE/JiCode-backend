package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.domain.model.SprintAggregation;
import com.github.pagehelper.PageInfo;
import org.springframework.boot.SpringApplication;

/**
 * @author Laurent Wu
 * @date 2023/12/26
 */
public interface SprintRepository {
    public SprintAggregation selectById(String id);
    public PageInfo<SprintAggregation> getPage(int pageNum, int pageSize);
    public int insert(SprintAggregation sprintAggregation);
    public int updateById(SprintAggregation sprintAggregation);
    public int deleteById(String id);
}
