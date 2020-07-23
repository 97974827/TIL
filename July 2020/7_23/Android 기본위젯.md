#### Android 

기본위젯

- button - TextView 상속해서 만들어짐 



drawable

- 화면에 그릴수 있는것 
- xml 코드로 만듬  - 화면구성
- 화면의 일부에 어떤 그래픽을 그리고 싶을때 별도 xml파일  지정가능 





앱 아이콘 제작

- `app/manifests/AndroidManifest.xml`
- 앱이름 : `res/values/strings.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.mysummary1">

    <application
        android:allowBackup="true"
        android:icon="@drawable/ic_app"            -> 아이콘 넣기 
        android:label="@string/app_name"           -> 앱 이름 넣기
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        
        -> 하나의 화면나타냄 
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```



- 영화 페이지 디자인 

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"

    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <ImageView
            android:layout_width="100dp"
            android:layout_height="140dp"
            android:src="@drawable/image11"
            android:layout_marginTop="10dp"/>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            android:layout_margin="20dp">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="군도"
                    android:textSize="24dp"
                    android:textStyle="bold"/>

                <ImageView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:src="@drawable/ic_15"
                    android:layout_marginLeft="10dp"/>

            </LinearLayout>

        </LinearLayout>

    </LinearLayout>

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:layout_marginLeft="10dp">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="한줄평"
            android:textSize="18dp"
            android:textStyle="bold"
            android:layout_centerVertical="true"
            />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentRight="true"
            android:layout_marginRight="10dp"
            android:text="작성하기"
            android:textSize="18dp"
            android:textStyle="bold"
            android:background="@drawable/linear_border_orange"
            />

    </RelativeLayout>


</LinearLayout>

```

