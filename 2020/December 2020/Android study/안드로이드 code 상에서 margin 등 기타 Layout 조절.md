### [[Android\] code 상에서 margin 등 기타 Layout 조절](https://devbible.tistory.com/45)

devbible 2010. 8. 25. 17:58

LinearLayout layout  = (LinearLayout) findViewById(R.id.LinearLayout);

위에서 해당 레이아웃이 layout 이라고 할때.
code상에서 기본적으로 Height, width, padding 등 지원하는것도 있지만 margin 등
상세한 레이아웃을 바로 지정하지는 못한다..

그래서 code상에서 레아이웃에대한 변경이 필요할때 LayoutParams 를 이용해야한다.



LinearLayout layout  = (LinearLayout) findViewById(R.id.LinearLayout);

//아래처럼 해당 **레이아웃.LayoutParams** 를 만들어주고 (해당 레이아웃이 랠러티브나,앱솔루트등 다른거라면 그거에 해당하는 레이아웃.LayoutParams 를 해야한다.

LinearLayout.LayoutParams layparam = (LinearLayout.LayoutParams) layout.getLayoutParams();

//아래처럼 값들을 설정한다음
layparam .bottomMargin = 10;
layparam .topMargin = 10;

//아래처럼 Layout에 LayoutParams 를 설정해주면 적용된다.
layout.setLayoutParams(layparam);


LayoutParams 는 재사용 할 수 있어서 필요한거 몇개 만들어놓고 계속 쓰면 좋을듯..

[작성자] devbible.tistory.com



출처: https://devbible.tistory.com/45 [devbible]