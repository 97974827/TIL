### JSP JDBC 

```mysql
# DB 생성
CREATE DATABASE jsp_practice default character set utf8;

# 유저생성
CREATE USER 'jsp' IDENTIFIED BY 'jsp';

# 권한주기
GRANT ALL PRIVILEGES ON jsp_practice.* TO 'jsp';

# 테이블 생성 
CREATE TABLE member(
	id VARCHAR(20) primary key,
	pw VARCHAR(20) NOT NULL,
	NAME VARCHAR(30) NOT NULL,
	email VARCHAR(80)
);

SELECT * FROM member;

INSERT INTO member (id,pw,NAME) VALUES('abc123', '1234', '홍길동');
INSERT INTO member VALUES('def456', '4433', '이순신', 'aaa@bbb.com');
INSERT INTO member VALUES('weef777', '5687', '이순신', 'ca@bbdasdb.com');
INSERT INTO member VALUES('sadf222', '2020', '장보고', 'hon1@sad.com');
INSERT INTO member VALUES('eqq444', '2223', '유관순', '123333@sed.com');
INSERT INTO member VALUES('dsadfsdef456', '6655', '강감찬', 'kang1@naver.com');
INSERT INTO member VALUES('deasdfsdafsdaff', '5687', '안창호', 'ann3344@sasa.co.kr');
INSERT INTO member VALUES('flashWKDWKDaos', '8484', '이봉창', 'mrlee00@yahoo.co.kr');
INSERT INTO member VALUES('elqldjsdj', '3333', '전봉준', 'sksmsshren@zmzm.com');
INSERT INTO member VALUES('myWKd11', '4666', '각시탈', 'rkrtlxkfdlek@hanmail.net');
INSERT INTO member VALUES('apfhd882', '5365', '최무식', 'choi9876@daum.net');
```