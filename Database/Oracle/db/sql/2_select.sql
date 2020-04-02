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

-- ��¥ ����
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

-- A�� �̸��� ������
select *
from emp
where upper(ename) like upper('%A%');

-- A�� �ڿ��� 3��° �̸�
select *
from emp
where ename like '%A__';

select *
from emp
where ename like '__A__';

-- 81�⵵�� �Ի��� ��� ���
select *
from emp
where HIREDATE like '81%';
 
select *
from emp
where SUBSTR(HIREDATE,4,2) = '02';

select *
from emp
where HIREDATE LIKE ('%05___');

-- where null processing
select * from emp
where mgr is not null;

select * from emp
where comm is null;

select ename,sal,comm,sal+nvl(comm,0) as total 
from emp
where comm is not null;

-- where �������� alliance(��Ī : ���⼭�� total)��� �Ұ� 
select ename,sal,comm,sal+nvl(comm,0) as total 
from emp
where sal+nvl(comm,0) >= 2000;

-- sort (default : asc ��������)
select ename,sal,comm,sal+nvl(comm,0) as total
from emp
order by sal;

select ename,sal,comm,sal+nvl(comm,0) as total
from emp
order by sal desc;

select ename,sal,comm,sal+nvl(comm,0) as total
from emp
order by sal asc , comm desc;

select ename,sal,sal+nvl(comm,0) as total
from emp
order by sal asc , comm ;

select ename,sal,sal+nvl(comm,0) as total
from emp
where comm is not null
order by sal asc , comm ;

select ename,sal,comm,sal+nvl(comm,0) as total
from emp
where comm is not null
order by  comm ;

-- ������ ��Ī ��� ����
select ename,sal,comm,sal+nvl(comm,0) as total
from emp
where sal+nvl(comm,0) > 2000
order by  total;

select ename,sal,comm,sal+nvl(comm,0) as total
from emp
where sal+nvl(comm,0) > 2000
order by 4;

-- ������ �Լ� single row function
conn SCOTT/TIGER;
select dname,lower(dname),loc
from dept;

select round(44.55), round(44.55,1),trunc(44.55) from dual;
select sal, trunc(sal*0.03) as TAX
from emp;

select ename,hiredate,substr(hiredate,4,2) as month
from emp;

-- ����� �Ի� ���� 12���� ���� ����
select ename,hiredate,substr(hiredate,4,2) as month
from emp
where substr(hiredate,4,2) = 12;

select sysdate,substr(sysdate,4,2),sysdate + 2 from dual;

select sysdate,to_char(sysdate,'YYYY'),sysdate + 2 from dual;
select sysdate,to_char(sysdate,'day'),sysdate + 2 from dual;
select sysdate,to_char(sysdate,'mm'),sysdate + 2 from dual;
select sysdate,to_char(sysdate,'dd'),sysdate + 2 from dual;

-- emp ���� ��� ���� �Ի�� ������ ���
select ename,to_char(hiredate,'mm') as "month", 
to_char(hiredate,'day') as "day" 
from emp
order by "month";

select sysdate , to_date('2019/12/24') from dual;
select sysdate , to_date('2019-12-24') from dual;
select sysdate , to_date('2019 12 24') from dual;
select sysdate , to_date('19 12 24') from dual;
select sysdate , to_date('49 12 24') from dual;
select sysdate , to_date('12/24/19','mm/dd/yy') from dual;
select sysdate , to_date('2/24/20','mm/dd/yy') from dual;

-- decode �Լ� (�μ����� ���� �ۼ�Ʈ�� �ٸ���)
select ename, sal, deptno, decode(deptno, 10, round(sal*1.2),
										  20, round(sal*0.7),
										  sal) as "BONUS"
from emp
order by deptno;

