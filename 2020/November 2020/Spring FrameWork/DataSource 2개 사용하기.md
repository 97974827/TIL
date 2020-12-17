### DataSource 2개 사용하기

행이™ 2016. 4. 18. 10:40

일반적으로는 1개의 DB만 연결하지만

2개의 DB에서 조회라도 하는 경우가 생겼을 때

요것들만 손대주면 가뿐할 듯 하다.

 

전자정부프레임워크, iBatis를 사용한 경우이다.

Spring, MyBatis도 그리 다르지 않으니... 양해를... 쿨럭;;;

 

 

\1. xxx-datasource.xml

 

DB-A에 대한 선언
<bean id="dataSource1" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
  <property name="driverClassName" value="JdbcDriverClass정보"/>
  <property name="url" value="접속정보" />
  <property name="username" value="접속ID"/>
  <property name="password" value="접속Password"/>
</bean>


DB-B에 대한 선언
<bean id="dataSource2" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
  <property name="driverClassName" value="JdbcDriverClass정보"/>
  <property name="url" value="접속정보" />
  <property name="username" value="접속ID"/>
  <property name="password" value="접속Password"/>
</bean>

 

 

\2. xxx-sqlMap.xml

 

DB-A에 대한 선언
<bean id="sqlMapClient**1**" class="org.springframework.orm.ibatis.SqlMapClientFactoryBean">
  <property name="configLocation" value="classpath:/egovframework/sqlmap/sql-map-config**1**.xml"/>
  <property name="dataSource" ref="dataSource1"/>
</bean>


DB-B에 대한 선언
<bean id="sqlMapClient**2**" class="org.springframework.orm.ibatis.SqlMapClientFactoryBean">
  <property name="configLocation" value="classpath:/egovframework/sqlmap/sql-map-config**2**.xml"/>
  <property name="dataSource" ref="dataSource2"/>
</bean>

※ **1**개 DB로 사용할 때 정의했었던 파일들이

  모두 **2**개씩 필요하다.

 

※ sql-map-config.xml 파일은 사용할 SQL들이 들어있는 xml파일들을 기술하는 파일이다.

※ sql-map-config.xml이나 SQL들이 들어있는 xml파일들은

  DB를 1개 사용할 때처럼 해당 DB 기준으로 편하게 사용하면 된다.

 

 

 

DB-A에 대한 DAO 정의

: 전자정부프레임워크에서는 기본으로 dataSource를 사용하도록 되어있으니

 별다른 처리를 하지 않아도 된다.

 (dataSource id 와 sqlMapClient id를 변경하지 않았을 경우)

: 결론은, 별도로 손을 대지 않아도 된다는...;;;

3.1. xxxDAO**1**.java

 

@Repository("xxxDAO**1**")
public class xxxDAO**1** extends EgovAbstractDAO {

  ....
}

 

 

DB-B에 대한 DAO 정의 (**※중요****※**)

3.2. xxxDAO**2**.java

 

@Repository("xxxDAO**2**")
public class xxxDAO**2** extends EgovAbstractDAO {

 

  @Resource(name = "sqlMapClient**2**")  **←** **※****중요※**
                             DB-B용으로 정의한 sqlMapClient를 셋팅하라는 의미.

  public void setSuperSqlMapClient( SqlMapClient sqlMapClient ) {
    super.setSqlMapClient( sqlMapClient );
  }

 

  ....
}

 

 

\4. DAO를 사용하는 Class에서는

  필요한 DAO에 대한 정의만 하면 된다.

 

  @Resource(name = "xxxDAO**1**")
  private xxxDAO**1** xxxDAO**1**;

 

  @Resource(name = "xxxDAO**2**")
  private xxxDAO**2** xxxDAO**2**;

 

 

준비는 끝.

이제 사용하면 된다는...

아니라면, Spring 기본을 좀 더 파야할 듯...^^;



출처: https://lilymate.tistory.com/480 [행이네]