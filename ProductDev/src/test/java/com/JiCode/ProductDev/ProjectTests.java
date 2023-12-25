package com.JiCode.ProductDev;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ScheduleMapper;
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

/**
 * 对project聚合和仓储的相关测试
 * @author Laurent Wu
 * @date 2023/12/24
 */
@Service
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductDevApplication.class)
@WebAppConfiguration
public class ProjectTests {
    @Autowired
    ProjectAggregation projectAggregation;

    @Autowired
    ProjectRepository projectRepository;

    @Test
    public void testSelectById(){
        ProjectAggregation projectAggregation = projectRepository.selectById("6");
        System.out.println(projectAggregation);
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
        System.out.println(projectRepository.insert(projectAggregation));
    }

    @Test
    public void testUpdateProjectById(){
        String id = "1";
        String status ="done";
        Float progress = 0.5F;
        Date startTime = new Date(2023, 12, 23);
        Date endTime = new Date(2023, 12, 23);
        String managerId = "1";
        List<String> members =  Arrays.asList("1", "2", "3");
        ProjectAggregation projectAggregation = ProjectAggregation.createProject(id, status, progress, startTime, endTime, managerId, members);
        System.out.println(projectRepository.updateById(projectAggregation));
    }

    @Test
    public void testDeleteById(){
        String id = "6";
        System.out.println(projectRepository.deleteById(id));
    }

}
