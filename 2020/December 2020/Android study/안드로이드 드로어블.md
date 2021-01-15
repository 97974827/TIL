#### 안드로이드 드로어블

**드로어블 (Drawable)**

드로어블(Drawable)은 뷰에 설정할 수 있는 객체이며 그래픽으로 그릴 수 있습니다.

그래픽이라고 하면 흔히 떠올리는 것이 선이나 원을 선으로 그려주는 것인데요, 이런 작업은 보통 소스 코드에서 하게 됩니다.

하지만 소스 코드가 아닌 XML로 그래픽이 어떻게 그려질지 정의할 수 있다면 좀 더 편리하게 사용할 수 있을 겁니다.

드로어블은 소스 코드에서 만들 수도 있고 XML에서 정의할 수도 있는데 XML로 만들어 사용하는 경우가 많습니다.

드로어블 XML 파일은 /res/drawable 폴더 안에 넣어서 마치 이미지처럼 뷰의 배경으로 설정될 수 있습니다.

드로어블에는 이미지 파일을 보여줄 때 사용하는 비트맵 드로어블(BitmapDrawable), 상태별로 다른 그래픽을 참조할 수 있는 상태 드로어블(StateListDrawable), 두 개의 드로어블 간에 바뀌도록 만들 수 있는 전환 드로어블(TransitionDrawable), 색상과 그러데이션을 포함하여 도형 모양을 정의할 수 있는 쉐이프 드로어블(ShapeDrawable) 등이 있습니다.

지정한 거리만큼 안쪽으로 들어오도록 만들 수 있는 인셋 드로어블(InsetDrawable)은 뷰가 뷰의 실제 범위보다 작은 백그라운드가 필요 할 때 유용하게 사용됩니다.

다른 드로어블을 클리핑하는 클립 드로어블(ClipDrawable)은 진행률 표시 줄과 같은 항목을 구현하는 데 많이 사용됩니다.

그 외로 다른 드로어블의 크기를 바꿀 수 있는 스케일 드로어블(ScaleDrawable)도 있죠.

다양한 기능의 드로어블이 있지만 그중에서 앱을 만들 때 가장 많이 사용하는 드로어블이 상태 드로어블과 쉐이프 드로어블입니다.

 

**상태 드로어블**

상태 드로어블은 뷰의 상태에 따라 뷰에 보여줄 그래픽을 다르게 지정할 수 있도록 합니다.

/res/drawable 폴더 안에 새로운 XML 파일을 만들면 최상위 태그는 <selector>가 됩니다.

그 안에 <item> 태그를 넣을 수 있으며 drawable 속성에는 이미지나 다른 그래픽을 설정하여 화면에 보이도록 할 수 있습니다.

state_ 로 시작하는 속성은 상태를 나타내는데 예를 들어 state_pressed 속성은 눌린 상태를 의미하고 state_focused는 포커스를 받은 상태를 의미합니다.

```markup
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:state_pressed="true"  
android:drawable="@drawable/ic_thumb_up_selected" />

    <item android:drawable="@drawable/ic_thumb_up" />

</selector>
```

상태 속성이 지정되지 않은 <item> 태그에는 drawable 속성에 ic_thumb_up 이미지가 설정되어 있으므로 디폴트 이미지로 보이게 됩니다.

state_pressed 속성이 설정된 <item> 태그에는 ic_thumb_up_selected 이미지가 설정되어 있으며 이 이미지는 뷰가 눌렸을 때 보이게 됩니다.

이렇게 만든 XML 파일은 뷰의 background 속성으로 설정될 수 있습니다.

/res/drawable 폴더 안에 thumb_up.xml 이라는 이름의 파일을 만들었다면 다음과 같이 버튼의 배경으로 설정할 수 있습니다.

```markup
<Button
    android:id="@+id/button"
    android:layout_width="80dp"
    android:layout_height="80dp"
    android:background="@drawable/thumb_up"
    />
```