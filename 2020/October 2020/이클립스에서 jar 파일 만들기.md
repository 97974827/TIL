**[IT/Java](https://appsnuri.tistory.com/category/IT/Java)**

### 이클립스에서 jar 파일 만들기

storya 2016. 3. 22. 15:22

\1. 이클립스를 이용해 작성된 class를 jar 파일로 제작하려면 원하는 파일이 들어 있는 프로젝트를 선택한다.

  마우스 오른쪽 버튼을 누르고 나오는 메뉴에서 export를 선택한다.



![img](https://t1.daumcdn.net/cfile/tistory/21659B5056F0E27A0C)



\2. Export 메뉴를 선택하면 아래 보여지는 것처럼 Export창이 보여지는데 이 창에서 Java 항목의 JAR file를 선택함 



![img](https://t1.daumcdn.net/cfile/tistory/256FFA4E56F0E29E19)



\3. 아래 JAR Export 창에서 현재 JAR 파일로 만들 프로젝트가 선택되어 있는데 해당 프로젝트의 모든 파일을 JAR 파일에 담으려면 5번으로 이동한다. 프로젝트의 특정 파일만 JAR 파일에 담으려면 4번으로 이동함



![img](https://t1.daumcdn.net/cfile/tistory/2736104D56F0E37512)



\4. 선택한 프로젝트의 특정 파일만 JAR 파일에 포함시키려면 아래와 같이 프로젝트를 열어 원하는 파일만 선택하고 원하지 않는 파일의 선택을 해제한다.



![img](https://t1.daumcdn.net/cfile/tistory/220F984856F0E39507)



\5. JAR 파일명을 지정하고 저장될 경로를 선택한 후 next 버튼을 누른다.



![img](https://t1.daumcdn.net/cfile/tistory/22154C4E56F0E3C501)





\6. JAR 파일로 변환시 컴파일 오류 또는 컴파일 경고 내용을 함께 보길 원할 경우 아래 메뉴에 체크하고 next



![img](https://t1.daumcdn.net/cfile/tistory/225F385056F0E3EE17)



\7. Main Class로 사용하게 될 클래스를 검색해 지정함

 (만약 Main Class를 지정하지 않고 jar 파일을 만든 후 실행 시키려면

 콘솔창에서 java -classpath .;파일명.jar 패키지명.메인이 있는 클래스명 을 직접 입력함)  



![img](https://t1.daumcdn.net/cfile/tistory/213B604956F0E41A23)



\8. JAR 파일로 변환시 컴파일 오류 또는 컴파일 경고 내용이 보여지게 옵션을 선택했기 때문에 오류 또는 경고 내용이 있을 때

  아래와 같이 해당 내용을 볼 수 있는 창이 보여진다. 그 창에서 Details를 클릭하면 해당 오류 또는 경고 내용을 확인할 수 있다.



![img](https://t1.daumcdn.net/cfile/tistory/2116804A56F0E44216)



\9. 아래와 같이 Details를 클릭했을 때 경고 내용이 보여진다. 수정을 원하면 클래스를 수정한 후 다시 명령을 수행하고

  프로그램 구동시 문제가 되지 않으면 OK 버튼을 누르면 finish 버튼을 눌러 JAR 파일을 생성한다.



![img](https://t1.daumcdn.net/cfile/tistory/22226D4C56F0E47022)



\10. 생성된 JAR 파일은 콘솔창에서 java -jar 파일명.jar로 실행할 수 있다.



출처: https://appsnuri.tistory.com/49 [이야기앱 세상]