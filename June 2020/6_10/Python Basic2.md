# [2020-06-10] 수 TIL

### Python Basic2 

```python
# 반복문 for
# for 문은 in 오른쪽 컬렉션 내부의 요소들을 왼쪽부터
# 하나씩 꺼내서 in 왼쪽변수에 대입해 실행문을 실행
# 실행횟수는 처음부터 컬렉션 갯수 바퀴수로 정해져 있다
# 컬렉션이란 여러 값을 모아놓은 집합이며 리스트, 문자열이
# 가장 대표적인 예시이다
# 리스트는 [] 사이에 있는 자료이며,
# 문자열도 "문자 배열"이기 때문에 컬렉션으로 취급된다

for student in [1, 2, 3]:
    print(student, "번 학생의 출석을 체크합니다")

for alphabet in "abcdefghijklmnop":
    print(alphabet)

# range(시작값(생략가능), 끝값, 증가값(생략가능)) --> 1부터 5까지 시작
for student in range(1, 6):
    print(student, "번 학생의 출석을 체크합니다")

# n의 배수의 합
sum = 0
n = 3  #int(input("배수 입력 : "))
for number in range(0, 1000, n):
    sum += number
print("총합 : ", sum, "입니다")

```

#### String indexing / Slicing / Method

```python
# 문자열 조작
# 파이썬은 문자열을 기본타입으로 지원할 뿐만 아니라
# 문자열을 조작하는 풍부한 명령들을 가지고있다
# 문자열은 메모리상에 개별 문자들이 일렬로 늘어선 형태로 저장된다
# 문자열은 첨자 index 로 관리되며 앞글자부터 0번이 부여되며
# 순차적인 첨자가 부여되어 관리된다
# 앞에서부터 셀 때는 문자열의 첫 문자는 0번부터 시작하여 1씩 증가하고
# 뒤에서부터 셀 때는 문자열의 마지막 문자는 -1번부터 시작하여
# 앞으로 올수록 1씩 감소한다

# Indexing
string = "Python"
print(string[2])     # t
print(string[-2])    # o

for s in string:
    print(s, end=" ")  # P y t h o n
print()

# 문자열 슬라이싱 이란 범위를 지정해 여러 문자열을 묶어서 잘라오는 것을 의미
# []를 이용해 잘라오게 되며 범위 지정이기 때문에 :을 이용해서
# 문자열 [시작값:끝값+1(:구간)]을 나타냄
# 끝값은 range() 처럼 -1을 해줘야 정상적으로 작동

# Slicing
print(string[2:5])   # tho
print(string[3:])    # hon
print(string[:4])    # Pyth
print(string[2:-2])  # th

ssn = "960201-2142123"
print(ssn[:6])  # 960201
print(ssn[7:])  # 2142123

file = "20180819-134709.jpg"
print("촬영날짜 : " + file[4:6] + "월 " + file[6:8] + "일")
print("촬영시간 : " + file[9:11] + "시 " + file[11:13] + "분 " + file[13:15] + "초")
print("확장자  : " + file[-3:])

# 문자열을 슬라이싱 할때 []의 세번째 값으로 step을 지정하면
# 문자열을 step 만큼 건너뛰면서 읽으며 지정하지 않을 시 1로 지정
print("-" * 40)

yoil = "월화수목금토일"
print(yoil[::2])    # 월수금일
print(yoil[::-1])   # 일토금목수화월

# 문자열 관리함수, 메서드
# 문자열은 수치형 자료에 비해 복잡하고 길이도 가변적이어서
# 파이썬에서는 문자열을 조작할 수 있는 편한 함수와 메서드들을 제공
# 함수 : 공용적으로 내장기능
# 메서드 : 클래스에 속해있는 함수

s = "python programming class"
print(len(s))        # 24
print(s.find("o"))   # 4
print(s.rfind("o"))  # 뒤에서 부터 찾아냄 : 9 (찾는게 없으면 -1 리턴 / 대소문자 구분함)
print(s.upper())
print(s)

if not s.find("c") == -1:
    print("성공")
elif not s.find("java") == -1:
    print("성공")
else:
    print("실패")

print(s.index('o'))  # 4 인덱스 번호 리턴
print(s.count('n'))  # 찾는 문자열이 없으면 0 , 있으면 갯수 리턴

s2 = """생각이란 생각할수록 생각나므로 생각하지 말아야 할 생각은
        생각하지 않으려고 하는 생각이 옳은 생각이라고 생각합니다"""
print("'생각'의 등장 횟수 : ", s2.count("생각"))


# 특정문자가 어느 위치에 있는지 관심 없고 단순히 포함여부만 알고싶으면
# in 키워드 사용 : 문자열 내부에 해당문자가 포함되어 있을시 True,
# 없을 시 False 리턴
# 반대로 not in 은 포함되어 있지 않은지 검사
print("a" in s, "z" in s)
print("pro" in s, "x" not in s)

name = "홍길동"
if name.startswith("홍"):  # 시작문자 검사 != endswith 끝문자 검사
    print("홍씨")
if name.startswith("박"):
    print("박씨")

# 공백제거 왼쪽, 오른쪽, 양쪽  -> 크롤링 많이씀
print(s)
print(s.lstrip())
print(s.rstrip())
print(s.strip())


```

```python
# 문자열 분할
str_local = "서울->대전->대구->부산"
print(str_local.split("->"))

s1 = "떡볶이 김말이 닭강정"
print(type(s1))
s1 = s1.split(" ")  # 문자열형에서 split을 쓰면 쪼개진 문자열이 list로 저장됨
print(type(s1))

# 리스트 메서드
# join()은 "문자열".join(입력받을자료) 문법을 사용하여
# 입력받을자료에 해당하는 자료의 인덱스번호 사이사이마다
# "문자열" 에 해당하는 자료를 집어넣어준다

s = "^_^"
print(s.join("안알랴줌"))  # 안^_^알^_^랴^_^줌

s3 = "배?고?프?다"
s4 = s3.split("?")  # s4 = ['배', '고', '프', '다']
print(s4)
s4 = "!".join(s4)   # s4 = 배!고!프!다
print(s4)


ldol = "방탄소년단,여자친구,아이오아이"
singer = ldol.split(",")
for sss in singer:
    print(sss, "파이팅")


```

