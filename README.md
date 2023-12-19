# JiCode-starter
## 0. 功能

* 自动配置 MyBatis 的读写分离数据源
* 自动依赖注入 MyBatis 的 SqlSessionFactory 
* 实现业务的读写分离

## 1. 配置方法

* 将 release 里最新版的压缩包下载，然后解压到 `C:\Users\<username>\.m2\repository\com\jicode` 目录下（可能需要新建 jicode 目录）
* 在工程中使用以下语句导入该包

```pom.xml
<dependencies>	
	<dependency>
		<groupId>com.JiCode</groupId>
		<artifactId>JiCode-starter</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</dependency>
</dependencies>
```
* 在配置文件中写下如下配置（ mybatis 配置项下也可以自己指定所需资源所在的位置）

```
app:
  datasource:
    master: # 唯一主库
      jdbcUrl: jdbc:mysql://121.41.227.227:3306/jicode?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&allowPublicKeyRetrieval=TRUE&sslMode=REQUIRED
      username: jicode_user
      password: JiCode2024!
    slave: # 多个从库
      slave1:
        jdbcUrl: jdbc:mysql://121.41.227.227:3307/jicode?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&allowPublicKeyRetrieval=TRUE&sslMode=REQUIRED
        username: jicode_user
        password: JiCode2024!
      slave2:
        jdbcUrl: jdbc:mysql://121.41.227.227:3308/jicode?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&allowPublicKeyRetrieval=TRUE&sslMode=REQUIRED
        username: jicode_user
        password: JiCode2024!
  mybatis:
    basePackage: com.JiCode.<artifactId>.adaptor.output.dataaccess.mappers
    typeAliasesPackage: com.JiCode.<artifactId>.adaptor.output.dataaccess.DBModels
    mapperLocations: classpath*:com/JiCode/<artifactId>/**/*.xml
```

现在项目里应该已经可以自动注入了一个 MyBatis 里的 SqlSessionFactory 类，并且配置好了读写分离数据源

## 2. 使用方法

* 在只读业务函数上加上 @Transactional(readOnly = true) ，如下：

``` 
    @Transactional(readOnly = true)
    public String read() {
        ...
    }
```

* 在写业务函数上加上  @Transactional ，如下：

```
    @Transactional
    public String write() {
        ...
    }
```

这样是为业务函数开启一个事务，之后这个业务函数里调用的所有函数都属于这个事务，会根据事务的是否只读标记来自动判断使用哪个数据库里的数据源；也就是以这个业务函数为入口的事务中所有的 Mybatis 数据访问相关的操作都会自动实现读写分离，根据读写标识使用不同数据库的数据源，而从实现自动化读写分离。

