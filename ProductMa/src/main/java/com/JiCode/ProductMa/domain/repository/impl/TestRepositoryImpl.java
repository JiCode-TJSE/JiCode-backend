package com.JiCode.ProductMa.domain.repository.impl;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Test;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.TestMapper;
import com.JiCode.ProductMa.domain.model.TestAggregation;
import com.JiCode.ProductMa.domain.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepositoryImpl implements TestRepository {
    @Autowired
    TestMapper testMapper;

    public TestAggregation selectById(String id){
        Test test = testMapper.selectByPrimaryKey(id);
        return TestAggregation.createTest(test.getId(), test.getName());
    }
}
