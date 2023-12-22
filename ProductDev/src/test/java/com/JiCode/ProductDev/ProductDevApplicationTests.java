package com.JiCode.ProductDev;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ScheduleMapper;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
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
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Service
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductDevApplication.class)
@WebAppConfiguration
public class ProductDevApplicationTests {

//	@Autowired
//	ScheduleMapper scheduleMapper;

    @Autowired
    ProjectRepositoryImpl projectRepositoryImpl;

    @Autowired
    ScheduleRepositoryImpl scheduleRepositoryImpl;

    public void testSelectById(){
        ProjectAggregation projectAggregation = projectRepositoryImpl.selectById("1");
        Project project = new Project();
        BeanUtils.copyProperties(projectAggregation, project);
        System.out.println(project);
    }


    @Test
    public void testSelectPage(){
//        ScheduleAggregation scheduleAggregation = scheduleRepositoryImpl.selectById("1");
//        Schedule schedule = new Schedule();
//        BeanUtils.copyProperties(scheduleAggregation, schedule);
//        System.out.println(schedule);



        PageInfo<ProjectAggregation> pageInfo = projectRepositoryImpl.selectAll(1, 10);

        System.out.println("Page number: " + pageInfo.getPageNum());
        System.out.println("Page size: " + pageInfo.getPageSize());
        System.out.println("Total pages: " + pageInfo.getPages());
        System.out.println("Total elements: " + pageInfo.getTotal());

        List<ProjectAggregation> projectAggregations = pageInfo.getList();
        for (ProjectAggregation projectAggregation : projectAggregations) {
            // print the content of projectAggregation
            // you need to implement the toString method in ProjectAggregation class
            System.out.println(projectAggregation);
        }

    }

    @Test
    public void testInsert(){
        String id = "6";
        String status ="pending";
        Float progress = 0.5F;
        Date startTime = new Date(2023, 12, 23);
        Date endTime = new Date(2023, 12, 23);
        String managerId = "1";
        System.out.println(projectRepositoryImpl.insert(id, status, progress, startTime, endTime, managerId));
    }

    @Test
    public void testUpdateById(){
        String id = "6";
        String status ="done";
        Float progress = 0.5F;
        Date startTime = new Date(2023, 12, 23);
        Date endTime = new Date(2023, 12, 23);
        String managerId = "1";
        System.out.println(projectRepositoryImpl.updateById(id, status, progress, startTime, endTime, managerId));
    }

    @Test
    public void testDeleteById(){
        String id = "6";
        System.out.println(projectRepositoryImpl.deleteById(id));
    }

}
