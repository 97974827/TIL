### 안드로이드 ViewPager 

- 슬라이드로 화면 전환 하는 기능 



```java
package com.example.myviewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Fragment1 f1;
    Fragment2 f2;
    Fragment3 f3;

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);

        manager = getSupportFragmentManager();
        CustomerPagerAdapter adapter = new CustomerPagerAdapter(manager);

        f1 = new Fragment1();
        adapter.addItem(f1);
        f2 = new Fragment2();
        adapter.addItem(f2);
        f3 = new Fragment3();
        adapter.addItem(f3);

        pager.setAdapter(adapter);
    }

    class CustomerPagerAdapter extends FragmentStatePagerAdapter{
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public CustomerPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item){
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}
```



```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">


    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentStart="true"
        android:layout_alignParentTop="true"
        android:layout_marginStart="2dp"
        android:layout_marginTop="4dp"
        android:text="Button" />

    <androidx.viewpager.widget.ViewPager
        android:id="@+id/pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@+id/button"
        android:layout_alignParentLeft="true">

    </androidx.viewpager.widget.ViewPager>

</RelativeLayout>
```





#### 예제) TabLayout + ViewPager

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">


    <com.google.android.material.tabs.TabLayout
        android:id="@+id/tabs"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#eaeaea"
        app:tabMode="fixed"
        app:tabTextColor="@android:color/holo_blue_dark"
        app:tabSelectedTextColor="@android:color/holo_orange_light"
        app:tabIndicatorColor="@android:color/black"
        tools:ignore="MissingConstraints">
    </com.google.android.material.tabs.TabLayout>

    <androidx.viewpager.widget.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </androidx.viewpager.widget.ViewPager>

</LinearLayout>
```



```java
package com.example.mytabviewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 탭 메뉴 추가
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);

        PagerAdapter adapter = new PageAdapter(getSupportFragmentManager());
        tabs.addTab(tabs.newTab().setText(adapter.getPageTitle(0)));
        tabs.addTab(tabs.newTab().setText(adapter.getPageTitle(1)));
        tabs.addTab(tabs.newTab().setText(adapter.getPageTitle(2)));

        // 뷰 페이저 슬라이드 추가
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter); // 페이지 어댑터 추가

        // 뷰페이저 페이지 변화 처리
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        // 탭 레이아웃 이벤트 처리
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

    }
}
```



```java
package com.example.mytabviewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentStatePagerAdapter {

    List<Fragment> items = new ArrayList<Fragment>();

    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm);
        items.add(new PageFragment1());
        items.add(new PageFragment2());
        items.add(new PageFragment3());
    }

    public void addItem(Fragment item){
        items.add(item);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "1번째 탭";
            case 1:
                return "2번째 탭";
            case 2:
                return "3번째 탭";
            default:
                return null;
        }
    }
}

```

