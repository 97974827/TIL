# [2020-06-30 화 TIL ]

### Linux DNS, WEB, Database

```shell
실습)
www.samadal.com
index.html 내용이 화면에 출력되게 설정
DocumentRoot:samadal 홈디렉토리
samadal 홈디렉토리 : /export/home/samadal/

# yum의 실행파일 위치 반드시 기억
cp -p /etc/yum_samadal /usr/bin/yum 

압축파일에서 yum 사용시 주의점 반드시 putty는 재접속
```



### Web Alias

- httpd.conf
- 경로가 복잡할 경우 간략하게 문자열을 동격을 시킴 
- 사용자 편의성 증가 

```shell
Example: Alias /webpath /full/filesystem/path

				# 절대경로 전부 다써줘야함 
Alias	/ck		/export/home/samadal/1/2/3/4/5
```





### PHP / phpmyadmin install

- https://www.phpmyadmin.net/donate/
- tar.gz 업로드 
  - `tar xvfz 파일명`
  - httpd.conf 설정변경 

```shell

# DirectoryIndex: sets the file that Apache will serve if a directory
165 # is requested.
166 #
167 <IfModule dir_module>
168     DirectoryIndex index.html index.php <- php 모듈추가 
169 </IfModule>

```



`yum -y install --skip-broken php*`

- **phpmyadmin 5.5 버전부터 유료화 - 대체 버전 4.0.10.20** 
  - [참고 - [CentOS] phpMyAdmin "Cannot start session without errors"](https://webdir.tistory.com/183)

- DNS : www.samadal.com/phpmyadmin
  - 디비 계정 접속 = 리눅스 커맨드 창 실행이랑 동일 

