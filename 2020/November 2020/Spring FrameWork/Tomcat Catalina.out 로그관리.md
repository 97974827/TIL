# Tomcat catalina.out 로그관리

톰캣 catalina.out 크키 커지는것 막기

https://enterkey.tistory.com/396





omcat 실행중에 발생하는 모든 종류의 로그는 기본값으로 catalina.out 파일에 기록이 됩니다. 따라서, 별도의 관리를 하지 않으면 파일이 끊임없이 커져서 여러가지 문제를 발생시킬수 있습니다. 아래는 catalina.out 파일을 관리하는 여러가지 예시입니다. 상황에 맞게 사용하면 될거 같습니다.

 https://roadrunner.tistory.com/634

**1. catalina.out 에 로그를 기록하지 않는 방법**

 

  vi /Apache Tomcat설치 경로/bin/catalina.sh

  

  파일이 열리면 아래 그림의 노란색 실선으로 그어진 부분을 찾아서



![img](https://blog.kakaocdn.net/dn/qKCvf/btqAmsBc7Ur/w0gHZwhtq49Xe3zyCGye70/img.png)



  노란색 실선 부분을 아래와 같이 고쳐줍니다.

 

​    touch "$CATALINA_OUT" **-->** #touch "$CATALINA_OUT"

​    \>> "$CATALINA_OUT" 2>&1 "&" **-->** >> /dev/null 2>&1 &

 



![img](https://blog.kakaocdn.net/dn/bvH3ew/btqAjmWX32s/yHolJDRAJShA9C0dk3I6ck/img.png)



  이제 서버 상태로그만 catalina.yyyy-mm-dd.log 파일에 기록이 되고 catalina.out 에는 로그가 기록되지 않습니다.

 

  주의해야 할 것은 프로그램 실행과정에 발생하는 어떠한 로그도 catalina.out 에 기록되지 않기 때문에 log4j 등으로 프로그램에서 발생하는 로그를 기록할 수 있도록 별도의 작업을 해야 합니다.

 

 

**2. catalina.out 파일을 날짜별로 따로 저장하는 방법**

 

  \>> /dev/null 2>&1 & 대신 >> "$CATALINA_OUT".$(date '+%Y-%m-%d') 2>&1 "&" 로 바꾸면 됩니다.



![img](https://blog.kakaocdn.net/dn/cH7ZQF/btqAkStTw3e/GKuKDNmhCkGVc0vvdLSgpk/img.png)



  위와 같이 고치면 catalina.out 파일이 catalina.out.yyyy-mm-dd 형식으로 매일 하나씩 생성이 됩니다.

 

 

**3. Apache 의 rotatelogs 를 이용해서 일자별로 로그파일을 생성하는 방법**

 

  참고 : https://mycup.tistory.com/216

​      https://share9.tistory.com/39



출처: https://roadrunner.tistory.com/634 [삶의 조각들]