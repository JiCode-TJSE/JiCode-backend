package com.JiCode.ProductMa.domain.model;

import com.JiCode.ProductMa.domain.repository.TestRepository;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
// 这个注解只生成getter，因为一个聚合里的东西应该由聚合自己的方法来管理，而不应该让外部调用setter方法，会造成混乱/数据不一致
@Getter
@NoArgsConstructor
public class TestAggregation {
    @Autowired
    TestRepository testRepository;

    public String id;

    public String name;

    static public TestAggregation createTest(String id, String name) {
        TestAggregation testAggregation = new TestAggregation();
        testAggregation.id = id;
        testAggregation.name = name;
        return testAggregation;
    }

}
