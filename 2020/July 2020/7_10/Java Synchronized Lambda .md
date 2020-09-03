# [2020-07-10 금 TIL]

### Java Thread

### 동기화 (synchronized)

- 멀티스레드 프로세스의 경우 여러 스레드가 같은 프로세스 내의 자원을 공유해서 작업하기 때문에 서로의 작업에 영향을 주게 된다 
- 동기화 사용이유?

```
스레드 A가 작업하던 도중에 다른 스레드 B에게 제어권이 넘어갔을 때, 스레드 A가 작업하던 
공유데이터를 스레드 B가 임의로 변경하였다면, 다시 스레드 A가 제어권을 받아서 
나머지 작업을 마쳤을 때 원래 의도했던 것과는 다른 결과를 얻을 수 있음 
```

- 이러한 일이 발생하는 것을 방지하기 위해서 한 스레드가 특정 작업을 끝마치기 전까지 다른 스레드에 의해 방해 받지 않도록 하는 것이 필요하다 = **`임계영역(Critical Section) 과 잠금(Lock) 도입`**
- 공유 데이터를 사용하는 코드 영역을 임계 영역으로 지정해놓고, 공유 데이터(객체)가 가지고 있는 lock을 획득한 단 하나의 스레드만 이 영역 내의 코드를 수행할 수 있도록 한다.
- 그리고 해당 스레드가 임계 영역내의 모든 코드를 수행하고 벗어나서 lock을 반납해야 비로소 다른 스레드가 반납된 lock을 획득하여 임계 영역의 코드를 수행할 수 있게 된다.
- 이처럼 **한 스레드가 진행 중인 작업을 다른 스레드가 간섭하지 못하도록 막는것**을 동기화라고 한다 
- JDK1.5이상 - 다양한 방식의 동기화 구현 가능 
  - **java.util.concurrent.locks**
  - **java.util.concurrent.atomic**



#### synchronized을 이용한 동기화

```java
1. 메서드 전체를 임계 영역으로 지정 - 권장
    public synchronized void calcSum(){ // 임계영역 (Critical section)
    ...
	}

2. 특정한 영역을 임계 영역으로 지정
    synchronized (객체의 참조변수){ // 임계 영역 (Critical section)
    ...
	}
```

- 첫 번째 방법은 메서드 앞에 synchronized 을 붙이면 메서드 전체가 임계영역으로 설정된다
  - 스레드는 synchronized메서드가 호출된 시점부터 해당 메서드가 포함된 객체의 lock을 얻어 작업을 수행하다가 메서드가 종료되면 lock을 반환한다.
- 두 번째 방법은 메서드 내의 코드 일부를 블럭{}으로 감싸고 블럭 앞에 'synchronized(참조변수)'를 붙이는 것인데, 이 때 참조변수는 락을 걸고자하는 객체를 참조하는 것이여야 한다 
  - 이 블럭의 영역 안으로 들어가면서부터 스레드는 지정된 객체의 lock을 얻게 되고 , 이 블럭을 벗어나면 lock을 반납한다



은행계좌 출금예제 

- 잔고가 음수인 경우가 생김
- 이유는 한 스레드가 if문의 조건식을 통과학고 출금하기 바로 직전에 다른 스레드가 끼어들어서 출금을 먼저 했기 때문이다 
- 잔고(balance)가 출금하려는 금액(money)보다 커서 조건식에 true가 되어 출금을 수행하려는 순간 , 다른 스레드에게 제어권이 넘어가서 다른 스레드가 출금하여 잔고가 0인 상태에서 출금을 하여 마이너스가 됨 

```java
package threading;

class Account{
	private int balance = 1000;
	
	public int getBalance() { return balance; }
	
    // 1. 메서드 동기화 - 이 방법이 좋음 
	public synchronized void withdraw(int money) { // 계좌출금 
		if(balance >= money) {
			try { 
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			
			balance -= money;
		}
	}
    
    // 2. 해당구간 동기화 블록 설정
    public void withdraw(int money) { // 계좌출금
		synchronized(this) {
			if(balance >= money) {
				try { 
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
				
				balance -= money;
			}
		}
	}
}

class RunnableEx implements Runnable{
	
	Account acc = new Account();
//	Thread th;
//	
//	public RunnableEx() {
//		th = new Thread();
//	}
	
	@Override
	public void run() {
		while(acc.getBalance() > 0) {
			int money = (int) (Math.random() * 3 + 1) * 100; // 100,200,300 중의 한 값을 임의로 선택해서 출금 
			acc.withdraw(money);
			System.out.println("balance : " + acc.getBalance());
		}
		System.out.println("thread exited");
	}
	
//	public void start() {
//		th.start();
//	}
}


public class SynchronizedThreadTest {
	
	public static void main(String[] args) {
//		Thread r1 = new Thread(new RunnableEx());
//		Thread r2 = new Thread(new RunnableEx());
//		r1.start();
//		r2.start();
		
		Runnable r = new RunnableEx(); 
		new Thread(r).start();
		new Thread(r).start();
		
		
	}
}

```





### 람다식 (Lambda expression)

- JDK1.8 부터 추가됨
- 함수형 언어의 기능
- **메서드를 하나의 식(expression)으로 표현한 것이다** 
- 메서드를 람다식으로 표현하면 메서드의 이름과 반환값이 없으지므로 , 람다식을 `익명 함수`라고 부른다 
- 특정 클래스에 반드시 속해야하는 메서드 제약과 달리 람다식은 하나의 독립적인 기능을 하기때문에 함수의 용어를 사용하게 됨 

```java
반환타입 메서드이름(매개변수 선언){
    문장들
}
		|
		|
		V




int[] arr = new int[5];

// 람다식 표현 
Arrays.setAll(arr, (i) -> (int) (Math.random()*5)+1); // 1 ~ 5

// 메서드 표현
int method(){
    return (int) (Math.random()*5) + 1;
}
```

