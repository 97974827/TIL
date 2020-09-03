# [2020-07-07 화 TIL]

### Java

#### Calendar / Date

- java.time 패키지
- **Calendar** 는 추상클래스 여서 객체 직접 생성 X
- Calendar 가 새롭게 추가되면서 **Date**는 대부분의 메서드가 **'deprecated(더이상 사용되지 않음)'** 되었으므로 잘사용되지 않음
- 그럼에도 Date를 필요로 한 메서드들이 존재하기 때문에 Calendar를 Date로 또는 그 반대로 변환할 일이 생긴다 

```java
// 기본적으로 현재날짜와 시간이 설정됨
Calendar today = Calendar.getInstance();
System.out.println("월(0~11) 0:1월 : " + today.get(Calendar.MONTH));
```

- 주의
  - **get(Calendar.MONTH)** 로 얻어오는 월의 값은 1~12가 아닌 0~11 의 값 범위이다 0이면 1월, 11이면 12월

- set() 으로 날짜 설정 가능 



***

#### 형식화 클래스

- java.text 패키지 
- 숫자, 날짜, 텍스트 데이터를 일정한 형식에 맞게 표현할 수 있는 방법을 객체지향적으로 설계하여 표준화



#### SimpleDateFormat

- 날짜를 계산할 때 Date 와 Calendar 만으로는 날짜 데이터를 원하는 형태로 다양하게 출력하는 것은 불편하고 복잡하다

```java
package datetime;

import java.util.Date;
import java.text.SimpleDateFormat;

public class SimpleDateFormatMain {
	
	public static void main(String[] args) {
		Date today = new Date();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
		
		String result = df.format(today);
		String df2_res = df2.format(today);
		
		System.out.println(result);
		System.out.println(df2_res);
		
	}
}
2020-07-07
2020-07-07 16:32:28 오후

```



