### [안드로이드] 스레드(Thread) , 핸들러(Handler) 정리

막내 프로그래밍막내 2019. 3. 5. 02:03



스레드는 동시작업을 할려면 필요한 **하나의 작업단위**라고 볼 수 있다.

예를들어, 코드에 따로 스레드를 생성 및 실행을 안하고 실행시킨 경우 **메인스레드**라고 하는 것만 작업을 하고 있는거다. (즉 스레드를 배우지 않았을 때의 안드로이드 프로그래밍은 메인스레드 하나만 돌아가고 있던거라고 보면된다.)



하지만 **동시에 여러작업을 하기 위해서는 스레드를 생성하고 실행시켜야**하고 이를 구현할 줄 알아야한다. 이러한 사용법을 예시코드로 남겨본다.



그러나 구현하는데있어서 **주의해야할 점**이 있다. xml즉 **UI부분에 접근하는 것은 스레드 2개가 동시에 접근을 할 수 없다**. 그래서 기본적으로는(초기) 메인스레드만 UI에 접근 할 수 있고 **메인 외 다른 스레드들은 별도의 제어를 통해 UI부분을 다뤄야한다.**

**
**

이러한 제어중에 핸들러라는것이 있는데 핸들러가 뭔지 간단하게 살펴보자.

**핸들러는 각각의 스레드 안에 만들어질 수 있고 다른 스레드에서 요청하는 정보를 순서대로 실행시켜 줄 수 있기 때문에 리소스에 대한 동시 접근의 문제를 해결해 줍니다.**

**
**

예시코드를 통해 알아보자.



추가설명은 다음 사이트를 참고하면 된다.

https://www.edwith.org/boostcourse-android/lecture/17086/

https://developer.android.com/guide/components/processes-and-threads?hl=ko



================================================

먼저 공통적인 xml코드 및 화면이다.

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="46dp"
        android:layout_marginStart="148dp"
        android:layout_marginLeft="148dp"
        android:layout_marginTop="231dp"
        android:layout_marginEnd="148dp"
        android:layout_marginRight="148dp"
        android:layout_marginBottom="232dp"
        android:text="스레드시작"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/textView"
        android:layout_width="171dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="109dp"
        android:layout_marginLeft="109dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="104dp"
        android:layout_marginRight="104dp"
        android:layout_marginBottom="8dp"
        android:text="처음 초기값"
        android:textSize="25dp"
        app:layout_constraintBottom_toTopOf="@+id/button"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.49"
        android:gravity="center"/>

    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="148dp"
        android:layout_marginLeft="148dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="148dp"
        android:layout_marginRight="148dp"
        android:layout_marginBottom="8dp"
        android:text="스레드 실행 후 값 확인"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/button" />
</android.support.constraint.ConstraintLayout>
```

![img](https://t1.daumcdn.net/cfile/tistory/99E37E3C5C7D549609)







--------------코드들에 대한 설명은 주석을 참고----------------------------



코드1<MyThread1> (단순 스레드 생성 및 실행이다)

스레드에서 UI접근을 못하므로 백그라운드로 value값만 증가시키고 확인버튼(Button2)을 이용해서 값을 확인함, 버튼을 눌러야만 스레드가 작업한 것을 화면으로 확인이 가능하므로 불편하다.

```
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("현재 값 : " + value);
            }
        });
    }

    //스레드 구현한 객체
    class BackgroundThread extends Thread {
        boolean isRun = false;

        public void run() {
            isRun = true;
            //1초마다 벨류값 1씩 증가시키는 스레드임
            while ((isRun)) {
                value += 1;
                //textView.setText("현재 값 : " + value); 만약에 여기서 button2같은 텍스트UI 값을 바꿔주는 걸 한다면
                //에러가 발생한다. (메인액티비티는 메인스레드가 이미 점유하고있기 떄문에 다른 스레드에서 접근을 하면 데드락이 걸려 에러가생김
                //그래서 보통 이런식으로 활용을 잘안하고 핸들러를 사용해서 스레드를 활용한다. (MyThread2에서 확인)
                try {
                    Thread.sleep(1000);
                } catch (Exception e) { }
            }
        }
    }
}
```







코드2<MyThread2> (핸들러를 사용하였다.) 

확인버튼(Button) 사용안한다. 핸들러를 이용해서 스레드에서 동적으로 UI에 접근할 수 있게끔 한다. 

```
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    ValueHandler handler = new ValueHandler();

    //핸들러를 사용한 스레드 사용(메인액티비티의 UI를 메인스레드가 아닌 다른 스레드에서 핸들러를 이용해 접근가능)
    //MyThread , MyThread3 프로젝트와 비교\
    //이것도 실제 개발에서는 이렇게 잘안한다, 왜냐하면 저렇게 메세지를 보내고 핸들러에서 처리하고 하는 과정이 복잡하기떄문이다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //textView.setText("현재 값 : " + value);
            }
        });
    }

    //스레드 구현한 객체
    class BackgroundThread extends Thread {
        boolean isRun = false;
        int value = 0;
        public void run() {
            isRun = true;
            //1초마다 벨류값 1씩 증가시키는 스레드임
            while ((isRun)) {
                value += 1;

                //obtainMessage로 메세지를 참조하고 메세지에 보낼 데이터를 참조하고 sendMessage해줌
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);
                message.setData(bundle);
                //sendMessage가 되면 이 handler가 해당되는 핸들러객체가(ValueHandler) 자동으로 호출된다.
                handler.sendMessage(message);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) { }
            }
        }
    }

    //핸들러구현한 객체(핸들러역할)
    class ValueHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView.setText("현재 값 : " + value); //핸들러를 이용해 스레드에서 UI접근 가능 
        }
    }
}
```







코드3<MyThread3> (핸들러 및 post 이용)

핸들러의 post메소드를 사용하여 위의 코드2보다 더 간편하게 스레드에서 UI접근이 가능케함. (코드2와 마찬가지로 버튼2는 필요없다.)

```
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    //스레드 내부에서 쓸 수 있는 핸들러를 전역으로 정의
    //ValueHandler handler = new ValueHandler();

    Handler handler2 = new Handler();


    //MyThread2에비해 더 간단해짐
    //실제로 가장 많이 쓰임
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*BackgroundThread thread = new BackgroundThread();
                thread.start();*/

                //Runable객체를 implent하는방법(이것을 구현함으로써 한번 실행될 객체를 정의가능)
                //스레드를 만들고 그안에 Runnable을 집어넣는데 myThread2에서 스레드를 클래스로 별도로
                //만들었을떄와 차이가 없음.
                new Thread(new Runnable() {
                    boolean isRun = false;
                    int value = 0;

                    @Override
                    public void run() {
                        isRun = true;
                        //1초마다 벨류값 1씩 증가시키는 스레드임
                        while ((isRun)) {
                            value += 1;
                            //핸들러클래스로서 post로 던질수가있음.
                            //핸들러의 post 메소드를 호출하면 Runnable 객체를 전달할 수 있습니다.
                            //핸들러로 전달된 Runnable, 객체는 메인 스레드에서 실행될 수 있으며 따라서 UI를 접근하는 코드는 Runnable 객체 안에 넣어두면 됩니다.
                            //post 메소드 이외에도 지정된 시간에 실행하는 postAtTime 메소드와 지정된 시간만큼 딜레이된 시간후 실행되는 postDelayed 메소드가 있습니다.
                            handler2.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText("현재값 : " + value);
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }
                        }
                    }
                }).start(); //start()붙이면 바로실행시킨다.
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //textView.setText("현재 값 : " + value);
            }
        });
    }

    /*//스레드 구현한 객체
    class BackgroundThread extends Thread {
        boolean isRun = false;
        int value = 0;
        public void run() {
            isRun = true;
                //1초마다 벨류값 1씩 증가시키는 스레드임
                while ((isRun)) {
                    value += 1;

                //obtainMessage로 메세지를 참조하고 메세지에 보낼 데이터를 참조하고 sendMessage해줌
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);
                message.setData(bundle);
                //sendMessage가 되면 이 handler가 해당되는 핸들러객체가(ValueHandler) 자동으로 호출된다.
                handler.sendMessage(message);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) { }
            }
        }
    }*/

    /*//핸들러구현한 객체(핸들러역할)
    class ValueHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView.setText("현재 값 : " + value);
        }
    }*/
}
```









----실행화면(코드3)-----

1.첫화면

![img](https://t1.daumcdn.net/cfile/tistory/991C16395C7D59C419)





\2. 스레드시작버튼 클릭시 (확인버튼은 코드1에서만 사용하므로 필요없음)

![img](https://t1.daumcdn.net/cfile/tistory/994DAD3E5C7D59D52B)

![img](https://t1.daumcdn.net/cfile/tistory/9915703E5C7D59D601)

![img](https://t1.daumcdn.net/cfile/tistory/998EFD3E5C7D59D608)











일단 이렇게 3가지 예시코드를 공부했다. 이외에도 AsynTask 등 스레드를 다루는 다른 방법들이 있고 스킬적인 부분이 있는데 이는 이후에 차근차근 공부할 예정이다.