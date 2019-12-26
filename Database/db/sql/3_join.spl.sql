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
