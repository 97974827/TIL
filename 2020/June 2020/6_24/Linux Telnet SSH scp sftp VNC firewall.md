# [2020-06-24 수 TIL]

### Linux remote service / firewall



#### 1. telnet (텔넷)

- 1990년대 최초 원격 시스템 접속 관리 툴 (window 기본 탑재)
- **보안이 너무 취약함** 

- **Window Client** 환경

  - Port : 23번 
  - 윈도우 cmd - `telnet [원격 IP주소]`
  - 명령 실행안될 때 
    - 제어판 - 프로그램 제거 - windows 기능 켜기 / 끄기 선택 
    - Telnet Client 체크 

- **Linux server** 환경

  - 인터넷이 안되고 yum 불가하고 rpm 으로만 설치해야한다

  - 하지만 인터넷이 안되서 미러사이트에서 패키지 업로드가 불가능 하다 

  - 그러면 마운트 되어있는 cdrom 의 CentOS ISO 파일의 디렉토리를 들어가서 패키지를 검색한다 

    ```sh
    $ umount /dev/sr0 
    $ mount /dev/sr0 /mnt  # 보통 cdrom은 /mnt에 마운트 포인트씀
    $ cd /mnt/Packages/
    
    
    # telnet 패키지 설치
    # ls telnet*
    telnet-0.17-64.el7.x86_64.rpm  telnet-server-0.17-64.el7.x86_64.rpm
    
    $ rpm -ivh telnet-0.17-64.el7.x86_64.rpm
    $ rpm -ivh telnet-server-0.17-64.el7.x86_64.rpm
    
    ```

  - #### **방화벽 설정**

    - port설정
    - 서비스 설정
    - **Daemon (데몬) - 중요**
      - "백그라운드 프로세스"
      - 한 개의 **서비스**는 반드시 한 개의 **데몬**이 존재 (동작) 한다

    ```shell
    # 방화벽 상태확인 
    firewall-cmd --state
    
    # 설정된 포트 확인
    $ firewall-cmd --list-ports
    
    # 23번 포트 설정
    $ firewall-cmd --add-port=23/tcp
    
    # 활성 서비스 확인
    $ firewall-cmd --list-services
    
    # 서비스 설정
    $ firewall-cmd --add-service=telnet
    
    # 모든 설정된 정보보기
    $ firewall-cmd --list-all
    
    public (active)
      target: default
      icmp-block-inversion: no
      interfaces: ens33
      sources: 
      services: dhcpv6-client ssh telnet
      ports: 23/tcp
      protocols: 
      masquerade: no
      forward-ports: 
      source-ports: 
      icmp-blocks: 
      rich rules: 
    
    # 방화벽 재시작
    $ firewall-cmd --reload
    
    # 영구적으로 포트설정
    $ firewall-cmd --permanent --add-port=23/tcp
    
    # 영구적으로 서비스설정 
    $ firewall-cmd --permanent --add-service=telnet
    
    # 영구적으로 포트 삭제
    $ firewall-cmd --permanent --remove-port=23/tcp
    
    # 데몬 이름확인
    # 없으면 yum install setuptool
    $ setup
    
    # 시스템 컨트롤 : 데몬서비스 활성화, 재시작, 상태확인, 멈춤, 시작
    $ systemctl enable 서비스이름
    $ systemctl restart 서비스이름 
    $ systemctl status 서비스이름 
    $ systemctl stop 서비스이름
    $ systemctl start 서비스이름 
    
    # telnet은 특성상 관리자 계정 접속 안됨 
    # 슈퍼유저 권한 접근 
    $ su -
    ```



#### 2. ssh (Secure shell)

- telnet 보다 보안성 강화
- SSH 서비스 구동이유 : `/etc/services` 파일에 서비스가 설정이 되어 있기떄문
- 단, 관리자 접속은 열려있다 



#### <기본 셋팅구조>

| telnet        | ssh                                                          |
| ------------- | ------------------------------------------------------------ |
| 관리자 접속 X | 관리자 접속 O (관리자접속은 기본적으로 막아줘야함 : 문제는 있다 ) |
| 사용자 접속 O | 사용자 접속 O                                                |
| 평문화 전송   | 암호화 전송                                                  |
| 포트 23       | 포트 22                                                      |

- **ICT** : 정보통신기기 

- **원격 접속은 항상 보안을 신경써야한다** 



#### telnet 보안 강화 

- ssh 나오기 까지 시간이 걸렸었는데 **telnet의 보안강화를 위해 krb5 패키지**가 나왔음 
- **krb5** (켈베로스 : 지옥문지기)

- 확인하려면 해커가 뚤리나안뚤리나 확인해야함 
- 패키지 설치 이후 텔넷 접속시 접속되면 적용됨



#### ssh - 관리자 계정 접속 설정 `/etc/ssh/sshd_config `

```shell
# ip 설정
ListenAddress 0.0.0.0

# root 접속 허용/불가
PermitRootLogin yes
```

***

#### ssh - scp / sftp

- **ssh 이용하여  데이터 전송 관련명령**

- #### SCP  

  -  cp에 s 하나 붙인 것과 같이 사용형식이 비슷함

  - Download

    - `scp samadal@192.168.1.101:/tmp/A /client`   

  - Upload

    - `scp /tmp/A samadal@192.168.1.101:/client`   

      

- #### sftp

  - ssh 이용하여 ftp 이용 

  - `sftp [유저명@IP주소] `

    ```shell
    # sftp 접속 전에 다운받을 경로 확인
    
    # 업로드
    sftp> put [파일명]   
    
    # 다운로드
    sftp> get [파일명]
    ```



#### 3. VNC (Virtual network computing)

- GUI  그래픽 원격 모드
- 포트 : 5900 - 모를 땐 `/etc/services` 파일에서 찾기
  - 포트번호가 1번이여도 접속 가능함 1=5901 alias 

```shell
$ .vnc/xstartup 파일이 실행되면 구축됨 

[root@localhost ~]# cd .vnc
[root@localhost .vnc]# ls -l
합계 24
-rw-r--r--. 1 root root  332  6월 24 17:29 config
-rw-r--r--. 1 root root 5491  6월 24 17:46 localhost.localdomain:1.log
-rw-r--r--. 1 root root    6  6월 24 17:29 localhost.localdomain:1.pid
-rw-------. 1 root root   16  6월 24 17:29 passwd
-rwxr-xr-x. 1 root root  540  6월 24 17:29 xstartup

```



#### 프로세스 명령

```shell
# 검색
$ ps -ef | grep [데몬이름 및 명령어]

# 죽이기
kill -9 [pid]
																	   [명령어동작]
[계정] [PID] [PPID] [CPU점유율]  [시간] [터미널창동작] [얼마동안 사용한시간]  [데몬프로그램이름]
root   2604  2446     0        09:09   pts/0      00:00:00         grep --color=auto vnc

```





***



#### ▶ 시스템 보안 로그 확인

- https://blog.naver.com/innerbus_co/221439737250

- `/var/log/secure` 경로확인 - 로그인 정보 

