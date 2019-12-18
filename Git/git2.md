# 원격 저장소 활용하기 (부제 : 집에서 복습하기)

## 준비사항

* 멀티캠퍼스 컴퓨터에 git 저장소 (데이터베이스 폴더)

```bash
$git init
$git 데이터베이스_기초.txt
$git commit -am "init"
```

* 집 컴퓨터에 git 저장소 (database)

```bash
$git clone {url}
```

* github 원격 저장소



## 시나리오

> 작업을 완료한 이후에는 항상 push
>
> 작업을 시작하기 전에는 항상 pull

1. 멀티캠퍼스에 도착

   ```bash
   $git init
   ```

2. 멀티캠퍼스에서의 작업

   ```bash
   #임의의 파일 수정/생성 후
   $git add .
   $git commit -m "message"
   ```

3.  멀티캠퍼스 퇴근

   ```bash
   $git push origin master
   ```

4. 집 도착

   ```bash
   $git pull origin master
   ```

5. 집 작업

   ```bash
   #임의의 파일 수정/생성 후
   $git commit -am "message"
   ```

6. 집 나가기

   ```bash
   $git push origin master
   ```

   

## 충돌 상황
만약에 원격저장소의 이력과 로컬 저장소의 이력이 다른 경우에는 아래의 메세지가 발생한다.

```bash
$ git push origin master
To https://github.com/Mishuni/database.git
 ! [rejected]        master -> master (fetch first)
error: failed to push some refs to 'https://github.com/Mishuni/database.git'
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.

# 원격저장소의 작업 내용 (work-commit)의 로컬 내용이 다르다.
# 원격 저장소의 변경 내용을 저장하고 다시 push
```

**이 메시지를 보게 된다면, 로컬에서 git log 원격저장소(github)의 커밋 이력들을 확인하고 다른 부분을 체크하자!!**

```bash
$git pull origin master
```

VIM 구간에서 빠져나올 때 -> ESC 클릭, : 입력, wq 입력, 엔터

통합 후,

```bash
$git push origin master
```

