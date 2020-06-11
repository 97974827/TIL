# [2020-06-11 목 ] TIL

### Python String Formatting / List / Tuple / Dictionary

```python
# 문자열 포맷팅
# 포맷팅은 문자열 사이사이에 다른 타입의 데이터를 넣어 조립하는 형태
# 포맷팅 자료가 많을 수록 사용에 용이하다
# %d : 정수
# %f : 실수
# %s : 문자열 (자료형 전부 받을수 있음)
# %c : 단일 문자
# %h : 16진수
# %o : 8진수
# %% : 문자 %

tvxq = 5
print("동방신기는 " + str(tvxq) + "명!")
print("동방신기는 %d명!" % tvxq)

# ValueError: incomplete format : % 자체를 출력하고 싶으면 두번입력
per = 97
print("오늘 학습 진도율은 %d%%" % per)


# 포매팅이 여럿인 경우는 % 뒤에 나열할 자료를 ()로 감싸준다
month = 6
day = 6
anni = "현충일"
print("%d월 %d일은 %s이다" % (month, day, anni))

# 리스트
# 개별 변수는 정수면 정수, 실수면 실수 등 딱 하나의 값만 저장할수 있는반면
# 리스트는 여러 개의 값을 집합적으로 저장
# 다른 언어의 배열에 해당하며 실제로 배열과 비슷한 방식으로 사용
# [] 괄호 안에 요소를 콤마로 구분하여 나열
# 저장 개수에 제한이 없어 많은 변수를 저장 가능
# 리스트 내부엔 여러 데이터 타입을 섞어서 저장할 수 있지만 일반적이지 않는다

score = [88, 95, 70, 100, 59]
print(score[0])   # 88
print(score[2])   # 70
print(score[-1])  # 59 뒤에서 첫번째

sum = 0
for s in score:
    sum += s
print(sum)
print(sum / len(score))

# 리스트 선언
list1 = []
list2 = list()
print(list1)
print(list2)

# 리스트 슬라이싱도 문자열과 방식이 같다
nums = [0,1,2,3,4,5,6,7,8,9]
print(nums[2:5])    # [2, 3, 4]
print(nums[:4])     # [0, 1, 2, 3]
print(nums[6:])     # [6, 7, 8, 9]
print(nums[1:7:2])  # [1, 3, 5]

# 문자열은 인덱스를 통해 값을 변경할 수 없지만 리스트는 인덱스를 통해 언제든 내부의 값을 변경가능
print("-" * 40)
s1 = "python"
# s1[2] = "x"  # TypeError: 'str' object does not support item assignment --> 변수성향 문자저장, 상수성향 문자저장 키워드

print(nums)
del nums[1]  # 리스트 요소 삭제
print(nums)  # [0, 2, 3, 4, 5, 6, 7, 8, 9]
del nums[1:3]
print(nums)  # [0, 4, 5, 6, 7, 8, 9]

# 리스트에 +, * 연산을 적용하면 문자열에서의 연산과 동일하게 작동
list1 = [1,2,3,4,5]
list2 = [10,11]
listadd = list1 + list2
print(listadd)
listmul = list1 * 4# 문자열 포맷팅
# 포맷팅은 문자열 사이사이에 다른 타입의 데이터를 넣어 조립하는 형태
# 포맷팅 자료가 많을 수록 사용에 용이하다
# %d : 정수
# %f : 실수
# %s : 문자열 (자료형 전부 받을수 있음)
# %c : 단일 문자
# %h : 16진수
# %o : 8진수
# %% : 문자 %

tvxq = 5
print("동방신기는 " + str(tvxq) + "명!")
print("동방신기는 %d명!" % tvxq)

# ValueError: incomplete format : % 자체를 출력하고 싶으면 두번입력
per = 97
print("오늘 학습 진도율은 %d%%" % per)


# 포매팅이 여럿인 경우는 % 뒤에 나열할 자료를 ()로 감싸준다
month = 6
day = 6
anni = "현충일"
print("%d월 %d일은 %s이다" % (month, day, anni))

# 리스트
# 개별 변수는 정수면 정수, 실수면 실수 등 딱 하나의 값만 저장할수 있는반면
# 리스트는 여러 개의 값을 집합적으로 저장
# 다른 언어의 배열에 해당하며 실제로 배열과 비슷한 방식으로 사용
# [] 괄호 안에 요소를 콤마로 구분하여 나열
# 저장 개수에 제한이 없어 많은 변수를 저장 가능
# 리스트 내부엔 여러 데이터 타입을 섞어서 저장할 수 있지만 일반적이지 않는다

score = [88, 95, 70, 100, 59]
print(score[0])   # 88
print(score[2])   # 70
print(score[-1])  # 59 뒤에서 첫번째

sum = 0
for s in score:
    sum += s
print(sum)
print(sum / len(score))

# 리스트 선언
list1 = []
list2 = list()
print(list1)
print(list2)

# 리스트 슬라이싱도 문자열과 방식이 같다
nums = [0,1,2,3,4,5,6,7,8,9]
print(nums[2:5])    # [2, 3, 4]
print(nums[:4])     # [0, 1, 2, 3]
print(nums[6:])     # [6, 7, 8, 9]
print(nums[1:7:2])  # [1, 3, 5]

# 문자열은 인덱스를 통해 값을 변경할 수 없지만 리스트는 인덱스를 통해 언제든 내부의 값을 변경가능
print("-" * 40)
s1 = "python"
# s1[2] = "x"  # TypeError: 'str' object does not support item assignment --> 변수성향 문자저장, 상수성향 문자저장 키워드

print(nums)
del nums[1]  # 리스트 요소 삭제
print(nums)  # [0, 2, 3, 4, 5, 6, 7, 8, 9]
del nums[1:3]
print(nums)  # [0, 4, 5, 6, 7, 8, 9]

# 리스트에 +, * 연산을 적용하면 문자열에서의 연산과 동일하게 작동
list1 = [1,2,3,4,5]
list2 = [10,11]
listadd = list1 + list2
print(listadd)
listmul = list1 * 4
```

#### 중첩 리스트 / 반복문

```python
# 중첩 리스트 이용
list1 = [[1,2,3], [4,5], [6,7,8,9]]
print(list1[0])     # [1,2,3]
print(list1[2])     # [6,7,8,9]
print(list1[2][1])  # 7

# 중첩 리스트 요소 출력 (2차원)
for sub in list1:
    for item in sub:
        print(item, end=" ")
    print()

print("-" * 40)
score = [
    [88, 76, 92, 98],
    [65, 70, 58, 82],
    [82, 80, 78, 88]
]
total = 0          # 총점
total_subject = 0  # 전체 과목수
num = 1            # 학생의 번호
for score_int in score:
    for elm in score_int:
        total += elm
        num += 1
print("총점은 %d점 입니다 " % total)
# print("과목수는 %d과목 입니다" % total_subject)
print("학생의 번호는 %d번 입니다" % num)
print("-" * 40)

rooms = [
    [101, 102, 103, 104],
    [201, 202, 203, 204],
    [301, 302, 303, 304],
    [401, 402, 403, 404]
]
nodeliver = [102, 204, 303, 401]  # 요금 미납 세대
num = 1
i = 0
for room in rooms:
    for r in room:
        if r not in nodeliver:
            print("'%d'호에 신문을 배달 했습니다" % r)
        else:
            print("요금 미납 세대 '%d'호 입니다" % r)
    #     if nodeliver[i] == r:
    #         print("요금 미납 세대 '%d'호 입니다" % r)
    #         if i < 3:
    #             i += 1
    #     else:
    #         print("'%d'호에 신문을 배달 했습니다" % r)
    # print()
'''
'101'호에 신문을 배달 했습니다
요금 미납 세대 '102'호 입니다
'103'호에 신문을 배달 했습니다
'104'호에 신문을 배달 했습니다

'201'호에 신문을 배달 했습니다
'202'호에 신문을 배달 했습니다
'203'호에 신문을 배달 했습니다
요금 미납 세대 '204'호 입니다

'301'호에 신문을 배달 했습니다
'302'호에 신문을 배달 했습니다
요금 미납 세대 '303'호 입니다
'304'호에 신문을 배달 했습니다

요금 미납 세대 '401'호 입니다
'402'호에 신문을 배달 했습니다
'403'호에 신문을 배달 했습니다
'404'호에 신문을 배달 했습니다
'''
```

```python
# TODO List Comprehension
# 리스트 컴프리헨션 (내포)
# 리스트 안의 요소가 일정한 규칙을 가지는 수열이라면 일일히
# 나열할 필요없이 파이썬이 제공하는
# 컴프리헨션 문법으로 처리할 수 있다

# 리스트 내부의 자료 하나하나마다 특정 조건을 걸어서
# 새롭게 저장할 때 사용하는 문법이다.
# 반복문으로 대체할 수 있지만 리스트 내포가 좀 더 코드가 간결하다

# - [수식 for 변수 in 리스트 if 조건]
# for문으로 리스트를 순회하여 if조건에 True가 나오는 곳에 수식을 적용하여
# 리스트를 생성하는 원리

nums = [n * 2 for n in range(1, 11)]
print(nums)  # [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]

list1 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
list2 = []
for i in list1:
    list2.append(i * 2)
print(list2)

# 리스트 내포 문법 -> [저장규칙 for 변수 in 배열]
# 배열요소를 변수에 하나씩 번갈아가며 담아 저장규칙에 따라새로저장
list3 = [i * 2 for i in list1]                # 리스트1에 요소하나하나를 2씩 곱하여 출력
print(list3)  # [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]
list4 = [i * 2 for i in list1 if i % 2 == 1]  # 리스트1에 요소하나하나를 홀수에만 2씩 곱하여 출력
print(list4)  # [2, 6, 10, 14, 18]
list5 = [i * i for i in list1 if i % 2 == 0]
print(list5)  # [4, 16, 36, 64, 100]

```

### List Method

```python
nums = [1, 2, 3, 4]
nums.append(5)  # 요소를 리스트의 맨 끝에 추가함
print(nums)     # [1, 2, 3, 4, 5]

nums.insert(2, 90)   # 요소를 리스트의 특정 위치에 삽입함
print(nums)          # [1, 2, 90, 3, 4, 5]

nums2 = [7, 8, 9]
print(nums + nums2)  # [1, 2, 90, 3, 4, 5, 7, 8, 9]
print(nums, nums2)   # [1, 2, 90, 3, 4, 5] [7, 8, 9]  # 롤백됨

nums.extend(nums2)   # 리스트에 다른 리스트를 병합시킴 (변경된 내용이 그대로 유지)
print(nums)          # [1, 2, 90, 3, 4, 5, 7, 8, 9]

nums.remove(1)  # 삭제할 요소를 직접 값을 지정하여 삭제
print(nums)  # [2, 90, 3, 4, 5, 7, 8, 9]
del(nums[2:4])  # 범위지정 가능
print(nums)  # [2, 90, 5, 7, 8, 9]

# 리스트 검색
print(nums.index(2))  # 조회자료가 몇번 인덱스인지, 없을시 에러발생
strstr = "num123"
print(strstr.find("2"))   # 문자열에서 가능 : 조회자료가 몇번 인덱스인지, 없을시 -1 출력
print(nums.count(90))  # 리스트 내에 찾는 자료가 몇 개인지 정수로 출력

# 전체 리스트 삭제
nums2.clear()
print(nums2)
nums[:] = []
print(nums)


```

#### Tuple

#### 튜플을 사용해야 하는 이유 

- 튜플로 가능한 일은 리스트로도 모두 가능합니다. 
- 리스트는 튜플에 비해 요소를 변경하는 편집도 가능합니다.
- 결국 리스트는 튜플의 기능을 모두 포괄하는 더 큰 범위의 타입이지만 튜플을 사용해야 하는 이유가 있습니다.

1. 비용의 차이: 리스트는 변경의 가능성을 대비해야 하므로 더 많은 메모리를 소모하고 속도도 느립니다. 이에 비해 튜플은 값의 집합만 표현할 뿐 바뀔 일이 없으므로 내부구조가 단순하고 속도도 빠릅니다.
2. 데이터 안정성: 리스트는 실수로 내부 데이터가 의도치 않게 바뀔 수 있지만 튜플은 한번 정해지면 바꿀 수 없어 실수할 위험이 적습니다. 데이터베이스나 네트워크에서 얻은 데이터는 단순히 참조만 하면 될 뿐 편집할 일이 없으므로 리스트보다 튜플로 처리하는 것이 안전합니다.
3. 리스트와 상호 변경 가능: 리스트와 튜플은 값 변경 가능성 여부만 다를 뿐 구조가 비슷해 상호변경이 가능합니다. 리스트를 튜플로 변경할 때는 tuple()함수를 사용하며 반대는 list()함수를 사용합니다.  


```python
# 튜플은 값의 집합이라는 측면에서는 리스트와 유사하지만
# 초기화 한후에는 편집이 불가능
# 튜플은 상수 리스트라고도 부르며 ()를 사용하여 표현

# ex) 한번 담고 변경 할 필요가 없는 자료는 튜플에 넣어야 적합
# 리스트는 리스트인데 변경이 안됨
# 튜플 만들때 () 사용안해도됨
score = 87, 95, 76, 100, 98
sum = 0
s = 0
for s in score:
    sum += s
print(s, sum)
print(score)
print(type(score))

# 하나요소의 튜플은 콤마를 이용한다 ()를 쓰면 튜플이 안됨
tu = 2,
print(type(tu))

# 튜플 원본 자체에 변환을 주지않으면 슬라이싱, 인덱싱 가능
tu = 1, 2, 3, 4, 5
print(tu[3])
print(tu[1:4])
print(tu + (6, 7))
print(tu * 2)

# 불가능
# tu[3] = 33
# del tu[1]

a,b = 10, 20
print(a, b)
```

### Dictionary 

```python
# 사전 (Dictionary)
# 사전은 키와 값의 쌍을 저장하는 대용량의 자료구조
# 사전은 맵이라고도 부르며 연관배열이라고도 부른다

# 다른 언어는 라이브러리로 제공하는 고급 자료구조이지만
# 파이썬에서는 기본 데이터타입으로 제공하여 편리하게 사용가능
# 사전을 정의할 때는 {}괄호 안에 key:value 형태로 구분하여나열
# 사전에 데이터가 저장되는 것에는 순서가 없음

# 사전에 사용되는 key 는 중복값을 가질수 없고 변경할 수도 없다
# 그래서 튜플은 key 로 사용하는 것이 가능하지만 리스트로는 사용할수 없다
# value 는 값을 자유롭게 변경할 수 있다
# 사전에서 데이터를 검색할 때는 인덱스 대신 키를 사용하여 데이터를 검색한다

dic = {"멍멍이":"홍길동", "야옹이":"강감찬", "메뚜기":"장보고"}
print(type(dic))
print(dic)

print(dic["멍멍이"])
print(dic["메뚜기"])

# 검색하는 키가 사전 내부에 존재하지 않으면 에러 발생
# print(dic["장보고"])  # keyError

# 안전하게 데이터를 검색하려면 get()메소드 사용
print(dic.get("멍멍이"))
print(dic.get("야옹이"))

print(dic.get("장보고"))  # 키 에러는 없지만 "None" 발생 (실제자료없음)
print(dic.get("귀뚜라미", "없는 자료형입니다"))  # 없는 자료형입니다
print(dic.get("멍멍이", "없는 자료형입니다"))    # 홍길동

# value 새로 삽입
dic["멍멍이"] = "puppy"
print(dic)  # {'멍멍이': 'puppy', '야옹이': '강감찬', '메뚜기': '장보고'}

# 없는 키값 삽입
dic["소녀"] = "girl"
print(dic)  # {'멍멍이': 'puppy', '야옹이': '강감찬', '메뚜기': '장보고', '소녀': 'girl'}

# 사전 요소삭제
del dic["소녀"]
print(dic)  # {'멍멍이': 'puppy', '야옹이': '강감찬', '메뚜기': '장보고'}


```

