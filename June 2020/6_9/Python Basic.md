# [2020-06-09] 화 TIL

### Python Basic

- **IndentationError** : 불필요한 공백은 에러를 유발함 

```python
# 불필요한 공백은 에러를 유발
print("hello world")

a = "멍멍이"
b = "야옹이"

print(a+b*2)
print("1+2")
print(a, b, sep="와")  # 두개 이상 출력 할때 sep 사용

age = input("나이 : ")
print(age, "살", sep="")

dog = input("강아지 이름 >> ")
cat = input("고양이 이름 >> ")
print(dog, cat, sep="와 ", end="가 사료를 먹습니다.")
```

```python
hello world
멍멍이야옹이야옹이
1+2
멍멍이와야옹이
나이 : 20
20살
강아지 이름 >> 멍멍이
고양이 이름 >> 야옹이
멍멍이와 야옹이가 사료를 먹습니다.
```

#### input

- 입력 받은 값이 숫자이고 입력받은 값이 두개 이상에서 연산자를 이용하여 연산하고 싶을때  형변환 해줘야함 
- 문자열로 인식하여 문자열값으로 연산해버림 

```python
price = input("가격 : ")
num = input("갯수 : ")
print(price + num)  # print(int(price) - int(num)) 이런식으로 형변환
--------------------------------------------------------------
가격 : 1000
갯수 : 500
1000500

Process finished with exit code 0

price = input("가격 : ")
num = input("갯수 : ")
sum = int(price) * int(num)
print("총액은", sum, "원 입니다")

name = input("이름 >> ")
gen = input("성별 >> ")
age = int(input("나이 >> "))
year = 2020 - age + 1

print("\n이름 : " + name)
print("성별 : " + gen)
print("나이 : ", age)
print("출생년도 : ", year)
```

#### id() : 저장 주소 확인 

### 주의 : 내장함수 이름으로 변수를 만들면 해당 내장함수를 사용할 수 없다.

ex) print() , type() , id() if for while ..

#### 정수형 데이터 타입

```python
# 파이썬의 변수는 선언 시 별도의 데이터 타입을 지정하지 않는다.
# 데이터 타입은 변수에 저장되는 자료의 속성을 의미하는데  정수 , 실수 , 문자 , 논리 등이 있다.
# 파이썬에서는 데이터 타입 구분을 하지만 다른 언어처럼 변수에 저장할 때
# 변수 자체에서 받아들일 데이터 타입을 결정하는게 아니라 "최초로 대입되는 값을 데이터 타입으로 결정한다".
# 변수에 대입된 값은 언제든 다른 값으로 교체할 수 있다.
# 변수의 타입을 알고 싶을 때는 `type()`으로 감싸서 확인한다.
value = 1234
print(type(value))
value = "가나다라"
print(type(value))
value = 1.2345
print(type(value))

if type(value) == float:
    print("타입 일치")
    value = 'c'
    print(type(value))

# 다른 언어는 정수의 저장범위가 한정되어 있지만 , 파이썬은 굉장히 큰수도 저장할 수 있다.
a = 123456789
b = -543212345
c = 4 ** 100

print(a,b,c)

# 2진수 숫자 저장 시 숫자앞에 접두어로 0b(binary)를 붙인다.
a = 0b1101
print(a)

# 8진수 숫자 저장 시 숫자앞에 접두어로 0o(octal)를 붙인다.
b = 0o17
print(b)

# 16진수 숫자 저장 시 숫자앞에 접두어로 0x(hexadecimal)를 붙인다.
c = 0xAF
print(c)

# 10진수 변환 출력 
print(bin(a), oct(b), hex(c))
```



#### 실수, 논리, 문자형 데이터 타입

```python
a = 3.14
print(a, type(a))

# 실수형 변수는 e 표기법이 가능하다
# e 표기법은 e왼쪽숫자를 e오른쪽숫자 자릿수만큼 올리거나 내린다.
print(0.00000000000000000001)  # 1e-20  # 1을 20자리 내려라
print(0.1e+3)  # 100.0

print(3 > 5)				# False
print(100 == 100)			# True
a = 50
b = 70
print(a + b < 200)		# True

# 문자열 str 은 따옴표로 둘러싸인 문자의미
# 따옴표 안에 들어간 문자는 종류를 막론하고 문자열이 된다.
# 어떤 문자라도 문자열로 사용할 수 있다
# 저장길이에도 제한이 없다
s = '"I am hungry!"'
print(s)		# "I am hungry!"

# 만약 문장 내에서 "와 '를 둘 다 문자로 쓰고싶다면
# 문자로서 사용하는 따옴표 왼쪽에 \를 붙인다
s2 = "\"설렁탕을 사왔는데 왜 먹지를 못하나...\""
print(s2) 		# "설렁탕을 사왔는데 왜 먹지를 못하나..."

s3 = '\'다이어트는 내일부터 해야지\''
print(s3) 		# '다이어트는 내일부터 해야지'

s4 = "korea" "japan" "2002"
print(s4)		# koreajapan2002
```



#### 연산자 / 형변환 / 제어문

- +, -, *, /(실수나눗셈 결과), //(정수나눗셈결과), **(거듭제곱), %(나머지)

- not : 다른 언어 ! 와 동일 
- **0이 아니거나 값이 있으면 조건에 들어감 (참으로 간주함)**
  - b=5 if b:
- for 제어변수 in 컬렉션:
- range(시작값, 끝값, 증가값)

```python
s = "홍길동의 점수 "
n = 96
print(s + str(n))       # 홍길동의 점수 96

# print(10 + "34")      # TypeError: unsupported operand type(s) for +: 'int' and 'str'
print(10 + int("34"))   # 44

num1 = int("1a", 16)
num2 = int("110", 2)
print(num1, num2, int("77", 8))  # 26 6 63

b = 5
if not b < 0:
    print("0보다 작지 X")


add = int(input("배수를 입력 : "))
num = 0
n = add
summ = 0
while num <= 567:
    summ += num
    num += add

print(n, "의 배수 0 ~ 567 누적합계", summ)
```

