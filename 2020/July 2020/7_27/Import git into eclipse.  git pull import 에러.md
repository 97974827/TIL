## [Import git into eclipse.](https://yzzeee.tistory.com/1)  git pull import 에러 



 \> Window - Show View - Others - Git - Git Repositories 열기

\> 열린 Git repositories 윈도우에서 Clone a Git repository 선택

\> URI에 불러오기를 원하는 git 저장소에 가서 Clone or download에서 주소를 복사하여 붙여넣고 Next - Next - Finish하면

Git repositories에 가져와져~



\> 가져와진 것에 오른쪽 우클릭 - Import Projects - Finish !!!



※ 젠장 에러나네?????

" 오류: 기본 클래스 을(를) 찾거나 로드할 수 없습니다. "

해결해보자!!!

Project Explorer에서 불러온 프로젝트 우클릭 Properties - Java Build Path - Library 빨간거 다 Remove - Add Library -JRE System Library - Alteranate JRE에서 Intelled JREs - Add - Standard VM - Directory에서 검색해서 내 컴퓨터에 JRE 라이브러리 넣어줌(있으면 그걸로)

요기서 실행 잘되면 해결!!!

또 안되면? 자바 컴파일러 버전 맞춰주거나, 뭐.. 나도 잘 모르겠다 이리해서 해결함. 버전이 서로 호환이 안되서 생기는 문제로 추정.



https://yzzeee.tistory.com/1

