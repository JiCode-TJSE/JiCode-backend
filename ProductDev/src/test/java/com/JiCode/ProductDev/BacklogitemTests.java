package com.JiCode.ProductDev;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ScheduleMapper;
import com.JiCode.ProductDev.application.ScheduleApplication;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
    BacklogItemRepository backlogItemRepository;

    @Test
    public void test(){
        BacklogItemAggregation backlogItemAggregation = backlogItemRepository.selectById("1");
        System.out.println(backlogItemAggregation);
    }

    @Test
    public void testSelectPage(){
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
        String id = "1";
        System.out.println(backlogItemRepository.selectById(id));
    }

    @Test
    public void testInsertBacklogItem(){
        String id = "";
        String priority = "highest";
        Date startTime = new Date(2023, 12, 23);
        Date endTime = new Date(2023, 12, 23);
        String source = "inner schedule";
        String type = "safety";
        String description = "test";
        String projectId = "1";
        String managerId = "1";
        String scheduleId = "1";
        List<String> memberIds = Arrays.asList("1", "2", "3");
        BacklogItemAggregation backlogItemAggregation = BacklogItemAggregation.createBacklogItem(id, priority, startTime, endTime, source, type, description, projectId, managerId, scheduleId, memberIds);
        System.out.println(backlogItemRepository.insert(backlogItemAggregation));
    }

    @Test
    public void testUpdateBacklogItem(){
        String id = "1";
        String priority = "common";
        Date startTime = new Date(2023, 12, 23);
        Date endTime = new Date(2023, 12, 23);
        String source = "inner schedule";
        String type = "safety";
        String description = "test";
        String projectId = "1";
        String managerId = "1";
        String scheduleId = "1";
        // List<String> memberIds = Arrays.asList("4", "5", "6");
        List<String> memberIds = Arrays.asList("1", "2", "3");
        BacklogItemAggregation backlogItemAggregation = BacklogItemAggregation.createBacklogItem(id, priority, startTime, endTime, source, type, description, projectId, managerId, scheduleId, memberIds);
        System.out.println(backlogItemRepository.updateById(backlogItemAggregation));
    }

    @Test
    public void testDeleteBacklogItem(){
        String id = "1";
        System.out.println(backlogItemRepository.deleteById(id));
    }


}
