### 안드로이드  Activity

- 보통 하나의 화면을 구성할 때 Activity를 사용함 

- 생명주기가 있음 

  

#### Activity가 종료되는 경우 

- 사용자가 Back 버튼을 눌러 액티비티를 종료한 경우
- 액티비티가 백그라운드에 있을 때 시스템 메모리가 부족해진 경우 (OS가 강제 종료시킴)

- 언어 설정을 변경할때
- 화면을 가로/세로 회전할 때
- 폰트 크기나 폰트를 변경했을 때 





#### [Android] 화면 회전시에 Activity onCreate() 방지하기

[2 Comments](http://theeye.pe.kr/archives/1292#disqus_thread)

안드로이드는 참으로 신기한점이 많습니다. 개발자 편의를 봐주기 위한 노력이 군데군데 묻어나는 OS입니다. 그리고 그것을 이동통신사에서 커스터마이징하면서 자신들의 철학대로 바꾸곤 합니다. 구글에서 어느정도의 가이드라인을 잡아서 어느정도 이상은 커스터마이징을 할 수 없도록 하면 어떨까요?

다음의 경우는 이통사탓은 아니고-_-a 안드로이드의 이상한 철학쯤으로 보여지는 부분입니다. 정확히는 개발을 위한 편의를 생각했던 것이겠죠. 화면 회전시에 현재 보여지는 액티비티를 재생성 해버립니다. 아이폰의 경우 단순히 화면 회전만을 했다면 안드로이드는 액티비티를 제거후에 회전된 방향에 맞게 액티비티를 다시 생성하는군요.



기본적으로 상태값을 저장하도록 되어있어서 대부분의 위젯들은 본래의 상태값으로 복구가 됩니다. 하지만 그 과정이 매끄럽지 못하고 값을 잃어버리는 경우도 생깁니다.

[code]public class PreventLossActivity extends Activity
{
   private EditText mEditText;
	
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.main);
		
     mEditText = (EditText) findViewById(R.id.editText);
		
     Toast.makeText(this, “onCreate()”, Toast.LENGTH_SHORT).show();
   }
}[/code]

![사용자 삽입 이미지](http://theeye.pe.kr/wp-content/uploads/1/1216796403.png)

기본적으로 위와같은 코드를 작성하고 화면을 회전시켜보면 위와같이 onCreate()가 또 호출되는것을 알 수 있습니다. onCreate()의 모든 코드들이 재실행 된다는 것을 의미합니다.

[code]<activity
   android:name=”.PreventLossActivity”
   android:label=”@string/app_name”
   android:configChanges=”keyboardHidden|orientation”>[/code]

AndroidManifest.xml에서 위와 같이 **configChanges**설정을 해줍니다. 화면이 회전하거나 하드웨어 키보드를 닫을때에 해당 관련된 처리를 자동으로 처리하지 않고 액티비티 자체에서 알아서 하겠다는것을 알려주는 설정입니다.

실제로 위의 두가지 이벤트가 발생할 때 **onConfigurationChanged()**가 호출됩니다. 하지만 위의 예제 소스에서는 이 메서드를 구현하지 않았고 결과적으로 아무런 일이 일어나지 않습니다.

![사용자 삽입 이미지](http://theeye.pe.kr/wp-content/uploads/1/1247329077.png)

[1370370599.zip](http://theeye.pe.kr/wp-content/uploads/1/1370370599.zip)

This entry was posted in [Android](http://theeye.pe.kr/archives/category/dumb-programmer/dev-android) and tagged [Android](http://theeye.pe.kr/archives/tag/android), [configChanges](http://theeye.pe.kr/archives/tag/configchanges), [java](http://theeye.pe.kr/archives/tag/java), [keyboardHidden](http://theeye.pe.kr/archives/tag/keyboardhidden), [onConfigurationChanged](http://theeye.pe.kr/archives/tag/onconfigurationchanged), [onCreate](http://theeye.pe.kr/archives/tag/oncreate), [onDestroy](http://theeye.pe.kr/archives/tag/ondestroy), [orientation](http://theeye.pe.kr/archives/tag/orientation), [안드로이드](http://theeye.pe.kr/archives/tag/안드로이드) on [January 26, 2011](http://theeye.pe.kr/archives/1292).