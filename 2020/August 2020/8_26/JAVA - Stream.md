### JAVA - Stream

- 많은 수의 데이터를 다룰 때, 컬렉션이나 배열에 데이터를 담고 원하는 결과를 얻기 위해 for문과 Iterator를 이용해 작성함 - 코드가 너무길고 알아보기 어렵다
- 데이터 소스를 추상화 시킴
  - 데이터 소스가 무엇이던 간에 같은 방식으로 다룰 수 있게 되었다는 것과 간결함 , 그리고 코드의 재사용성이 높아진다는 것을 의미 

- 스트림은 데이터 소스를 읽기만하고 변경하지 않는다.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {
	
	public static void main(String[] args) {
		String[] strArr = {"aaa", "bbb", "ccc"};
		List<String> strList = Arrays.asList(strArr);
		
		System.out.println(strList);
		
		Stream<String> strStream1 = strList.stream(); // 스트림 생성
		Stream<String> strStream2 = Arrays.stream(strArr);
		
		strStream1.forEach(System.out::print); // 스트림은 작업을 내부반복으로 처리한다 - 스트림의 출력하는 방식
		strStream2.forEach(System.out::print);
			
	}
}
```



