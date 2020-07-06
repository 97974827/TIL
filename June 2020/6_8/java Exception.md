# 2020-06-08 월 TIL

### Java

**생성자**

- 클래스 내부에 파라미터가 없는 생성자를 선언 - **묵시적** 생성자
- 클래스 내부에 파라미터가 있는 생성자를 선언 - **명시적** 생성자 

- 클래스 내부에 어떠한 생성자도 없을 시 객체 생성과정에서 컴파일러가 자동으로 추가 생성 - 기본 생성자 

**"클래스에서 생성자아 없으면 객체에 접근 자체를 하지 못한다"**

***

### 예외처리 Exception

- 에러에는 심각한 에러(seriout error)와 가벼운 에러(mild error)가 있다.
- 심각한 에러는 시스템 상의 문제로 인한 에러로 개발자가 처리할 수 없는 에러를 말한다.
- 가벼운 에러는 개발자가 코드를 통해 처리할 수 있는 에러를 말하며 이 방식을 예외처리라고 한다.
- 예외에는 컴파일러 체크 예외 와 실행 예외(Runtime Exception)가 있다.
- 컴파일러 체크 예외는 자바 소스를 컴파일 하는 과정에서 예외 처리 코드를 검사하여 예외 처리코드가 없다면 컴파일 오류가 발생한다.
- 실행 예외는 컴파일하는 과정에서 예외처리 코드를 검사하지 않는 예외를 말한다.
- 예외 처리는 컴파일 예외와 실행 예외에 대한 대처방법이다.
- 예외 처리는 시스템 스스로 오류를 복구하는 것이 아니고 오류 발생 가능성이 있는 부분에 대한 처리를 미리 프로그래밍 해주는 것이다.
- 프로그램에서 문제가 될만한 부분을 예상하여 사전에 **"문제가 발생하면 이렇게 처리하라"** 라고 프로그래밍 하는 것을 예외 처리라고 한다.

### 예외처리 문을 쓰는 이유

- if~else 로 처리할수는 있는데 예외처리를 한 구문인지 한눈에 들어오지 않기 때문
- 눈에 띄게 처리할 필요가 생김 



### 실행 예외 Runtime Exception

- 실행 예외는 컴파일러가 예외 처리코드를 체크하지 않기 때문에 오로지 개발자의 경험에 의해서 예외처리 코드를 삽입해야한다.
- 만약 개발자가 실행 예외에 대해 예외처리 코드를 넣지 않았을 경우 해당 예외가 발생하면  프로그램은 곧바로 종료가 된다.

### 주요 실행예외

1. **NullPointerException**
   - 객체 참조가 없는 상태 , 즉 null 값을 갖는 참조변수로 객체 접근 연산자인 dot(.)를 사용했을때 발생 
2. **ArrayIndexOutOfBoundsException**
   - 배열에서 인덱스 범위를 초과하여 사용할 경우 발생 
3. **NumberFormatException**
   - 문자열로 되어 있는 데이터를 숫자로 변경하는 경우 발생
4. **ClassCastException**
   - 형변환은 부모클래스와 자식클래스간에 발생하고 구현 클래스와 인터페이스 간에도 발생한다.
   - 이러한 관계가 아니라면 다른클래스로 타입을 변환할수 없다.
   - 상속관계나 인터페이스 관계가 없는 클래스들을 억지로 형변황 할 경우 발생 
   - ex)
     - 몬스터를 직업 클래스로 바꾸기는 불가능 (상속관계 X )
     - 상어를 육지동물 클래스로 바꾸기는 불가능 (인터페이스 관계 X)
5. **ArithmeticException**
   - 0으로 나누기 

### try ~catch

- 프로그램에서 예외가 발생했을 경우 프로그램의 갑작스러운 종료를 막고, 정상 실행을 유지할 수 있도록 처리하는 코드를 예외 처리코드라고 한다.
- try ~catch ~finally 블록은 생성자 내부나 메서드 내부에서 작성되어 컴파일 예외와 실행 예외가 발생할 경우에 예외처리를 할수 있게 한다.
- try 블록에는 예외 발생 가능성이 있는 코드를 작성 , try 블록의 코드가 예외발생없이 정상 실행되면 catch 블록은 실행 X
- try 내부에서 예외가 발생하면 즉시 실행을 멈추고 catch 블록으로 이동하여 예외처리 코드를 실행함

#### finally 구문이 실행되지 않는 이유

- finally 구문 이전에 System.exit() 구문을 호출했을 시 
- 컴퓨터가 꺼져서 시스템이 멈추었을 시 
- finally 블록 내부에서 예외가 발생

```java
package ExceptionStudy;

public class TryCatchCase1 {
	public static void main(String[] args) {
		
		int[] iArr = {1,2,3};
		
		try {
			System.out.println(iArr[0]);
			System.out.println(iArr[1]);
			System.out.println(iArr[2]);
//			Integer.parseInt("가"); 		 
            // NumberFormatException           문자열로 되어 있는 데이터를 숫자로 변경하는 경우 발생
//			System.out.println(3/0);      
            // ArithmeticException             0으로 나누기 
			System.out.println(iArr[3]);  
            // ArrayIndexOutOfBoundsException  배열에서 인덱스 범위를 초과하여 사용할 경우 발생 
			
		} catch (ArrayIndexOutOfBoundsException | ArithmeticException e) { // 두가지 유형을 한꺼번에 | 기호로 처리 
			System.out.println("ArrayIndexOutOfBoundsException ArithmeticException: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("예외발생");
			System.out.println("NumberFormatException : " + e.getMessage());
		} finally {
			System.out.println("처리완료");
		}
		
	}
}

 // 통합 예외 -- 모든 종류 예외 처리 가능 
 catch (Exception e){
     System.out.println("예외발생 : " + e.getMessage());
 }
```



***

### throw

- 연산자 new를 이용해서 발생 시키려는 예외 클래스의 객체를 만든다음
- 키워드 throw를 이용해서 예외를 발생시킨다.
- ```throw new Exception("고의로 발생시킴");```



#### 메서드에 예외 선언 : throws

- 예외를 처리하는 방법에는 지금까지 배워 온 try ~ catch 문을 사용하는 것 외에 , 예외를 메서드에 선언하는 방법이 있다 
- 메서드에 예외를 선언하려면 , 메서드의 선언부에 키워드 throws를 사용해서 메서드 내에서 발생할 수 있는 예외를 적어주기만 하면된다. 그리고 예외가 여러 개일 경우에는 쉼표(,)로 구분한다

```java
void method() throws Exception1, Exception2, ... ExceptionN{
	// 내용 
}
```

- 하지만 이렇게 예외를 선언하면 이 예외뿐만 아니라 그 자손타입의 예외까지도 발생할 수 있다는 점에 주의
- 오버라이딩할 때는 단순히 선언된 예외의 개수가 아니라 상속관계까지 고려해야한다 



- 사실 예외를 메서드의 throws에 명시하는 것은 예외를 처리하는 것이 아니라 , **자신(예외가 발생할 가능성이 있는 메서드)을 호출한 메서드에게 예외를 전달하여 예외처리를 떠맡기는 것이다**

- 이처럼 예외가 발생한 메서드에서 예외처리를 하지 않고 자신을 호출한 메서드에게 예외를 넘겨줄 수는 있지만 , 이것으로 예외가 처리된 것은 아니고 예외를 단순히 전달만 하는 것이다 
- **결국 어느 한 곳에서는 반드시 try~catch 문으로 예외처리를 해주어야한다** 



```java
package exception;

import java.io.File;

public class ExceptionMain {
	
	public static void main(String[] args) {
		
		File file = createFile("");
		System.out.println(file.getName() + " 파일 생성 성공");
			
	}
	
	static File createFile(String file_name) {
		try {
			if(file_name.equals("") || file_name == null) {
				throw new Exception("파일이름이 유효 X");
			}
		} catch(Exception e) {
			file_name = "제목없음.txt";
			e.printStackTrace();
		} finally {
			File f = new File(file_name);
			createNewFile(f);
			return f;
		}
	}
	
	static void createNewFile(File f) {
		try {
			f.createNewFile();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

```

- 이 예제는 예외가 발생한 메서드에서 직접 예외를 처리하도록 되어있다
- createFile 메서드를 호출한 메인 메서드에서는 예외가 발생한 사실을 알지 못한다 
- 적절하지 못한 파일 이름이 입력되면 예외를 발생시키고 catch 블럭에서 파일이름을 제목없음.txt 로 설정해서 파일을 생성한다 



#### finally

- finally 블럭에서는 예외의 발생여부에 관계 없이 파일을 생성한다 
  - 가급적 finally에서는 return 사용 X
- try catch 블럭에서 return 문을 만나도 finally 블록은 실행된다 