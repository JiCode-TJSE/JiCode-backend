package com.JiCode.ProductMa.domain.model;

import com.JiCode.ProductMa.domain.repository.TestRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class TestAggregation {
    @Autowired
    TestRepository testRepository;

    public String id;

    public String name;

    static public TestAggregation createTest(String id, String name){
        TestAggregation testAggregation = new TestAggregation();
        testAggregation.id = id;
        testAggregation.name = name;
        return testAggregation;
    }

}
