select * from employees;
select * from tab;
select * from DEPARTMENTS;
select * from jobs;

-- 문제1. 각 사원(employee)에 대해서 사번(employee_id), 이름(first_name), 
-- 부서명(department_name), 매니저(manager)의 이름(first_name)을 조회하시오.
select e1.employee_id, e1.first_name, d.department_name,
	   e2.first_name
from employees e1,employees e2, departments d
where e1.manager_id = e2.employee_id(+)
	  and e1.department_id = d.department_id
order by e1.employee_id;
	  
-- 문제2. 자신의 매니저보다 
-- 연봉(salary)을 많이 받는 직원들의 성(last_name)과 연봉(salary)을 출력하시오. 
select e1.last_name, e1.salary
from employees e1, employees e2
where e1.manager_id = e2.employee_id 
	  and e1.salary > e2.salary;
	  
-- 문제3. 업무명(job_title)이 
-- ‘Sales Representative’인 직원 중에서 연봉(salary)이 9,000 이상, 
-- 10,000 이하인 직원들의 이름(first_name), 
-- 성(last_name)과 연봉(salary)을 출력하시오.
select e.first_name , e.last_name, e.salary
from employees e, jobs j
where e.job_id = j.job_id 
	and lower(j.job_title) = lower('Sales Representative')
	and (e.salary between 9000 and 10000) ;
	
-- 문제4. Query 문장을 작성하십시오. 
-- 자신의 매니저보다 채용일(hire_date)이 빠른 사원의 사번(employee_id), 
-- 성(last_name)과 채용일(hire_date)을 조회하라.
select e1.employee_id, e1.last_name,
to_char(e1.hire_date,'yyyy-mm-dd') as hiredate
from employees e1, employees e2
where e1.manager_id = e2.employee_id and e1.hire_date<e2.hire_date;

-- 문제5. 08년도에 입사한 직원의 이름(first_name), 
-- 입사일(hire_date), 관리자사번(employee_id), 
-- 관리자 이름(first_name)을 조회합니다. 
-- 단, 관리자가 없는 사원정보도 출력결과에 포함되어야 합니다.
select e1.first_name, e1.hire_date, e1.employee_id, e2.first_name
from employees e1, employees e2
where e1.manager_id = e2.employee_id(+)
and to_char(e1.hire_date,'yy') = 8;

-- 문제6. ‘Sales’부서에 속한 직원의 이름(first_name), 
-- 급여(salary), 부서이름(department_name)을 조회하시오. 
select e1.first_name, e1.salary, d.department_name
from employees e1, DEPARTMENTS d
where e1.department_id = d.department_id 
	and upper(d.department_name) = upper('Sales');
	
select e1.first_name, e1.salary, d.department_name
from employees e1 join departments d
	on e1.department_id = d.department_id
where d.department_name = 'Sales';

-- 문제7. 월 별 입사자 수를 조회하는 SQL 쿼리문을 작성하세요. 월 순으로 정렬하세요. 
-- 출력: 월 | 입사자수
select to_char(e1.hire_date,'mm') as month,count(*)
from employees e1
group by to_char(e1.hire_date,'mm')
order by month;


