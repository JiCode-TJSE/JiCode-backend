package com.JiCode.ProductMa;

import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.JiCode.ProductMa.domain.model.RequirementAggregation;
import com.JiCode.ProductMa.domain.repository.RequirementRepository;

@SpringBootTest
public class RequirementArrTests {

    @Autowired
    private RequirementRepository requirementRepository;

    @Test
    public void testSelectById() throws Exception {
        // Arrange
        String id = "test";
        RequirementAggregation agg;
        agg = requirementRepository.selectById(id);
        System.out.println(agg);
    }

    // @Test
    // public void testInsert() throws Exception {
    // // Arrange
    // requirementRepository.insert(agg);
    // RequirementAggregation insertedAgg =
    // requirementRepository.selectById(agg.getId());
    // System.out.println(insertedAgg);
    // }

    // @Test
    // public void testDelete() throws Exception {
    // // Arrange
    // String id = "test";
    // requirementRepository.delete(id);
    // RequirementAggregation deletedAgg = requirementRepository.selectById(id);
    // // 由于已经删除，所以deletedAgg应该为null
    // assertNull(deletedAgg);
    // }

    // @Test
    // public void testUpdate() throws Exception {
    // RequirementAggregation agg = this.requirementAggregation;
    // requirementRepository.update(agg);
    // RequirementAggregation updatedAgg =
    // requirementRepository.selectById(agg.getId());
    // System.out.println(updatedAgg);
    // }

}