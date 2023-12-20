package com.JiCode.ProductDev;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
class ProductDevApplicationTests {

//	@Autowired
//	ScheduleMapper scheduleMapper;

	// 单元测试
	@Test
	void TestSelectAll() throws IOException {
		// 加载mybatis的核心配置文件，获取sessionfactory
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		System.out.println(sqlSessionFactory==null);

		// 获取session对象，用这个对象来执行sql语句
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 获取mapper的代理对象
		ScheduleMapper scheduleMapper = sqlSession.getMapper(ScheduleMapper.class);
		List<Schedule> schedule = scheduleMapper.selectByExample(null);
		System.out.println(schedule);

		// 释放资源
		sqlSession.close();
	}

}
