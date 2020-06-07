# 2020-06-06 토 TIL

### JAVA

#### static 변수, 메서드

- 프로그램에서 공유하는 자원들을 static 키워드로 주로씀
- 객체를 만들지 않고도 이용 가능 

***

### 싱글톤 패턴 (Singleton Pattern)

- 어떤 클래스의 객체는 오직 하나임을 보장하며 , 이 객체에 접근할 수 있는 전역적인 접촉점을 제공하는 패턴
  - **"하나를 만들어놓고 필요하는 곳에 빌려주기만 한다"** 라는 의미 

- 클래스 객체를 유일하게 하나만 생성하여 모든곳에서 하나의 객체에 접근하게 하여, 전역의 개념을 객체를 사용 할수 있다.
- **객체의 생성을 제한하기 위해 사용한다.**

Company.java

```java
package Singleton;

public class Company {
	
	private String str; // 문자열 변수
	
	private static Company c = new Company();  // 지역내부에서 자기자신이 자기자신을 만듬 
	
	// 생성자 private 
	private Company() {
		str = "Company";
		System.out.println(str);
	}
	
	public static Company getCompany() {
		return c;
	}

}

```

Main.java

```java
package Singleton;

public class TestSingleton {
	
	public static void main(String[] args) {
		// Company c = new Company(); // err : 접근 X
		
		Company c1 = Company.getCompany();
		Company c2 = Company.getCompany();
		
		System.out.println(c1);
		System.out.println(c1==c2);
		System.out.println(c2);
	}
}
/*
Company
Singleton.Company@15db9742
true
Singleton.Company@15db9742
*/

```

- 특징
  - 외부 생성 불가능
  - 이미 생성이 되어있음 
  - 생성자는 Company 내부에 생성된 것 처럼 보이지만 static 키워드로 힙영역에 저장되어있음 
  - 힙데이터 영역에 만들어진 객체 c (참조변수)는 실제 데이터가 들어있지 않고 Company 를 참조하는 참조형 변수 일뿐이다. (표지판 역할)
  - c1, c2 는 static Company 를 가르키기 때문에 같음 

***

### final

- 클래스, 메서드, 변수에 적용되며 abstract와 동시에 사용될 수 없다.
- final 클래스의 경우 상속이 안됨 즉, 서브 클래스를 가질 수 없다.
- final 메서드는 재정의를 할 수 없다.
- final 변수는 값을 변경 할 수 없다.

#### final class

- 클래스 선언시 final 을 사용하면 그 클래스는 상속이 불가능
- 자식 클래스를 가질수 없고, 오직 외부에서 객체 생성을 통해서만 사용 가능
- final 클래스의 대표적 예 String 클래스
  - 사용자가 임의로 String 클래스를 상속받아 메서드를 재정의하는 것을 방지하기 위함

#### final 변수

- 선언만 했을 땐 값을 한번만 넣어줄수 있음
- 한번 넣은 값은 변경 불가 

#### static final 상수

- 불변의 값
- 객체마다 저장할 필요가 없는 공용성을 가져야 하며 , 여러가지 값으로 초기화 될수 없기 때문에 static 과 final 제한자를 동시에 붙여 선언한다.
- 상수의 이름은 모두 대문자로 작성하는 것이 관례 , 연결된 단어라면 _로 단어들을 연결

***

### abstract

- 클래스와 메서드에 적용된다
- 추상(abstract) 클래스는 실체 클래스들의 멤버변수와 메서드들의 이름을 통일할 목적으로 사용
- 추상(abstract) 메서드가 있는 클래스는 반드시 추상 클래스여야 한다.
- 그러나 추상 클래스에 반드시 추상 메서드만 선언할 필요는 없고 일반 메서드도 선언 가능



#### 추상 클래스 (구현 클래스)

- 상속만을 목적필요 그리고 객체 만들지 못하게 하기위함 으로써 필요

- 추상 클래스는 new 키워드를 이용해서 **객체를 만들지 못하고** 오직 **상속**을 통해서 자식 클래스로 구체화 시켜야 한다.
- 추상 클래스도 일반 클래스와 마찬가지로 멤버변수, 생성자, 메서드를 선언할 수 있다.
- 추상클래스로 선언된 클래스는 객체를 만들수 없다. 오로지 상속만 가능하다.
- new를 사용하여 직접 생성자를 호출할 수 없지만, 자식 객체가 생성될 때 super() 를 호출하여 추상 클래스 객체를 생성하므로 추상 클래스도 생성자가 반드시 있어야 한다.



#### 추상 메서드

- 추상 클래스 내에서만 선언 가능
- 메서드의 선언부만 있고 메서드 실행 내용이 들어가는 중괄호 {}가 없는 메서드를 말함 --> 객체 못만듬
- **추상 클래스를 설계할 때 , 자식 클래스가 반드시 실행 내용을 채우도록 강요하고 싶은 메서드가 있을 경우 , 해당 메서드를 추상 메서드로 선언한다.** (클래스와 메서드를 abstract 선언)
- **자식 클래스에서 반드시 부모 추상클래스의 추상 메서드를 재정의 하여 실행 내용을 작성해야 한다. 그렇지 않으면 컴파일 에러 발생** 

```java
public abstract class Job { // 클래스에도 키워드 추가 (안쓰면 에러발생)
	private String name;
	public int hp;
	public int mp;
	public int attack;
	
	public Job(String name) {
		this.name = name;
		this.hp = 40;
		this.mp = 40;
		this.attack = 5;
	}

    // 추상 메서드 : 중괄호 실행문 X , 상속만을 목적으로 사용 
	public abstract void Attack(Monster m);

	public abstract void showInfo();
```

```java
public static void main(String[] args) {
	Job j1 = new Job("잡종");			 	 // error : Cannot instantiate the type Job
	Monster m = new Monster("몬스터");		// error : Cannot instantiate the type Monster
	j1.Attack(m);
}
// 추상클래스는 객체를 인스턴스화 할수 없다 , 오로지 상송만을 목적으로 사용 
```



