# [2020-06-30 화 TIL ]

## Linux Database Server



- CentOS 미러사이트 
  - **http://mirror.centos.org/**
- mysql **5.3이상** 부터는 무료지원이 아니라 유료지원 바뀜 
- CentOs 7. 부터는 mysql 이 **mariadb**로 대체됨 
- 패키지 : **mariadb***
- 포트: **3306**
- 서비스 : **mysql**
- 데몬 : **mariadb**
- `./mysqld ` 로깅
  - 실행 경로 : `/usr/libexec`

***

#### `mysql -u root -p mysql`

- mysql : 명령어
- -u : DB의 사용자 계정 

- root : DB의 관리자 계정 
- -p : 패스워드
- mysql : DB의 이름 
  - **마스터 DB** 
  - **슈퍼 DB**
    
    - **mysql 대표적 데이터 베이스 이름**
    
      

- 크기순 : DB > Table > Record > Field > Value
- Value 만 대소문자 구분함 

- **ctrl + L   :   clear 기능**



| 명령어                                           | DB 에서 사용 | Table 에서 사용 |
| ------------------------------------------------ | ------------ | --------------- |
| create                                           | O            | O               |
| drop                                             | O            | O               |
| select                                           | X            | O               |
| delete                                           | X            | O               |
| update                                           | X            | O               |
| insert                                           | X            | O               |
| alter                                            | O            | O               |
| show                                             | O            | O               |
| use                                              | O            | X               |
| desc<br>describe<br>explain<br>(세 개 전부 같음) | X            | O               |



### < mysql : 마스터DB 초기값 설정>

- 최소한 이렇게 설정을 해두어야 한다 
- user 테이블 
  - password 넣기 
  - user 공란 삭제
- db 테이블
  - 비우기 

<img src="./마스터 DB 초기값설정.PNG">





#### command line

#### show

```shell
# 데이터베이스 목록조회
$ show databases;

# 테이블 조회
$ show tables;
```

- **DB**(대문자) : 데이터베이스 이름
- **db**(소문자) : DB와 DB를 사용할 계정을 연동시키는 테이블 
- **user** : DB의 계정이 생성되는 테이블



#### use, desc

```shell
# mysql 라는 데이터베이스 적용
$ use mysql;

# test 라는 데이터베이스 적용 
$ use test;

# db라는 테이블 목록조회
$ desc db;

# user라는 테이블 목록조회
$ desc user;
```

- db table

  - host : IP

  - Db : DB

  - User : user

    

#### create

```mysql
# dbsamadal 이라는 데이터베이스 생성
create database dbsamadal;

# 필드 존재 X
create table tbsamadal;
ERROR 1113 (42000): A table must have at least 1 column

# 문법에러
create table tbsamadal (num, name);
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near ' name)' at line 1

# tbsamadal table 생성 
MariaDB [mysql]> create table tbsamadal (num int(3), name char(15));
Query OK, 0 rows affected (0.01 sec)

```

- 테이블을 생성할때는 **Field** 와 **Type** 이 항상 같이 온다 

  

#### drop

```mysql
# dbsamadal 이라는 데이터베이스 제거
drop database dbsamadal;

# tbsamadal 이라는 테이블 제거
drop table tbsamadal;
```



#### select

```mysql
# 유자라는 테이블에서 모든 정보 검색
select * from user;

select host, user, password from user;

select host, db, user from db;
```

- 필드의 이름을 검색할때는 순서가 상관이 없다 

  

#### delete

````mysql
delete from [테이블명] [조건식];
						where [필드명]=['값'];

# user테이블에서 user필드가 공란일때 레코드 삭제 
delete from user where user='';
````



#### update : 테이블 값 변경 

```mysql
# 없는 형식 추가
update [테이블명] set [필드명]=['바뀌어지게 될 값'] where [조건식];

# user필드가 ;root;까지간 패스워트 변경                                                      
update user set password=password('samadal') where user='root';


```



#### insert

```mysql
1) 
insert into [테이블명] (필드1,필드2,...) values ('값1','값2',...));

insert into user (host,user,password) 
values ('localhost', 'usersamadal', password('samadal'));

2) 
# 값의 갯수는 필드를 생략할 경우 테이블에 있는 필드의 총 갯수 만큼 값을 써줘야함 
insert into [테이블명] values ('값1','값2','...')

# db 테이블에 계정 연동 시키기 - 값 전부 넣어주기 
insert into db values ('localhost', 'dbsamadal', 'usersamadal',
'y','y','y','y','y','y','y','y','y','y','y','y','y','y','y','y','y','y','y');

```

- **char** / **varchar** 차이점
  - char 
    - 고정적으로 잡음 
    - 15자리를 선언하면 반드시 15자리가 들어가야한다 
  - **varchar**
    - 포괄적으로 잡음 
    - 메모리 절약 



#### alter 

- 필드와 타입을 작업을 해주는 명령
- 테이블 필드 속성 타입 변경 
  - 작업
    - **add** : 추가
    - **change** : 변경 (타입, 필드이름 전부 변경 가능) / modify : 타입만 변경가능 
    - **drop** : 삭제

```mysql
# 바꿀때
alter table [테이블명] change [기존필드명] [바꿀필드명] [바꿀타입](크기)

# 기존에 있던 필드 tel을 phone으로 변경, 타입은 varchar(13)
alter table tbsamadal change tel phone varchar(13) null default null;

# phone 필드 삭제 
alter table tbsamadal drop phone;

# tel 필드추가 varchar(13) 타입
alter table tbsamadal add tel varchar(13) not null;
```



