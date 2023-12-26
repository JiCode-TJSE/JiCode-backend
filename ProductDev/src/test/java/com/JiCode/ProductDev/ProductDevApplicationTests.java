package com.JiCode.ProductDev;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ScheduleMapper;
import com.JiCode.ProductDev.domain.factory.BacklogItemFactory;
import com.JiCode.ProductDev.domain.model.BacklogItemAggregation;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
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
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Service
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductDevApplication.class)
@WebAppConfiguration
public class ProductDevApplicationTests {
    @Autowired
    BacklogItemFactory backlogItemFactory;

    @Autowired
    ProjectRepositoryImpl projectRepositoryImpl;

    @Autowired
    ScheduleRepositoryImpl scheduleRepositoryImpl;

    @Autowired
    BacklogItemRepositoryImpl backlogItemRepositoryImpl;

    @Test
    public void testSelectById(){
        ProjectAggregation projectAggregation = projectRepositoryImpl.selectById("6");
        Project project = new Project();
        BeanUtils.copyProperties(projectAggregation, project);
        System.out.println(project);
    }


    @Test
    public void testSelectPage(){


        PageInfo<BacklogItemAggregation> pageInfo = backlogItemRepositoryImpl.getPage(1, 10);

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
    public void testInsert(){
        String status ="pending";
        Float progress = 0.5F;
        Date startTime = new Date(2023, 12, 23);
        Date endTime = new Date(2023, 12, 23);
        String managerId = "1";
        List<String> members =  Arrays.asList("1", "2", "4");
        ProjectAggregation projectAggregation = ProjectAggregation.createProject(null, status, progress, startTime, endTime, managerId, members);
        System.out.println(projectRepositoryImpl.insert(projectAggregation));
    }

    @Test
    public void testUpdateById(){
        String id = "6";
        String status ="done";
        Float progress = 0.5F;
        Date startTime = new Date(2023, 12, 23);
        Date endTime = new Date(2023, 12, 23);
        String managerId = "1";
        List<String> members =  Arrays.asList("1", "2", "3");
        ProjectAggregation projectAggregation = ProjectAggregation.createProject(id, status, progress, startTime, endTime, managerId, members);
        System.out.println(projectRepositoryImpl.updateById(projectAggregation));
    }

    @Test
    public void testDeleteById(){
        String id = "6";
        System.out.println(projectRepositoryImpl.deleteById(id));
    }

    @Test
    public void testSelectBacklogItemById(){
        String id = "1";
        System.out.println(backlogItemRepositoryImpl.selectById(id));
    }

    @Test
    public void testInsertBacklogItem(){
        String id = "5";
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
        BacklogItemAggregation backlogItemAggregation = backlogItemFactory.createBacklogItem(id, priority, startTime, endTime, source, type, description, projectId, managerId, scheduleId, memberIds);
        System.out.println(backlogItemRepositoryImpl.insert(backlogItemAggregation));
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
        List<String> memberIds = Arrays.asList("4", "5", "6");
        BacklogItemAggregation backlogItemAggregation = backlogItemFactory.createBacklogItem(id, priority, startTime, endTime, source, type, description, projectId, managerId, scheduleId, memberIds);
        System.out.println(backlogItemRepositoryImpl.updateById(backlogItemAggregation));
    }

    @Test
    public void testDeleteBacklogItem(){
        String id = "1";
        System.out.println(backlogItemRepositoryImpl.deleteById(id));
    }


}
