package com.JiCode.ProductDev;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Release;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ReleaseMapper;
import com.JiCode.ProductDev.domain.factory.ReleaseFactory;
import com.JiCode.ProductDev.domain.model.ReleaseAggregation;
import com.JiCode.ProductDev.domain.repository.ReleaseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductDevApplication.class)
@WebAppConfiguration
public class ReleaseTests {
    //test
    @Autowired
    ReleaseMapper releaseMapper;


    @Autowired
    ReleaseRepository releaseRepository;

    @Autowired
    ReleaseFactory releaseFactory;

    @Test
    public void testSelectById(){
        ReleaseAggregation releaseAggregation = releaseRepository.selectById("3");
        System.out.println(releaseAggregation);
    }

    @Test
    public void testMapper(){
        Release release = new Release();
        release.setId("1");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 26);

        Date startTime = calendar.getTime();
        Date endTime = calendar.getTime();
        release.setType("yes");
        release.setProjectId("1");
        release.setManagerId("1");
        releaseMapper.updateByPrimaryKey(release);
    }
//    @Test
//    public void testGetPage(){
//        System.out.println(releaseRepository.getPage(1, 10));
//    }

    @Test
    public void testInsert(){
        String id = null;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 26);

        Date startTime = calendar.getTime();
        Date endTime = calendar.getTime();
        String goal = "yes";
        String projectId = "1";
        String managerId = "1";
        List<String> memberIds = Arrays.asList("1", "2", "3");

        ReleaseAggregation releaseAggregation = releaseFactory.createRelease(id, startTime, endTime, goal, projectId, managerId, memberIds);
        releaseRepository.insert(releaseAggregation);
        System.out.println(releaseAggregation);
    }

}
