```
 at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$201(ScheduledThreadPoolExecutor.java:180)
        at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:293)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
        at java.lang.Thread.run(Thread.java:748)
Caused by: org.apache.ibatis.exceptions.PersistenceException:
### Error querying database.  Cause: org.springframework.jdbc.CannotGetJdbcConnectionException: Failed to obtain JDBC Connection; nested exception is java.sql.SQLException: HikariDataSource HikariDataSource (HikariPool-1) has been closed.
### The error may exist in file [/opt/tomcat/apache-tomcat-9.0.39/webapps/ROOT/WEB-INF/classes/mappers/device/DeviceMapper.xml]
### The error may involve kr.gls.myapp.device.repository.IDeviceMapper.selectDeviceAddr
### The error occurred while executing a query
### Cause: org.springframework.jdbc.CannotGetJdbcConnectionException: Failed to obtain JDBC Connection; nested exception is java.sql.SQLException: HikariDataSource HikariDataSource (HikariPool-1) has been closed.
        at org.apache.ibatis.exceptions.ExceptionFactory.wrapException(ExceptionFactory.java:30)
        at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:150)
        at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:141)
        at sun.reflect.GeneratedMethodAccessor56.invoke(Unknown Source)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:433)
        ... 17 more
Caused by: org.springframework.jdbc.CannotGetJdbcConnectionException: Failed to obtain JDBC Connection; nested exception is java.sql.SQLException: HikariDataSource HikariDataSource (HikariPool-1) has been closed.
        at org.springframework.jdbc.datasource.DataSourceUtils.getConnection(DataSourceUtils.java:82)
        at org.mybatis.spring.transaction.SpringManagedTransaction.openConnection(SpringManagedTransaction.java:82)
        at org.mybatis.spring.transaction.SpringManagedTransaction.getConnection(SpringManagedTransaction.java:68)
        at org.apache.ibatis.executor.BaseExecutor.getConnection(BaseExecutor.java:338)
        at org.apache.ibatis.executor.SimpleExecutor.prepareStatement(SimpleExecutor.java:84)
        at org.apache.ibatis.executor.SimpleExecutor.doQuery(SimpleExecutor.java:62)
        at org.apache.ibatis.executor.BaseExecutor.queryFromDatabase(BaseExecutor.java:326)
        at org.apache.ibatis.executor.BaseExecutor.query(BaseExecutor.java:156)
        at org.apache.ibatis.executor.CachingExecutor.query(CachingExecutor.java:109)
        at org.apache.ibatis.executor.CachingExecutor.query(CachingExecutor.java:83)
        at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:148)
        ... 22 more
Caused by: java.sql.SQLException: HikariDataSource HikariDataSource (HikariPool-1) has been closed.
        at com.zaxxer.hikari.HikariDataSource.getConnection(HikariDataSource.java:96)
        at org.springframework.jdbc.datasource.DataSourceUtils.fetchConnection(DataSourceUtils.java:158)
        at org.springframework.jdbc.datasource.DataSourceUtils.doGetConnection(DataSourceUtils.java:116)
        at org.springframework.jdbc.datasource.DataSourceUtils.getConnection(DataSourceUtils.java:79)
        ... 32 more

```

00331-10000-00001-AA157