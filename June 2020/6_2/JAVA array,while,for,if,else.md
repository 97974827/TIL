### JAVA

- 배열 변수.length : 배열 길이 측정
- int[] arr = new int[5];



#### String.format

- [stackoverflow - 모자란 자릿수 문자열을 앞 0으로 채우기](https://stackoverflow.com/questions/4051887/how-to-format-a-java-string-with-leading-zero)

***

### 표준 입출력

input

- 파일의 데이터를 읽는다 (loading)
- 키보드의 입력데이터를 읽는다
- 네트워크상의 데이터를 읽는다(인터넷 -> 자바로 끌어옴 (크롤링))

output

- 파일에 데이터를 쓴다 (save)
- 모니터에 데이터를 쓴다(출력)
- 네트워크상에 데이터를 쓴다 (파일전송)



- 자바에서 데이터를 출력하는 명령은 **System.out**이고, 데이터를 입력 받을때는 **System.in**을 이용



### Scanner API 사용법

```java
import java.util.Scanner; // 자바 내부에 설치

// 1. 스캐너 객체 생성
Scanner scan = new Scanner(System.in); 

// ex) 2. 입력 받을 데이터 타입에 따른 메서드 호출
scan.next();       //  공백을 포함하지 않는 문자열을 입력받을때
scan.nextline();   //  공백을 포함한 문자열을 입력 받을 때
scan.nextInt();    //  정수를 입력받을 때
scan.nextDouble(); //  실수를 입력받을 때


// 3. 스캐너 객체 종료
scan.close();
```

