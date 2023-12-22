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

    @Test
    public void test(){
//        ScheduleAggregation scheduleAggregation = scheduleRepositoryImpl.selectById("1");
//        Schedule schedule = new Schedule();
//        BeanUtils.copyProperties(scheduleAggregation, schedule);
//        System.out.println(schedule);

        ProjectAggregation projectAggregation = projectRepositoryImpl.selectById("1");
        Project project = new Project();
        BeanUtils.copyProperties(projectAggregation, project);
        System.out.println(project);

    }

}
