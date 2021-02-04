# Fragment 잘 써보기 — Bundle()

[![hongbeom](https://miro.medium.com/fit/c/96/96/0*VlbXyp_nOjcqkidS)](https://hongbeomi.medium.com/?source=post_page-----c2fd8fe96967--------------------------------)

[hongbeom](https://hongbeomi.medium.com/?source=post_page-----c2fd8fe96967--------------------------------)Follow

[Apr 5, 2020](https://medium.com/hongbeomi-dev/fragment-잘-써보기-bundle-c2fd8fe96967?source=post_page-----c2fd8fe96967--------------------------------) · 3 min read



https://medium.com/hongbeomi-dev/fragment-%EC%9E%98-%EC%8D%A8%EB%B3%B4%EA%B8%B0-bundle-c2fd8fe96967





![Image for post](https://miro.medium.com/max/60/0*WEwqt6wnHu6-3-pg?q=20)

![Image for post](https://miro.medium.com/max/4032/0*WEwqt6wnHu6-3-pg)

Photo by [Franki Chamaki](https://unsplash.com/@franki?utm_source=medium&utm_medium=referral) on [Unsplash](https://unsplash.com/?utm_source=medium&utm_medium=referral)

> 프래그먼트를 사용할 때 필요한 데이터를 효율적으로 전달하는 방법을 알아봅니다.

# 🧐 What is Fragment?

Fragment는 다른 Activity에 재사용할 수 있는 “하위 Activity”와 같은 개념이라고 설명되어 있습니다. 자체적인 수명주기를 가지며 하나의 Activity에 여러 개의 Fragment를 결합하여 사용할 수도 있고 하나의 Fragment를 여러 개의 Activity에서 재사용할 수도 있습니다. 더 자세한 설명을 확인하시려면 [여기](https://developer.android.com/guide/components/fragments?hl=ko)를 참고하시면 됩니다.

# 📦 Bundle()

Fragment 혹은 Activity에서 다른 Fragment로 데이터를 전달할 때 사용하는 것이 `Bundle()` 입니다. 새로운 Fragment를 생성할 때 `arguments`에 `Bundle()`을 넘겨줌으로써 데이터를 넘겨줄 수 있는 것입니다. `arguments`는 `Bundle()`을 파라미터로 받고 `Bundle()`은 `put~~()` 메소드를 호출하여 데이터를 담아서 `arguments`에 넘깁니다. `String`, `Long`, `Int` 타입은 물론 `Parcelable`한 클래스도 `Bundle()`에 담을 수 있습니다. 코드를 보며 확인해보도록 하겠습니다.

<iframe src="https://medium.com/media/4d80c3b6a3827523f542b6f891854d6b" allowfullscreen="" frameborder="0" height="303" width="680" title="fragment - bundle" class="t u v hd aj" scrolling="auto" style="box-sizing: inherit; position: absolute; top: 0px; left: 0px; width: 680px; height: 303px;"></iframe>

MyActivity.kt

현재 `MyActivity`에서 `MyFragment`의 `arguments`에 `String` 데이터인 `“value”`를 넘기고 있습니다. 그리고 `MyFragment`에서는 밑의 코드처럼 데이터를 받아서 사용할 것입니다.

<iframe src="https://medium.com/media/311b7f4e75eae94b53efd0cae489cbb3" allowfullscreen="" frameborder="0" height="127" width="680" title="fragment - bundle" class="t u v hd aj" scrolling="auto" style="box-sizing: inherit; position: absolute; top: 0px; left: 0px; width: 680px; height: 126.984px;"></iframe>

MyFragment.kt

`arguments`에서 키를 통해서 방금 `MyActivity`에서 보낸 데이터를 받아서 사용할 수 있습니다. 아래 코드와 같이 `by lazy`와 `requireArguments()` 메소드를 통해 키에 매칭되는 데이터가 없다면 `IllegalStateException`을 던지도록 만들수도 있습니다.

<iframe src="https://medium.com/media/a22e662259811a36ec3e42a41900a644" allowfullscreen="" frameborder="0" height="171" width="680" title="MyFragment2.kt" class="t u v hd aj" scrolling="auto" style="box-sizing: inherit; position: absolute; top: 0px; left: 0px; width: 680px; height: 171px;"></iframe>

MyFragment.kt

하지만 위의 코드처럼 데이터를 수신할 Fragment를 생성하면서 `arguments`에 데이터를 넘겨주는 코드를 Activity에서 작성하다보면 여러 개의 Fragment에 각기 다른 데이터를 전달해주는 경우 Activity에 코드가 엄청 길어질 것을 예측할 수 있습니다. 이를 위해 위의 코드를 살짝 고쳐보겠습니다.

# 👀 데이터를 수신하는 Fragment에 코드 작성하기

위의 코드는 Activity에서 데이터를 넘겨주는 코드를 작성했습니다. 이제 데이터를 수신하는 Fragment에서 위 코드를 작성해보도록 하겠습니다.

<iframe src="https://medium.com/media/2e6e709ad505a8cf86bfed67c1cb800a" allowfullscreen="" frameborder="0" height="457" width="680" title="fragment - bundle" class="t u v hd aj" scrolling="auto" style="box-sizing: inherit; position: absolute; top: 0px; left: 0px; width: 680px; height: 456.984px;"></iframe>

MyFragment.kt

`MyActivity`는 이제 `KEY`를 몰라도 되고 `newInstance()` 메소드에 `data`만 파라미터로 넣어주면 됩니다. 타입에 따라 다른 데이터를 보내줘야 할 경우 이렇게 사용할 수 있습니다.

<iframe src="https://medium.com/media/7aeaf59472d1bd1a3ddc0639a393b24d" allowfullscreen="" frameborder="0" height="369" width="680" title="fragment - bundle" class="t u v hd aj" scrolling="auto" style="box-sizing: inherit; position: absolute; top: 0px; left: 0px; width: 680px; height: 369px;"></iframe>

물론 같은 데이터를 넣어준다면 Activity에서 `when()`문으로 Fragment를 생성하고 공통으로 `argument`에 같은 키로 값을 넣어주는 것이 더 효율적인 코드일 것 입니다.