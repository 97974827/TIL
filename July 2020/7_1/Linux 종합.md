# [2020-07-01 수 TIL ]

### Linux DNS / WEB / DB Server 

- **XE 제로 보드** 사이트
  - https://www.xpressengine.com/
  - https://xe1.xpressengine.com/index.php?mid=download&package_id=18325662&release_id=22756225
- 홈페이지 디자인 무료제작 
  
- 리눅스에도 .zip 파일 지원하기 때문에 `unzip` 명령으로 압축 풀수 있음 



### <종합실습>

<img src="./종합실습.PNG">

 

- 파티션 유저  - Web Server(/dev/sdb)
  - main (/dev/sdc)
    - it 
    - cafe 
    - blog
  - jin (원하는 계정)
- domain : kangmung.com
- **오토마운트 활성 후 사용자 생성** 
- 시스템 3대 따로
  - DNS 
  - Web
  - DB
- xe 페이지 각각 3대 
  - 각 사용자 디렉토리 



***

#### 7/1

- OS 올릴때 swap 가상메모리 잘못잡음 
  - Swap Memory 추가하기 (CentOS 7)
    - [https://enesto.github.io/2018/11/19/181119_Swap%20Memory%20%EC%B6%94%EA%B0%80/](https://enesto.github.io/2018/11/19/181119_Swap Memory 추가/)
  
- automount - `/etc/fstab` 설정 잘못함 

- 패키지 설치 문제 

- #### **VMware Failed to lock the file 오류**
  
- 출처: https://pikon.tistory.com/entry/VMware-Failed-to-lock-the-file-오류-이렇게 [피콘]
  
- DNS, DB, WEB 방화벽 / 데몬 / 패키지 설정완료

- main 유저 홈 디렉토리 못찾아감 / 나머지는 홈 디렉토리 잘찾아감  - 해결 

```shell
# 오토마운트 정보

/dev/sdb1                         /export/home/mp_main/main/mp_it          ext4    defaults        1 2
/dev/sdb2                         /export/home/mp_main/main/mp_cafe        ext4    defaults        1 2
/dev/sdb3                         /export/home/mp_main/main/mp_blog        ext4    defaults        1 2
/dev/sdb5                         /export/home/mp_main/main/mp_hyunjin     ext4    defaults        1 2
/dev/sdc1                         /export/home/mp_main                     ext4    defaults        1 2

```



***

#### 7/2

- 먼저, 오토마운트 활성 후, 사용자 도입해야합니다.
  - main 마운트 이후 main 생성
  - 다른 유저 마운트 이후 다른 유저생성 

- #### 주의 : 유저관련 파티션 / 마운트작업

  - **유저 사용중일때** 
    - 홈 디렉토리 임시 옴기기
    - 마운트 해제
    - 파티션 마운트에 홈 디렉토리 변경
  - **마운트 작업** 
    - 파티션 마운트 먼저하기
    - 파티션에 유저 생성
  - **하드디스크 파티션 수정** 
    - 유저 홈 디렉토리 임시 옴기기 
    - 파티션 수정
    - 마운트 작업
    - 파티션 마운트에 홈 디렉토리 변경

- Alias  지정
  - 각 홈 디렉토리 경로 설정 
    - ex ) `Alias /it 		 /export/home/mp_main/main/mp_it/it/public_html`



#### 미비된점

- web 설정 : main 도큐먼트루트 지정 
  - 사용자 디렉토리 별 Alias 적용 
  - 사용자 디렉토리별 index.html 만들어 업로드 
- DB 사용자 / 테이블 / 설정
- XE 제로보드 셋팅



***

#### 현재 부족한점

- 쉘 스크립트 작성법
- cron 백업

- 저장공간 로그파일 관리 (메모리) - 오래된 로그 파일삭제 

- cat / find / 리다이렉션 활용