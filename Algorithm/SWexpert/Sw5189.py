
def dfs(j):
    global sub_result, result
    if result < sub_result:
        return
    if len(visited)==n :
        sub_result += number[j][0]
        result = sub_result
        return
    for d in range(1,n):
        if d not in visited:
            visited.append(d)
            sub_result += number[j][d]
            dfs(d)
            visited.remove(d)
            sub_result -= number[j][d] 


T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    n = int(input())
    number = [list(map(int,input().split())) for _ in range(n)]
    visited = []
    visited.append(0)
    sub_result , result = 0, 9876521
    for d in range(1,n):
        sub_result += number[0][d]
        dfs(d)
        sub_result -= number[0][d]
    
    print('#%d %d'%(test_case,result))

