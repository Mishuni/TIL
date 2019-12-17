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

   