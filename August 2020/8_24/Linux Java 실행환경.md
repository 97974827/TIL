# Linux Java 실행환경 설정 

- [how to java install in Debian 10](https://linuxize.com/post/install-java-on-debian-10/)

### Installing OpenJDK 8

1. Start by updating the packages list and installing the dependencies necessary to [add a new repository](https://linuxize.com/post/how-to-add-apt-repository-in-ubuntu/) over HTTPS:

   ```
   sudo apt updatesudo apt install apt-transport-https ca-certificates wget dirmngr gnupg software-properties-common
   ```

2. Import the repository’s GPG key using the following [wget](https://linuxize.com/post/wget-command-examples/) command:

   ```
   wget -qO - https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public | sudo apt-key add -
   ```

3. Add the AdoptOpenJDK APT repository to your system:

   ```
   sudo add-apt-repository --yes https://adoptopenjdk.jfrog.io/adoptopenjdk/deb/
   ```

4. Once the repository is enabled, update apt sources and install Java 8 using the following commands:

   ```
   sudo apt updatesudo apt install adoptopenjdk-8-hotspot
   ```

5. Finally, verify the installation by checking the Java version:

   ```
   java -version
   ```

   The output should look something like this:

   ```output
   openjdk version "1.8.0_212"
   OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_212-b04)
   OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.212-b04, mixed mode)
   ```

## `JAVA_HOME` Environment Variable

The `JAVA_HOME` [environment variable](https://linuxize.com/post/how-to-set-and-list-environment-variables-in-linux/) is used by some Java applications to determine the Java installation location.

To set the `JAVA_HOME` environment variable, use the `update-alternatives` command to find where Java is installed:

```
sudo update-alternatives --config java
```

In this example, the installation paths are as follows:

- OpenJDK 11 is located at `/usr/lib/jvm/java-11-openjdk-amd64/bin/java`
- OpenJDK 8 is located at `/usr/lib/jvm/adoptopenjdk-8-hotspot-amd64/bin/java`

Once you found the path of your preferred Java installation, open the `/etc/environment` file:

```
sudo nano /etc/environment
```

Assuming you want to set `JAVA_HOME` to OpenJDK 11, add the following line, at the end of the file:



- 생활코딩 자바 실행환경 설정 https://opentutorials.org/module/516/5565

```shell
# 컴파일
$ javac 자바파일명.java

# 실행
$ java 자바파일명 
```

***

#### Linux - Jar 파일 압축 / 풀기

- 참고 : https://m.blog.naver.com/PostView.nhn?blogId=vivacarla&logNo=221306139444&proxyReferer=https:%2F%2Fwww.google.com%2F

```shell
# 풀기 
$ jar xvf 파일명 

# 압축 
$ 
```



***

### Linux - uart 

- rxtx-2.1-7  Binary Download
  - http://rxtx.qbang.org/wiki/index.php/Download

- [[Java\] 시리얼 통신(java-simple-serial-connector - jssc)](http://forum.falinux.com/zbxe/index.php?document_srl=849025)




- #### **JDK 경로** 

`/usr/lib/jvm/adoptopenjdk-8-hotspot-amd64/lib`





#### 라이브러리 종류

1. **RXTXcomm.jar**
2. **comm.jar** 
   - USB 형식 지원하지 않는 다고 함 
   - https://www.codeproject.com/Questions/450480/How-communicate-with-serial-port-in-Java
3. **Jssc.2.7.0-src.jar**
   - 이거 사용함 
   - [Java - 시리얼 통신 ]([http://forum.falinux.com/zbxe/index.php?mid=lecture_tip&search_target=user_name&search_keyword=%EC%9D%B4%EB%B3%91%EB%B3%B5&document_srl=849025](http://forum.falinux.com/zbxe/index.php?mid=lecture_tip&search_target=user_name&search_keyword=이병복&document_srl=849025))
   - [jssc 샘플 코드](https://code.google.com/archive/p/java-simple-serial-connector/wikis/jSSC_examples.wiki)

