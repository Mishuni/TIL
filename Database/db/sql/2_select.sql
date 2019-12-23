conn SCOTT/TIGER

select * from emp;

desc emp
-- emp 테이블의 구조

select 가져올 열
from 테이블 목록;

select ename, sal, deptno
from emp;

-- 중복제거 
select distinct job from emp;

select ename, sal, sal*12
from emp;

select ename, sal, sal*12 as "연봉"
from emp;

select ename, jab; sal, comm
from emp;

-- to_char 함수 : 열 타입을 char 타입으로 변환
-- nvl 함수 : Null 값을 지정된 값으로 바꿔줌
select ename, job, sal, comm, sal+nvl(comm,0) as "총급여" 
from emp;

select empno, ename, nvl(to_char(mgr),'<<CEO>>') as "매니저 코드"
from emp;

-- '||' 문자열 연결 연산자
select ename||' : '||job
from emp;

-- dual : 가상의 테이블
select 20*20*4
from dual;

select sysdate
from dual;

select user
from dual;

-- COUNT 함수 : 갯수 구해주는 함수
select COUNT(distinct job) from emp;

-- row 제한을 거는 where 절
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

-- <> 는 != 와 같은 의미
select ename, hiredate from emp
where hiredate <> '81/05/01';

-- between and
select ename, sal
from emp
where sal >= 2450 AND sal <= 3000;

select ename, sal
from emp
where sal >= 2450 and sal <= 3000;

-- between 은 경계선이 추가됨, 작은 값이 반드시 앞에 와야 함
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

-- like 연산자
select *
from emp
where ename = 'A';

select *
from emp
where ename like '%A%';



select *
from emp
where ename like '%A__';

-- 81년도에 입사한 사원 목록

select *
from emp
where HIREDATE like '81%';