--\\70.12.60.90
-- sqlplus system/1234

alter user hr identified by hr account unlock;

conn hr/hr
 sqlplus hr/hr

-- 존재하는 전체 테이블 목록 보기
select * from tab;

-- 나가기 명령어 : exit

-- TABLESPACE 만들기 : 테이블이 저장되는 곳
create TABLESPACE mc
datafile 'C:\oraclexe\app\oracle\oradata\XE\mc.dbf'
size 10M
autoextend on next 1M maxsize UNLIMITED;

-- 지우고 싶을 때
drop TABLESPACE mc INCLUDING CONTENTS AND Datafiles;

-- 계정 생성, 삭제 
create user test01 identified by 1234
default TABLESPACE mc;

-- 권한 부여
grant connect,resource,dba to test01;
-- 권한 제거
revoke dba from test01;
revoke (제거할 권한) from (제거 당할 계정);
-- 계정 제거
drop user test01 cascade;

-- SCOTT/TIGER 계정 생성 
create user SCOTT identified by TIGER
default TABLESPACE mc;

-- C:\oraclexe\app\oracle\product\11.2.0\server
-- 오라클 서버 프로그램

-- @c:\lib\scott.sql;
-- scott.sql 파일에 있는 명령이 한번에 수행이 됨

select * from emp;

-- C:\oraclexe\app\oracle\product\11.2.0\server\sqlplus\admin\glogin.sql
-- 계정 들어갈 때마다 초기에 시작하는 명령들?
파일에서 

set linesize 300;
set pagesize 20;
col ename for a10;
col job for a12;
col deptno for 9999;
col sal for 9999;
col mgr for 9999;
col comm for 9999;
col ename for a12;

-- 추가 >> tabel 가독성 높이기 위해서

-- 오라클 휴지통 비우기
purge recyclebin;
