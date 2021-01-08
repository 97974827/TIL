## [안드로이드 JSON 파헤치기](https://link2me.tistory.com/1247)

서버로부터 데이터를 가져오는 방법은 JSON, XML 두가지 방식을 사용한다.

정부가 제공하는 자료는 XML 로 되어 있는데, 변환처리가 까다롭기 때문에 JSON으로 변환해주는 라이브러리를 사용하여 변환처리하면 개발하기 편하다.

JSON 방식에 대해 단계별로 정리를 해두고자 구글링 및 타 블로그 자료를 참조하여 테스트를 했다.

단계별로 정리하는 이유는 사용할 용도가 배열 형태로 여러 데이터를 가져와야 할 경우도 있지만, 하나의 데이터만 전달받아서 가져올 경우도 있다.



먼저 JSON 의 형태부터 알고 넘어가자.

![img](https://t1.daumcdn.net/cfile/tistory/2279F43758EED0B336)



서버에서 가져온 데이터라고 가정하고 JSON 데이터(배열)을 분리해서 값을 추출하는 예제다.



import android.os.Bundle; import android.support.v7.app.AppCompatActivity; import android.widget.TextView;  import org.json.JSONArray; import org.json.JSONException; import org.json.JSONObject;  import java.io.BufferedReader; import java.io.IOException; import java.io.InputStreamReader; import java.io.OutputStreamWriter; import java.io.PrintWriter; import java.net.HttpURLConnection; import java.net.MalformedURLException; import java.net.URL; import java.net.URLEncoder;  public class MainActivity extends AppCompatActivity {    private TextView textView;   String test =       "[{'name':'배트맨','age':37,'address':'고담'},"+        "{'name':'슈퍼맨','age':36,'address':'뉴욕'},"+        "{'name':'앤트맨','age':28,'address':'LA'}]";    @Override   protected void onCreate(Bundle savedInstanceState) {     super.onCreate(savedInstanceState);     setContentView(R.layout.activity_main);      textView = (TextView) findViewById(R.id.tv_json);      try {       parse();     } catch (Exception e) {     }   }    private void parse() throws Exception {      StringBuffer sb = new StringBuffer();      try {       JSONArray jarray = new JSONArray(test); // JSONArray 생성       for(int i=0; i < jarray.length(); i++) {         JSONObject jsonObj= jarray.getJSONObject(i); // JSONObject 추출         String address = jsonObj.getString("address");         String name = jsonObj.getString("name");         int age = jsonObj.getInt("age");         sb.append(             "주소:" + address +             " 이름:" + name +             " 나이:" + age + "\n"         );       }       textView.setText(sb.toString());     } catch (JSONException e){       e.printStackTrace();     }   } } 



서버에서 넘어온 데이터가 아래와 같은 구조일 수도 있다.

String test =
  "{"result":[{'name':'배트맨','age':37,'address':'고담'},"+
        "{'name':'슈퍼맨','age':36,'address':'뉴욕'},"+
      "{'name':'앤트맨','age':28,'address':'LA'}]}";



중괄호{}로 둘러싸인 것을 볼 수 있다. 그러므로

JSONObject jsonObj = new JSONObject(test);

JSONArray jarray = jsonObj.getJSONArray("result");



JSONArray 를 뽑아낸 다음에 위에서 나온 과정을 수행하면 된다.

for(int i=0; i < jarray.length(); i++) {
  JSONObject jObj= jarray.getJSONObject(i); // JSONObject 추출
  String address = jObj.getString("address");
  String name = jObj.getString("name");
  int age = jObj.getInt("age");
  sb.append(
      "주소:" + address +
      " 이름:" + name +
      " 나이:" + age + "\n"
  );
}



위 예제에서 다음 단계로 고려할 사항은

**1. 서버에서 JSON 데이터를 가져오는 방법
**

**2. 배열 데이터를 메모리에 저장하는 방법**

이다.





**1. 서버에서 JSON 데이터를 가져오는 방법**

안드로이드 6.0부터 Apache HTTP API는 지원안한다.

HttpURLConnection 클래스를 이용하라고 하는데, 이유는 이게 더 효율적이라고 한다.

서버와 통신을 하는 함수를 하나 잘 만들어서 유용하게 사용하면 좋다.



대부분 개발자는 Volley 라이브러리, Retrofit2 라이브러리를 이용하여 서버 데이터를 가져와서 파싱처리한다.

이 블로그에 게재된 Volley 라이브러리 또는 Retrofit2 라이브러리를 예제를 이용하는 걸 권장한다.



앱 build.gradle 에서 androidx 로 된 라이브러리를 이용하는 게시글이 최신 자료이다.

아래 코드 HttpUrlConnection 코드는 이용하지 않아도 된다.

Volley 라이브러리, Retrofit2 라이브러리는 백그라운드 처리를 다하고 결과만 파싱하여 처리하면 된다.



*** 아래 코드 작성일자 : 2017.4.14 일 ******

아래 함수가 100% 만족스런 함수는 아니지만 이거 테스트하느라고 엄청 삽질을 했다.

세션처리 부분은 아직 제대로 이해를 못했다.

Eclipse 에서 세션처리가 문제 없이 동작되던게 Android Studio 에서 세션이 제대로 동작하지 않는다.

그래서 서버쪽에서 처리하는 로직을 변경했다.



 import android.app.Activity; import android.util.Log; import android.webkit.CookieManager;  import java.io.BufferedReader; import java.io.DataOutputStream; import java.io.InputStreamReader; import java.net.HttpURLConnection; import java.net.URL;  public class PHPComm extends Activity {    // serverURL : JSON 요청을 받는 서버의 URL   // postParams : POST 방식으로 전달될 입력 데이터   // 반환 데이터 : 서버에서 전달된 JSON 데이터   public static String getJson(String serverUrl, String postParams) throws Exception {       BufferedReader bufferedReader = null;        try {          URL url = new URL(serverUrl);          HttpURLConnection conn = (HttpURLConnection) url.openConnection();        // 세션 쿠키 전달        String cookieString = CookieManager.getInstance().getCookie(Value.IPADDRESS);                StringBuilder sb = new StringBuilder();           if(conn != null){ // 연결되었으면          conn.setConnectTimeout(10000); // milliseconds 연결 타임아웃시간          //add request header          conn.setRequestMethod("POST");          conn.setRequestProperty("USER-AGENT", "Mozilla/5.0");          conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");          conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");          if (cookieString != null) {           conn.setRequestProperty("Cookie", cookieString);           Log.e("PHP_getCookie", cookieString);          }          conn.setConnectTimeout(10000);          conn.setReadTimeout(10000);          conn.setUseCaches(false);          conn.setDefaultUseCaches(false);          conn.setDoOutput(true); // POST 로 데이터를 넘겨주겠다는 옵션          conn.setDoInput(true);          // Send post request          DataOutputStream wr = new DataOutputStream(conn.getOutputStream());          wr.writeBytes(postParams);          wr.flush();          wr.close();          int responseCode = conn.getResponseCode();          System.out.println("GET Response Code : " + responseCode);               if(responseCode == HttpURLConnection.HTTP_OK){ // 연결 코드가 리턴되면            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));            String json;            while((json = bufferedReader.readLine())!= null){              sb.append(json + "\n");            }              }          bufferedReader.close();        }              return sb.toString().trim();         } catch(Exception e){          return new String("Exception: " + e.getMessage());      }       } } 



이 함수를 활용하는 방법은 아래와 같다.

// 버튼을 클릭하면 서버에서 결과를 가져온다. btn_search.setOnClickListener(new View.OnClickListener() {   @Override   public void onClick(View view) {     // 세션이 제대로 동작되지 않아서 사용자별 idx 를 폰에 저장했다가 가져와서 키로 사용     settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);      // 검색어(search) 와 key(idx) 를 postParameter 로 넘김     Uri.Builder builder = new Uri.Builder()         .appendQueryParameter("search", editText.getText().toString().trim())         .appendQueryParameter("idx", settings.getString("idx",""));     String postParams = builder.build().getEncodedQuery();     new getJSONData().execute(Value.IPADDRESS + "/get_json.php",postParams);   } }); class getJSONData extends AsyncTask<String, Void, String> {   @Override   protected String doInBackground(String... params) {     try {       return PHPComm.getJson(params[0],params[1]);     } catch (Exception e) {       return new String("Exception: " + e.getMessage());     }   }    protected void onPostExecute(String result){     searchJSON=result;     showList();   } }  // 서버 정보를 파싱하기 위한 변수 선언 String searchJSON; private static final String TAG_RESULTS="result"; private static final String TAG_UID = "uid"; private static final String TAG_NAME = "name"; private static final String TAG_Mobile ="mobile"; JSONArray peoples = null;  protected void showList() {   StringBuffer sb = new StringBuffer();   try {     JSONObject jsonObj = new JSONObject(searchJSON);     peoples = jsonObj.getJSONArray(TAG_RESULTS);      for(int i=0;i<peoples.length();i++){       JSONObject c = peoples.getJSONObject(i);       String uid = c.getString(TAG_UID);       String name = c.getString(TAG_NAME);       String mobile = c.getString(TAG_Mobile);       sb.append(           "idx:" + uid +               " 이름:" + name +               " 휴대폰:" + mobile + "\n"       );     }     textView.setText(sb.toString());   } catch (JSONException e) {     e.printStackTrace();   } } 



간단하게 PHP 서버에서 검색된 결과 데이터를 화면에 출력만 하는 로직이라 간단하게 처리했다.



\2. 배열 데이터를 메모리에 저장하는 방법은

Custom ListView 를 사용하는 법을 알아야 되는 과정이라서 여기서는 생략한다.



서버 get_json.php 파일은 아래와 같다.

보다 자세한 사항은 http://link2me.tistory.com/1022 를 참조하면 된다.



 <?php if(!isset($_SESSION)) {    session_start(); }  @extract($_POST); // POST 전송으로 전달받은 값 처리 if(!(isset($idx) && !empty($idx))) {    echo 0;    exit; }  require_once 'dbconnect.php';  // 화면에 출력할 칼럼 발췌 $sql = "select uid,name,mobile from Person "; if(!empty($search)) {    $sql .= "where name LIKE '%".$search."%' or mobile LIKE '%".$search."%'"; } $R = array(); // 결과 담을 변수 생성 $result = mysqli_query($dbconn,$sql); while($row = mysqli_fetch_object($result)) {    array_push($R, $row); } echo json_encode(array('result'=>$R));  ?> 



테스트에 사용된 파일 부분만 발췌한 것이다.

내용이 보강되고 달라지면 파일 찾기도 힘들거 같아 첨부해둔다.



출처: https://link2me.tistory.com/1247 [소소한 일상 및 업무TIP 다루기]