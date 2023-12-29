package com.JiCode.ProductDev;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Release;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.LogMapper;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ReleaseMapper;
import com.JiCode.ProductDev.domain.factory.ReleaseFactory;
import com.JiCode.ProductDev.domain.model.ReleaseAggregation;
import com.JiCode.ProductDev.domain.repository.ReleaseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
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
    ReleaseFactory releaseFactory;

    @Autowired
    ReleaseRepository releaseRepository;

    @Test
    public void testSelectById(){
        ReleaseAggregation releaseAggregation = releaseRepository.selectById("1");
        System.out.println(releaseAggregation);
    }

    @Test
    public void testInsert(){
        String id = null;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER); // 注意，月份是从0开始的，所以11代表12月
        calendar.set(Calendar.DAY_OF_MONTH, 26);
        Date startTime = calendar.getTime();
        Date endTime = calendar.getTime();
        String topic = "wh";
        String stage = "done";
        ReleaseAggregation releaseAggregation = releaseFactory.createRelease(id, startTime, endTime, "yes", "1", "1", Arrays.asList("1", "2"),topic, stage);
        releaseRepository.insert(releaseAggregation);
    }

    @Test
    public void testGetPage(){
        List<ReleaseAggregation> releaseAggregations = releaseRepository.getPage(1, 10).getList();
        for(ReleaseAggregation releaseAggregation:releaseAggregations){
            System.out.println(releaseAggregation.getId());
        }
    }

    @Test
    public void testUpdate(){
        ReleaseAggregation releaseAggregation = releaseRepository.selectById("1");
        releaseAggregation.setType("no");
        releaseRepository.update(releaseAggregation);
    }

    @Test
    public void testDelete(){
        releaseRepository.deleteById("1");
    }


}
