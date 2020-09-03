### Javax.smartcardio import 

- https://stackoverrun.com/ko/q/1551880

1. 자바 프로젝트를 만들거나 기존 프로젝트를 엽니 다
2. 프로젝트를 마우스 오른쪽 단추로 클릭하고 특성 대화 상자를여십시오.
3. 빌드 경로-> 라이브러리 탭을 선택하고 "JRE 시스템 라이브러리"트리를 펼치십시오.
4. "액세스 규칙"항목을 선택하고 오른쪽의 "편집"버튼을 누릅니다.
5. "추가"버튼으로 규칙을 적용 할 수 있습니다. 드롭 다운을 "액세스 가능"으로 설정하고 "javax / smartcardio / **"값을 입력하십시오.

이 설정으로 javax.smartcardio의 클래스에 액세스 할 수있었습니다.





자바 스마트 카드 관련 사이트

- [API](https://docs.oracle.com/javase/8/docs/jre/api/security/smartcardio/spec/javax/smartcardio/package-summary.html#package.description)

- [smartcardio](https://bbaeggar.tistory.com/34)
- [Android - NFC]([https://nobilitycat.tistory.com/entry/JAVA-nfc-%EC%A0%9C%EC%96%B4](https://nobilitycat.tistory.com/entry/JAVA-nfc-제어))