### Android Event / ListView



#### TableLayout

- 옵션
  - **stretchColumns** : 각각의 추가되는 뷰를 칼럼이라고 봄
    - 남아있는 여유 공간을 다 차지하도록 함 (값 : 0,1,2)



#### EditText

- layout_span : 얼만큼 차지하도록 할것인가 "3"



### 이벤트 처리하기 

- xml - id 이름 = Java 파일 변수 이름 동일해야함 
- **터치 이벤트**

```java
package com.example.myevent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        View view = findViewById(R.id.view1);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) { // 움직이는 순간순간 호출됨
                int action = motionEvent.getAction();

                float x = motionEvent.getX();
                float y = motionEvent.getY();

                if(action == MotionEvent.ACTION_DOWN){
                    println("손가락 눌렸음 : " + x + ", " + y);
                } else if(action == MotionEvent.ACTION_MOVE) {
                    println("손가락 움직임 : " + x + ", " + y);
                } else if(action == MotionEvent.ACTION_UP){
                    println("손가락 떼졌음 : " + x + ", " + y);
                }

                return true;
            }
        });

    }

    public void println(String data){
        textView.append(data + "\n");
    }



}
```

