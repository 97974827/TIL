### 안드로이드 TabMenu 활용

build gradle app

```
dependencies {
    //noinspection GradleCompatible
    implementation 'com.android.support:design:25.+' // 추가
}
```



activitiy_main

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <androidx.coordinatorlayout.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <com.google.android.material.appbar.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

            <androidx.appcompat.widget.Toolbar
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:background="@color/colorPrimaryDark"
                android:theme="@style/ThemeOverlay.AppCompat.Dark"
                android:elevation="1dp"
                android:id="@+id/toolbar">
            </androidx.appcompat.widget.Toolbar>

            <com.google.android.material.tabs.TabLayout
                android:id="@+id/tabs"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:elevation="1dp"
                android:background="@android:color/background_light"
                app:tabMode="fixed"
                app:tabTextColor="@color/colorPrimary"
                app:tabSelectedTextColor="@color/colorAccent">
            </com.google.android.material.tabs.TabLayout>

        </com.google.android.material.appbar.AppBarLayout>

        <FrameLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior"
            android:id="@+id/container">

        </FrameLayout>

    </androidx.coordinatorlayout.widget.CoordinatorLayout>


</RelativeLayout>
```



MainActivity.java

```java
package com.example.mytab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    TabFragment1 f1;
    TabFragment2 f2;
    TabFragment3 f3;

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        f1 = new TabFragment1();
        f2 = new TabFragment2();
        f3 = new TabFragment3();

        getSupportFragmentManager().beginTransaction().add(R.id.container, f1).commit();

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("1번째텝")); // 탭 추가 가능
        tabs.addTab(tabs.newTab().setText("2번째텝")); // 탭 추가 가능
        tabs.addTab(tabs.newTab().setText("3번째텝")); // 탭 추가 가능
        tabs.addTab(tabs.newTab().setText("4번째텝")); // 탭 추가 가능
        tabs.addTab(tabs.newTab().setText("5번째텝")); // 탭 추가 가능
        tabs.addTab(tabs.newTab().setText("6번째텝")); // 탭 추가 가능
        tabs.addTab(tabs.newTab().setText("7번째텝")); // 탭 추가 가능
        tabs.addTab(tabs.newTab().setText("8번째텝")); // 탭 추가 가능


        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if (position == 0){
                    selected = f1;
                } else if (position == 1){
                    selected = f2;
                } else if (position == 2){
                    selected = f3;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ((ViewGroup) tabs.getChildAt(0)).getChildAt(2).setVisibility(View.GONE); // 탭메뉴 숨기기 
    }
}
```

