### Spring, Mybatis 에서 다중 DB 연결하기

Spring, Mybatis 를 가지고 포탈 프로젝트를 하는 중에 Cognos 레파지토리 DB를 봐야할 일이 생겨서 기존에 연결되있는 Oracle DB 에다가 추가적으로 MSSQL 을 연결해보는데 의외로 금방 해결 ㅋ

application-context.xml 만 건드리면 끝!


일단 기존에 추가되어 있던 오라클 연결 부분이다.

Datasource, TransactionManager, sqlSessionFactory, sqlSesstion이 차례대로 지정되어 있다.

[![img](http://4.bp.blogspot.com/-0h6sVKih4kw/VI-ZCqqGA4I/AAAAAAAAAQ4/R1xoG4Q9q1I/s1600/%EB%8B%A4%EC%A4%91DB%EC%97%B0%EB%8F%991.PNG)](http://4.bp.blogspot.com/-0h6sVKih4kw/VI-ZCqqGA4I/AAAAAAAAAQ4/R1xoG4Q9q1I/s1600/다중DB연동1.PNG)




다음으로 추가한 부분이다.

MS SQL 추가에 맞게 그대로 값을 넣어주면 된다.

***) 주의할 점은 TransactionManager에 sqlSessionFactory의 datasource를 바로위에서 새로 추가해준 datasource 명을 넣어줘야 하고 sqlSession에서 sqlSessionFactory 명을 추가해준 이름으로 알맞게 바꿔주어야 한다.** 
**(복붙하다가 실수하면 망)**



[![img](http://4.bp.blogspot.com/-jHAYVSbRJ6Q/VI-ZmV0-RtI/AAAAAAAAARU/5KSBD6kjX1U/s1600/%EB%8B%A4%EC%A4%91DB%EC%97%B0%EB%8F%992.PNG)](http://4.bp.blogspot.com/-jHAYVSbRJ6Q/VI-ZmV0-RtI/AAAAAAAAARU/5KSBD6kjX1U/s1600/다중DB연동2.PNG)

그리고 DB 연결이 1개 있을 때는 control단과 dao 단에서 autowired 하면 자동으로 그 데이터 소스로 연결이 되었지만 2개 이상일 때는 이름으로 지정해줘야 한다.

@Resource 어노테이션을 사용해서 다음과 같이 지정해주면 된다.

[![img](http://2.bp.blogspot.com/-TnY9LODvw-A/VI-ZCrx37xI/AAAAAAAAAQ0/59FFhsxGYBM/s1600/%EB%8B%A4%EC%A4%91DB%EC%97%B0%EB%8F%993.PNG)](http://2.bp.blogspot.com/-TnY9LODvw-A/VI-ZCrx37xI/AAAAAAAAAQ0/59FFhsxGYBM/s1600/다중DB연동3.PNG)



[![img](http://4.bp.blogspot.com/-_5nTYAs086w/VI-ZDvz1_9I/AAAAAAAAAQ8/4_-6-UMs_Ok/s1600/%EB%8B%A4%EC%A4%91DB%EC%97%B0%EB%8F%994.PNG)](http://4.bp.blogspot.com/-_5nTYAs086w/VI-ZDvz1_9I/AAAAAAAAAQ8/4_-6-UMs_Ok/s1600/다중DB연동4.PNG)



지정 안해주면 톰캣 올릴 때 오류남