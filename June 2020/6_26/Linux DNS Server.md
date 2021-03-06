# [2020-06-26 금 TIL]

### Linux

### DNS Server

- **D**omain **N**ame **S**erver

- **DNS는 다른 패키지와 다르게 패키지/방화벽/데몬 이름들이 다 다르다** 
- `도메인으로 검색하면 IP 형식으로 바꿔주는 시스템 `
- `사용자들이 쉽게 사용할 수 있도록 만든 시스템 `
- 클라이언트의 한번 검색하면 주소를 매핑(기억) 할수 있게 해줌 - 두번 검색이상부턴 검색속도가 빠름 

- **패키지 이름** 
  - **bind***
- **방화벽** 
  - domain : port 53
  - 서버 : tcp 필요 , 클라이언트 : udp 필요 
  - 서비스 : dns

- Selinux 방화벽 - centos  6.6 이상
  - 별도 방화벽 차단 
  - 경로 : `/etc/selinux/config`

```shell
 1 
 2 # This file controls the state of SELinux on the system.
 3 # SELINUX= can take one of these three values:
 4 #     enforcing - SELinux security policy is enforced.
 5 #     permissive - SELinux prints warnings instead of enforcing.
 6 #     disabled - No SELinux policy is loaded.   # 이렇게 되어있어야함 
 7 SELINUX=enforcing
 8 # SELINUXTYPE= can take one of three values:
 9 #     targeted - Targeted processes are protected,
 10 #     minimum - Modification of targeted policy. Only selected processes are protected.
 11 #     mls - Multi Level Security protection.
 12 SELINUXTYPE=targeted

```



- **데몬**
  - **named.service**



#### Domain

- 인터넷 웹브라우저를 들어가기 위해서 주소를 나타내는데, 문자 or 숫자 형태로 되어있음
- 문자열 주소형식 = 도메인 (원래는 숫자 형식의 IP로 되어있다 )

- ex) www.naver.com.        210.89.160.88

  - **`www / 88 ` - 호스트 대역**     

    - **변할수 있는 값이다**   
    - 바꾸면 페이지를 전환 가능 

  - **`naver.com / 210.89.160` - 네트워크 대역**      

    - **변하지 않는 고정 값이다** 

  - **`.(마지막 점)` - Root Domain**

    - 실질적으로 검색을 시작하는 부분 

    - 하위 디렉토리 

      - com

      - or

      - co.kr

      - net

        하위 디렉토리 

        - naver

- 정방향 조회 
  
  - Domain -> IP 변환 (udp)
- 역방향 조회
  - IP -> 역 IP
  - 210.89.160.88 -> 88.160.89.210

- ##### nslookup : ip,domain 검색

***

### DNS 환경설정파일 

- `/etc/resolv.conf`

- `/etc/hosts`

- `/etc/named.conf`

- `/etc/named.rfc1912.zones`

- `/var/named/named.localhost`

  

#### `/etc/hosts`

- Domain을 IP로 바꾸는 역할 (Alias 역할)  - **설정 안해도됨** 

```shell
1 127.0.0.1   localhost localhost.localdomain localhost4 localhost4.localdomain4
2 ::1         localhost localhost.localdomain localhost6 localhost6.localdomain6
3 192.168.1.101           	ns.samadal.com  				samadal.com # 추가함
	ip			   [FQDN : 호스트를 가진 전체도메인명]			[도메인명](Alias)
				    Fully Qualified Domain Name
```



#### `/etc/resolv.conf`

- 인터넷 사용가능하게 해주는 파일 
- **시스템 종료하면 다시 설정해줘야함** 
- 그래픽 - 네트워크 - 네임서버 DNS

```shell
# Generated by NetworkManager
domain samadal.com        # 도메인
search samadal.com		  # 이 도메인 검색
nameserver 192.168.1.101  # 서버주소
nameserver 192.168.1.2    # gateway  (내부시스템 gateway=dns)
nameserver 168.126.63.1   # kns (인터넷 서버중 하나) 슈퍼dns

```

<ex. 펌프>

<img src="./펌프.PNG">

- **nameserver를 여러 개 두어서 도메인을 찾음** - /etc/resolv.conf





#### `/etc/named.conf`

- 패키지 설치안되있으면 생성 되지 않는 파일 
- **53번 전용 포트 설정 파일** 

```shell
# 해당 설정만 바꾸면 됨 

# {해당 IP} 만 열어놓는다 
13 listen-on port 53 { any; };

# 질의를 허락하겠다
21 allow-query 	{ any; };

# 재치 질문을 반복하지 않는다  - 메모리낭비 안하기위함 
33  recursion no;
```



#### `/etc/named.rfc1912.zones`

- **정방향, 역방향 영역을 정의하는 파일** 
- 적혀있는 쉘 스크립트 복사해서 밑에 붙여넣은후 수정해야함 

```shell
# 이렇게 수정 

# IP와 Domain 은 매핑을 시켜줘야한다 
# 1.168.192 samadal.com

# 정방향
43 zone "samadal.com" IN {
44         type master;					# master : 주 DNS , slave : 보조 DNS(백업용)
45         file "samadal.com.zone";     # 만들어야될 파일 (원하는대로 해도됨)
46         allow-update { any; };		# 페이지 업데이트 항상
47         allow-transfer { any; };		# 변환 
48 };

# 역방향
zone "1.168.192.in-addr.arpa" IN {
51         type master;
52         file "samadal.com.rev";
53         allow-update { any; };
54         allow-transfer { any; };
55 };


```



#### `/var/named/named.localhost`

- centos 5. 에서는 공백이 하나만있어도 데몬 재실행이 되지 않았음 - 빈문자 유의 
- 주의 
  - 12,13 라인 에는 **웹서버 IP**를 넣어줘야함 

```shell
# 정방향 수정본 
1 $TTL 1D
2 @       IN SOA  ns.samadal.com.         root.samadal.com. (
3                                         0       ; serial
4                                         1D      ; refresh
5                                         1H      ; retry
6                                         1W      ; expire
7                                         3H )    ; minimum
8         IN      NS      ns.samadal.com.
9         IN      A       192.168.1.101
10 
11 
12 ns      IN      A       192.168.1.101  
13 www     IN      A       192.168.1.101   

# 저장할때 다른이름으로
:w samadal.com.zone

# 강제열기 - named.localhost 원본으로 되돌리기
:e! samadal.com.zone

# 역방향 수정본 
1 $TTL 1D
2 @       IN SOA  ns.samadal.com.         root.samadal.com. (
3                                         0       ; serial
4                                         1D      ; refresh
5                                         1H      ; retry
6                                         1W      ; expire
7                                         3H )    ; minimum
8         IN      NS      ns.samadal.com.
9         IN      A       192.168.1.101
10 
11 
12 101     IN      PTR     ns.samadal.com.
13 101     IN      PTR     www.samadal.com.


# 역방향 파일 새로저장 
:w samadal.com.rev 

# 현재 열려있는 파일은 samadal.com.zone 파일이기 때문에 강제 종료
:q!

# 허가권 , 소유권 변경
# 660, root:named
-rw-rw----. 1 root  named  243  6월 26 14:43 samadal.com.rev
-rw-rw----. 1 root  named  233  6월 26 14:38 samadal.com.zone


```

- IN : internet 
- SOA : 인증
- root.(samadal.com.) - 괄호부분은 안써도 괜찮다 
- IN      NS      ns.samadal.com.
  - ns.samadal.com.  도메인의 네임서버로 인터넷 검색을 함 
- ns     IN      A       192.168.1.101
  - web server IP 적어줘야함 



#### 정방향 역방향 정의 파일 권한 소유권 변경

```shell
chmod 645 kang*; chown :named kang*
```

