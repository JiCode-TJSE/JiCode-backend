package com.JiCode.ProductDev;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ScheduleMapper;
import com.JiCode.ProductDev.application.ScheduleApplication;
import com.JiCode.ProductDev.domain.factory.BacklogItemFactory;
import com.JiCode.ProductDev.domain.model.BacklogItemAggregation;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
import com.JiCode.ProductDev.domain.repository.Impl.BacklogItemRepositoryImpl;
import com.JiCode.ProductDev.domain.repository.Impl.ProjectRepositoryImpl;
import com.JiCode.ProductDev.domain.repository.Impl.ScheduleRepositoryImpl;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 对backlogitem聚合和仓储的测试
 * @author Laurent Wu
 * @date 2023/12/24
 */
//@Service
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductDevApplication.class)
@WebAppConfiguration
public class BacklogitemTests{
    @Autowired
    BacklogItemFactory backlogItemFactory;

    @Autowired
    BacklogItemRepository backlogItemRepository;

    @Test
    public void test(){
        BacklogItemAggregation backlogItemAggregation = backlogItemRepository.selectById("1");
        System.out.println(backlogItemAggregation);
    }

    @Test
    public void testGetPage(){
        PageInfo<BacklogItemAggregation> pageInfo = backlogItemRepository.getPage(1, 10);

        System.out.println("Page number: " + pageInfo.getPageNum());
        System.out.println("Page size: " + pageInfo.getPageSize());
        System.out.println("Total pages: " + pageInfo.getPages());
        System.out.println("Total elements: " + pageInfo.getTotal());

        List<BacklogItemAggregation> backlogItemAggregations = pageInfo.getList();
        for (BacklogItemAggregation backlogItemAggregation : backlogItemAggregations) {
            // print the content of projectAggregation
            // you need to implement the toString method in ProjectAggregation class
            System.out.println(backlogItemAggregation);
        }

    }

    @Test
    public void testSelectBacklogItemById(){
        String id = "2";
        System.out.println(backlogItemRepository.selectById(id));
    }

    @Test
    public void testInsertBacklogItem(){
        String id = null;
        String priority = "highest";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER); // 注意，月份是从0开始的，所以11代表12月
        calendar.set(Calendar.DAY_OF_MONTH, 26);

        Date startTime = calendar.getTime();
        Date endTime = calendar.getTime();
        String source = "inner schedule";
        String type = "safety";
        String description = "test";
        String projectId = "1";
        String managerId = "1";
        String scheduleId = "1";
        List<String> memberIds = Arrays.asList("1", "2", "3");
        List<String> sprintIds = Arrays.asList("1", "2", "3");
        List<String> releaseIds = Arrays.asList("1", "2", "3");
        String topic = "wh";
        String status = "未完成";
        BacklogItemAggregation backlogItemAggregation = backlogItemFactory.createBacklogItem(id, priority, startTime, endTime, source, type, description, projectId, managerId, scheduleId, memberIds, topic, sprintIds, releaseIds, status);
        System.out.println(backlogItemRepository.insert(backlogItemAggregation));
    }

    @Test
    public void testUpdateBacklogItem(){
        String id = "2";
        String priority = "common";
        //获取日期
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER); // 注意，月份是从0开始的，所以11代表12月
        calendar.set(Calendar.DAY_OF_MONTH, 26);
        Date startTime = calendar.getTime();
        Date endTime = calendar.getTime();

        String source = "inner schedule";
        String type = "safety";
        String description = "test";
        String projectId = "1";
        String managerId = "1";
        String scheduleId = "1";
        // List<String> memberIds = Arrays.asList("4", "5", "6");
        List<String> memberIds = Arrays.asList("1", "2", "5");
        List<String> sprintIds = Arrays.asList("1", "2", "3");
        List<String> releaseIds = Arrays.asList("1", "2", "3");
        String topic = "whh";
        String status = "未完成";
        BacklogItemAggregation backlogItemAggregation = backlogItemFactory.createBacklogItem(id, priority, startTime, endTime, source, type, description, projectId, managerId, scheduleId, memberIds,topic,sprintIds,releaseIds,status);
        System.out.println(backlogItemRepository.updateById(backlogItemAggregation));
    }

    @Test
    public void testDeleteBacklogItem(){
        String id = "b43d0e2d-1b22-47d2-a590-d6d6dd9ccc6a";
        System.out.println(backlogItemRepository.deleteById(id));
    }

    @Test
    public void testAssociateWithBacklogItem(){
        String backlogItemId1 = "1";
        String backlogItemId2 = "2";
        System.out.println(backlogItemRepository.associateWithBacklogItem(backlogItemId1, backlogItemId2));
    }

    @Test
    public void testAssociateWithProductRequirement(){
        String backlogItemId = "1";
        String productRequirementId = "1";
        System.out.println(backlogItemRepository.associateWithProductRequirement(backlogItemId, productRequirementId));
    }


}
