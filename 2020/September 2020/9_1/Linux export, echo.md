#### Linux

**export**

```shell
$ export water
# 환경변수 목록에 저장 (터미널이 꺼지면 사라짐)

vi ~/.bashrc
# 매번 쉘을 실행할 때마다 쉘변수를 환경변수로 자동으로 설정하고 싶다면 .bashrc 파일에 변수를 저장할 수 있음 

```



**echo**

- 변수 출력 

```shell
$ water="삼다수"
$ echo water
water
$ echo $water
삼다수

$ env
# 운영체제 변수출력 (환경변수)

$ unset water
# 변수명 삭제 
```



***

### Oracle JDK / OpenJDK

- https://rwd337.tistory.com/191

JAVA 애플리케이션을 실행하기 위해서는 JVM, JDK가 필요한데 

JDK는 컴파일하기 위해 필요하고 , JVM은 실행하기 위해 필요 (hotspot 이라고도 부름, JAVA 기술의 핵심)



JDK의 2가지 버전

1. 폐쇄적인 상업 코드기반 : 오라클 JDK

   - 차이 : 오라클에는 없는 재산권이 걸린 플러그인을 제공함 

   - Oracle이 인수하여 없어진 Sun Microsystems 시절의 유산 
   - 글꼴 라이브러리
   - Java Web Start 
   - 사용자의 웹 브라우저에서 자바 애플릿을 실행하려면 필요한 기능이다. 서버 애플리케이션 개발에는 쓰이지 X

2. 오픈 소스기반 : OpenJDK





