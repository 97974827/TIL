# [2020-06-18 목 TIL]

### Linux

- #### grep

  - cat /etc/passwd | grep samadal
    - samadal이라는 문자열 포함된 것만 찾아줌 
    - grep 명령은 와일드카드 문자를 쓰지않는다 

***

- 사용자와 관련된 파일

  - `/etc/passwd`

    - 사용자 계정의 정보가 있는 파일 -> **없애면 계정이 안들어가짐** 

  - `/etc/default/useradd`

  - `/etc/shadow`

    - 계정 비밀번호는 암호화 처리가 되어 있음 
    - **암호화 형식은 무조건 난수**로 되어야 한다 (서로 다른 컴퓨터에서 같은 비밀번호 설정시 같은 비밀번호이면 보안 취약)

  - `/etc/group`

  - `/etc/skel/*`

    - skel 정의 - 사전적의미 (해골)

      - 사용자를 생성할 떄, 그 사용자의 홈 디렉터리에 자동으로 /etc/skel/안에 있는 파일과 디렉터리를 (/home/유저) 에 **자동 생성** 시키는 역할 
      - 여러 계정을 만들어야 할때 유용하게 사용함 

    - ```shell
      [root@localhost home]# ls -al /etc/skel/
      total 32
      drwxr-xr-x.   3 root root  4096 Jun 15 14:10 .
      drwxr-xr-x. 139 root root 12288 Jun 18 04:12 ..
      -rw-r--r--.   1 root root    18 Mar 31 22:17 .bash_logout
      -rw-r--r--.   1 root root   193 Mar 31 22:17 .bash_profile
      -rw-r--r--.   1 root root   231 Mar 31 22:17 .bashrc
      drwxr-xr-x.   4 root root  4096 Jun 15 14:09 .mozilla
      
      ```

```shell
$ samadal:x:1000:1000:samadl:/home/samadal:/bin/bash

# samadal 		: 사용자 계정이름
# x       		: 비밀번호 (들어가는 자리)
# 1000	 	    : UID (user id), 유저의번호 -> (시스템은 문자라는걸 모르기때문에 숫자로 출력함)
# 1000          : GID (Group id), 그룹의 번호 -> 1) 시스템(숫자), 2) 사용자(문자)
# samadal       : Comment (부연설명/닉네임)
# /home/samadal : 사용자계정의 홈 디렉토리 - 중요
# /bin/bash     : 쉘 종류 -> bash
```

- 쉘종류
  - sh : 가장오래된 쉘
  - **bash** : 기본적으로 사용하는 쉘 -  "[계정@호스트]#" 이런식으로 보여지는 역할 
  - **ksh** : 유닉스 기본값이다 
  - csh : C언어 기반 쉘
  - tcsh : C언어 기반 쉘을 조금더 강화 시킴 (큰 차이는 없음)

***

### 사용자 생성

- `useradd`
  - 계정을 생성하면 home 디렉토리에 파일도 같이 만들어진다 
  - 옵션
    - **-c** : comment
    - **-s** : 쉘 종류 
    - **-mk** : 사용자 skel 디렉토리 지정
    - **-d** : 홈 디렉토리 경로 지정 

```shell
# 형식 : useradd [옵션1][값1][옵션2][값2] ... [사용자명]  (옵션, 값은 생략가능 /둘은매칭이되어야한다)
$ useradd user1

# 끝에서부터 3라인 출력 -> 새로운 계정 생성후 확인하면 추가되어있음 
$ tail -3 /etc/passwd

# 유저 생성 후 계정정보 파일보기 
$ useradd -c testuser -s /bin/tcsh user2
[root@localhost home]# tail -3 /etc/passwd
samadal:x:1001:1001::/home/samadal:/bin/bash
user1:x:1002:1002::/home/user1:/bin/bash
user2:x:1003:1003:testuser:/home/user2:/bin/tcsh


# useradd user1 
확인: 
1_1) 사용자 정보 확인
# tail-3/etc/passwd 

1_2)사용자 집 확인
# ls-l/home/ 
1_3)사용자 개인메일 파일확인
# ls-l/var/spool/mail/

# useradd -c testuser-s /bin/tcsh user2 
# tail-3/etc/passwd 
# ls-l/home/ 
-c : comment, -s : 쉘변경

# mkdir/cloud/ 
# useradd -s /bin/sh -d /cloud/user3 user3 

# useradd -d /clouduser33 [오류] [계정은생성됬음] 
# tail -5 /etc/passwd
# useradd -d /cloud/ictuser333 
# tail -6 /etc/passwd 
# ls -l /cloud/

# 사용자의 홈 디렉토리의 정보만 변경 : ( /home 은 바뀌지 않음 )
$ useradd -d 
# useradd-d/clouduser33 [오류] 

# 비밀번호 변경
$ passwd 계정명

# 쉘 종류 확인 
$ echo $SHELL
/bin/bash

# /etc/skel에 빈 파일 생성 후 사용자 생성 
[root@localhost user]$ ls -al /etc/skel/
total 32
drwxr-xr-x.   3 root root  4096 Jun 15 14:10 .
drwxr-xr-x. 139 root root 12288 Jun 18 04:12 ..
-rw-r--r--.   1 root root    18 Mar 31 22:17 .bash_logout
-rw-r--r--.   1 root root   193 Mar 31 22:17 .bash_profile
-rw-r--r--.   1 root root   231 Mar 31 22:17 .bashrc
drwxr-xr-x.   4 root root  4096 Jun 15 14:09 .mozilla
[root@localhost user]$ touch /etc/skel/sam
[root@localhost user]$ ls -al /etc/skel
total 32
drwxr-xr-x.   3 root root  4096 Jun 18 04:22 .
drwxr-xr-x. 139 root root 12288 Jun 18 04:12 ..
-rw-r--r--.   1 root root    18 Mar 31 22:17 .bash_logout
-rw-r--r--.   1 root root   193 Mar 31 22:17 .bash_profile
-rw-r--r--.   1 root root   231 Mar 31 22:17 .bashrc
drwxr-xr-x.   4 root root  4096 Jun 15 14:09 .mozilla
-rw-r--r--.   1 root root     0 Jun 18 04:22 sam
[root@localhost user]$ useradd user4
[root@localhost user]$ tail -7 /etc/passwd
user:x:1000:1000:user:/home/user:/bin/bash
samadal:x:1001:1001::/home/samadal:/bin/bash
user1:x:1002:1002::/home/user1:/bin/bash
user2:x:1003:1003:testuser:/home/user2:/bin/tcsh
user3333:x:1004:1004::/linux/ict:/bin/bash
user33:x:1005:1005::/home/user33:/bin/bash
user4:x:1006:1006::/home/user4:/bin/bash


[root@localhost user]# ls -al /home/user1
total 24
drwx------. 3 user1 user1 4096 Jun 17 23:53 .
drwxr-xr-x. 8 root  root  4096 Jun 18 04:23 ..
-rw-r--r--. 1 user1 user1   18 Mar 31 22:17 .bash_logout
-rw-r--r--. 1 user1 user1  193 Mar 31 22:17 .bash_profile
-rw-r--r--. 1 user1 user1  231 Mar 31 22:17 .bashrc
drwxr-xr-x. 4 user1 user1 4096 Jun 15 14:09 .mozilla
[root@localhost user]# ls -al /home/user4
total 24
drwx------. 3 user4 user4 4096 Jun 18 04:23 .
drwxr-xr-x. 8 root  root  4096 Jun 18 04:23 ..
-rw-r--r--. 1 user4 user4   18 Mar 31 22:17 .bash_logout
-rw-r--r--. 1 user4 user4  193 Mar 31 22:17 .bash_profile
-rw-r--r--. 1 user4 user4  231 Mar 31 22:17 .bashrc
drwxr-xr-x. 4 user4 user4 4096 Jun 15 14:09 .mozilla
-rw-r--r--. 1 user4 user4    0 Jun 18 04:22 sam

2) skel 에 모든것을 samskel에 복사 
1) samskel 생성 -> mkdir -p /etc/samskel

2) 사용자 지정 skel 디렉토리 지정 
시도
$ cp -r ./skel/* /etc/samskel  -> .m 디렉토리 복사가안됨 
# -> .* 이표현은 쓰면안됨 (..때문에 )

정답 
$ cp -r .b* .m* * /etc/samskel 
-> .b .m으로 시작하는것 그리고 모든것을 디렉토리,파일 /etc/samskel로 옴기기 


# sam 파일을 mas로 바꾼 후 새로운 사용자에게 스캘 디렉토리 추가 
$ useradd -m -k /etc/samskel user5 
[-m과 -k는 함께 사용될 때 반드시 순서를 -m 부터 한다 ]

-m : create home (실제론 move 뜻을 가짐)
-k : 


[root@localhost skel]# ls -al /home/user5
total 24
drwx------. 3 user5 user5 4096 Jun 18 04:53 .
drwxr-xr-x. 9 root  root  4096 Jun 18 04:53 ..
-rw-r--r--. 1 user5 user5   18 Jun 18 04:47 .bash_logout
-rw-r--r--. 1 user5 user5  193 Jun 18 04:47 .bash_profile
-rw-r--r--. 1 user5 user5  231 Jun 18 04:47 .bashrc
-rw-r--r--. 1 user5 user5    0 Jun 18 04:47 mas
drwxr-xr-x. 4 user5 user5 4096 Jun 18 04:47 .mozilla


# 실습
/usertest/test(사용자)/public(자동생성디렉토리)

스캘 디렉토리 적용 


3) 사용자 계정 생성시 기본 설정 파일 및 명령어

명령어 : useradd -D (전체가 명령어) [옵션] [값]
문서파일 : cat /etc/default/useradd

HOME=/home       -b   - 홈 디렉토리 
SHELL=/bin/bash  -s   - 기본값 bash
SKEL=/etc/skel   없음  - 기본값 skel

[root@localhost usertest]# useradd -D
GROUP=100
HOME=/home
INACTIVE=-1
EXPIRE=
SHELL=/bin/bash
SKEL=/etc/skel
CREATE_MAIL_SPOOL=yes

[root@localhost usertest]# cat /etc/default/useradd
# useradd defaults file
GROUP=100
HOME=/home          - 홈 디렉토리(주의)
INACTIVE=-1         
EXPIRE=
SHELL=/bin/bash     - 기본값 bash
SKEL=/etc/skel      - 기본값 skel
CREATE_MAIL_SPOOL=yes - 개인 메일파일을 만들거냐(/home 밑에 생성)

# useradd -D -b /kg -s /bin/sh  (주의 : 사용자 만들때는 디렉토리 뒤에 /써주면 안됨 )
-> 정보 바뀜
HOME=/kg 
SHELL=/bin/sh

- 사용자 계정 많이생성할때 사용용이

```

#### 시스템 관리 팁

- 나 - 기업인

- 고객 

- 이 명령은 이렇게 써야하는구나 / 이렇게 쓰면 좋겠다 생각 하면 좋음 