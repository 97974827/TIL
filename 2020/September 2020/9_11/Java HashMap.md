### Java HashMap 

- 효과적인 사용방법 : http://tech.javacafe.io/2018/12/03/HashMap/



### Print

1. 일반적인 출력 경우 

```java
Map <Integer, String> map_crc_data = new HashMap<Integer, String>();

int no;
String crc;

map_crc_data.put(no, crc); // 반복
for(Map<Integer, String> elem : map_crc_data.entrySet()){
    int key = elem.getKey();
    String value = elem.getvalue();
}
```



