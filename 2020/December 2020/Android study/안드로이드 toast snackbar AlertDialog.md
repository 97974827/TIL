### 안드로이드 toast / snackbar / AlertDialog



```java
package com.example.mydrawable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "위치가 바뀐 토스트", Toast.LENGTH_LONG);

                // 토스트 메시지 위치를 바꿔줌
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 200, 200);
                toast.show();
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();

                // xml파일안에 레이아웃 스타일 만든 후 inflate을 통해 가져온것
                View layout = inflater.inflate(R.layout.toastborder, (ViewGroup) findViewById(R.id.toast_layout_root));

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("바꾼 토스트");
                Toast toast = new Toast(getApplicationContext());

                // 토스트 속성을 따로 넣어줄수 있음
                toast.setGravity(Gravity.CENTER, 0, -100);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout); // 위에서 정의한 레이아웃 정의
                toast.show();
            }
        });

        /*
          외부 라이브러리 추가
          File - Project Structure - Module - all package - + 눌러서 찾고자하는 패키지 검색
          현 시점에서는 com.google.android.material 패키지를 포함하시면 스낵바를 사용할 수 있습니다!
        */
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 아래쪽에서 올라오는 스낵바 형식
                Snackbar.make(view, "스낵바입니다.", Snackbar.LENGTH_LONG).show();

            }
        });

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showMessage();
            }
        });

    }

    private void showMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("안내");
        builder.setMessage("종료하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        textView = (TextView) findViewById(R.id.textView);

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar.make(textView, "예 버튼이 눌렸습니다.", Snackbar.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               Snackbar.make(textView, "아니오 버튼이 눌렸습니다.", Snackbar.LENGTH_LONG).show();
            }
        });

        AlertDialog dialog = builder.create(); // 최종적으로 알림대화상자 만듬
        dialog.show();
    }
}
```





