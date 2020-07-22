

#### [자바 내일날짜 구하기](https://pikac.tistory.com/194)

```java
Calendar cal = new GregorianCalendar();
cal.add(Calendar.DATE, i);
 
// int i
// 0 == 오늘
// 1 == 내일
//-1 == 어제
 
 
Date date = cal.getTime();


출처: https://pikac.tistory.com/194 [pikac]
```





#### [자바 IO 다루기](https://nowonbun.tistory.com/310)

- 프로그램에서 I/O는 파일 다루는 것을 뜻함

- 프로그램에서 가장 많이 사용하는 리소스 : 파일, 소켓(통신)

- 우리가 사용하는 PC나 서버는 메모리의 한계가 있기 때문에 모든 데이터를 전부 변수를 선언해서 메모리에 둘 수 없습니다.

  또 이 메모리는 시스템을 재부팅하거나 프로그램을 재기동을 하게 되면 데이터가 사라지기 때문에 데이터를 보존하는 역할로 파일을 다루게 됩니다.

  파일은 바이너리로 구성되어 있기 때문에 IO를 사용할 때의 자료형은 대부분 byte(unsigned char)로 처리됩니다.

  

  출처: https://nowonbun.tistory.com/310 [명월 일지]



