package com.JiCode.ProductDev;

import com.JiCode.ProductDev.domain.factory.SprintFactory;
import com.JiCode.ProductDev.domain.model.SprintAggregation;
import com.JiCode.ProductDev.domain.repository.SprintRepository;
import com.JiCode.ProductDev.exceptions.sprint.DeleteFailureException;
import com.JiCode.ProductDev.exceptions.sprint.InsertFailureException;
import com.JiCode.ProductDev.exceptions.sprint.SetReleaseException;
import com.JiCode.ProductDev.exceptions.sprint.UpdateFaliureException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductDevApplication.class)
@WebAppConfiguration
public class SprintTests {

    @Autowired
    SprintRepository sprintRepository;

    @Autowired
    SprintFactory sprintFactory;

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
    public void testInsert() throws InsertFailureException {
        String id = null;

        // 设定日期
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER); // 注意，月份是从0开始的，所以11代表12月
        calendar.set(Calendar.DAY_OF_MONTH, 26);

        // 创建一个Date对象
        Date startTime = calendar.getTime();
        Date endTime = calendar.getTime();
        String goal = "yes";
        String type = "plan";
        String projectId = "1";
        String managerId = "1";
        List<String> members =  Arrays.asList("1");
        String topic = "wh";
        SprintAggregation sprintAggregation = sprintFactory.createSprint(id, startTime, endTime, goal, type, projectId, managerId, null, members, topic);
        sprintRepository.insert(sprintAggregation);
    }

    @Test
    public void testUpdateById() throws UpdateFaliureException {
        String id = "1";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER); // 注意，月份是从0开始的，所以11代表12月
        calendar.set(Calendar.DAY_OF_MONTH, 26);
        Date startTime = calendar.getTime();
        Date endTime = calendar.getTime();
        String goal = "no";
        String type = "plan";
        String projectId = "1";
        String managerId = "1";
        List<String> members =  Arrays.asList("1");
        String topic = "wh";
        SprintAggregation sprintAggregation = sprintFactory.createSprint(id, startTime, endTime, goal, type, projectId, managerId, null, members, topic);
        sprintRepository.updateById(sprintAggregation);
    }

    @Test
    public void testDeleteById() throws DeleteFailureException {
        sprintRepository.deleteById("1");
    }

    @Test
    public void testSetRelease() throws SetReleaseException {
        String sprintId = "3";
        String releaseId = "2";
        sprintRepository.setRelease(sprintId, releaseId);
    }

    @Test
    public void testAssociateWithRelease(){
        String sprintId = "3";
        String releaseId = "2";
        sprintRepository.associateWithRelease(sprintId, releaseId);
    }
}
