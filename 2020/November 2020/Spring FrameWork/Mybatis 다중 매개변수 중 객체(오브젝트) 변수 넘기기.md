## [Mybatis 다중 매개변수 중 객체(오브젝트) 변수 넘기기](https://meaownworld.tistory.com/119)

일반객체를 @Param으로 보내는 방법은 다른 블로그나 검색을 통해서도 쉽게 정보를 구할 수 있는데



매개변수 여러개 중 오브젝트 변수 또는 객체 변수가 포함되어 있을 때는



나오질 않아서 그 방법을 적습니다.



```
String select(@Param("obj")Object object);
```





**1. 오브젝트 1개**



오브젝트문이 하나라면 그냥 그 안의 변수들을 사용하면 됩니다.



예를들어 object 안에 attribute1 attirbuete2 변수가 포함되어 있다면 get에서 설정한 이름 대로 그냥 사용하시면 됩니다.

```
"select * from thistable where id = #{attributename1} and pw = #{attributename2}"
```

이런 식으로 말이죠.













**2. 오브젝트 객체와 다른 변수**

```
String select(@Param("obj")Object object, @Param("normal")int a);
```



이런 식으로 오브젝트가 끼어있는 다중 파라미터라면



```
"select * from thistable where id = #{obj.attributename1} and pw = #{a}"
```

이런 식으로 오브젝트 이름에는 설정한 파라미터 문자열을 앞에 적어주시고



다른 변수는 그냥 적어주세요.