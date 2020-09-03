### centos yum error

- 참고 : https://unix.stackexchange.com/questions/459192/centos-7-no-packages-marked-for-update
  - /etc/resolv.conf  -> dns 추가 이후 `yum clean all`





#### 서버 

\1. socket() API가 통신을 위한 종료점을 작성하고 소켓 객체를 리턴한다. 
\2. Bind()   네트워크 엑세스를 위해 소켓에 바인드 한다. 

\3. Listen() 서버가 연결을 수락하도록 합니다.

\4. accept() 연결을 받아들입니다. 소켓은 주소에 바인드 되어 연결을 리스닝하고 있어야 합니다. 

\5. Send()  소켓에 데이터를 보냅니다. 

  Recv()  소켓에서 데이터를 수신합니다. 

\6. close() 소켓 파일 기술자를 닫습니다.

####  

#### 클라이언트

\1. socket() API가 통신을 위한 종료점을 작성하고 소켓 객체를 리턴한다. 

\2. connect() 파라미터 값인 address에 있는 원격 소켓에 연결합니다.

\3. Send()  소켓에 데이터를 보냅니다. 

  Recv()  소켓에서 데이터를 수신합니다. 

\4. close() 소켓 파일 기술자를 닫습니다.



출처: https://uiandwe.tistory.com/1245 [조아하는모든것]