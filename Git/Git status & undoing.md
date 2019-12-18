# Git status & undoing

## 1. commit

```
# WD O, staging area X
$ git commit
# commit 할 것이 없지만, -> staging area가 비어있음.
# untracked file이 있다. -> git commit 이력에 담기지 않은 파일은 있음.
nothing added to commit but untracked files present

# WD X, staging area X
$ git commit
# 어떠한 변경 사항도 없음.
nothing to commit
```

### status

1. 새로 파일 생성 한 경우

   ```
   $ git status
   On branch master
   
   No commits yet
   
   # commit 이력에 담긴 적 없는 파일들
   Untracked files:
   # 커밋 될 목록(staging area)에 추가하려면, git add <file>
     (use "git add <file>..." to include in what will be committed)
           a.txt
   
   nothing added to commit but untracked files present (use "git add" to track)
   ```

2. `add` 한 이후

   ```
   $ git add a.txt
   $ git status
   On branch master
   
   No commits yet
   # 커밋될 변경사항들(changes)
   Changes to be committed:
     (use "git rm --cached <file>..." to unstage)
           new file:   a.txt
   ```

### commit 메시지 작성하기

> 부제 : vim 활용 기초

```
$ git commit
# Please enter the commit message for your changes. Lines starting
# with '#' will be ignored, and an empty message aborts the commit.
#
# On branch master
#
# Initial commit
#
# Changes to be committed:
#       new file:   a.txt
#
```

- 편집(입력)모드 :

   

  ```
  i
  ```

  - 문서 편집 가능

- 명령 모드 :

   

  ```
  esc
  ```

  - `dd` : 해당 줄 삭제

  - ```
    :wq
    ```

     

    : 저장 및 종료

    - `w` : write
    - `q` : quit

  - ```
    :q!
    ```

     

    : 강제 종료

    - `q` : quit
    - `!` : 강제

```
$ git commit -m 'commit message'
```

- 커밋 메시지는 항상 해당 작업 이력을 나타낼 수 있도록 작성한다.
- 일관적인 포맷으로 작성하려고 노력한다.

### log

> 커밋은 해시 값(hash value)에 의해서 구분된다.
>
> SHA-1 해시 알고리즘을 사용하여 표현한다.

```
$ git log
commit f9b084477904001f48c56d2c0d89fc04a92fe17a (HEAD -> master)
Author: edutak <edutak.ssafy@gmail.com>
Date:   Wed Dec 18 09:44:00 2019 +0900

    Add a.txt

    * a.txt 내용 추가
$ git log --oneline
f9b0844 (HEAD -> master) Add a.txt
$ git log -1
$ git log --oneline --graph
$ git log -1 --oneline
```

### commit undoing

1. 커밋 메시지 수정

   ```
   $ git commit --amend
   ```

   커밋 메시지 수정하는 경우 해시 값이 변경되므로, 다른 이력으로 관리가 된다.

   **따라서, 공개된 저장소(원격 저장소)에 이미 push 된 경우 절대 수정해서는 안된다.**

2. 특정 파일 추가하기

   - `c.txt` 파일을 같이 커밋을 하려고 했는데, `add` 를 하지 않고 커밋 해버렸다.

   ```
   $ git add c.txt
   $ git commit --amend
   ```

## Staging area

커밋 이력이 있는 파일 수정하는 경우

```
$ git status
On branch master
# 변경 사항인데(WD O), staging area X
Changes not staged for commit:
# git add로  staging area로 보낼 수 있다.
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        modified:   a.txt

no changes added to commit (use "git add" and/or "git commit -a")

$ git add a.txt
$ git status
On branch master
Changes to be committed:
# unstage 하기 위해서(staging area에서 제외하기 위해서)
  (use "git restore --staged <file>..." to unstage)
        modified:   a.txt
```

### `add` 취소하기

```
$ git restore --staged <file>
```

- 구버전의 git 에서는 아래의 명령어를 사용해야 한다.

  ```
  $ git reset HEAD <file>
  ```

## WD 변화 삭제하기

> git에서는 모든 commit 시점으로 되돌릴 수 는 있다.
>
> 다만, WD 삭제하는 것은 되돌릴 수가 없다.

```
$ git restore <file>
```

- 구버전 git 에서는 아래의 명령어를 사용해야 한다.

  ```
  $ git checkout -- <file>
  ```