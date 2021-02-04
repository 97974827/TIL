### 자바) 오류: 기본 클래스 을(를) 찾거나 로드할 수 없습니다 [해결방법]

https://m.blog.naver.com/PostView.nhn?blogId=hotkimchi13&logNo=221184700791&proxyReferer=https:%2F%2Fwww.google.com%2F



이번시간에는 **코딩**에 대한 포스팅이 아닌
많은분들이 자바를 처음 시작하셨거나, 기존에 자바개발환경을
**eclipse**에서 **Notepad++**, **메모장** 등으로 넘어가시는 분들이 흔히
겪는 **오류**에 대해 해결방법을 알려드리는 시간을 같도록 하겠습니다..

일단 오류의 내용은 이번 포스팅제목과 같이
**오류: 기본 클래스 을(를) 찾거나 로드할 수 없습니다**
위 처럼 **java파일을 컴파일한후 실행을 하였을때 뜨는 오류**중에 하나인데요..

![img](https://mblogthumb-phinf.pstatic.net/MjAxODAxMTRfMTMg/MDAxNTE1ODk0Mzc2MTcw.oQjamLSWp1hpZX_52iNYfsCscdL6l1Lgk3fcOiUYJaQg.wovLYvWEvxQ76GLlrom-Njzp8o5diipsoO64ymmc71gg.JPEG.hotkimchi13/-1.jpg?type=w800)



일단 **문제점**을 바로알려드리면.. 대부분 **"****환경변수****"** 설정에서 한가지
**빼먹는 부분**때문에 그럴겁니다..

아니면 컴파일한후 java파일을 실행할때 **패키지명**이 붙었다거나, 패키지명으로 실행되지
않았을 경우로 크게 세가지로 나뉩니다..

일단 **패키지명**에대한 오류는 단순히 cmd에서 javav파일을 컴파일한후 생성된
class파일이 있는 **폴더위치**에 정확히 진입해주신상태에서 실행해주시면 됩니다.
ex) c드라이버에 있을경우 cmd에서 **c:** 를 입력해주신다음 폴더를 가르키는 명령어는
**cd 폴더명** 으로 c:드라이버에 있는 폴더에 들어가실수 있습니다.
처음부터 cmd로 실행하시면 현재 이 포스팅을 보시는 분의 컴퓨터 유저로 들어가있을겁니다..
그거 잘 생각해 주시고 패키지명 오류는 교쳐 주시면 됩니다..

그리고 제가 오늘 자세히 다룰 내용은 **"환경변수"** 설정에서 미처 추가하지 못한
한가지 부분에 대해 설명을 드리려고 왔기 때문에
한번 확인해 볼까요..?






우선 현 포스팅은 **자바 환경변수 설정**이 제대로 이루어진 상태의 컴퓨테에만 유효합니다.
(자바 환경변수 설정은 네이버,구글등에 검색해 보시면 자세히 나옵니다)



![img](https://mblogthumb-phinf.pstatic.net/MjAxODAxMTRfMjk3/MDAxNTE1ODk1OTY0MDQ5.3UaA7gk-hQXSkMh0odm9lIZBiSmzhaTkL8xee7IHQ1Mg.m5hdFSPEhSSxMV7aamLIgjUnD3Gbd98QQX6PxQASDsgg.JPEG.hotkimchi13/image_8825636401515895834604.jpg?type=w800)

\1. 우선 밑 사진과 같이 "**내 PC**" 아이콘에 **마우스 우클릭**을 해주신다음
**속성(R)**을 눌러주세요..!

![img](https://mblogthumb-phinf.pstatic.net/MjAxODAxMTRfMTA2/MDAxNTE1ODkzNDMxNDUx.V0wr6S27VLKLNaOi7YjLgv0758ut3iD0Fh6d71NS6jAg.PrdBQPjsnsRyPAGqHH7ClFM6UvvCJsIAK_NU1OrbarUg.JPEG.hotkimchi13/1.jpg?type=w800)




**2.** 그러면 **시스템** 창이 하나 열릴겁니다.. 그곳 좌측 중앙에 
**"고급 시스템 설정"** 이라고 있을텐데 그곳을 눌러주세요

![img](https://mblogthumb-phinf.pstatic.net/MjAxODAxMTRfMjQ1/MDAxNTE1ODkzNTE3Mzc2.D697wwvKUFewrt3lk5PNMTkeHwL6qpxqy-3iB_XivX8g.7rwsi3FxtA74DsWWZ2suN4f8UXyFcrk0B0ZjbxhfsoIg.JPEG.hotkimchi13/2.jpg?type=w800)




**3.** 그러면 **시스템 속성** 창이 하나 더 뜰겁니다..
그곳 우측 하단에 보시면 **"환경 변수(N)"** 이라고 **버튼**이 있을텐데
그거 눌러주세요..!

![img](https://mblogthumb-phinf.pstatic.net/MjAxODAxMTRfMTgz/MDAxNTE1ODkzNTc4MjY3.xSdRhTqZjylsgQRr_aPvm2271qLT6OSyizzaRJ_-_M4g.Or-n-louC0qxmr0pyOmJaolWCtL4NAEs6GMgfQJoOeUg.JPEG.hotkimchi13/3.jpg?type=w800)




**4.** 위 사진처럼 **1,2,3,4 순서**로 들어가주시면 되는데
**중요한건 위 4번 창입니다..**
**CLASSPATH** **편집**을 눌르면 4번창이 뜰텐데요..
그곳에서 **"변수 값(V):"** 부분에 저희는 자바 환경변수 설정대로 하였을때
**%JAVA_HOME%\lib** 이라는 경로 지정되어있을겁니다..
위 처럼 되어 있다고요? **그럼 당연히 안됩니다..**

cmd상에서는 **javac ,java -version, javac -version**등의 **명령어**를
입력하여 실행하였을때에는 잘나오겠죠.. 자바 환경변수 설정을 제대로 다 해줬거든요..
하지만 중요한건 자바 파일을 실행할때 **확장자 인식**을 시켜주는 부분이 없다는 겁니다..

그래서 저희는 **%JAVA_HOME%\lib** 옆 문장에 확장자를 인식할수 있게
추가로 문장을 붙여주셔야 합니다.. 밑 사진처럼 
**%JAVA_HOME%\lib** 뒤에 **;.;** 을 붙여보시거나 **;.** 을 붙여주세요..!
( ;(=세미콜론) , ,(=쉼표) )

![img](https://mblogthumb-phinf.pstatic.net/MjAxODAxMTRfMzEg/MDAxNTE1ODkzODIyNjIy.-znILFPDbcuOCgB7TwvPsOCX0LgTDGRLfOqmZEALmcMg.fEdPqh8oXim9JxySQcA1AfBZffWcOTHjfz0wYgcVXTkg.JPEG.hotkimchi13/5.jpg?type=w800)




**5.** 위 처럼 뒤에 추가 문장을 붙여주시고 전부 확인누르고 나가주신다음..
**cmd창이 켜있다면 재시작** 해주신다음
아까 오류난 java파일을 재 컴파일 하고 실행하여 보시면 밑 사진처럼
**잘되는것**을 확인하실수 있습니다..!

![img](https://mblogthumb-phinf.pstatic.net/MjAxODAxMTRfMjk1/MDAxNTE1ODk0MzU0Mzky.afUUa_C2N8GdxqL44_lv0lo8OLxmi81pTM-Wnfnekkcg.MnUfbyyHXNUbR0ZX6SnfB_DOHBJcHdLerZTr9gCfFx0g.JPEG.hotkimchi13/5.jpg?type=w800)

> 추가로 의견이 있으시거나
> 막히는 부분등이 있다면 댓글로
> 남겨주시면 참고하도록
> 하겠습니다..!

[![스티커 이미지](https://storep-phinf.pstatic.net/banyapeach_03/original_4.png?type=p100_100)](https://m.blog.naver.com/PostView.nhn?blogId=hotkimchi13&logNo=221184700791&proxyReferer=https:%2F%2Fwww.google.com%2F#)



***\**\*그리고\*\* \*\*블로그\*\* \*\*이웃\*\*\*\*좀 늘리고 싶은데 저와\*\* 
\*\*이웃\*\* \*\*맺고 싶으신 분은\*\* \*\*서로이웃\*\*\*\*이나\*\* \*\*이웃\*\* \*\*부탁드립니다,.,.,\*\*

\*\*
\*\*\*\*포스팅이 도움이 되셨다면\*\* \*\*♥\*\*\*\*공감 부탁드립니다..!\*\**\***
연관검색어 : 자바 , 자바 환경변수 , 자바 환경변수 설정 , 자바 오류 , 오류 , 환경변수 , 환경변수 설정 , 환경변수 설정 오류 , 
자바 실행오류 , 자바파일 실행 오류 , 실행오류 , 오류 기본 클래스 을(를) 찾거나 로드할 수 없습니다 , 
오류: 기본 클래스 을(를) 찾거나 로드할 수 없습니다 , 오류: 기본 클래스 을(를) 찾거나 로드할 수 없습니다 해결방법 , 
오류 기본 클래스 을(를) 찾거나 로드할 수 없습니다 해결 방법 , java , java file , java error , 성열암