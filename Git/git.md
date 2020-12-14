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



### 2. add - staging area

> git으로 관리되는 파일들은 Working directory, Staging Area, Commit 단계를 거쳐 이력에 저장된다.

```bash
$git add a.txt #file name
$git add images/ #folder
$git add . # all files in current directory
```

* add 전 상태

  ```bash
  student@M160219 MINGW64 ~/Desktop/til (master)
  $ git status
  On branch master
  
  No commits yet
  
  Untracked files:
    (use "git add <file>..." to include in what will be committed)
          abc.jpg
          git.md
          image/
          markdown.md
  
  nothing added to commit but untracked files present (use "git add" to track)
  
  ```

* add 후 상태

  ```bash
  $ git status
  On branch master
  
  No commits yet
  
  Changes to be committed:
    (use "git rm --cached <file>..." to unstage)
          new file:   abc.jpg
          new file:   git.md
          new file:   image/abc.jpg
          new file:   image/image-20191216141838332.png
          new file:   markdown.md
  
  ```

### 3. commit

>  커밋은 코드에 이력을 남기는 과정이다.

```bash
$ git commit -m "커밋 메세지"
```

* 커밋 메세지는 항상 해당 이력에 대한 정보를 담을 수 있도록 작성하는 것이 좋다.

* 일관적인 커밋 메세지를 작성하는 습관을 들이자.

* 이력 확인을 위해서는 아래의 명령어를 활용한다.

  ```bash
  $ git log
  commit 9ec9c576f0dac25da6b89932201715b6b0369f38 (HEAD -> master)
  Author: Mishuni <miseon543@gmail.com>
  Date:   Mon Dec 16 14:26:11 2019 +0900
  
      modification
  ```

  ```bash
  $ git status
  On branch master
  Changes not staged for commit:
    (use "git add <file>..." to update what will be committed)
    (use "git restore <file>..." to discard changes in working directory)
          modified:   git.md
  
  no changes added to commit (use "git add" and/or "git commit -a")
  ```

  

> 항상 status 명령어를 통해 git의 상태를 확인하자 (commit 이후에는 log 명령어를 통해 이력들을 확인하자)



## 원격 저장소 활용하기

> 원격 저장소 (remote repository)를 제공하는 서비스는 다양하게 존재한다. 
>
> 본 프로그램에서는 github 기준으로 진행한다.

### 0. 준비하기

* Github에서 저장소(repository) 생성

### 1.원격 저장소 설정

```bash
$ git remote add origin {github url}
```

* {github url}부분에서는 원격저장소 url을 작성한다.

* 원격 저장소(remote)로 {github url} 을 origin 이라는 이름으로 추가(add)하는 명령어이다.

* 원격 저장소 목록을 보기 위해서는 아래의 명령어를 활용한다.

  ```bash
  $git remote -v 
  origin  https://github.com/Mishuni/refactored-bassoon.git (fetch)
  origin  https://github.com/Mishuni/refactored-bassoon.git (push)
  ```

  

### 2. push

```bash
$git push origin master
```

* 설정된 원격 저장소(origin)으로 push 

폴더의 내용을 수정 및 삭세, 생성 등을 하게 된다면, add, commit 명령어를 통해서 이려을 저장하고 push 명령어를 통해 업로드 한다.



### 3. pull

```bash
$git pull origin master
```



## Branch

### 1. create branch

```sh
$ git checkout -b [branch name]
$ git push origin [branch name]
```

### 2. delete branch

```sh
$ git checkout [other branch name]
$ git branch --delete [branch name]
# force
$ git branch -D [branch name]
$ git push origin :[branch name]
```

### 3. checkout branch

```sh
$ git checkout [branch name]
```

