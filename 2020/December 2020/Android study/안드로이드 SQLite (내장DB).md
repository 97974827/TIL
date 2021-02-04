### 안드로이드 SQLite

- 단말에 데이터를 저장하는 방법 (간단하게 저장)
- 리눅스 파일시스템으로 저장 
- 인터넷이 안되는 상황
- 서버 X
- 임베디드 데이터베이스 

```java
package com.example.mydatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;

    SQLiteDatabase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);

        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataBaseName = editText.getText().toString();
                openDataBase(dataBaseName);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tableName = editText2.getText().toString();
                createTable(tableName);
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText3.getText().toString();
                String ageStr = editText4.getText().toString().trim();
                String mobile = editText5.getText().toString();
                int age = -1;
                try {
                    age = Integer.parseInt(ageStr);
                } catch(Exception e) {}
                insertData(name, age, mobile);
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tableName = editText2.getText().toString();
                selectData(tableName);
            }
        });
    }

    public void selectData(String tableName){
        println("selectData() 호출됨");
        if (dataBase != null){
            String sql = "SELECT * FROM " + tableName;
            Cursor cursor = dataBase.rawQuery(sql, null);
            println("데이터 조회갯수 : " + cursor.getCount());

            for(int i=0; i<cursor.getCount(); i++){
                cursor.moveToNext(); // 레코드이동
                String name = cursor.getString(0);
                int age = cursor.getInt(1);
                String mobile = cursor.getString(2);

                println("#" + i + " -> " + name + ", " + age + ", " + mobile);
            }
            cursor.close();
        }
    }

    public void insertData(String name, int age, String mobile){
        println("insertData() 호출됨");
        if (dataBase != null){
            String sql = "INSERT INTO customer(name, age, mobile) VALUES (?,?,?)";
            Object[] params = {name, age, mobile};
            dataBase.execSQL(sql, params);
            println("데이터 추가함");
        }
    }

    public void openDataBase(String dataBaseName){
        println("openDataBase() 호출됨");
        dataBase = openOrCreateDatabase(dataBaseName, MODE_PRIVATE, null); // 데이터베이스 생성메소드
        if (dataBase != null){
            println("데이터베이스 오픈됨");
        }
    }

    public void createTable(String tableName){
        println("createTable() 호출됨");
        if (dataBase != null){
            // autoincrement : integer 값 허용
            String sql = "CREATE TABLE " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)";
            dataBase.execSQL(sql);
            println("테이블생성됨");
        } else {
            println("먼저 데이터베이스를 오픈하세요.");
        }
    }

    public void println(String data){
        textView.append(data + "\n");
    }

}
```



### SQLiteOpenHelper

- 새로만드는 CustomerDatabase 클래스는 DatabaseHelper 객체와 버전정보관리
- 핼퍼클래스를 상속한DatabaseHelper 클래스안에서는 처음 데이터베이스가 만들어질 때는 onCreate()
- 버전이 바뀌어 업그레이드 될때는 onUpgrade() 메소드 호출 

```java
package com.example.mydatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;

    SQLiteDatabase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);

        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataBaseName = editText.getText().toString();
                openDataBase(dataBaseName);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tableName = editText2.getText().toString();
                createTable(tableName);
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText3.getText().toString();
                String ageStr = editText4.getText().toString().trim();
                String mobile = editText5.getText().toString();
                int age = -1;
                try {
                    age = Integer.parseInt(ageStr);
                } catch(Exception e) {}
                insertData(name, age, mobile);
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tableName = editText2.getText().toString();
                selectData(tableName);
            }
        });
    }

    public void selectData(String tableName){
        println("selectData() 호출됨");
        if (dataBase != null){
            String sql = "SELECT * FROM " + tableName;
            Cursor cursor = dataBase.rawQuery(sql, null);
            println("데이터 조회갯수 : " + cursor.getCount());

            for(int i=0; i<cursor.getCount(); i++){
                cursor.moveToNext(); // 레코드이동
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String mobile = cursor.getString(3);

                println("#" + i + " -> " + name + ", " + age + ", " + mobile);
            }
            cursor.close();
        }
    }

    public void insertData(String name, int age, String mobile){
        println("insertData() 호출됨");
        if (dataBase != null){
            String sql = "INSERT INTO customer(name, age, mobile) VALUES (?,?,?)";
            Object[] params = {name, age, mobile};
            dataBase.execSQL(sql, params);
            println("데이터 추가함");
        }
    }

    public void openDataBase(String dataBaseName){
        println("openDataBase() 호출됨");
        /*dataBase = openOrCreateDatabase(dataBaseName, MODE_PRIVATE, null); // 데이터베이스 생성메소드
        if (dataBase != null){
            println("데이터베이스 오픈됨");
        }*/

        DataBaseHelper helper = new DataBaseHelper(this, dataBaseName, null, 3);
        dataBase = helper.getWritableDatabase();
    }

    public void createTable(String tableName){
        println("createTable() 호출됨");
        if (dataBase != null){
            // autoincrement : integer 값 허용
            String sql = "CREATE TABLE " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)";
            dataBase.execSQL(sql);
            println("테이블생성됨");
        } else {
            println("먼저 데이터베이스를 오픈하세요.");
        }
    }

    public void println(String data){
        textView.append(data + "\n");
    }

    class DataBaseHelper extends SQLiteOpenHelper{

        public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // 일반 테이블 생성호출
        @Override
        public void onCreate(SQLiteDatabase db) {
            println("onCreate() 호출");
            String tableName = "customer";
            String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)";
            db.execSQL(sql);
            println("테이블생성됨");
        }

        // 버전이 바뀌었을때 호출
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            println("onUpgrade() 호출 : " + oldVersion + ", " + newVersion);

            if (newVersion > 1){
                String tableName = "customer";
                db.execSQL("DROP TABLE IF EXISTS " + tableName);
                println("테이블 삭제함");

                String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)";
                db.execSQL(sql);

                println("테이블 새로 생성됨");
            }
        }
    }

}
```

