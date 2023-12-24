package com.JiCode.ProductMa.application.Impl;

import com.JiCode.ProductMa.application.TestApplication;
import com.JiCode.ProductMa.application.dto.SelectTestByIdDto;
import com.JiCode.ProductMa.domain.model.TestAggregation;
import com.JiCode.ProductMa.domain.repository.TestRepository;
import com.JiCode.ProductMa.domain.repository.impl.TestRepositoryImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestApplicationImpl implements TestApplication {
    @Autowired
    TestAggregation testAggregation;

    @Autowired
    TestRepositoryImpl testRepositoryImpl;

    @Transactional(readOnly = true)
    public SelectTestByIdDto selectTestById(String id) {
        TestAggregation ta = testRepositoryImpl.selectById(id);
        SelectTestByIdDto selectTestByIdDto = new SelectTestByIdDto();
        BeanUtils.copyProperties(ta, selectTestByIdDto);
        return selectTestByIdDto;
    }

}
