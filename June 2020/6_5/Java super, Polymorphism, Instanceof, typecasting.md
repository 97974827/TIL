# 2020-06-05 금 TIL

#### super (부모클래스를 의미, 자식 생성자에서 사용 가능 ) - 공존하는 의미

- 클래스 내의 멤버 변수와 지역변수의 이름이 같을 경우 구분을 위해 **this 키워드**를 사용하듯이 부모 클래스와 자식 클래스의 멤버의 이름이 같은 경우 **super.**를 사용 

- **한단계 위 부모 클래스의 객체를 지정할 때 사용**하는 키워드
- **super.** 을 사용하면 부모클래스의 멤버를 참조 가능
- **super()** 는 **생성자 내부에서만 사용이 가능** , 부모클래스의 생성자를 호출하는 데 사용 
  - **부모 클래스에게 매개변수를 전달해라**
- **생성자의 첫 라인에는 반드시 this() , super() 가 있어야 하는데** 이를 기술하지 않으면 묵시적으로 super() 가 삽입되어 부모 클래스의 기본 생성자를 자동으로 호출한다.
- 생성자 내부에서 또 다른 생성자를 호출할 때는 반드시 **생성자 블록 내부의 첫 라인에 기술**해야 함 

- 상속 받은 자식 객체는 객체가 부모객체와 자기자신 객체 가 동시 존재함

***

### ```this.``` 와 ```this()```

- ```this.```  인스턴스 자신을 가르키는 참조 변수
- ```this()``` 자기자신 생성자 

```java
public class Car{
    String color;
    String gearType;
    int door;
    
    public Car(){   
        this("blue", "auto", 4);
    }
    
    public Car(String color){
        this(color, "auto", 4);
    }
    
    public Car(String color, String gearType, int door){
		this.color = color;
        this.gearType = gearType;
        this.door = door;
    }
}
```

***

### 다형성 Polymorphism

- ```객체가 여러 형태를 가진다``` 
- 프로그램의 확장성 용이 
- 하나의 객체가 여러가지 유형으로 사용되는 것을 의미
- 자료형 하나로 여러 가지를 쓸수 있음 
- 상속을 전제 조건으로 함 
- 자식클래스가 부모클래스의 타입을 가질수 있음 

- ex)  온라인 게임 
  - 몬스터나 직업군을 추가할 때 일일히 하나씩 만들수 없으므로 다형성을 이용

```java
// 다형성
부모클래스 부모객체 = new 자식클래스();
```

#### instanceof

- 객체가 지정한 클래스의 인스턴스인지 아닌지 검사할 때 사용하는 연산자.
- instance of 연산자의 왼쪽항의 객체가 오른쪽 항 클래스의 인스턴스 즉 오른쪽 항의 객체가 생성되었다면 true를 리턴 
- ex)
  - Person p = new Student() : Person인지 Student인지 확인
    - p instanceof Student -> true
    - System.out.println(magic instanceof Job);

```java
int i = 0;
for (Monster n : mArr) {
	if (mArr[i] instanceof Orc) { // 몬스터 객체 배열의 인스턴스가 오크 일때 -> true
		System.out.println(n.getName());				
	}
	i++;
}
    
Magician m1 = new Magician();
Magician m2 = new Magician();		
Warrior w1 = new Warrior();
Warrior w2 = new Warrior();
jArr[0] = m1;
jArr[1] = m2;
jArr[2] = w1;
jArr[3] = w2;

i=0;
for(Job j : jArr) {
    if(j instanceof Warrior) {
        System.out.println(j.getName() + " 의 직업은 전사 입니다.");
    } else {
        System.out.println(j.getName() + " 의 직업은 마법사 입니다.");
    }
}
```



***

### 강제 타입 변환 Type Casting

- 강제 타입 변환은 **부모** 타입을 **자식** 타입으로 변환하는 것
- 객체에서 타입 캐스팅을 사용하려면 우선 먼저 Promotion이 일어나야함  즉 , 부모 타입으로 한번 형변환이 된 자식 객체만 강제 타입변환을 사용 가능 
- Promotion 이 일어나면 자식클래스가 가지고 있는 재정의 되지 않은 메서드를 사용할 수 없다는 단점
- 이 단점을 극복하기 위해 강제 타입변환을 사용하여 자식 메서드를 호출하는 방법 사용 

```java
Job j = new Magician();
Orc o1 = new Orc();

j.Attack(o1);
//j.fireBall(o1); -> error : 마법사의 고유의 스킬 파이어볼

Magician m2 = (Magician) j;

m2.Attack(o1);
m2.fireBall(o1);
```



***

### 이중 모음 Heterogeneout Collection

- 배열에 다형성을 적용
  - ex)
    - int[] arr = new int[10];
    - int 형 정수 10개 담을 수 있음
- 하지만 다형성을 이용하면 이중모음 구조의 객체 배열이 생성가능
  - Monstar[] MonsterArr = new Monster[10];
  - 몬스터를 상속받은 클래스 전부 넣을수 있음 

```java
Magician magic = new Magician();
Warrior warrior = new Warrior();
Monster[] mArr = new Monster[4];

Orc o1 = new Orc();
Orc o2 = new Orc();
Orc o3 = new Orc();
Golem g1 = new Golem();

mArr[0] = o1;
mArr[1] = o2;
mArr[2] = o3;
mArr[3] = g1;

System.out.println(mArr[0].getName());
System.out.println(mArr[1].getName());
System.out.println(mArr[2].getName());
System.out.println(mArr[3].getName());
	
for (Monster n : mArr) {
    System.out.println(n.getName());
}
```



***

### static 심화

- 사용 제한자 Usage Level modifier
- static 제한자는 변수, 메서드에 적용되는 키워드
- static 메서드나 변수는 해당 클래스의 객체 없이도 참조할 수 있다.
- static 제한자는 지정된 변수와 메서드를 객체와 무관하게 만들어주기 때문에 this를 가질수 없다.
- static 메서드는 non-static 메서드로 재정의 될수 없다. (오버로딩)
- 대표적인 static 메서드는 애플리케이션의 **main()** 메서드
- **힙데이터 영역에 저장되서 스택영역의 데이터들과 공유**

***

### MySql 

#### 날짜 구하기

- 어제, 내일 날짜 구하기 

``` WHERE DATE_FORMAT(`t_charger`.start_time, "%Y-%m-%d") = CURDATE() - INTERVAL 1 day;```

``` WHERE DATE_FORMAT(`t_charger`.start_time, "%Y-%m-%d") = CURDATE() + INTERVAL 1 day;```

