### debian apt / dpkg



- #### **apt**

  - `apt-get update` : 패키지 최신버전 업데이트 (오픈소스이기 때문에 계속 바뀌어서 반복적으로 해줘야함)

  - `apt-get install 패키지명` : 패키지 설치

    - `-f ` : 의존성 파일들 강제로 설치함

  - `apt-get upgrade` : 설치되어 있는 패키지를 모두 새 버전으로 업그레이드

  - `apt-get autoremove` : 사용하지 않은 모든 패키지를 자동적으로 삭제 

  - `apt-get remove 패키지명` : 패키지만 삭제

  - `apt-get --purge remove 패키지명` : 패키지 삭제 + **설정 파일삭제** (이 패키지와 관련 기록된 모든 것 삭제)

  - `apt-cache pkgnames` : 시스템에 들어 있는 패키지의 이름을 모두 보여줌 (제공하는 패키지 이름 모두 출력)

  - `apt-cache search 패턴` : 패턴에 맞는 패키지 목록을 찾음 (검색)

  - `apt-cache show 패키지명 ` : 패키지에 대해 읽을 수 있는 기록을 본다 (패키지 정보보기)

    

  - 시스템에 들어 있는 패키지가 몇개 있는지 알아보기 위해서 `apt-cache pkgnames > 원하는 파일이름`





- #### **dpkg**

  - `dpkg -i 파일명.deb` : deb 파일 설치
  - `dpkg -P 패키지명` : 패키지 제거
  - `dpkg -l ` : 설치된 패키지 리스트
  - `dpkg -L 패키지명 ` : 설치된 패키지에 포함된 파일 보기 
  - `dpkg -c 파일명.deb` : deb 파일 포함된 파일들 보기 
  - `dpkg -I 파일명.deb` : deb 파일의 정보보기 
  - `dpkg -S 파일명` : 파일의 패키지명 알아내기 
  - `apt-get install` 명령어로 받은 deb 파일의 저장 위치 `/var/cache/apt/archive/` 에 .deb 패키지파일로 저장됨

***

### 환경변수 설정

```shell
~/.bashrc

#[user java path config]
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/
export PATH=$PATH:$JAVA_HOME/bin

/etc/environment

/etc/profile

# 설정 저장
source ~/.bashrc
source /etc/environment
```

