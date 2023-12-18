// import org.apache.ibatis.session.SqlSessionFactory;
// import org.mybatis.spring.SqlSessionFactoryBean;
// import org.mybatis.spring.annotation.MapperScan;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.core.io.support.PathMatchingResourcePatternResolver;

// @Configuration
// @MapperScan(basePackages = "com.JiCode.ProductDev") // 指定 MyBatis Mapper
// 接口的包路径
// public class MyBatisConfig {

// @Autowired
// private DataSource dataSource; // 这里会自动注入 MSDataSourceConf 中定义的数据源

// @Bean
// public SqlSessionFactory sqlSessionFactory() throws Exception {
// SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
// factoryBean.setDataSource(dataSource); // 设置数据源
// factoryBean.setMapperLocations(
// new
// PathMatchingResourcePatternResolver().getResources("classpath*:com/JiCode/ProductDev/**/*.xml"));
// // 指定
// // MyBatis
// // Mapper
// // XML
// // 文件的位置
// return factoryBean.getObject();
// }
// }