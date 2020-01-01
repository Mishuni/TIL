def dfs():
    

import sys


sys.stdin = open("5188.txt", "r")

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    n = int(input())
    a = []
    print("#"+str(test_case)+" ")
    for i in range(n):
        a.append([])
        for j in range(n):
            a[i].append(int(input()))

