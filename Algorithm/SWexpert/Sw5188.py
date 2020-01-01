
def dfs(y,x):
    global sub_result, result
    
    if result < sub_result:
        return
    if y == n-1 and x == n-1:
        result = sub_result
        return
    for d in range(2):
        y_tmp = y + dy[d]
        x_tmp = x + dx[d]
        if(y_tmp<n and x_tmp<n) and ((y_tmp,x_tmp)not in visited):
            visited.append((y_tmp,x_tmp))
            sub_result += number[y_tmp][x_tmp]
            dfs(y_tmp,x_tmp)
            visited.remove((y_tmp,x_tmp))
            sub_result -= number[y_tmp][x_tmp]
    
        
#sys.stdin = open("5188.txt", "r")

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    n = int(input())
    number = [list(map(int,input().split())) for _ in range(n)]
    visited = []
    
    dy = [0,1]
    dx = [1,0]
    sub_result , result = number[0][0], 9876521
    dfs(0,0)
    print('#%d %d'%(test_case,result))

