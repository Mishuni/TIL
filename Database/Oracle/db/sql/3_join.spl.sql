select * from dept;
select * from emp;

-- cartesian product
select ename, loc
from emp,dept;

-- �ùٸ� join ���� �ο�
select ename, emp.deptno, loc
from emp , dept
where emp.deptno = dept.deptno;

-- ����� ��ȣ�� ���ϱ�
select ename, e.deptno, dname, loc
from emp e, dept d
where e.deptno = d.deptno;

-- sal > 2000 and deptno = 20 ����� �̸�, sal, loc ���
select ename, sal, loc
from emp e, dept d
where e.deptno = d.deptno and e.sal > 2000 and d.deptno = 10;

-- Ansi join -- 
-- Ansi ? ǥ��, DB ��� ���� ������ �� �ֵ���
--   ,   ---> join
-- where ---> on

-- inner join
select ename, e.deptno, dname, loc
from emp e join dept d
on e.deptno = d.deptno
order by e.deptno;
-- ���� ���� (inner �� default)
select ename, e.deptno, dname, loc
from emp e inner join dept d
on e.deptno = d.deptno;
-- join �� ���õ� ���Ǹ� on �ܶ��� �ֱ�
select ename, sal, loc
from emp e join dept d
on e.deptno = d.deptno 
where e.sal > 2000 and d.deptno = 10;

-- outer join : null ���� �� ���
-- null �ʿ� + ��ŷ  
-- ���ȵ� ��Ҹ� ���ϰ� �ִ� table�� �ƴ� ���� (+) ��ŷ
-- (+) ��ŷ �� �� ���� master
select ename, d.deptno, dname, loc
from emp e, dept d
where e.deptno(+) = d.deptno;
-- Ansi join (outer)
-- left, right ��� ������� master ���̺��� ������� ��
-- �ؿ� ���� right �� ���̺��� master�� outer join
-- master �� �ַ� �Ǿ, master�� ���ȵ� ��ұ��� ���
select ename, d.deptno, dname, loc
from emp e right outer join dept d
on e.deptno = d.deptno;

-- non equi join --
select * from salgrade;
-- oracle join
select ename,sal,grade
from emp e,salgrade s
where sal between s.losal and s.hisal;
-- ansi join
select ename,sal,grade
from emp e join salgrade s
on sal between s.losal and s.hisal;

-- n ���� ���̺� join
-- �����, sal, dname, salgrade
select ename,sal,d.dname,s.grade
from emp e, dept d, salgrade s
where e.deptno = d.deptno and e.sal between s.losal and s.hisal;
-- ansi join
select ename,sal,d.dname,s.grade
from emp e 
	join dept d
	on e.deptno = d.deptno 
	join salgrade s
	on e.sal between s.losal and s.hisal;
-- sal > 1500
select ename,sal,d.dname,s.grade
from emp e 
	join dept d
	on e.deptno = d.deptno 
	join salgrade s
	on e.sal between s.losal and s.hisal
where sal>1500;

-- oracle self join --
-- ����� �̸��� �� ����� ����̸� ���
select e1.ename,e2.ename
from emp e1, emp e2
where e1.mgr = e2.empno(+);

select e1.ename, nvl(e2.ename,'CEO') as manager
from emp e1  left join emp e2
on e1.mgr = e2.empno;

-- ��纸�� ������ ���� ���
select e1.ename,e2.ename,
	   e1.sal as "member sal", e2.sal as "manager sal" 
from emp e1, emp e2
where e1.mgr = e2.empno(+) and e1.sal>e2.sal;

select e1.ename, nvl(e2.ename,'CEO') as manager, 
	   e1.sal as "member sal", e2.sal as "manager sal" 
from emp e1  left join emp e2
on e1.mgr = e2.empno
where e1.sal >= e2.sal;

-- �����Լ� multi row function
select ename, round(sal) from emp;
select avg(sal) from emp;

select round(avg(sal))||' ����' as "��ձ޿�" from emp;


select deptno, round(avg(sal))||' ����' as "�μ� �� ��ձ޿�" 
from emp
group by emp.deptno;

-- count�Լ��� null ���� �����ϰ� ���
select avg(sal), count(*), min(sal), max(sal), count(mgr)
from emp;

select avg(sal), count(*), min(sal), max(sal), count(mgr)
from emp
where deptno = 10;

select round(avg(sal),2) as average, 
	count(*), min(sal), max(sal), count(mgr), deptno
from emp
group by deptno
order by deptno

select round(avg(sal),2) as average, 
	count(*), min(sal), max(sal), count(mgr), d.dname
from emp e, dept d
where e.deptno = d.deptno
group by d.dname;

-- e.deptno �� �׷��� ������ d.dname �� �Ҹ��� ����
-- ORA-00979: not a GROUP BY expression
-- group by ���� ������ ���� select ���� ������ ����
select round(avg(sal),2) as average, 
	count(*), min(sal), max(sal), count(mgr), d.dname
from emp e, dept d
where e.deptno = d.deptno
group by e.deptno;
-- �ذ��
select round(avg(sal),2) as average, 
	count(*), min(sal), max(sal), count(mgr), e.deptno, d.dname
from emp e, dept d
where e.deptno = d.deptno
group by e.deptno, d.dname;

-- Having �� --
-- �μ��̸��� ��ձ޿��� 2000�̻��� ��� ��� (���� �Լ� ����� ���� ���ǹ�)
select round(avg(sal)) as average, e.deptno, d.dname
from emp e, dept d
where e.deptno = d.deptno
group by e.deptno, d.dname
having round(avg(sal))>2000;

-- subQuery --
-- Ford ���� �޿��� ���� ��� ���
select sal from emp where ename = 'FORD';
select * from emp where sal > 3000;

select *
from emp
where sal > (select sal from emp
			where ename = 'FORD');
			
-- ��ü ��� �޿����� ���� �޴� ��� ���
select * from emp
where sal < (select avg(sal) from emp);
-- ���� ���� �޿� �޴� ��� ���
select * from emp
where sal = (select min(sal) from emp);

-- �μ��� �ְ� �޿��� �޴� ��� ���
select * from emp
where sal in (select max(sal) from emp
			group by deptno)
order by deptno;
-- �� ������ �μ��� �������� Ȯ������ �ʾ� ���� ���ɼ� ����
-- �ذ�� 1
select * from emp , (select max(sal) as sal, deptno from emp
			group by deptno) d
where emp.sal = d.sal and emp.deptno = d.deptno
order by emp.deptno;
-- �ذ�� 2
select * from emp
where (deptno,sal) in (select deptno,max(sal) from emp
			group by deptno)
order by deptno;

-- rownum --
select ename,job,sal from emp;
select rownum,ename,job,sal from emp;
-- rownum �� ����(�޿� ������ �ϰ� ������)
select rownum,ename,job,sal from emp
order by sal;
-- �ذ��
select rownum,ename,job,sal 
from (select ename,job,sal from emp order by sal desc);
-- �޿� top 3
select rownum,ename,job,sal 
from (select ename,job,sal from emp order by sal desc)
where rownum < 4;

select rownum,ename,job,sal 
from (select ename,job,sal from emp order by sal desc)
where rownum between 1 and 3;

-- oracle page ó�� --
-- rownum�� ������ ���� 1���� �����ؼ� ������ �˻��ؾ���
-- �ؿ� ������ ����� ���� �� (����)
select rownum,ename,job,sal 
from (select ename,job,sal from emp order by sal desc)
where rownum between 4 and 7;
-- �ذ��
select *
from (
	select rownum row#,ename,job,sal 
	from (select ename,job,sal from emp order by sal desc)
)
where row# between 4 and 7;


