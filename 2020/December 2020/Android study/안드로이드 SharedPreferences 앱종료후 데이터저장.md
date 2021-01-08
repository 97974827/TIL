안드로이드 앱 종료 후 데이터 저장 

```java
String sfName = "myFile";
@BindView(R.id.editText1)
EditText editText1;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    // 지난번 저장해놨던 사용자 입력값을 꺼내서 보여주기
    SharedPreferences sf = getSharedPreferences(sfName, 0);
    String str = sf.getString("name", ""); // 키값으로 꺼냄, 두번째 인수는 앱 처음 시작할 때 디폴트 값

    editText1.setText(str); // EditText에 반영함
} // end of onCreate

@Override
protected void onStop() {
    super.onStop();
    // Activity 가 종료되기 전에 저장한다
    // SharedPreferences 에 설정값(특별히 기억해야할 사용자 값)을 저장하기
    SharedPreferences sf = getSharedPreferences(sfName, 0);
    SharedPreferences.Editor editor = sf.edit();//저장하려면 editor가 필요
    String str = editText1.getText().toString(); // 사용자가 입력한 값
    editor.putString("name", str); // 입력
    editor.commit(); // 파일에 최종 반영함 //꼭!!!!!!
}
```



이제 앱을 실행하고 edittext에 글을 쓰고 앱을 종료해보자!

다시 앱을 실행시켰을 때 썼던 글이 그대로 있으면 성공!!





★응용해보자1

<LoginActivity에 다녀온 적이 있다면 My버튼을 눌렀을 때 '있음' 토스트메시지 띄우기, 

다녀온 적이 없다면 '없음' 토스트메시지 띄우기!>



=> MainActivity에 있는 my버튼을 누르면 "my"의 값을 1로 변경하면 된다!



```java
@Override

public void onClick(View view) {

    switch (view.getId()) {

        case R.id.btn_my:
            // SharedPreferences 에 설정값(특별히 기억해야할 사용자 값)을 저장하기
            SharedPreferences sf = getSharedPreferences(sfName, 0);
            SharedPreferences.Editor editor = sf.edit();//저장하려면 editor가 필요

            int i = sf.getInt("my", 0); //0은 디폴트값!
            if (i == 0)
                Toast.makeText(this, "없음", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "있음", Toast.LENGTH_SHORT).show();

            editor.putInt("my", 1); // 입력
            editor.commit(); // 파일에 최종 반영함
            break;
    }

} 
```



★응용해보자2

<LoginActivity에 다녀온 적이 있다면 My버튼을 눌렀을 때 MyPageActivity로, 

다녀온 적이 없다면 LoginActivity에 가도록!>



=> Toast 줄 부분만 바꾸면 됨!

```java
@Override
public void onClick(View view) {
    Intent intent;
    switch (view.getId()) {
        case R.id.btn_my:
            // SharedPreferences 에 설정값(특별히 기억해야할 사용자 값)을 저장하기
            SharedPreferences sf = getSharedPreferences(sfName, 0);
            SharedPreferences.Editor editor = sf.edit();//저장하려면 editor가 필요

            int i = sf.getInt("my", 0);
            if (i == 0)
               intent = new Intent(this, LoginActivity.class);
            else
                intent = new Intent(this, MyPageActivity.class);

            startActivity(intent);

            editor.putInt("my", 1); // 입력
            editor.commit(); // 파일에 최종 반영함
            break;
    }
```

} 