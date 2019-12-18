'''
문제 5.
표준 입력으로 물품 가격 여러 개가 문자열 한 줄로 입력되고, 각 가격은 ;(세미콜론)으로 구분되어 있습니다.
입력된 가격을 높은 가격순으로 출력하는 프로그램을 만드세요.
# 입력 예시: 300000;20000;10000
'''

prices = input('물품 가격을 입력하세요: ')
# 아래에 코드를 작성해 주세요.
money = prices.split(";")
boxes = []

for m in money:
    boxes.append(int(m))

#sort 는 원본 데이터를 정렬
#sorted는 원본 데이터는 정렬시키지 않는다.
boxes.sort(reverse=True)

for m in boxes:
    print(m)
