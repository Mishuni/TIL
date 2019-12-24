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

-- C:\oraclexe\app\oracle\product\11.2.0\server
-- ����Ŭ ���� ���α׷�

-- @c:\lib\scott.sql;
-- scott.sql ���Ͽ� �ִ� ����� �ѹ��� ������ ��

select * from emp;

-- C:\oraclexe\app\oracle\product\11.2.0\server\sqlplus\admin\glogin.sql
-- ���� �� ������ �ʱ⿡ �����ϴ� ��ɵ�?
���Ͽ��� 

set linesize 300;
set pagesize 20;
col ename for a10;
col job for a12;
col deptno for 9999;
col sal for 9999;
col mgr for 9999;
col comm for 9999;
col ename for a12;

-- �߰� >> tabel ������ ���̱� ���ؼ�

-- ����Ŭ ������ ����
purge recyclebin;
