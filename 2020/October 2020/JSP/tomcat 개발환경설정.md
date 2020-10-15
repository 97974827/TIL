

### 이클립스 설정 

1. 이클립스에 플러그인 java EE 설치  - Maven 
2. 이클립스 한글 인코딩 변경 - Window - preference 클릭 - encoding검색 General - workspace 에 MS-949 -> UTF-8 로 변경 
3. Web (CSS files, HTML Files, JSP Files) : Korean, KUC-KR -> UTF-8
4. Window - Preferences 에서 General - web Brower (Chrome 선택)



### tomcat 개발환경설정

- tomcat 다운로드 사이트 : https://tomcat.apache.org/download-90.cgi 

- core - 압축 파일 (9버전 사용)

- 압축 파일로 셋팅하기 

  

# 1. 개발환경 세팅(3) 아파치 톰캣 apache tomcat 설치 및 환경변수 설정

지엔키 2019. 1. 17. 23:34



\1. http://tomcat.apache.org/ 에 접속합니다.



![img](https://t1.daumcdn.net/cfile/tistory/998ACC445C408F320C)





\2. 왼쪽의 Download 의 Tomcat 8 부분을 클릭합니다.



![img](https://t1.daumcdn.net/cfile/tistory/9988F9445C408F331E)



\3. Core 의 64-bit windows.zip 을 클릭하여 파일을 다운받아 줍니다. (자신의 버전에 맞게!)

다운받은 파일의 압축을 풀면 



![img](https://t1.daumcdn.net/cfile/tistory/9976BF445C408F3405)



이런 폴더가 나오는데 자신이 아파치 톰캣을 설치하고 싶은 폴더에 복사 붙여넣기 하여줍니다.

저의 경우는 C드라이브 바로 아래에 붙여넣었습니다.



![img](https://t1.daumcdn.net/cfile/tistory/99F762445C408F3409)





\4. 이렇게 폴더를 붙여넣기 한 후 위의 주소창을 클릭하면 해당 폴더의 경로를 알 수 있는데

해당 폴더의 경로를 복사해둡니다.



![img](https://t1.daumcdn.net/cfile/tistory/99D27B445C408F3401)





\5. 내 PC 를 마우스 우클릭 한 후 속성(R) 을 누르고 왼쪽의 고급 시스템 설정 을 클릭합니다.



![img](https://t1.daumcdn.net/cfile/tistory/995366445C408F340E)





\6. 아래쪽의 환경 변수(N).. 버튼을 클릭합니다.



![img](https://t1.daumcdn.net/cfile/tistory/99145E445C408F3508)



\7. 아래쪽의 시스템 변수(S) 의 새로 만들기(W).. 버튼을 클릭합니다.



![img](https://t1.daumcdn.net/cfile/tistory/99589B445C408F3506)



\8. 변수 이름(N) : CATALINA_HOME

변수 값(V) : 위에서 자신이 복사해두었던 경로를 입력합니다.

저의 경우는 C드라이브 바로 아래에 폴더를 붙여넣기 했기 때문에

C:\apache-tomcat-8.5.37

이 되겠네요.

입력한 뒤 확인 버튼을 눌러줍니다.



![img](https://t1.daumcdn.net/cfile/tistory/997AC14D5C408F360D)





\9. 시스템 변수 중 Path 인 것을 찾아 선택한 후 편집(I)... 을 눌러줍니다.



![img](https://t1.daumcdn.net/cfile/tistory/9946A44D5C408F3606)



\10. 새로 만들기(N) 을 클릭한 후



![img](https://t1.daumcdn.net/cfile/tistory/991FA84D5C408F3619)





\11. %CATALINA_HOME%\bin

을 입력하고 확인을 눌러 줍니다.





![img](https://t1.daumcdn.net/cfile/tistory/997CF34D5C408F360D)





\12. 해당 폴더에 bin 폴더에 들어가서 startup.bat 파일을 실행시키면 

까만 콘솔창이 뜨면서 서버가 실행되는데

서버가 실행되고 콘솔창을 끄지 않은 상태에서

인터넷 주소창에 http://localhost:8080/ 를 입력한 후 



![img](https://t1.daumcdn.net/cfile/tistory/99C2554D5C408F3701)



위와 같은 화면이 뜬다면 설치가 성공한 것입니다!



- 주의사항 
  - 톰캣 startup.bat 파일 끄면 톰캣 실행 X
  
  - 톰캣 설치다음 이클립스 설정에서 서버타입에 톰캣이 보이지 않을떄 https://blog.naver.com/hjinha2/221180700448
  
  - Servers - 서버 생성 - 아파치 톰캣 9선택 - Tomcat installation directory 잡아줘야함 
  
  - 톰캣 설치 경로 파일 경로 설정 해야함
  
  - <img src="./이클립스 톰캣생성후 설정.PNG">
  
  - <img src="./이클립스 톰캣생성후 설정2.PNG">
  
  - 안전 하게 수동 변경하기 위해 체크 해야함
  
     
  
  - <img src="./이클립스 톰캣생성후 설정3.PNG">
  
  - 서버 설정 변경 이후 저 버튼을 클릭해줘야 동기화가 된 상태 
  
    
  
  - <img src="./이클립스 톰캣생성후 설정4.PNG">
  
  - 프로젝트 파일 - Servers 삭제 하면안됨
  
    
  
  - 프로젝트 생성
    - New - Dynamic Web Project 
      - 이름 설정 후 Web Module 에서 박스 체크 
      - <img src="./톰캣 프로젝트 생성.PNG">

