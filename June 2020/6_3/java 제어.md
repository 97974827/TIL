# 2020-06-03 수 TIL

### JAVA

- String 문자열을 int 형으로 치환하는 방법 

- ```java
  String str = "2002";
  int year = Integer.parseInt(str); 
  // year 변수에 정수값 2002가 할당됨 
  
  // parseInt() 메서드를 사용하려면 치환하기 전의 String 변수 값이 반드시 숫자 형태여야 한다.
  ```

  

- NumberFormatException : 숫자로 바뀔수 없다 



#### 향상된  for 루프 

- 배열 및 컬렉션의 원소 추출용도

```java
for(배열의 값을 담을 변수 : 배열의 이름){
	실행문;
}

int[] arr = {1,2,3,4,5};
		
for(int i : arr) {
    System.out.println(i);
}

```

- continue; 
  - 반복문의 맨 처음으로 돌아가 실행문은 건너뛰어 실행 X

```java
String[] arr = {"자바", "파이썬", "c", "c++"};

for(String i : arr) {
    if (i == "파이썬") continue;
    System.out.println(i);
}

/*
자바
c
c++
*/
```