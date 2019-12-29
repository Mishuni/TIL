--\\70.12.60.90
-- sqlplus system/1234

alter user hr identified by hr account unlock;

conn hr/hr
 sqlplus hr/hr

-- �����ϴ� ��ü ���̺� ��� ����
select * from tab;

-- ������ ��ɾ� : exit

-- TABLESPACE ����� : ���̺��� ����Ǵ� ��
create TABLESPACE mc
datafile 'C:\oraclexe\app\oracle\oradata\XE\mc.dbf'
size 10M
autoextend on next 1M maxsize UNLIMITED;

-- ����� ���� ��
drop TABLESPACE mc INCLUDING CONTENTS AND Datafiles;

-- ���� ����, ���� 
create user test01 identified by 1234
default TABLESPACE mc;

-- ���� �ο�
grant connect,resource,dba to test01;
-- ���� ����
revoke dba from test01;
revoke (������ ����) from (���� ���� ����);
-- ���� ����
drop user test01 cascade;

-- SCOTT/TIGER ���� ���� 
create user SCOTT identified by TIGER
default TABLESPACE mc;
-- ���� �ο�
grant connect,resource,dba to SCOTT;


-- C:\oraclexe\app\oracle\product\11.2.0\server
-- ����Ŭ ���� ���α׷�

-- @c:\lib\scott.sql;
-- scott.sql ���Ͽ� �ִ� ����� �ѹ��� ������ ��
select * from emp;

-- C:\oraclexe\app\oracle\product\11.2.0\server\sqlplus\admin\glogin.sql
-- ���� �� ������ �ʱ⿡ �����ϴ� ��ɵ�?
���Ͽ��� 

set linesize 300;
set pagesize 30;
col ename for a10;
col job for a12;
col deptno for 9999;
col sal for 9999;
col mgr for 9999;
col comm for 9999;
col EMPLOYEE_ID for 9999;
col FIRST_NAME for a10;
col LAST_NAME  for a10;
col EMAIL  for a10;
col JOB_ID for a12;
col PHONE_NUMBER for 100000;
col SALARY for 9999;
col COMMISSION_PCT for 9999;
col MANAGER_ID for 9999;
col DEPARTMENT_ID for 9999;

-- �߰� >> tabel ������ ���̱� ���ؼ�

-- ����Ŭ ������ ����
purge recyclebin;
