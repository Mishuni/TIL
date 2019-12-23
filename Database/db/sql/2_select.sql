conn SCOTT/TIGER

select * from emp;

desc emp
-- emp ���̺��� ����

select ������ ��
from ���̺� ���;

select ename, sal, deptno
from emp;

-- �ߺ����� 
select distinct job from emp;

select ename, sal, sal*12
from emp;

select ename, sal, sal*12 as "����"
from emp;

select ename, jab; sal, comm
from emp;

-- to_char �Լ� : �� Ÿ���� char Ÿ������ ��ȯ
-- nvl �Լ� : Null ���� ������ ������ �ٲ���
select ename, job, sal, comm, sal+nvl(comm,0) as "�ѱ޿�" 
from emp;

select empno, ename, nvl(to_char(mgr),'<<CEO>>') as "�Ŵ��� �ڵ�"
from emp;

-- '||' ���ڿ� ���� ������
select ename||' : '||job
from emp;

-- dual : ������ ���̺�
select 20*20*4
from dual;

select sysdate
from dual;

select user
from dual;

-- COUNT �Լ� : ���� �����ִ� �Լ�
select COUNT(distinct job) from emp;

-- row ������ �Ŵ� where ��
select ename,job,deptno
from emp
where deptno = 10;

select ename,job,deptno
from emp
where upper(job) = upper('Manager');

select ename,job,deptno
from emp
where lower(job) = lower('Manager');

select ename, hiredate from emp
where hiredate > '82/01/01';

select ename, hiredate from emp
where hiredate = '81/05/01';

-- <> �� != �� ���� �ǹ�
select ename, hiredate from emp
where hiredate <> '81/05/01';

-- between and
select ename, sal
from emp
where sal >= 2450 AND sal <= 3000;

select ename, sal
from emp
where sal >= 2450 and sal <= 3000;

-- between �� ��輱�� �߰���, ���� ���� �ݵ�� �տ� �;� ��
select ename, sal
from emp
where sal between 2450 and 3000;

select ename, job, deptno
from emp
where deptno = 10 or deptno = 20 ;

select ename, job, deptno
from emp
where deptno in(10,20) ;

select *
from dept
where (deptno,loc) in((20,'DALLAS'),(30,'CHICAGO'));

-- like ������
select *
from emp
where ename = 'A';

select *
from emp
where ename like '%A%';



select *
from emp
where ename like '%A__';

-- 81�⵵�� �Ի��� ��� ���

select *
from emp
where HIREDATE like '81%';