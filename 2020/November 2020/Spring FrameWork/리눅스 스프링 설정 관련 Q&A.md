## 데이터 수집장치 비즈니스 로직

- [출처 - 리눅스에 spring 개발환경 설치](https://velog.io/@max9106/Spring-%EB%A6%AC%EB%88%85%EC%8A%A4%EC%97%90-spring-%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD-%EC%84%A4%EC%B9%98-n6k4r22kre)



### 운영체제 : CentOS 7.8 사용예정

- 이 WAS 의 역할?
  - json형태의 데이터를 송수신 할 수 있으면서 동시에 시리얼통신도 가능해야한다.
  - 시리얼통신은 jssc 라이브러리 사용예정 
  - 데이터는 수집 목적으로 이용하며 DB가 존재함 
  - 클라이언트 : JavaFX , 모바일 앱 (통신방식 : json 파싱) 



### 1. 필요 프로그램 

- jdk
- apache tomcat
- mysql



### 2. 외부 라이브러리 사용 계획 목록

- mysql connector
- spring JDBC template
- mybatis
- mybatis-spring
- HikariCP (커넥션풀)
- jssc (485 시리얼 통신)
- Spring Security
- Spring Test
- jackon-databind (JSON 파싱)





### Q & A

- 리눅스에서 homeController 실행시키면 되나?
  - 커맨드라인 실행문제 ..  -> 이클립스에서 말고 커맨드라인에서 자바 WAS를 실행시킬때

```shell
# homeController가 com.spring.datacollect.mvc.controller 패키지면 
# 커맨드라인에서 컴파일옵션은 어떻게 줘야하는가?
$ javac -d ..??
```

- **A : \1. 리눅스환경 애플리케이션 실행건에 관해서** 
  
  
  
  **\- 먼저 웹앱을 WAR로 빌드한다.**
  
  **\- 빌드방법에 관해선 메이븐을 사용하시면 됩니다.**
  
  **\- 명령어 `$ mvn package`**
  
  **\- pom.xml에** 
  
  **<artifactId>myapp</artifactId>**
  
  **<version>1.0-SNAPSHOT</version>**
  
  **<packaging>war</packaging>**
  
  
  
  **이라고 설정시 프로젝트 내부 target폴더에 `myapp-1.0-SNAPSHOT.war`가 생성됩니다.**
  
  
  
  **\- 이 파일의 이름을 ROOT.war로 변경해주세요.**
  
  **\- 그리고 리눅스 환경에 설치된 톰캣의 webapp 폴더에 넣어 주세요.**
  
  **\- 그리고 /tomcat/bin 폴더 내부의 startup.sh 파일을 실행시켜 주세요. 그러면 톰캣이 실행되며 webapp폴더에 넣어둔 ROOT.war 의 압축이 풀리며 웹앱이 실행됩니다.**
  
  

***



- 톰캣 포트를 기본포트에서 다른포트로 변경가능한가? (ex) 80 -> 5000
  - 기존의 restful 서버는 파이썬이였고 공유기를 통한 5000-50000 포트를 사용중이여서

**A. \2. 톰캣 포트변경**

**\- /tomcat/conf 디렉토리 내부에 server.xml에서 수정합니다.**

**`<Connector port="8080" protocol="HTTP/1.1"**

​        **connectionTimeout="20000"**

​        **redirectPort="8443" />`**

**\- 이 부분의 포트를 변경해주세요.**



***



- 프로그램은 jdk, tomcat, mysql 만 설치하면되나? (스프링 관련은 설치안하나?)

- CLI 서버인데 톰캣설치 후 확인은 어떻게 하나?

  

- 메이븐 프로젝트를 이용해야한다면 스프링레거시 프로젝트와 달리 webapp 폴더가 존재하지않아 web.xml 그리고 root-context.xml 파일과 servlet-context.xml 파일이 없는데 여기에 설정은 어떻게 할 것인가? 

**A. \4. 메이븐 프로젝트의 경우 webapp디렉토리는 수동으로 만들어주셔야 합니다.**

**아래와 같은 구조로 만들어 주시고 관련 xml파일들은 강의에서 사용했던 방식처럼 셋팅해주시면 됩니다.**

**├ src**

**│  ├ main**

**│  │  ├─ java**

**│  │  ├─ resources**

**│  │  └─ webapp**

**│  │        ├── static**

**│  │        └── WEB-INF**

**│  └ test**

**│     ├─ java**

**│     └─ resources**

**│**

**├ target** 

**└ pom.xml**



***



- 위에 적어놓은 외부 라이브러리들 전부 사용가능한가?   

**A. 외부 라이브러리는 pom.xml에 의존성 설정만 해두시면 메이븐 센트럴에서 알아서 관리해주며 빌드시 자동포함되니 배포환경에 따른 변화에 대해서는 걱정하지 않으셔도 됩니다.**



***



- 최종적으로 서비스 이용하려면 내가만든 스프링프로젝트와 톰캣 이 2가지 프로그램을 항상 가동시켜야 하나?

**A. 빌드된 스프링 앱을 톰캣 webapp폴더에 올리신 후 1번의 방식으로 톰캣만 24시간 실행되는 환경을 만들어주시면 무중단 서비스가 됩니다.**



***



- 추후 웹 클라이언트로 진행한다면 JSP를 병행하며 사용하여 하나의 WAS 프로젝트로 만들어서 진행해도 되나? 

**A. 물론 하나의 WAS프로젝트로 진행하셔도 되며 프론트엔드 서버와 백엔드 API 서버를 따로 운영하셔도 됩니다. 프로젝트 규모나 서비스 목적에 따라 변화가능한 부분입니다.**

***



- 리눅스에서 빌드를 할 떄 어떻게 해야하는가?
  1. 윈도우 이클립스에서 프로젝트 전체 소스코드를 업로드(sftp & git) 하여 커맨드라인에서 실행
  2. jar/war 파일 생성하여 업로드하여 실행
     - 참고사이트 https://www.hanumoka.net/2018/05/21/centOs-20180521-centos-deploy-spring-war/

**A.  리눅스에서 빌드도 빌드도구 메이븐을 활용해서 똑같이 진행하시면 됩니다.**

**혹시 깃허브에서 풀링해서 배포하시는 경우라면 커스텀 쉘스크립트를 짜셔야 합니다.**

**이부분은 내용이 깊어지니 관련 서적이나 문서를 참고하셔야 할 것 같습니다.**