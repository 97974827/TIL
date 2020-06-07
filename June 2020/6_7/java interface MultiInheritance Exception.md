# 2020-06-07 일 TIL

### java

### 인터 페이스 Interface

- 추상클래스를 범용적으로 편하게 만든 문법 (추상클래스보다 내용을 모호하게 만듬)
- 추상클래스와 마찬 가지로 new 객체생성을 하지 못한다.

- 자바의 인터페이스는 객체의 사용방법을 정의한 타입 (메서드 명세서)으로 객체의 교환성을 높여주기 때문에 다형성을 구현하는 매우 중요한 역할을 한다.
- 인터페이스를 선언할 때는 **interface** 키워드를 사용하며 , 클래스에서 인터페이스를 구현할 때는 클래스 이름 뒤에 **implements** 키워드를 사용하여 구현한다.
- 클래스는 멤버변수 , 생성자 , 메서드를 구성 멤버로 가지지만 인터페이스는 **상수와 메서드만을 구성멤버로 가진다.**
- 인터페이스는 데이터를 저장할 수 없기 때문에 데이터를 저장할 객체 또는 정적 변수(static)를 선언할 수 없다.
- 따라서 인터페이스 선언된 변수는 public static final 을 생략하더라도 컴파일 과정에서 자동으로 붙게된다. (상수)
- 인터페이스의 메서드를 추상메서드 형식으로 선언하면 abstract을 붙이지 않더라도 자동으로 컴파일 과정에서 붙게 된다. (추상메서드)
- 클래스가 상속 가능한 것처럼 인터페이스도 extends 키워드를 사용하여 인터페이스 간의 상속을 구현할 수 있으며 , **다중 상속**도 표현 가능하다.
- ex) 인터페이스를 쓰는 이유
  - 몬스터 중 공격을 하는 몬스터 , 안하는 몬스터 나눌수 있음 

```java
package FirstInterface;

// 수상생물 인터페이스 생성
public interface Aquatic { 
	public abstract void swimming(); // 수영
	public abstract void breathUnderWater(); // 물속 숨쉬기
	
}
```

```java
package FirstInterface;

public class Mermaid implements Aquatic{ // 인어

	@Override
	public void swimming() {
		System.out.println("인어가 수영을 합니다.");
	}

	@Override
	public void breathUnderWater() {
		System.out.println("인어가 물속에서 호흡을 합니다.");
	} 	
}

public class Shark implements Aquatic{  

	@Override
	public void swimming() {
		System.out.println("상어가 수영을 한다.");
	}

	@Override
	public void breathUnderWater() {
		System.out.println("상어가 물속에서 숨을 쉰다.");	
	}
}

public class Octopus implements Aquatic{

	@Override
	public void swimming() {
		System.out.println("문어가 수영을 한다.");
	}

	@Override
	public void breathUnderWater() {
		System.out.println("문어가 물속에서 숨을 쉰다.");
	}
	
}

```

```java
public static void main(String[] args) {
    Mermaid m = new Mermaid();
    Shark s = new Shark();
    Octopus oct = new Octopus();

    // 인터페이스도 다형성 성립
    Aquatic[] aquaArr = { m, s, oct };

    for(Aquatic a : aquaArr) {
    	a.swimming();
	    a.breathUnderWater();
    }
}
```

### 다중상속을 잘 안쓰는 이유

- 다형성 개념을 적용했을 때 메서드가 정의되어 있지 않기 때문에 사용을 못함
- 다형성 구현에 제약이 있기 때문에 신중하게 구현하여야 한다.

```java
package MultiInheritance;

public class MultiMain {
	
	public static void main(String[] args) {
		Smartphone phone = new Smartphone();
		
		Computer computer = new Smartphone();
		TeleVision tv = new Smartphone();
		CellPhone cell = new Smartphone();
		
		phone.watchTV();
		phone.Call();
		phone.Internet();
		
		computer.watchTV(); // 컴퓨터에는 티비보는 기능이 없다.
		computer.Call();    // 컴퓨터에는 전화하는 기능이 없다.
		computer.Internet();
		
		tv.watchTV();
		tv.Call();     // TV에는 전화하는 기능이 없다.
		tv.Internet(); // TV에는 인터넷 기능이 없다.
		
		cell.watchTV();  // 셀폰에는 티비보는 기능이 없다. 
		cell.Call();
		cell.Internet(); // 셀폰에는 인터넷 기능이 없다.
	}
}
```

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
- 프로그램에서 문제가 될만한 부분을 예상하여 사전에 "문제가 발생하면 이렇게 처리하라" 라고 프로그래밍 하는 것을 예외 처리라고 한다.