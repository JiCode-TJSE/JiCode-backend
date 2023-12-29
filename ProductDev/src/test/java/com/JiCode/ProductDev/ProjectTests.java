package com.JiCode.ProductDev;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ScheduleMapper;
import com.JiCode.ProductDev.domain.factory.ProjectFactory;
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
import java.util.*;

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
//    @Autowired
//    ProjectAggregation projectAggregation;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectFactory projectFactory;

    @Test
    public void testSelectById(){
        ProjectAggregation projectAggregation = projectRepository.selectById("6");
        System.out.println(projectAggregation);
    }

    @Test
    public void testGetPage(){
        PageInfo<ProjectAggregation> pageInfo = projectRepository.getPage(1, 10);

        System.out.println("Page number: " + pageInfo.getPageNum());
        System.out.println("Page size: " + pageInfo.getPageSize());
        System.out.println("Total pages: " + pageInfo.getPages());
        System.out.println("Total elements: " + pageInfo.getTotal());

        List<ProjectAggregation> projectAggregations = pageInfo.getList();
        for(ProjectAggregation projectAggregation : projectAggregations){
            // print the content of projectAggregation
            // you need to implement the toString method in ProjectAggregation class
            System.out.println(projectAggregation);
        }
    }

    @Test
    public void testInsert(){
        String status ="pending";
        Float progress = 0.5F;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER); // 注意，月份是从0开始的，所以11代表12月
        calendar.set(Calendar.DAY_OF_MONTH, 26);
        Date startTime = calendar.getTime();
        Date endTime = calendar.getTime();
        String managerId = "1";
        List<String> members =  Arrays.asList("1", "2", "4");
        String topic =  "wh";
        ProjectAggregation projectAggregation = projectFactory.createProject(null, status, progress, startTime, endTime, managerId, members,topic);
        System.out.println(projectRepository.insert(projectAggregation));
    }

    @Test
    public void testUpdateProjectById(){
        String id = "1";
        String status ="done";
        Float progress = 0.5F;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER); // 注意，月份是从0开始的，所以11代表12月
        calendar.set(Calendar.DAY_OF_MONTH, 26);
        Date startTime = calendar.getTime();
        Date endTime = calendar.getTime();
        String managerId = "1";
        List<String> members =  Arrays.asList("1", "2", "3");
        String topic =  "wh";
        ProjectAggregation projectAggregation = projectFactory.createProject(id, status, progress, startTime, endTime, managerId, members,topic);
        System.out.println(projectRepository.updateById(projectAggregation));
    }

    @Test
    public void testDeleteById(){
        String id = "6";
        System.out.println(projectRepository.deleteById(id));
    }

}
