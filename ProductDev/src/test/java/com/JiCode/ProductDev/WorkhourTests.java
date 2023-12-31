package com.JiCode.ProductDev;

import com.JiCode.ProductDev.domain.factory.SprintFactory;
import com.JiCode.ProductDev.domain.model.SprintAggregation;
import com.JiCode.ProductDev.domain.model.WorkhourAggregation;
import com.JiCode.ProductDev.domain.repository.SprintRepository;
import com.JiCode.ProductDev.domain.repository.WorkhourRepository;
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
public class WorkhourTests{

    @Autowired
    WorkhourRepository workhourRepository;


    @Test
    public void testSelectBySchedule() throws Exception{
        List<WorkhourAggregation> workhourAggregations = workhourRepository.selectBySchedule("1");
        for(WorkhourAggregation workhourAggregation : workhourAggregations){
            System.out.println(workhourAggregation);
        }
    }

}
