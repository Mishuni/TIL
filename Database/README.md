# Database

## Oracle

### 0. 환경설정

> 데이터베이스 서버

1. 필요 프로그램 다운로드 : [오라클](https://www.oracle.com/downloads/) 페이지 접속

2. downloads > Database > Database 18c Express Edition (교육용) > 설치 

3. Developer Tools > SQL Devleoper > windows 64-bit with JDK 8 included 설치

4. 환경 변수 설정 : 내 PC > 속성 > 고급 시스템 설정 > 고급 > 환경 변수 > 시스템 변수 > Path > 편집

5. C:\oraclexe\app\oracle\product\11.2.0\server\bin 추가

6. sqldeveloper C:\ 에 압축 풀기  

   'sqldeveloper' 는 sql 문의 편집을 쉽게하고, table 모델도를 제공



### 1. 기본 시작

1. 관리자 시스템 계정으로 sql 실행 

   Database password (관리자 시스템 계정) : `1234` 

```관리자 계정으로 로그인한 sql
c:\Users\student>sqlplus system/1234
SQL*Plus: Release 11.2.0.2.0 Production on 월 12월 23 10:10:01 2019

Copyright (c) 1982, 2014, Oracle.  All rights reserved.


Connected to:
Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production

```

2. 다른 계정으로 바꾸기

   ```sql
   SQL> conn hr/hr
   ERROR:
   ORA-28000: the account is locked
   Warning: You are no longer connected to ORACLE.
   
   SQL> alter user hr identified by hr account unlock;
   User altered.
   
   SQL> conn hr/hr
   ```

> hr/hr : 샘플 계정, 인사관리 샘플 테이블이 구축 되어 있음
>
> alter : 관리자만 내릴 수 있는 명령



### 2. TABLESPACE 생성

> DBF 파일이 있으면 백업 가능
>
> 기본적으로 저장되는 곳은 system

```bash
create TABLESPACE mc
datafile 'C:\oraclexe\app\oracle\oradata\XE\mc.dbf'
size 10M
autoextend on next 1M maxsize UNLIMITED;

```

### 3. 계정생성

```bash
SQL> create user test01 identified by 1234
  2  default TABLESPACE mc;

User created.

```

계정을 만들고, 그 계정의 data들이 저장될 dbf 파일을 mc로 설정


### 기타 참고 사이트

[w3schools](https://www.w3schools.com/)