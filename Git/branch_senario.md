### 상황 1. fast-foward

> fast-foward는 feature 브랜치 생성된 이후 master 브랜치에 변경 사항이 없는 상황

1. feature/test branch 생성 및 이동

   ```bash
   $git checkout -b feature/test
   Switched to a new branch 'feature/test'
   ```

   

2. 작업 완료 후 commit

   ```bash
   $touch test.txt
   $git add test.txt
   $git commit -m "Complete test"
   [feature/test 6bbcb00] complete test
    1 file changed, 0 insertions(+), 0 deletions(-)
    create mode 100644 test.txt
   
   ```

3. master 이동

   ```bash
   $git checkout master
   Switched to branch 'master'
   M       README.md
   ```

   

4. master에 병합

   ```bash
   (master)$git merge feature/test
   Updating 060b29d..6bbcb00
   Fast-forward
    test.txt | 0
    1 file changed, 0 insertions(+), 0 deletions(-)
    create mode 100644 test.txt
   
   ```

   * master branch에서 merge를 실행해야 feature/test가 master에 합병된다.

5. 결과 -> fast-foward (단순히 HEAD를 이동)

   * master brach에서 더 수정된 것이 엎고, 앞으로 나아가기만 하면 될 때의 작동

6. branch 삭제
    ```bash
   $ git branch -d feature/test
   Deleted branch feature/test (was 6bbcb00).
   ```

------

### 상황 2. merge commit

> 서로 다른 이력(commit)을 병합(merge)하는 과정에서 다른 파일이 수정되어 있는 상황
>
> git이 auto merging을 진행하고, commit이 발생된다.

1. feature/signout branch 생성 및 이동

   ```bash
   # $git branch feature/signout
   # $git checkout feature/signout
   $git checkout -b feature/siout
   Switched to a new branch 'feature/singout'
   ```

   

2. 작업 완료 후 commit

   ```bash
   $touch signout.html
   $git add .
   $git commit -m "Add signout.html"
   ```

3. master 이동

   ```bash
   $git checkout master
   ```

4. *master에 추가 commit 이 발생시키기!!*

   - **다른 파일을 수정 혹은 생성하세요!**

   ```bash
   $touch hotfix.txt
   $git add
   $git commit -m "hotfix.txt on master"
   ```

   ```bash
   $git log --oneline
   f530874 (HEAD -> master) hotfix.txt on master
   6bbcb00 complete test
   060b29d Complete index page
   9cd802a Add README
   ```

5. master에 병합

   ```bash
   (master)$git merge feature/signout
   Merge made by the 'recursive' strategy.
    branch_senario.md | 97 ++++++++++++++++++++++++++++++++++++++++++++-----------
    1 file changed, 78 insertions(+), 19 deletions(-)
   
   ```

   

6. 결과 -> 자동으로 *merge commit 발생*

   - vim 편집기 화면이 나타납니다.
   - 자동으로 작성된 커밋 메시지를 확인하고, `esc`를 누른 후 `:wq`를 입력하여 저장 및 종료를 합니다.
     - `w` : write
     - `q` : quit
   - 커밋이 확인 해봅시다.

7. 그래프 확인하기

   ```bash
   $git log --oneline --graph
   *   1aeb1e2 (HEAD -> master) Merge branch 'feature/signout'
   |\
   | * 6f5f37e (feature/signout) Add signout.html
   * | f781871 ttt.txt on master
   |/
   *   3c722a7 Merge branch 'feature/board'
   |\
   | * f589415 Complete board.html
   * | 3ce8c83 Update README on master
   |/
   * bc57864 Add
   * 3f720f7 Update
   * f530874 hotfix.txt on master
   * 6bbcb00 complete test
   * 060b29d Complete index page
   * 9cd802a Add README
   ```

   

8. branch 삭제

   ```bash
   $git branch -d feature/signout
   ```

   

------

### 상황 3. merge commit 충돌

> 서로 다른 이력(commit)을 병합(merge)하는 과정에서 동일 파일이 수정되어 있는 상황
>
> git이 auto merging을 하지 못하고, 해당 파일의 위치에 라벨링을 해준다.
>
> 원하는 형태의 코드로 직접 수정을 하고 merge commit을 발생 시켜야 한다.

1. feature/board branch 생성 및 이동

   ```bash
   $git checkout -b "feature/board"
   ```

2. 작업 완료 후 commit

   ```bash
   $touch board.html
   $git add .
   $git commit -m "Complete board.html & updete README"
   $git lon --oneline
   f589415 (HEAD -> feature/board) Complete board.html
   bc57864 (master, feature/signout) Add
   3f720f7 Update
   f530874 hotfix.txt on master
   6bbcb00 complete test
   060b29d Complete index page
   9cd802a Add README
   ```

3. master 이동

   ```bash
   $git checkout master
   ```

4. *master에 추가 commit 이 발생시키기!!*

   - **동일 파일을 수정 혹은 생성하세요!**

   ```bash
   # (master)README.md 수정
   $git add .
   $git commit -m "Updete README on master"
   $git log --oneline
   3ce8c83 (HEAD -> master) Update README on master
   bc57864 Add
   3f720f7 Update
   f530874 hotfix.txt on master
   6bbcb00 complete test
   060b29d Complete index page
   9cd802a Add README
   ```

   

5. master에 병합

   ```bash
   $git merge feature/board
   CONFLICT (add/add): Merge conflict in branch_senario.md
   Auto-merging branch_senario.md
   Auto-merging README.md
   CONFLICT (content): Merge conflict in README.md
   Automatic merge failed; fix conflicts and then commit the result.
   $ git status
   On branch master
   You have unmerged paths.
     (fix conflicts and run "git commit")
     (use "git merge --abort" to abort the merge)
   
   Unmerged paths:
     (use "git add <file>..." to mark resolution)
           both modified:   README.md
           both added:      branch_senario.md
   
   no changes added to commit (use "git add" and/or "git commit -a")
   # both modified : 양 쪽에서 수정됨
   ```

   

6. 결과 -> *merge conflict발생*

7. 충돌 확인 및 해결

   ```
   <<<<<<< HEAD
   master에서 추가 함
   =======
   board 게시판 기능 구현 완료 
   >>>>>>> feature/board
   ```

   * HEAD(현재상황)에서 수정된 사항
   * feature/board에서 수정된 사항
   * 원하는 형태로 코드를 수정한다. (Visual Code로 보면 확인 가능)

8. merge commit 진행

   ```
   $ git add
   $ git commit
   ```

   - vim 편집기 화면이 나타납니다.
   - 자동으로 작성된 커밋 메시지를 확인하고, `esc`를 누른 후 `:wq`를 입력하여 저장 및 종료를 합니다.
     - `w` : write
     - `q` : quit
   - 커밋이 확인 해봅시다.

9. 그래프 확인하기

   ```bash
   $git log --oneline --graph
   *   3c722a7 (HEAD -> master) Merge branch 'feature/board'
   |\
   | * f589415 (feature/board) Complete board.html
   * | 3ce8c83 Update README on master
   |/
   * bc57864 Add
   * 3f720f7 Update
   * f530874 hotfix.txt on master
   * 6bbcb00 complete test
   * 060b29d Complete index page
   * 9cd802a Add README
   ```

   

10. branch 삭제

    ```bash
    $git branch -d feature/board
    ```

    