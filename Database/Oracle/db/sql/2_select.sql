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

-- 날짜 정보
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

-- A가 이름에 있으면
select *
from emp
where upper(ename) like upper('%A%');

-- A가 뒤에서 3번째 이면
select *
from emp
where ename like '%A__';

select *
from emp
where ename like '__A__';

-- 81년도에 입사한 사원 목록
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

-- where 절에서는 alliance(별칭 : 여기서는 total)사용 불가 
select ename,sal,comm,sal+nvl(comm,0) as total 
from emp
where sal+nvl(comm,0) >= 2000;

-- sort (default : asc 오름차순)
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

-- 정렬은 별칭 사용 가능
select ename,sal,comm,sal+nvl(comm,0) as total
from emp
where sal+nvl(comm,0) > 2000
order by  total;

select ename,sal,comm,sal+nvl(comm,0) as total
from emp
where sal+nvl(comm,0) > 2000
order by 4;

-- 단일행 함수 single row function
conn SCOTT/TIGER;
select dname,lower(dname),loc
from dept;

select round(44.55), round(44.55,1),trunc(44.55) from dual;
select sal, trunc(sal*0.03) as TAX
from emp;

select ename,hiredate,substr(hiredate,4,2) as month
from emp;

-- 사원의 입사 월이 12월인 정보 추출
select ename,hiredate,substr(hiredate,4,2) as month
from emp
where substr(hiredate,4,2) = 12;

select sysdate,substr(sysdate,4,2),sysdate + 2 from dual;

select sysdate,to_char(sysdate,'YYYY'),sysdate + 2 from dual;
select sysdate,to_char(sysdate,'day'),sysdate + 2 from dual;
select sysdate,to_char(sysdate,'mm'),sysdate + 2 from dual;
select sysdate,to_char(sysdate,'dd'),sysdate + 2 from dual;

-- emp 에서 사원 들의 입사월 정보를 출력
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

-- decode 함수 (부서마다 서비스 퍼센트가 다르게)
select ename, sal, deptno, decode(deptno, 10, round(sal*1.2),
										  20, round(sal*0.7),
										  sal) as "BONUS"
from emp
order by deptno;

