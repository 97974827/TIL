# Spring Scheduler - 일정 주기마다 실행하는 스케쥴러 세팅

https://m.blog.naver.com/PostView.nhn?blogId=deeperain&logNo=221609802306&proxyReferer=https:%2F%2Fwww.google.com%2F



***

|      |      |
| ---- | ---- |
|      |      |

개발을 하다보면 여러가지 기능이 필요한 경우가 있습니다. 정해진 시간마다 정해진 일을 수행하는 반복작업을 하는 경우가 있습니다.



특정 시간마다 디비에 접근한다거나 특정코드를 실행하는등 반복작업을 하는 경우 Spring 개발환경에서는 간단한 어노테이션(Annotation)사용으로 간편하게 사용 할수 있도록 지원 해 주고 있습니다.  @Scheduled를 사용하면 되며, @Scheduled는 여러가지 설정을 통해 다양한 방식의 반복작업을 진행 할 수 있도록 해줍니다.



\1. AuthenticateCrontab class

```java
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
 
import java.util.Date;
 
@Service
@EnableScheduling
public class AuthenticateCrontab {
 
 /**
   호출이 완료 되면 완료 이후 5초후 재 호출 된다.
   예를들어  12:00:00에 실행되어 로직 처리까지 3초가 걸렸다면 다음 실행은 완료 후부터 5초후로 
  12:00:08에 실행 된다.
 */
 @Scheduled(fixedDelay = 5000)
 public void fixedDelayTask() {
 System.out.println(new Date() + " This runs in a fixed delay");
 }
 
 /**
  * 호출 완료 여부와 상관 없이 6초마다 재호출된다.
  * 예를 들어 12:00:00에 실행되었다면, 매 6초마다 실행 된다.(12:00:06,12:00:12,.....)
  */
 @Scheduled(fixedRate = 6000)
 public void fixedRateTask() {
 System.out.println(new Date() + " This runs in a fixed rate");
 }
 
 /**
  * 호출은 7초마다 되지만 처음 실행시만 2초간 딜레이 후 실행된다.
  * 예를 들어 12:00:00에 실행되었다면 2초를 쉬고 실행되고 다음실행은 7초마다 실행된다.
  * (12:00:02,12:00:09,12:00:16) 
  */
 @Scheduled(fixedRate = 7000, initialDelay = 2000)
 public void fixedRateWithInitialDelayTask(){
 System.out.println(new Date() + " This runs in a fixed delay with a initial delay");
 }
 
 /**
  * 월~금까지만 10초마다 실행
  */
 @Scheduled(cron = "10 * * * * MON-FRI")
 public void cronTask(){
 System.out.println(new Date() + " This runs in a cron schedule");
 }
}


출처: https://kanzler.tistory.com/60 [kanzler의 세상 이야기]
```





다양한 옵션 조합을 통해 반복일정을 세부화하여 설정 할 수 있게 지원 해주고 있습니다. 특정 thread 하다 돌려서 반복 처리하는 것보다 @Scheduled를 이용하는것이 훨씬 간편하고 다양한 옵션을 사용 할 수 있습니다.



출처: https://kanzler.tistory.com/60 [kanzler의 세상 이야기]