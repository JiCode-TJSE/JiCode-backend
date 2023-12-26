package com.JiCode.ProductDev;

import com.JiCode.ProductDev.domain.model.SprintAggregation;
import com.JiCode.ProductDev.domain.repository.SprintRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductDevApplication.class)
@WebAppConfiguration
public class SprintTests {

    @Autowired
    SprintRepository sprintRepository;

    @Test
    public void testSelectById() {
        SprintAggregation sprintAggregation = sprintRepository.selectById("1");
        System.out.println(sprintAggregation);
    }

    @Test
    public void testGetPage() {
        System.out.println(sprintRepository.getPage(1, 10));
    }

    @Test
    public void testInsert(){
        String id = "3";
        Date startTime = new Date(2023, 12, 23);
        Date endTime = new Date(2023, 12, 23);
        String goal = "yes";
        String type = "plan";
        String projectId = "1";
        String managerId = "1";
        List<String> members =  Arrays.asList("1");
        SprintAggregation sprintAggregation = SprintAggregation.createSprint(id, startTime, endTime, goal, type, projectId, managerId, null, members);
        sprintRepository.insert(sprintAggregation);
    }

    @Test
    public void testUpdateById(){
        String id = "1";
        Date startTime = new Date(2023, 12, 23);
        Date endTime = new Date(2023, 12, 23);
        String goal = "no";
        String type = "plan";
        String projectId = "1";
        String managerId = "1";
        List<String> members =  Arrays.asList("1");
        SprintAggregation sprintAggregation = SprintAggregation.createSprint(id, startTime, endTime, goal, type, projectId, managerId, null, members);
        sprintRepository.updateById(sprintAggregation);
    }

    @Test
    public void testDeleteById(){
        sprintRepository.deleteById("1");
    }
}
