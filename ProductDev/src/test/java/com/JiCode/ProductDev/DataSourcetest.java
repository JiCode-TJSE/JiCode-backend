package com.JiCode.ProductDev;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ScheduleMapper;
import com.JiCode.ProductDev.application.testApplication;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataSourcetest {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testSqlSessionFactory() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);
            List<Schedule> schedule = mapper.selectAll();
            System.out.println("\033[31m" + "这是红色的文字" + "\033[0m");
            System.out.println(schedule);
            // 这里替换为你的测试查询
            // YourEntity entity = mapper.selectById(1);
            // Assertions.assertNotNull(entity);
        }
    }

    @Autowired
    private testApplication scheduleService;

    @Test
    public void testGetAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        assertNotNull(schedules, "schedules should not be null");
        // 这里可以添加更多的断言来检查 schedules 的内容
        System.out.println(schedules);
    }
}