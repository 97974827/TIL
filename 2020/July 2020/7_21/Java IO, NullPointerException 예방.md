# [2020-07-21 화 TIL]

### JAVA IO

- [자바 중급](https://programmers.co.kr/learn/courses/9/lessons/267#)

- 파일 없는 예외 발생 할 수 있으니 항상 예외처리 
- 매개변수는 읽어들이거나 쓰기위한 파일 경로지정

- **FileInputStream**
  - 파일로부터 읽어들이는 객체 

```java
FileInputStream fis = new FileInputStream("퍄일경로");
FileOutputStream fos = new FileOutputStream(파일경로);
```

- **FileOutputStream**
  - 파일로부터 쓰기위한 객체 



#### [NullPointerException 방지하기](https://goddaehee.tistory.com/126)

1. **null 파라미터를 다른 메소드에 넘기지말자** 
2. **null 비교 여부 처리 추가**

```java
String a = null;
System.out.println(a.indexOf("갓"));

// 결과
Exception in thread "main" java.lang.NullPointerException

// 예방
String a = null;	
if(a != null){
    System.out.println(a.indexOf("갓"));
}
```



3. **toString()  대신 valueOf() 사용**

4. **Chaining 메소드 호출 자제하기**

   **"메소드 체이닝(Method Chaining)"** 이라는 패턴이 있다.

   쉬운 예를 들어 

   ​	EX) 객체.메소드().메소드().메소드(); 

   ​	위와 같은 구조이며 이렇게 코드를 작성하면 작성 코드량이 현저하게 줄어든다.

   ​	하지만 이런 체이닝 메소드에서 **"NPE"**가 발생하면 정말 디버깅 하기 어렵다.