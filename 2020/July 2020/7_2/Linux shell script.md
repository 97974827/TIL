# [2020-07-02 목 TIL]

### Linux shell script

- 쉘을 사용해서 프로그래밍 가능
- **서버 작업 자동화 및 운영(DevOps)**을 위해 익혀둘 필요있음
  - perl
  - python
- 쉘 명령어를 기본으로 하되, 몇 가지 문법이 추가된 형태
  - 쉘 명령어 -> 쉘 스크립트 
- 시스템 프로그래밍에서 꼭 익히는 내용 중 하나 

- 오래된 로그파일은 삭제 하는게 좋다 



```
문제점
서버가 어느날 다운되었다
확인해보니 서버 저장공간이 하나도 남지 않음
로그 파일 업데이트가 안되어 관련 프로그램 비정상종료
이유는 로그 파일이 많이 쌓여있음
어떻게 하면 자동으로 오래된 로그 파일을 삭제할까?

해결 
간단한 쉘 스크립트 생성 및 주기적 실행 
```



#### 기본 문법

- 쉘 스크립트는 파일로 작성 후 , 파일을 실행한다
- 파일의 가장 위의 첫 라인은 `#!/bin/bash`로 시작
- 쉘 스크립트 파일은 실행 권한을 가지고 있어야함 
- 일반적으로 `파일이름.sh` 와 같은 형태로 파일이름을 작성



```bash
# bash쉘에서 제공하는 echo함수를 이용하여 화면에 "Hello bash!" 를 출력 

# hellobash 파일 생성
vi hellobash.sh

#!/bin/bash

# 화면 출력 명령어 : echo
echo "Hello Bash!"

# 쉘스크립트 실행
./hellobash.sh

# 주석은 # 기호로 처리함
```



- 변수
  - 변수명 = 데이터
  - 변수명 = 데이터 사이에 띄어쓰기는 허용 X
- 사용
  - **`$변수명`**  으로 사용됨  

```bash
#!/bin/bash

mysql_id='root'
mysql_directory='/etc/mysql'

# echo 출력 - $변수
echo $mysql_id
echo $mysql_directory

# 출력
root
/etc/mysql
**********************************************
이름,나이,직업

vi my_info.sh

#!/bin/bash

name="jin"
age=28
info="pro"

echo $name
echo $age
echo $info

# 개행문자 삽입 X
echo $name $age $info

#출력
jin
28
pro
jin 28 pro

```



#### 배열

- 선언 
  - 변수명 = (데이터1 데이터2 데이터3 ...)
- 사용
  - ${변수명[인덱스번호]}

```bash
#!/bin/bash

daemons=("httpd" "mysqld" "vsftpd")
echo ${daemons[1]} 		# 데몬 배열의 두번째 인덱스 출력
echo ${daemons[@]}		# 데몬 배열의 모든 데이터 출력
echo ${daemons[*]}		# 데몬 배열의 모든 데이터 출력
echo ${#daemons[@]}		# 데몬 배열 크기 출력 

filelist=($(ls))		# 해당 쉘스크립트 실행 디렉토리의 파일 리스트를 배열로 $filelist 변수
echo ${filelist[*]}		# $filelist 모든 데이터 출력

# 출력
mysqld
httpd mysqld vsftpd
httpd mysqld vsftpd
3
array.sh hellobash.sh index.html my_info.sh mysql.sh
```

