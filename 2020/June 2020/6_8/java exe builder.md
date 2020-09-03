# 2020-06-08 월 TIL

### JAVA exe File Building 

1. 내보낼 프로젝트 클릭 후 Export - Java - Runnable JAR file 선택
2. Launch comfiguration 에 해당 프로젝트의 메인 메서드 선택 , Export destination에 저장할 경로와 파일명 입력
3. Jsmooth 툴이용해서 왼쪽 Skeleton 클릭 - Skeleton Selection 에 `Console Wrapper` 선택 
4. 왼쪽 Executable 클릭 - Executable Binary에 경로 jar를 exe 확장자로 변경 
5. Current Directory 에 dot(.) 입력 
6.  왼쪽 탭의 Application 클릭
   -  Use an embedded jar 체크박스 체크 
   - 이클립스에서 내보낸 jar파일의 경로를 지정
   - 위쪽 Application Settings의 Main Class에 우리 프로젝트의 메인 메서드가 있는 클래스 선택
7. 상위 톱니바퀴 모양 클릭 
   - 실행 파일 경로지정 후 종료 