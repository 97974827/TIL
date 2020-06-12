# [2020-06-12 금] TIL

### Python Dictionary / Method / global

```python
dic = {"고양이":"야옹!", "강아지":"멍멍!", "호랑이":"어흥!"}
print("dic : ", dic)
print("key : ", dic.keys())
print("value : ", dic.values())
print("key_get : ", dic.get("고양이"))

# 딕셔너리 값 변경 가능
dic["고양이"] = "하이"
print("dic : ", dic)

del dic["고양이"]
print("dic : ", dic)

# 결과
# dic :  {'고양이': '야옹!', '강아지': '멍멍!', '호랑이': '어흥!'}
# key :  dict_keys(['고양이', '강아지', '호랑이'])
# value :  dict_values(['야옹!', '멍멍!', '어흥!'])
# key_get :  야옹!
# dic :  {'고양이': '하이', '강아지': '멍멍!', '호랑이': '어흥!'}
# dic :  {'강아지': '멍멍!', '호랑이': '어흥!'}
```

```python
# 딕셔너리를 활용한 음식점 관리 
foods = {}

while True:
    print("음식점 메뉴")
    print("1. 신규 메뉴등록")
    print("2. 전체보기")
    print("3. 종료")
    num = int(input(">> "))

    if num == 1:
        key_name = input("이름 입력 >> ")

        if key_name in foods:
            print("이미 등록된 메뉴")
        else:
            value_name = input("가격 입력 >> ")
            foods[key_name] = value_name
            print("신규 메뉴 %s (이)가 등록됨" % key_name)

    elif num == 2:
        print("-- 메뉴판 --")
        food = foods.keys()
        for elm in food:
            print(elm, ":" , foods[elm], "원")
        print("-" * 40)
        print("1. 기존메뉴수정 | 2. 메뉴 삭제 | 3. 나가기")
        select_num = int(input(">> "))

        if select_num == 1:
            food_key = foods.keys()
            change_name = input("바꿀 메뉴를 입력해 주세요 : ")

            if change_name in food_key:
                change_price = int(input("바꿀 가격을 입력해 주세요 : "))
                foods[change_name] = change_price
                print("%s 의 가격이 %s 원으로 수정되었습니다. " % (change_name, change_price))
            else:
                print("그런 메뉴는 없습니다.")

        elif select_num == 2:
            food_key = foods.keys()
            delete_name = input("지울 메뉴를 입력해 주세요 : ")

            if delete_name in food_key:
                del foods[delete_name]
                print("%s 이(가) 삭제 되었습니다 " % delete_name)
            else:
                print("그런 메뉴는 없습니다.")

        elif select_num == 3:
            pass
        else:
            print("Error")

    elif num == 3:
        break
    else:
        print("Error")
```

```python
# 함수는 반복적으로 사용되는 코드 블록들에 이름을 붙여놓은 형태
# 함수를 한번 정의해두면 지정한 이름으로 언제든지 해당 코드뭉치를 재사용
# 정의할 때 def 키워드 사용
# 정의해둔 함수를 사용하는 것을 호출 call 이라고 부른다
# 파이썬에서는 함수를 사용하려면 먼저 정의하고 반드시 그 후에 호출
# 먼저 호출하고 그이후에 정의한다면 에러 발생

def get_weapon(weapon):
    print(weapon + "을(를) 획득하였습니다.")

get_weapon("도끼")
get_weapon("지팡이")
get_weapon("단검")

# 인수는 함수를 호출할 때 함수가 실행에 필요한 값들을 전달하기 위한 매개체이며
# 그렇기 때문에 매개 변수라고도 부릅니다.
# 함수 호출부에서 어떤 인수를 전달하느냐에 따라 함수의 실행동작이 달라집니다.
# 인수는 개수의 제한이 없어 많은 값을 함수에 전달할 수도 있고
# 하나도 전달하지 않을 수도 있습니다.
# 인수를 얼마나 받을지는 함수를 정의할 때 정해두어야 합니다.

# 리턴값은 함수를 호출한 곳으로 함수의 최종실행 결과를 전달할 값입니다.
# 인수는 호출부에서 함수에게 전달되는 값이고
# 리턴값은 호출부에 실행결과를 보고하는 값입니다.
# 함수의 입장에서 볼 때 인수는 입력값이고 리턴값은 출력값입니다.
# 인수는 여러 개 존재할 수 있지만 리턴값은 언제나 하나만 존재해야 합니다.
# return 이라는 키워드를 사용하여 뒤에 전달할 값을 지정합니다.

def add(num1, num2):
    return num1 + num2

def multi(n1, n2):
    print("%d X %d = %d" % (n1, n2, (n1 * n2)))

result = add(10, 7)
print(result)
print(add(14, 19))
multi(4, 7)
multi(1, 2)

# 함수의 리턴값은 오직 "하나"입니다.
# 파이썬에서는 특별하게 return 키워드 뒤에
# 리턴값을 콤마로 나열하여 여러개 전달할수 있습니다.
# 이런경우 리턴값이 여러 개가 되는 것이 아닌
# 그 값들을 묶어서 튜플 하나로 전달합니다

def sum_and_mul(n1, n2):
    return n1 + n2, n1 * n2

result = sum_and_mul(2, 7)
print(result)  # 튜플형태

# return 구문의 용도는 두가지이다
# 1. return 값을 호출한 위치에 가져다준다
# 2. return문 실행시 즉시 함수를 종료
# 이 두가지는 동시에 진행되는 로직이다
# 따라서 문장에 2개이상의 return 문을 작성하면 첫번째 리턴문까지만
# 함수가 실행되고 바로 종료가된다

def sum_and_sub(n1, n2):
    return n1 + n2
    return n1 - n2

result = sum_and_sub(7,2)
print(result)

# pass 는 파이썬의 가장 간단한 명령어로 아무 동작도 하지 않는다
# 파이썬 인터프리터는 한줄씩 코드를 해석하고 실행하기 때문에
# 컴파일 언어들처럼 미리 틀을 짜두고 나머지를 먼저 코딩하고
# 코드를 테스트해볼 수 없다
# 함수나 제어문의 틀을 짜두고 내부 로직은 천천히 작성할 생각이라면 pass 명령을 사용

print("hello")
pass
print("world")

# 가변인수를 사용하면 하나의 인수로 여러개의 데이터를 받아서 처리할 수있다
# 가변인수는 함수정의부에서 인수를 선언할 때 인수 앞에 * 기호를 붙여
# 선언하며 여러개의 데이터를 튜플로 묶어서 함수 내부에 전달
def add_num(*nums):
    sum = 0
    for num in nums:
        sum += num
    return sum

print(add_num(3,6,9))
print("-"*40)

# n개의 정수를 받아 가장 큰숫자를 찾아 리턴
def get_max(*num):

    if len(num) == 0:
        return "입력받은 요소 X"
    else:
        max = 0
        for index in num:
            if max < index:
                max = index
        return max

print(get_max(1,10,15,4))
print(get_max())

# 디폴트 값 : 인수의 기본값을 지정할 때는 항상 마지막에 지정해야한다
def calc_step(start, endd, step=1):
    sum = 0
    for num in range(start, endd+1, step):
        sum += num
    return sum

print(calc_step(1,5))
print(calc_step(1,5,2))

# 키워드 인수
# 함수를 호출할 때는 함수를 정의할 때 지정한 순서대로 인수를 전달하며
# 호출문에서 전달한 순서대로 인수에 차례대로 대입
# 하지만 인수의 숫자가 많아질수록 인수의 순서를 잘 알기 어렵다
# 함수를 사용할 때 헷갈릴 가능성이 높아짐
# 파이썬에서는 순서와 무관하게 인수를 대입하는 방식을 제공하며
# 인수의 이름을 직접 지정하여 대입하는 방식을 허용

# 결과는 같음 
print(calc_step(1,10,1))
print(calc_step(start=1, endd=10, step=1))
print(calc_step(step=1, start=1, endd=10))
print(calc_step(endd=10, step=1, start=1))

```

#### Local / global Variable

```python
# 지역변수
# 지역변수는 함수 내부에서 선언되는 변수
# 지역 변수는 함수 안에서만 사용될 뿐 함수 블록을 나가면 사라짐

# 지역변수를 사용범위를 함수 내부로 제한하는 이유는
# 변수의 이름 충돌을 피하고 메모리를 절약하기 위함
# 스택영역은 회전이 빨라야한다

def intro():
    word = "하이!"
    print(word)

intro()
# print(word)  # NameError: name 'word' is not defined

# 전역변수
# 지역변수가 함수내부에서 사용되는 변수라면
# 전역변수는 프로그램 전체에서 사용되는 변수이다
# 즉, 지역변수는 함수내부에서만 사용가능하며
# 전역변수는 프로그램 전체에서 공용사용이 가능하다

sale_rate = 0.9

def calc_price(price):
    print(price)
    today_price = price * sale_rate
    print(today_price)

calc_price(1000)

# 위 예제에서는 sale함수내부에서 사용하는 변수 price는
# 의도상 새로운 price를 만들라는 의미가 아니라
# 기존의 전역변수 price를 사용하라는 의도이므로
# 이럴때는 컴퓨터에게 지역변수를 새로만들지말고
# 전역변수 price를 사용하라고 알려줘야 한다
# 이럴 때 함수 내부에서 global이라는 키워드를 사용하여
# 함수내부에서 사용하는 변수가 전역변수임을 알려준다

price = 1000
def sales():
    global price
    print("global : ", price)

sales()
print("local : ", price)
```

