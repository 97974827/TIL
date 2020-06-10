# [2020-06-09] 화 TIL

### `Java Design Pattern`

- 참고 youtube : [[이야기's ] 자바 디자인 패턴](https://www.youtube.com/watch?v=UEjsbd3IZvA&list=PLsoscMhnRc7pPsRHmgN4M8tqUdWZzkpxY&index=2&t=0s)

#### 인터페이스

- 사전적의미
  - 키보드나 디스플레이 따위처럼 사람과 컴퓨터를 연결하는 장치 

- 기능에 대한 선언과 기능에 구현을 분리할 수 있는 기능을 제공 
  - `선언 따로 하고 구현은 다른 파일에서 따로 함`
- 어떠한 기능의 호출하는 통로가 된다.

#### 델리게이트

- 위임하다 
- "두 객체와의 관계에서 델리케이트 한다"
- 어떤 기능을 구현할 때 **그 책임을 다른 객체에 떠넘기는 것** , 다른 객체의 기능을 빌려서 사용하는 것 

- 특정 객체에 기능을 사용하기 위해 다른 객체의 기능을 호출하는 것을 델리게이트 한다라고 함 

#### Strategy Pattern 스트래티지(전략) 패턴 

#### ```여러 알고리즘을 하나의 추상적인 접근점(인터페이스)을 만들어 접근 점에서 서로 교환 가능하도록 만든 패턴 ```

- 행위를 클래스로 캡슐화해 동적으로 행위를 자유롭게 바꿀 수 있게 해주는 패턴
- **즉, 전략을 쉽게 바꿀 수 있도록 해주는 디자인 패턴이다**
- 전략이란
  - 어떤 목적을 달성하기 위해 일을 수행하는 방식, 비즈니스 규칙, 문제를 해결하는 알고리즘 등
  - 특히 게임 프로그래밍에서 게임 캐릭터가 자신이 처한 상황에 따라 공격이나 행동하는 방식을 바꾸고 싶을 때 스트래티지 패턴은 매우 유용하다.
- **문제점**
  - 새로운 기능으로 변경하려고 기존 코드의 내용을 수정해야 하므로 OCP(Open Closed Principle)에 위배된다.
    - [OCP(Open Closed Principle) 란?](https://nesoy.github.io/articles/2018-01/OCP)
  - **메서드의 내용이 중복된다**. 이런 중복 상황은 많은 문제를 야기하는 원인이 된다.
  - 만약 공격하는 방식에 문제가 있거나 새로운 방식으로 수정하려면 모든 중복 코드를 일관성있게 변경해야만 한다.

```java
// 요구사항
게임 생성하여 캐릭터와 무기를 구현
무기는 두 종류가 있다.
- 칼, 검
    
무기 - 인터페이스 
칼, 검 - 무기를 상속받아 다형성 구현
캐릭터는 무기로 때릴수도 있고 무기가 없으면 맨손으로 공격할 수도 있다.
```

```java
package Strategy;

public interface Weapon {
	// 베기
	public abstract void Mow(); 

}
```

```java
package Strategy;

public class Knife implements Weapon{ 
	
	@Override
	public void Mow() {
		System.out.println("칼로 벤다.");
	}
}
package Strategy;

public class Sword implements Weapon{
	
	@Override
	public void Mow() {
		System.out.println("검으로 베다.");
	}

}
```

```java
package Strategy;

// 캐릭터 클래스 
public class Job {
	
	// 접근점 -- 무기
	private Weapon weapon;
	
	// 교환 기능 
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public void Attack() {
		if(weapon == null) {
			System.out.println("맨손공격");
		} else {
			// 델리게이트 - 칼이 될수도 있고 검이 될 수도 있다.
			weapon.Mow(); // 공격			
		}
		
	}
}
```

```java
package Strategy;

public class GameMain {
	
	public static void main(String[] args) {
		Job job = new Job(); // 캐릭터 생성 
		job.Attack(); // 무기 X -> 맨손 공격
		
		job.setWeapon(new Knife()); // 캐릭터 생성
		job.Attack(); // 무기 칼 -> 칼로 공격
		
		job.setWeapon(new Sword()); // 캐릭터 생성
		job.Attack(); // 무기 검 -> 검으로 공격 
	}
}
```

