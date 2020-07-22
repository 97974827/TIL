# [2020-07-15 수 TIL]

### Java - JsonParser 데이터 받아오기 

- https://huskdoll.tistory.com/38
- json_simple-1.1.jar 추가

```java
try {
    // pos_config 데이터 받아오기 - JSONParser
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = (JSONObject) jsonParser.parse(data_connect);
    JSONArray jsonarray_master_config = (JSONArray) jsonObject.get("result"); // object 키값 : result  
    System.out.println(jsonarray_master_config);

    for(int i=0; i<jsonarray_master_config.size(); i++) {
        JSONObject master_object = (JSONObject) jsonarray_master_config.get(i);
        System.out.println(master_object);
        String str_auth = master_object.get("auth").toString();

        System.out.println("데이터 수집장치 인증 번호 : " + str_auth);

        if(str_auth.equals("0")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("경고");
            alert.setHeaderText("에러");
            alert.setContentText("사용 할 수 없는 기기 입니다.");
            alert.showAndWait();
            hideMenu();
            return;
        }
    }
```



### JDOM - XML 데이터 받아오기

- ##### Java Document Object Model

- 참고 : https://m.blog.naver.com/PostView.nhn?blogId=javaking75&logNo=220091010933&proxyReferer=https:%2F%2Fwww.google.com%2F



- #### 날씨 jdom 파싱 참고사이트 

  - [자바참고 사이트](https://m.blog.naver.com/PostView.nhn?blogId=javaking75&logNo=220092064359&proxyReferer=https:%2F%2Fwww.google.com%2F)

  - [자바참고 사이트 2](https://joke00.tistory.com/31)

  - https://mindcontrol89.tistory.com/9

  - [기상청 RSS 안내페이지](https://www.weather.go.kr/weather/lifenindustry/sevice_rss.jsp)

  - 광주 광역시 RSS

    - http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=2920062400

  - 중기 예보 (전국)

    - http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108
    - https://violet31.tistory.com/4

  - #### 동네예보 XML 데이터 설명

    - http://www.kma.go.kr/images/weather/lifenindustry/timeseries_XML.pdf

- SAX (Simple API for XML)

```java
package kor.gls.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class WeatherUtil {
	
	// RSS 주소 
	private String rssFeed = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=%s";
	
	
	// 해당 Zone 정보를 주어 하루치 날씨 정보 가져오기  
	public List<Map<String, String>> getWeatherRssZoneParse(String str_zone) {
		
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		try {
			SAXBuilder parser = new SAXBuilder();				// Parser 객체생성
			parser.setIgnoringElementContentWhitespace(true);	
			
			String url = String.format(rssFeed, str_zone);
			Document doc = parser.build(url);					// doc = XML문서 전체 나타냄 
			Element root = doc.getRootElement();				// 하나의 태그를 나타냄 
			
			Element channel = root.getChild("channel");			// 하위 태그 
			Element item = channel.getChild("item");
			Element description = item.getChild("description");
			Element body = description.getChild("body");
			
			List<Element> list = body.getChildren("data");
			
			for(int i=0; i<list.size(); i++) {
				Element el = (Element) list.get(i);
				
				String seq = el.getAttributeValue("seq"); 		// 순번
				if(seq.equals("8")) break;  // 24시간 데이터만 추출 
				
				Map<String, String> data = new LinkedHashMap<String, String>();
				
				String wf_kor = el.getAttributeValue("wfKor");	// 날씨
				String temp = el.getAttributeValue("temp");		// 온도
				
				data.put("seq", seq);
				data.put("wfKor", wf_kor);
				data.put("temp", temp);
				
				for(Element dataChild : el.getChildren()) {
					data.put(dataChild.getName(), dataChild.getTextTrim());
				}
				
				System.out.println(data);
				result.add(data);
			}

			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	// 날씨문자열 값으로 이미지 변환하기 
	public void changeWeatherImage(String wfKor) {
		
	}
	
	public static void main(String[] args) {
		WeatherUtil weather = new WeatherUtil();
		weather.getWeatherRssZoneParse("2920062400");
	}
}	

```

```java
// 결과 

{seq=0, wfKor=흐림, temp=28.0, hour=15, day=0, tmx=29.0, tmn=-999.0, sky=4, pty=0, wfEn=Cloudy, pop=30, r12=0.0, s12=0.0, ws=1.4000000000000001, wd=0, wdKor=북, wdEn=N, reh=65, r06=0.0, s06=0.0}
{seq=1, wfKor=흐림, temp=26.0, hour=18, day=0, tmx=29.0, tmn=-999.0, sky=4, pty=0, wfEn=Cloudy, pop=30, r12=0.0, s12=0.0, ws=1.5, wd=5, wdKor=남서, wdEn=SW, reh=75, r06=0.0, s06=0.0}
{seq=2, wfKor=소나기, temp=23.0, hour=21, day=0, tmx=29.0, tmn=-999.0, sky=4, pty=4, wfEn=Shower, pop=60, r12=0.0, s12=0.0, ws=0.9, wd=4, wdKor=남, wdEn=S, reh=90, r06=30.0, s06=0.0}
{seq=3, wfKor=흐림, temp=21.0, hour=24, day=0, tmx=29.0, tmn=-999.0, sky=4, pty=0, wfEn=Cloudy, pop=30, r12=0.0, s12=0.0, ws=0.6000000000000001, wd=5, wdKor=남서, wdEn=SW, reh=95, r06=30.0, s06=0.0}
{seq=4, wfKor=흐림, temp=20.0, hour=3, day=1, tmx=30.0, tmn=20.0, sky=4, pty=0, wfEn=Cloudy, pop=30, r12=0.0, s12=0.0, ws=0.4, wd=5, wdKor=남서, wdEn=SW, reh=100, r06=0.0, s06=0.0}
{seq=5, wfKor=구름 많음, temp=20.0, hour=6, day=1, tmx=30.0, tmn=20.0, sky=3, pty=0, wfEn=Mostly Cloudy, pop=20, r12=0.0, s12=0.0, ws=0.4, wd=4, wdKor=남, wdEn=S, reh=100, r06=0.0, s06=0.0}
{seq=6, wfKor=구름 많음, temp=23.0, hour=9, day=1, tmx=30.0, tmn=20.0, sky=3, pty=0, wfEn=Mostly Cloudy, pop=20, r12=0.0, s12=0.0, ws=1.0, wd=3, wdKor=남동, wdEn=SE, reh=90, r06=0.0, s06=0.0}
{seq=7, wfKor=구름 많음, temp=27.0, hour=12, day=1, tmx=30.0, tmn=20.0, sky=3, pty=0, wfEn=Mostly Cloudy, pop=20, r12=0.0, s12=0.0, ws=1.1, wd=2, wdKor=동, wdEn=E, reh=70, r06=0.0, s06=0.0}

```

서울
인천
수원
파주
이천
평택
춘천
원주
강릉
대전
세종
홍성
청주
충주
영동
광주
목포
여수
순천
광양
나주
전주
군산
정읍
남원
고창
무주
부산
울산
창원
진주
거창
통영
대구
안동
포항
경주
울진
울릉도
제주
서귀포