'''
String Control

1. 글자 합체
string + string

2. 글자 삽입(수술)

3. 글자 자르기

'''

#1. 글자 합체

s1 = "happy " + "hacking"
print(s1)

#2. 글자 삽입

name = "sha"
age = 24
text = "제 이름은 {} 입니다. 나이는 {} 입니다.".format(name,age)
print(text)
#f string
f_text = f"제 이름은 {name} 입니다. 나이는 {age} 입니다."
print(f_text)

#3. 글자 자르기

text_name = text[:15]
print(text_name)

text_age = text[15:]
print(text_age)

text_split = text.split()
print(text_split)