# java.util.zip.ZipException: error in opening zip file 에러가 날때 배포후 WAS 스타트시 



09 Aug 2019 in [Java](https://stove99.github.io/category/java/)

war 파일을 맨들어 톰캣에 배포하고 서버를 리스타트 하는데 자꾸 아래와 같은 오류가 나면서 서버가 실행되지 않았다.

```
Caused by: org.apache.catalina.LifecycleException: Failed to start component [org.apache.catalina.webresources.StandardRoot]
    at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:167)
    at org.apache.catalina.core.StandardContext.resourcesStart(StandardContext.java:4868)
    at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5003)
    at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:150)
    ... 10 more
Caused by: org.apache.catalina.LifecycleException: Failed to initialize component
                                    [org.apache.catalina.webresources.JarResourceSet]
    at org.apache.catalina.util.LifecycleBase.init(LifecycleBase.java:113)
    at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:140)
    at org.apache.catalina.webresources.StandardRoot.startInternal(StandardRoot.java:724)
    at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:150)
    ... 13 more
Caused by: java.lang.IllegalArgumentException: java.util.zip.ZipException: error in opening zip file
    at org.apache.catalina.webresources.AbstractSingleArchiveResourceSet.initInternal(AbstractSingleArchiveResourceSet.java)
    at org.apache.catalina.util.LifecycleBase.init(LifecycleBase.java:107)
    ... 16 more
Caused by: java.util.zip.ZipException: error in opening zip file
    at java.util.zip.ZipFile.open(Native Method)
    at java.util.zip.ZipFile.<init>(ZipFile.java:225)
    at java.util.zip.ZipFile.<init>(ZipFile.java:155)
    at java.util.jar.JarFile.<init>(JarFile.java:166)
    at java.util.jar.JarFile.<init>(JarFile.java:130)
    at org.apache.tomcat.util.compat.JreCompat.jarFileNewInstance(JreCompat.java:188)
    at org.apache.tomcat.util.compat.JreCompat.jarFileNewInstance(JreCompat.java:173)
    at org.apache.catalina.webresources.AbstractSingleArchiveResourceSet.initInternal(AbstractSingleArchiveResourceSet.java)
    ... 17 more
```

당연히 war 파일이 비정상적이겠지 해서 jar xvf ROOT.war 로 압축을 풀었는데 정상적으로 잘 풀려서 톰캣이 이상해졌나 -_-? 하는 아주 잘못된 방향으로 생각을 하는 바람에 살짝 길게 삽질을 하게 되었다.

쓸데없는 소리는 이쯤에서 그만하고 결론적으로 원인은 war 파일안에 있는 lib 폴더에 있는 jar 파일들이 이상해서 발생하는 오류였다.

**오류를 해결할려면 로컬 PC에 있는 maven repository 를 싹 지우고 다시 라이브러리를 다운로드 받으면 된다.**

이클립스가 켜져 있으면 삭제 안되니깐 끄고 지워야 됨.

## 로컬 PC maven repository 위치

```
windows : c:\users\사용자명\.m2\repository
mac or linux : ~/.m2/repository
```