# Database

## Oracle

> 데이터베이스 서버

https://www.oracle.com/downloads/

Database password (관리자 시스템 계정) : 1234

```관리자 계정으로 로그인한 sql
c:\Users\student>sqlplus system/1234
SQL*Plus: Release 11.2.0.2.0 Production on 월 12월 23 10:10:01 2019

Copyright (c) 1982, 2014, Oracle.  All rights reserved.


Connected to:
Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production

SQL> conn hr/hr
ERROR:
ORA-28000: the account is locked


Warning: You are no longer connected to ORACLE.

SQL> alter user hr identified by hr account unlock;

User altered.
```



> hr/hr : 샘플 계정, 인사관리 샘플 테이블이 구축 되어 있음
>
> alter : 관리자만 내릴 수 있는 명령



### TABLESPACE 생성

> DBF 파일이 있으면 백업 가능

```bash
create TABLESPACE mc
datafile 'C:\oraclexe\app\oracle\oradata\XE\mc.dbf'
size 10M
autoextend on next 1M maxsize UNLIMITED;

```

## 계정생성

```bash
SQL> create user test01 identified by 1234
  2  default TABLESPACE mc;

User created.

```

계정을 만들고, 그 계정의 DATA들이 저장될 dbf 파일을 mc로 설정