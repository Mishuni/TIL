# Git Basic

## 0. 준비 사항



* [Git Bash]([https://gitforwindows.org/])
  * git을 활용하기 위한 CLI를 제공한다.
  
  * source tree, github dexktop 등을 통해 gui환경에서도 활용 가능하다.
  
    

## 1.로컬 저장소 활용하기
### 1. 저장소 초기화

```bash
$ git init
Initialized empty Git repository in C:/Users/student/Desktop/til/.git/

```

* 저장소(Repository)를 초기화 하게 되면, `.git` 폴더가 해당 디렉토리에 숨겨진 폴더로 생성된다.
* `bash`창에서는 (master)라고 표기된다.
  * 현재 브랜치가 master라는 것을 의미한다.



## 2. add - staging area

git으로 관리되는 파일들은 Working directory, Staging Area, Commit 단계를 거쳐 이력에 저장된다.

```bash
$git add a.txt #file name
$git add images/ #folder
$git add .* # all files in current directory
```

