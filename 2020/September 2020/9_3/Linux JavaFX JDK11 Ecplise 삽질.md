\* 주의 *

전 자바 몰라요..

이클립스도 몰라요..

vi 만 좋아합니다..

그러다 어쩔 수 없이 자바를 해야만 해서 겪은 일들 입니다...

스크린샷들은 그때 상황을 최대한 재현 한것들입니다.



요즘 여차저차 해서 java 를 해보기 시작 하였습니다.

\* Python 이 더좋은데. ㅠㅜ.



여튼 자바 를 설치 하였습니다.

처음에는 openjdk 1.8 을 설치하고 사용 하였습니다.



그러다 필요한 기능 구현을 위해서 JavaFX 가 필요한걸 알고  좀 찾아보니 openjdk 에는 javafx 가 포함되어 있지 않다는것을 알았습니다.



그래서 OpenJFK 도 설치하고 이리저리 해보았으나 모두 실패....



이클립스 라는것에서는 세팅이 쉽다고 해서 설치 후 이리저리 시도하였으나 실패...



그러던 중 오라클의 jdk 8 에는 Java FX 가 기본 포함되어 있다는 말을 듣고 오라클 자바 를 설치하고 JavaFX 가 잘 작동 되었습니다.



하지만 문제가 또 생겼습니다.



전 자바를 모르기 때문에 이리저리 예제를 통해서 공부를 하는데 예제에 사용된 javafx 버전이 높은것이였습니다..



그래서 또 삽질을 시작 합니다.



일단 java 11 과 javafx11 을 다운 받아서 압축을 풀어줬습니다.



[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMjQ3/MDAxNTcwNDE4NTM0NjUw.7NDFGae7orRCEwmiaC2IKGvN2GpGeIq1tiykSkFGMD8g.Ff5LdzBdjl2wVFsWkYPL9AVTvczhAj83EmwF4vmmHUAg.PNG.linuxni/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2019-10-07_12-22-02.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

그리고 이클립스에서 설정을 해 주었습니다.



이클립스 상단 메뉴 중 Windows -> Preferences 로 들어간 후 

왼쪽 메뉴에서 Java > Installed JREs 를 선택 합니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTk5/MDAxNTcwNDE4Njg3MDI2.QAjnGNTYo0V0Pa6RBZELi5ZrMU8JTRflMahXCiNWQvEg.89DNCDTK5zGkvzoSsl9MxOIZoLqSMbbzNiNnn5hSSF0g.PNG.linuxni/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2019-10-07_12-24-30.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

그럼 기존에 설치된 jdk1.8 이 세팅되어 있습니다.



왼족에 Add... 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfNzMg/MDAxNTcwNDE4NzcyMzA5.bRqriELI3IXLq7hMo3IkBJhtzp0kZQIBjJZSYu_trTsg.NWaei3pmYvIF9bOZM_x95NLqQMXPXN7vgYZRg7L5TeYg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

Standard VM 을 선택 후 아래 Next 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTUg/MDAxNTcwNDE4ODg2OTU5.hIEptPWGVrc2Q83Ah-st-xK2sEsbxNe4ZgHkSnqEMNcg.eQ8KNiCk5xCd5yOZTK2x3FAUuljIUAju8uVG6VxgQ00g.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

JRE home 옆의 Directory... 를 눌러서 위에서 압축을 풀어준 JDK 디랙터리 를 지정 합니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfNTIg/MDAxNTcwNDE4OTgzODQ5.Hc9WMIQ35b77z5xwrNQq5qBaD2FXDT-NHVPSgpK64Agg.wts4jA5XES66bzQgXmjhGeQUvJTntHmLR6juPtr_S6Ig.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

그리고 오른쪽 아래 Finish 를 눌러줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMjg4/MDAxNTcwNDE5MDIzMzUw.7TM2sxmNrFHFHAdmHagS_wT1sD-WRyhrZn7qNt3o2jAg.YFcZ-U2zJpFMWR53Mz9s4YrSu12dkikdiNCa_r-2akUg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

일단 JDK 설정이 되었습니다.



이제 javafx 설정을 해 봅니다.



이클립스 상단 Help 메뉴 를 누르고 Eclipse Marketplace 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfNjkg/MDAxNTcwNDE5MTgzOTM1.vX1OELkCOBTM-RjhoOBg21tj2WJQKci1Ww74XX203xUg.wUVXIZTCebXTTeF7mJ08kGzEJN7HjD84U5YQ5QsWPR0g.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

좀 기다리면 ...

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfNTYg/MDAxNTcwNDE5MjAxMjAz.gdVQCXd1kUBCu2WSws_mnMwJwDjevHJpkx0_3zIq9Rog.TXW6IFsE-ljgJI7a-LYN5C-lA-dGLcLVNLXKM2kvLCIg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

이런 화면이 나타납니다.



위에 돋보기 옆에 (fx) 라고 입력 후 엔터를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTEw/MDAxNTcwNDE5MjQwMDQy.YR2gFb_tedD20-wd0SyI7QA3FIAZfaDIbPmdQ-FWDvYg.SlpWRCvCW8LT4oGaV0osM7lMacRtHSJ-lXdymbReHlgg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

가장 위에 나온 e(fx)clipse 3.6.0 을 설치 해 줍니다.



정상적으로 설치가 완료 되었으면 

Windwos > Preferences 에 아래와 같이 

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMjY3/MDAxNTcwNDE5MzAxNDE2.saHP4Wquz-iRT_qdBUcmpAs3Z64WiasAznkJ36NWgK8g.HhNUVg3Yh8wlkL7pHjUi7NeGWbzJjo5J66RVcDmbRJ8g.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

JavaFX 가 생성 됩니다.



**이제부터 삽질의 시작입니다.**

****

**실패 한것들도 다 글을 썼기 때문에혹시 저와 같은 문제를 겪으신 분들은 일단 쭈욱 읽어보거나 급하면 아래로 쭈욱 내려가주세요...** 

****

제가 해본 순서대로 글을 적어 보겠습니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMjY3/MDAxNTcwNDE5MzAxNDE2.saHP4Wquz-iRT_qdBUcmpAs3Z64WiasAznkJ36NWgK8g.HhNUVg3Yh8wlkL7pHjUi7NeGWbzJjo5J66RVcDmbRJ8g.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

우선 전 여기서 JavaFX 11 + SDK 의 경로를 넣어줬습니다.'



이제 새로운 프로잭트를 만들고 실행되는지 확인을 해 보았습니다.



이클립스 왼족 상단 File 메뉴 누르고 New > Java Projetc 를 눌러 줍니다.

<iframe id="ssp-adda_tgtLREC" frameborder="no" scrolling="no" name="" style="margin: 0px; padding: 0px; border: 0px; font: inherit; vertical-align: bottom; width: 640px; height: 206px; visibility: inherit; display: block;"></iframe>

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfNSAg/MDAxNTcwNDIxOTk4NTk5.sqA3gwRtsY0KzRhhp89eTQnbMEf5mjWgZ2ahGE3moZQg.noaIUlEOm-zYvISd6esbjJ3g6MLdpq8x-n6fe9znM_og.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

Projectr name 을 써 주고 

중간쯤 JRE 는 11 버전을 선택하고 하단에 Next 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTE4/MDAxNTcwNDIyMTE1ODA2.vw87s4muUH602vLv3x9ENhPoF63nVxuxgAU41wrl5w8g.5C3KnVrhvh8WaOnermOUpaYkFzfd4nVEdZX6Y1IYYxMg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

Libraries 를 선택 합니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTcw/MDAxNTcwNDIyMjEzODk5.8Y_M6Z8umxUZUFNd1xiyKThyAdHW_h0VgWISyV9zleYg.JmrxWaQSh6vt87xDdn7sKjjU11d31uQZnxGWU-fLrOIg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

왼쪽의 Add Library 를선택 합니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMjA2/MDAxNTcwNDIwMDIzMDcx.DHQfxGkeKMwzzyCIQDiGHkVYwjGSM35BedlpXROOAs0g.8WFh0-Jq_0N0i0dX3jqt4lD9nDg0v1QbrXFX_BylKA8g.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

JavaFX SDK 를 선택 후  아래 next 와 Finish 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMjU1/MDAxNTcwNDIyMjU5Nzkw.80orNTjL57L1hD0tVlROBbhsrmxSZJ5C8DG_TsMuxkYg.KZuJXzlFNA8ggk6fvsgeFCw9ArqXKg0LDuEHcMuUdd8g.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

그리도 또 오른쪽 아래 Finish 를 선택 합니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfOTkg/MDAxNTcwNDIyNDE1NTM5.a7DK89cRNYFDDPboEg8XT_zhXhS9o0vrB7qeIU7mQ4Eg.Vi_FRHxs7zeMZxyXi71zcleqGHHagMZb04jzR--qsq4g.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

새로운 프로잭트가 생성 되었습니다.



우선 Main class 를 만들어 줍니다.

왼쪽 src 에서 마우스 오른쪽 버튼을 누르고 new > class  를 선택 합니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTg1/MDAxNTcwNDIyNTA5NTA0.c3GOuR-QhYfW8_zbhw0SoZbLxqgMOZCkoUEDUW27z04g.nGS8ij3Ll1lwbRWmkHnCCi5nrS1UUkXqPcyt1JnlePsg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

위와 같이 설정후 Finish 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTM3/MDAxNTcwNDIyNzQ1OTA1.pxiTEWtWH9DvhhfWpzDQd1zmEOFKzhNoTl-SnrXAMA4g.Gwuql-0m1MEanajRiZhfy3gfwdDcv-XaeRFnV5w9GIgg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

기본적인 코드가 작성 되었습니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTk0/MDAxNTcwNDIyODExNDQ5.V5xQ3XgPngiekMAtIE8NY404WRUhPBAhIfHoCkZR0hcg.88jOkXiKZ8YUb8QUhmgW0oEhPJVJWJ0q4M2Rbwds7rAg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

잘 작동 하네요..

\* 혹시 실행 오류가 난다면 왼족에 프로잭트 이름에서 마우스 오른쪽 버튼을 누르고 Run AS 에서 java application 을 선택 하세요.



인테넷에 있는 javafx 소스를 복 붙 해 봤습니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfNjkg/MDAxNTcwNDIyOTIzNjEx.UxnC9QRVAtHO2PSvmWAdRbaK-L-uvfma9Feid0LgBtsg.wvn7wA6Hfxz2bzrw-nD9T3tUmPr31YGHZ2EGIK6msewg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

import 에서 오류가 나기 시작합니다..



왼쪽의 프로잭트 이름에서 마우스 오른쪽 버튼을 누르고 Build Path > Add Libraries 를 눌러줍니다. 

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMjEx/MDAxNTcwNDIwNTYxNTY5.YPYHr0cIKq4QYp3iHTzN4tq40vFht4mtZYNqlgd_qg4g.TVqLO1s3WJu4CUm548e_6KebP51yxpy5GDUgK0TCpT4g.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

이번에는 User Library 를 선택 후 Next 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTQ5/MDAxNTcwNDIwNjA3OTcw.AcJgOxKlnhvYrg2T7F-4rrcVwtdg2W4qJCgB0eS_4A4g.3YKNfUJaI_sndOxM4HFAAFW5PMU1jYIdQ5k_z36Wgrkg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

왼쪽의 User Libraries.. 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMjE3/MDAxNTcwNDIwNjMzNTY1.MA86MRTc7VeOqnxi1znHnWrPeyI6Gn4MWaGmkOqcOusg.XLjOrXMN1ltffWK7wBZrA6xcPAnr4Fmcgd4hDSfv8ywg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

왼족의 New 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTc2/MDAxNTcwNDIwNjUzNjE0.O1EYmkWKUpIWFLii3sfXamkhzd1vTWierUsJn0e2d9Mg.Hu45r9eoyQi499LcHqiotN_vJ9OQ5l2QD16ifnkcmb8g.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTc1/MDAxNTcwNDIwNjc2MDI0.EEEH25orTZE60Gv78OsPWO_D2q9p1R7mYnR-AI-DJhkg.SpL2067V6Fcq2vonGYW0Jvd10SEbsNJoNpbYMd5h5ocg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

이름을 하나 넣어주고 ok 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTE5/MDAxNTcwNDIwNjkzNjY0.qBJ70smcGarbN4p82F-NCHf23IyvbqvOsjheGSEebsEg.YZLEDjSNfiggLbOUzt7bKb2t4LFN5hoI5NlHAcEmyz0g.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

이번에는 add External JARs 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTI0/MDAxNTcwNDIwNzYyNDcy.C3r3eAWi2cyukMGfIe12yZWnOqXxBlhDQNeOd8tNjvgg.jJrYwlU6EIYoc8hEZirsLwrrvbKssAcIGOmtuKRGq2Ag.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

javafx sdk 의 Lib 폴더에 있는 jar 파일들을 모두 선택하고 열기 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMjA0/MDAxNTcwNDIwODE5MTMw.FSc6hZ_Vwyy4Wf6QzpDhz6cviO6lX1mWldv5BGrmMqgg.I-WO-L7UP0fxIOBcx2tzCL6lx43o9wasrSKs-v-ENM0g.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

무언가 많이 추가가 되었네요..

Apply and Close 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTcx/MDAxNTcwNDIwODc2NjU2.b3BNkZqT-c31PvdKGGJA3HABqjiHc0BVPd7NrayMVL0g.rVCb0CKhXSX55Q2kGJz62lkgxnnG8qGYfegstVb6N4Yg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

Finish 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTcz/MDAxNTcwNDIyOTgyMDUx.wnl9K63I5Vb2oP4blxFUhRjun22RFooOfYRfw34dcDog.yPxKg-9RybTmbO7u1UT3i5ojaNuz5l_M-y8cDMcHiSkg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

javafx11 이 추가되면서 import 의 오류 마크가 다 사라졌습니다.



실행을 한번 시켜 보겠습니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTMy/MDAxNTcwNDIzMDA0ODU2.k7PvaVU2VJKCfg0vICzKjuKpAg1D0YgjZ7U1KLztLo8g.506l6I0wpZiS2vcRodD-bOf9-ZkfTN5UeRPvUJwQuHMg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

오류가 납니다...



Error: JavaFX runtime components are missing, and are required to run this application



라고 합니다...



구굴일을 열심히 해 보았습니다.



어딘가에 아래 줄을 넣으라고 합니다.

--module-path /"javafx/lib 경로" --add-modules=javafx.controls



좀 더 찾아보니 좋은 방법이 있네요. (변수를 미리 만들어주고 작업하기)

우선 이클립스 상단 Windows 메뉴에서 Preferences 에 들어갑니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTUx/MDAxNTcwNDIzNDY4Mjgz.-lgAbRwKy2hsURKSb09pnCFUwjKmAwNWOp4STgS2IuQg.z1_tSeAs0UdK3L4XG3g5bGfHXs7wyFGnQ7ONK2ndlWsg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

그리고 왼쪽 메뉴에서 Run/Debug 메뉴 아래 String Substitution 을 선택 합니다.

\* 저의 스샷은 이미 패치 설정이 되어 있습니다. 



왼쪽의 New  를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTI1/MDAxNTcwNDIzNDk3NjY1.0lakqbLXPlEM4ZosRZezgkFWN2W0w9dinAFlhmbQSEYg.jhylz2p3ymIGCcC6wD8dDltu_c_MgXTgdZN_v2vkZXkg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

Name 에는 PATH_TO_FX 를 넣어주고.

Value 는 Browse 를 눌러서 Javafx 폴더의 lib 폴더를 지정 합니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTQg/MDAxNTcwNDIzNTYyOTEy.c_FGFvNdBumC8NjeRVa6dlPYh_mVEuV_a17nm2lUbJIg.FjT05eR3IQWbvDY0iXSZWeRVFIa-Aq0GURH8mSVnLOog.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

그리고 ok 를 눌러 줍니다.

그리고 오른쪽 아래 Apply and Close 를 눌러 줍니다.



이번에는 이클립스 화면 왼쪽의 프로잭트 이름에서 마우스 오른쪽 버튼을 누르고 제일 아래 있는 Properties 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTUy/MDAxNTcwNDIzOTQyNTY3.CS8ztHJ739BaWIKFHVPPZ4x8PTWj5qGvwdjrlapU6-gg.K1u2dV03clJXWajsRTLPdCsr7mj9TLLFBSknTFQ9Rbwg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

Run/Debug Settiongs 에 들어갑니다.

Main 을 더블클릭 합니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMjgz/MDAxNTcwNDIzOTkyNTg4.p51nu4W4kc7VBCskyI9iA6W-zWP-XoGPGSsrfPufPLEg.4k5Kj-j793JvIMrWy-TxPPfDIzUj6ottV6q0NcHohxcg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

Arguments 를 선택 하고 VM arguments 에 

--module-path ${PATH_TO_FX} --add-modules=javafx.controls

를 넣어줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTk0/MDAxNTcwNDI0MDY3Njcx.LsDXx9bbeY1MBPyzL4xq_gmpxif9oQFUMnGTvtOEtAYg.i3zkDUkAo65bKlRDX93L4-sL98EDrSMU4KqafVj6MtAg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

ok 를 눌러주고 Apply and Close 를 눌러줍니다.



그리고 다시 실행을 해 보았습니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMjE2/MDAxNTcwNDI0MTM0ODMw.Cw2ROeuq9gFOWPVI3viYUpFua3Fxwgdtvsl0HGlU4lcg.6gD7xzR0C6z97GOfScqNHF8FyijUa6_H--kiY_9K0eIg.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

여전히 오류가 발생 합니다.

Error: Could not find or load main class --module-path

Caused by: java.lang.ClassNotFoundException: --module-path



모든 오류를 재현 할 수 없어 스크린샷은 없지만...



Error occurred during initialization of boot layer

java.lang.module.FindException: Module javafx.controls not found



이런 오류도 발생 했습니다.



그리고 최종적으로 본 오류가 



Exception in Application start method



입니다..

그리고 이 모든 오류를 해걸한 방법이

module-info.java 입니다.



module-info.java 를 만들어봅시다.

\* 이것의 저의 해결책이였습니다. 



프로잭트 이름에서 마우스 오른쪽 버튼을 누르고 configure > Create module-info.java 를 선택 합니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfMTYx/MDAxNTcwNDI1MjE3NTAy.lLIhm-8VUkvNcjNEgE0RL15XTAGf90f9e7wXMw4JMakg.lOh-4_8MRm3M4MhvGoHz1MvnrWYxvHIOKLyYb8D7bs0g.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

Create 를 눌러 줍니다.

[![img](https://mblogthumb-phinf.pstatic.net/MjAxOTEwMDdfODIg/MDAxNTcwNDI1MjM2MDE1.Ixu_iN7FtNGyQkNUvlchCfbhaFpxxODIynXQEQPRbsMg.djy4txocL7NSlDNCltTTUwTH4S2jlez7E05Vh6ym-y8g.PNG.linuxni/image.png?type=w800)](https://m.blog.naver.com/PostView.nhn?blogId=linuxni&logNo=221670596629&categoryNo=2&proxyReferer=https:%2F%2Fwww.google.com%2F#)

파일이 생성 되었습니다.



이전에 설정했던 --module-path ${PATH_TO_FX} --add-modules=javafx.controls 줄도 지워주고 실행을 해보면 오류없이 실행이 잘 됩니다.



겪었던 오류는 여러가지 이유가 있었겠지만 나름 이리저리 찾아본 결과 전 module-info.java 로 해결 하였습니다.



스크린샷 찍느라고 오류도 최대한 재현을 해 보았는데 잘 안되네요..

\* 메일 나던 오류가 안남. ㅠㅜ.



2019.10.10 추가

VM arguments 에 

--module-path ${PATH_TO_FX} --add-modules=javafx.controls 

에 더해서 javafx.fxml  를 넣어주면 오류가 안나는 경우도 있네요..



--module-path ${PATH_TO_FX} --add-modules=javafx.controls,javafx.fxml 

이렇게요..



테스트로 몇가지 해 보았는데...

어떤 프로잭트 는 VM arguments 추가 하면 오류 나고  module-info.java 를 해주면 오류가 안나고..

또 어떤 프로잭트는 VM arguments  추가해도 오류가 안나고 module-info.java  추가하면 오류가 나고..

똑같이 jdk11 + javafx11 인데... 

모르겠네요...





프로그래머의 일상

열심히 프로그램 작성 후 처음 실행 했는데 오류가 발생시 : 뭐지...?

열심히 프로그램 작성 후 처음 실행 했는데 오류가 없을시 : 뭐지...?

전에 만들었던 프로그램을 나중에 다시 필요해서 열었을때 : 뭐지...?

<iframe id="ssp-adcontent_tgtLREC" frameborder="no" scrolling="no" name="" style="margin: 0px; padding: 0px; border: 0px; font: inherit; vertical-align: bottom; width: 640px; height: 375px; visibility: inherit; display: block;"></iframe>

- [#jdk11](https://m.blog.naver.com/BlogTagView.nhn?blogId=linuxni&logNo=221670596629&tagName=jdk11&page=1)
- [#javafx11](https://m.blog.naver.com/BlogTagView.nhn?blogId=linuxni&logNo=221670596629&tagName=javafx11&page=1)
- [#자바](https://m.blog.naver.com/BlogTagView.nhn?blogId=linuxni&logNo=221670596629&tagName=자바&page=1)
- [#이클립스](https://m.blog.naver.com/BlogTagView.nhn?blogId=linuxni&logNo=221670596629&tagName=이클립스&page=1)
- [#오류](https://m.blog.naver.com/BlogTagView.nhn?blogId=linuxni&logNo=221670596629&tagName=오류&page=1)