# [2020-07-14 화 TIL] 

### Java 

### Apache - HttpClient , HttpCore 모듈 추가 

- 참고 : http://blog.naver.com/javaking75/220341248215

1. 홈페이지 바이너리 파일 다운

2. 압축 풀고 jar 파일 찾아서 라이브러리 추가 

   

- 설치 요소 

  | 라이브러리 파일 이름              | 설명                                                         |
  | --------------------------------- | ------------------------------------------------------------ |
  | (*) **commons-logging-x.x.x.jar** | 로깅을 하기 위한 라이브러리. HTTP Components 라이브러리는 기본적으로 Common Logging을 사용한다. |
  | (*) **commons-codec-x.x.x.jar**   | 데이터를 인코딩, 디코딩하기 위해서 필요한 라이브러리. HttpClient에서 데이터를 인코딩하여 서버로 보내거나 서버에서 받은 데이터를 디코딩하기 위해서 필요 |
  | (*) **httpcore-x.x.x.jar**        | HttpCore 모듈 라이브러리                                     |
  | (*) **httpclient-x.x.x.jar**      | HttpClient 모듈 라이브러리                                   |
  | httpmime-x.x.x.jar                | HTTP 프로토콜의 데이터 정보(컨텐츠 타입)를 나타내기 위한 MIME정보를 품고있는 라이브러리 |
  | httpclient-cache-x.x.x.jar        | 캐싱을 사용하기 위해서 필요한 라이브러리                     |
  | fluent-hc-x.x.x.jar               | 추가 기능이 포함된 라이브러리 **[출처]** [[Java\] HTTP 프로토콜을 손쉽게 사용할 수 있도록 도와주는 Apache HTTP 컴포넌트 ( HttpClient )](http://blog.naver.com/javaking75/220341248215)\|**작성자** [자바킹](http://blog.naver.com/javaking75) |

- 아파치 아카이브 파일 : http://archive.apache.org/dist/httpcomponents/



#### 이클립스 - 외부 라이브러리 추가 

- https://mainia.tistory.com/2273



### apache HTTPClient 라이브러리 이용하기 

https://stove99.tistory.com/26