# Android - Server 개발방안



**`1안. App - 서버 - 공유기 - 단말`**

**`2안. App - 구글서버 - 서버 - 공유기 - 단말`**

- 구글 클라우드 플랫폼 (GCP)
  - 30일만 무료지원
  - 유료 플랫폼
  - 원거리 통신만 가능함 



- 한국 울트라 배연창 

  - 홈페이지 : http://www.hkultra.co.kr/

  - http://hkultra.co.kr/gnuboard4/bbs/board.php?bo_table=p1




### 구조 

- **어플 - 서버**
  - 서버 클라이언트간 데이터 송수신 
  - 서버를 하드웨어 제어하는 데에 둬야함 
  - LAN 통신
  - 통신거리 : 원거리 
- **서버 - 하드웨어 (배연창 제어)** 
  - Push 서버 구현 
  - 무선 시리얼통신 (스레드)
  - DB 계속 고쳐주면 데이터시트에서 받아가면 됨
  - 통신거리 : 사무실 범위정도 



#### 무선랜 포팅

- **7/28 화**
- CentOS 6, 7 테스트
  
  - 무선 어댑터 사용 하지 않았을때 
  
    - 무선랜 잡히지 않았음 
  
  - 무선 어댑터 사용 했을때 
  
    - usb 어댑터 인식은 되지만, 무선랜 드라이버가 잡히지 않았음 
  



- **7/29 수**

  - CentOS 7.8

    - [IPTIME A2000UA USB-Wifi](http://airpage.org/xe/network_data/24833)

    - 무선랜 참고 사이트 

      - https://kecmok.tistory.com/25

    - 미러사이트 - kmod 관련 패키지 전부 추가 해봄 - X 

    - 인텔 사이트 파일 찾아서 추가 해봄 - X

      - https://wireless.wiki.kernel.org/en/users/Drivers/iwlwifi

    - https://linux.cc.iitk.ac.in/mirror/centos/elrepo/elrepo/el7/x86_64/RPMS/ 에서 패키지 설치 - X

      - <img src="캡처.PNG">
      - [USB 인식 과정 - /proc/bus/usb/devices 경로 존재 X](http://forum.falinux.com/zbxe/index.php?mid=network_programming&document_srl=785255) - x

      

    - **iptime n1usb (4500원 : 자체 wifi 수신기 )** - 미니 pc에는 무선랜 카드가 없어서 필요함 

      - https://github.com/terence-deng/mt7601u - 이 드라이버 설치  - O
      - 그대로 따라해야함 
      - <img src="./mt7601u.PNG">
      - `ra0` 인터페이스가 나오긴 했으나 잡히지 않음 

- nmtui 에서 설정 

  - https://unix.stackexchange.com/questions/370318/how-to-connect-to-wifi-in-centos-7clino-gui

  - 무선도 수동으로 ip 줘야함 - **포팅 성공**

  - `/etc/rc.d/rc.local` - 부팅 스크립트 작성 

    - ```shell
      # systemctl restart network
      chmod u+x /etc/rc.d/rc.local
      ```

<img src="./무선랜.PNG">



- 유 / 무선 동시 연결시 주의사항
- 무선 시리얼 통신방법?
  - 무선 랜카드 존재 X
  - **iptime n1usb** 무선 어댑터 사용 중인데 시리얼 통신이 가능한가 
    - fdisk 목록에 무선 파일이 없는거 같음 
    - ip는 잡힌상태 
- 소켓 통신시 구현 로직?
- 통신 테스트
  - App - 서버  - O
    - 모바일로 서버접근할 때 데이터 키고는 접속안됬고, 와이파이로는 접속 됬음 
  - 서버 - H/W - X 
    - test 
      - 1안
        - App - Linux GUI Python
      - 2안
        - App - Server 다중소켓?
      - 3안
        - 199 서버 없이 터치충전기 서버로 연결 - 최적
      - 1:1:1 소켓 통신이 가능한가 ?
        - App - Server - Touch 
      - 안드로이드 서버 - 터치충전기 - 지폐인식기 
- 자바에서 스트림, 파일, 소켓 등등 인스턴스를 생성하면 close()를 하는게 일반적인데 , 이 코드에서 소켓만 close() 시켜줘야하나? 아니면 스트림객체까지 close()를 시켜줘야하나? 

```java
socket = new Socket(host, port);
OutputStream out = socket.getOutputStream();
socket.close();
```

- **사장님 : 서버를 안쓰고 하는 방법?**
  - App - H/W (배연창 8비트)



- 미니 PC 
  - 사양 파악
  - **무선랜 지원 불가능할 경우 대책**?
    - 무선 어댑터 이용해서 처리 





- **Python3 Install**
  - centos7에선 python2 를 제거하면 안됨 - 패키지 종속성 어긋남 
  - https://tecadmin.net/install-python-3-8-centos/

```shell
$ sudo yum install gcc openssl-devel bzip2-devel libffi-devel
$ cd /opt
$ wget https://www.python.org/ftp/python/3.8.3/Python-3.8.3.tgz

$ tar xzf Python-3.8.3.tgz

$ cd Python-3.8.3
$ sudo ./configure --enable-optimizations
$ sudo make altinstall

$ python3.8 -V
```



### MariaDB 설치 및 환경 설정 

- 참고 : [https://twofootdog.github.io/MariaDB-CentOS7%EC%97%90%EC%84%9C-MariaDB-%EC%84%A4%EC%B9%98-%EB%B0%8F-%ED%99%98%EA%B2%BD%EC%84%A4%EC%A0%95/](https://twofootdog.github.io/MariaDB-CentOS7에서-MariaDB-설치-및-환경설정/)

CentOS7에서 MariaDB를 설치하고 프로젝트에 필요한 다양한 환경설정(데이터베이스생성, 외부접속 허용, 계정생성, 권한생성 등 등을 수행해보자

------

### [1. MariaDB repo 설정]

- repo 설정을 하지 않고 그냥 yum install로 mariadb를 설치하게 되면 설치가 진행되지 않는다. 따라서 repo 설정을 진행해야 한다. /etc/yum.repos.d/ 밑에 MariaDB.repo라는 파일을 만든 후 아래와 같이 정보를 입력한다.
- 명령어 : `sudo vi /etc/yum.repos.d/MariaDB.repo`![img](https://twofootdog.github.io/images/mariadb_20190322.jpg)

------

### [2. 설치]

- 명령어 : `sudo yum install MariaDB-server`![img](https://twofootdog.github.io/images/mariadb_20190322_2.jpg)

------

### [3. 확인]

- 명령어 : `rpm -qa | grep MariaDB`![img](https://twofootdog.github.io/images/mariadb_20190322_3.jpg)

------

### [4. DB시작 및 패스워드 변경]

- 명령어
  - `sudo systemctl start mariadb`
  - `sudo /usr/bin/mysqladmin -u root password '2815'`![img](https://twofootdog.github.io/images/mariadb_20190322_4.jpg)

------

### [5. 접속 확인]

- 명령어 : `mysql -u root -p`![img](https://twofootdog.github.io/images/mariadb_20190322_5.jpg)

------

### [6. 부팅 시 자동시작 확인]

- 명령어 : `sudo systemctl is-enabled mariadb`![img](https://twofootdog.github.io/images/mariadb_20190322_6.jpg)

------

### [7. root 계정 외부 접속 허용]

- 마리아DB는 기본적으로 외부접속이 허용되지 않는다. 따라서 특정 계정에게 외부접속 허용 권한을 부여해야 한다.
- 명령어 :
  - 마리아DB 접속 : `mysql -u root -p`
  - database를 mysql로 전환 : `use mysql;`
  - root계정 or 특정 계정에 외부접근 권한 부여 : `grant all privileges on (DB이름).(테이블이름) to '(계정명)'@'(접속위치)' identified by '(계정비밀번호)';`
  - 등록된 권한 확인 : `select host, user, password from user;`
  - refresh : `flush privileges;`![img](https://twofootdog.github.io/images/mariadb_20190322_7.jpg)

------

### [8. 프로젝트용 database 생성]

- 명령어 :
  - database 리스트 확인 : `show databases;`
  - database 생성(기본 UTF8 설정 추가) : `create database (데이터베이스명) default character set utf8 collate utf8_general_ci;`![img](https://twofootdog.github.io/images/mariadb_20190322_8.jpg)

------

### [9. 프로젝트용 사용자 추가]

- 명령어 :
  - 사용자 생성 : `create user '(유저명)';`
  - 사용자 외부 접속 권한 생성 : `grant all privileges on (데이터베이스명).(테이블명) to '(유저명)'@'%' identified by '(비밀번호)';`
  - 사용자 내부 접속 권한 생성 : `grant all privileges on (데이터베이스명).(테이블명) to '(유저명)'@'localhost' identified by '(비밀번호)';`
  - refresh : `flush privileges;`![img](https://twofootdog.github.io/images/mariadb_20190322_9.jpg)

------

### [10. 권한 부여 확인]

- 명령어 : `show grants for '(유저명)'@'(host)';`![img](https://twofootdog.github.io/images/mariadb_20190322_10.jpg)

------

### [11. 계정삭제 & 권한삭제 ]

- 명령어 :
  - 계정삭제 : `drop user '(유저명)'@'(host)';`
  - 권한삭제 : `revoke all on (데이터베이스명).(테이블명) from '(유저명)'@'(host)';`

### [MariaDB utf-8 인코딩 변경]

- /etc/my.cnf.d/client.cnf![img](https://twofootdog.github.io/images/mariadb_20190322_11.jpg)
- /etc/my.cnf.d/mysql-clients.cnf![img](https://twofootdog.github.io/images/mariadb_20190322_12.jpg)
- /etc/my.cnf.d/server.cnf![img](https://twofootdog.github.io/images/mariadb_20190322_13.jpg)
- DB 재시작 : `sudo systemctl restart mariadb`![img](https://twofootdog.github.io/images/mariadb_20190322_14.jpg)

------

*출처 :

- [https://zetawiki.com/wiki/CentOS7_MariaDB_%EC%84%A4%EC%B9%98](https://zetawiki.com/wiki/CentOS7_MariaDB_설치)
- https://wlsufld.tistory.com/40
- https://slobell.com/blogs/38 참고

