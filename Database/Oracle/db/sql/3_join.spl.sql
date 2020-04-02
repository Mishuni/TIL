select * from dept;
select * from emp;

-- cartesian product
select ename, loc
from emp,dept;

-- 올바른 join 조건 부여
select ename, emp.deptno, loc
from emp , dept
where emp.deptno = dept.deptno;

-- 요소의 모호함 피하기
select ename, e.deptno, dname, loc
from emp e, dept d
where e.deptno = d.deptno;

-- sal > 2000 and deptno = 20 사원의 이름, sal, loc 출력
select ename, sal, loc
from emp e, dept d
where e.deptno = d.deptno and e.sal > 2000 and d.deptno = 10;

-- Ansi join -- 
-- Ansi ? 표준, DB 상관 없이 수행할 수 있도록
--   ,   ---> join
-- where ---> on

-- inner join
select ename, e.deptno, dname, loc
from emp e join dept d
on e.deptno = d.deptno
order by e.deptno;
-- 위와 같음 (inner 가 default)
select ename, e.deptno, dname, loc
from emp e inner join dept d
on e.deptno = d.deptno;
-- join 과 관련된 조건만 on 단락에 넣기
select ename, sal, loc
from emp e join dept d
on e.deptno = d.deptno 
where e.sal > 2000 and d.deptno = 10;

-- outer join : null 까지 다 출력
-- null 쪽에 + 마킹  
-- 배당안된 요소를 지니고 있는 table이 아닌 곳에 (+) 마킹
-- (+) 마킹 안 된 곳이 master
select ename, d.deptno, dname, loc
from emp e, dept d
where e.deptno(+) = d.deptno;
-- Ansi join (outer)
-- left, right 라는 명령으로 master 테이블을 정해줘야 함
-- 밑에 예는 right 쪽 테이블이 master인 outer join
-- master 가 주로 되어서, master의 배당안된 요소까지 출력
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

-- n 개의 테이블 join
-- 사원명, sal, dname, salgrade
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
-- 사원의 이름과 그 사원의 상사이름 출력
select e1.ename,e2.ename
from emp e1, emp e2
where e1.mgr = e2.empno(+);

select e1.ename, nvl(e2.ename,'CEO') as manager
from emp e1  left join emp e2
on e1.mgr = e2.empno;

-- 상사보다 봉급이 많은 사원
select e1.ename,e2.ename,
	   e1.sal as "member sal", e2.sal as "manager sal" 
from emp e1, emp e2
where e1.mgr = e2.empno(+) and e1.sal>e2.sal;

select e1.ename, nvl(e2.ename,'CEO') as manager, 
	   e1.sal as "member sal", e2.sal as "manager sal" 
from emp e1  left join emp e2
on e1.mgr = e2.empno
where e1.sal >= e2.sal;

-- 집계함수 multi row function
select ename, round(sal) from emp;
select avg(sal) from emp;

select round(avg(sal))||' 만원' as "평균급여" from emp;


select deptno, round(avg(sal))||' 만원' as "부서 별 평균급여" 
from emp
group by emp.deptno;

-- count함수는 null 값은 제외하고 계산
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

-- e.deptno 로 그룹핑 했지만 d.dname 이 불리지 않음
-- ORA-00979: not a GROUP BY expression
-- group by 절에 참여한 열만 select 절에 포함이 가능
select round(avg(sal),2) as average, 
	count(*), min(sal), max(sal), count(mgr), d.dname
from emp e, dept d
where e.deptno = d.deptno
group by e.deptno;
-- 해결법
select round(avg(sal),2) as average, 
	count(*), min(sal), max(sal), count(mgr), e.deptno, d.dname
from emp e, dept d
where e.deptno = d.deptno
group by e.deptno, d.dname;

-- Having 절 --
-- 부서이름별 평균급여가 2000이상인 목록 출력 (집계 함수 결과에 대한 조건문)
select round(avg(sal)) as average, e.deptno, d.dname
from emp e, dept d
where e.deptno = d.deptno
group by e.deptno, d.dname
having round(avg(sal))>2000;

-- subQuery --
-- Ford 보다 급여가 많은 사원 목록
select sal from emp where ename = 'FORD';
select * from emp where sal > 3000;

select *
from emp
where sal > (select sal from emp
			where ename = 'FORD');
			
-- 전체 평균 급여보다 적게 받는 사원 목록
select * from emp
where sal < (select avg(sal) from emp);
-- 제일 적은 급여 받는 사원 목록
select * from emp
where sal = (select min(sal) from emp);

-- 부서별 최고 급여를 받는 사원 목록
select * from emp
where sal in (select max(sal) from emp
			group by deptno)
order by deptno;
-- 위 쿼리는 부서가 같은지를 확인하지 않아 오류 가능성 존재
-- 해결법 1
select * from emp , (select max(sal) as sal, deptno from emp
			group by deptno) d
where emp.sal = d.sal and emp.deptno = d.deptno
order by emp.deptno;
-- 해결법 2
select * from emp
where (deptno,sal) in (select deptno,max(sal) from emp
			group by deptno)
order by deptno;

-- rownum --
select ename,job,sal from emp;
select rownum,ename,job,sal from emp;
-- rownum 이 섞임(급여 순으로 하고 싶은데)
select rownum,ename,job,sal from emp
order by sal;
-- 해결법
select rownum,ename,job,sal 
from (select ename,job,sal from emp order by sal desc);
-- 급여 top 3
select rownum,ename,job,sal 
from (select ename,job,sal from emp order by sal desc)
where rownum < 4;

select rownum,ename,job,sal 
from (select ename,job,sal from emp order by sal desc)
where rownum between 1 and 3;

-- oracle page 처리 --
-- rownum은 가상의 열로 1부터 시작해서 조건을 검사해야함
-- 밑에 쿼리의 결과는 없을 것 (에러)
select rownum,ename,job,sal 
from (select ename,job,sal from emp order by sal desc)
where rownum between 4 and 7;
-- 해결법
select *
from (
	select rownum row#,ename,job,sal 
	from (select ename,job,sal from emp order by sal desc)
)
where row# between 4 and 7;


