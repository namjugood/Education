--서브 쿼리
-- Sub Query : 하나의 select 문장의 절 안에 포함된 또 하나의 select 쿼리문

update emp set deptno=30 where ename='SCOTT';

-- SCOTT이 근무하는 곳의 부서명과 지역 출력
-- 단일행 서브쿼리 : 서브 쿼리의 결과가 하나
select deptno from emp where ename='SCOTT';	-- 결과는 30'
-- 위 select 명령의 결과를 다른 select 명령에 사용
select dname, loc from dept
where deptno IN (select deptno from emp where ename = 'SCOTT');

select*from deptno;
--[연습문제] SCOTT과 동일직급(JOB)을 가진 사원을 출력하는 SQL문
select empno, ename, job from emp 
where job=(select job from emp where ename='SCOTT');
--[연습문제] SCOTT과 급여가 동일하거나 더 많이 받는 사원이름과 급여 출력
select ename, sal from emp where sal>=(select sal from emp where ename='SCOTT');


--[서브 쿼리 &  그룹함수]
--전체 사원 평균 급여보다 더 많은 급여를 받는 사원의 이름, 급여, job
select ename, sal, job from emp where sal>=(select avg(sal) from emp);
--다중행 서브쿼리 : 서브쿼리의 결과가 둘 이상(=, >= 말고 in() )
--급여를 2000 이상 받는 사원이 소속된 부서와 소속된 부서에서 근무하는 사원들의 이름, 부서번호, job
select deptno from emp where sal>=2000; -- 급여 4000 이상 사원의 부서번호;
select distinct deptno from emp where sal>=2000;	-- 10,20,30,40
--급여 2000 이상 사원의 부서번호(중복제거);
--distinct 필드명 : 필드 값의 중복된 값이 여러개라면 한 번만 출력
select ename, sal, job, deptno from emp
where deptno IN(select distinct deptno from emp where sal>=2000);
--where deptno IN(10,20,30,40) : 부서번호가 10이거나 20이거나 30이거나 40인 조건

--[연습문제]
--30번 부서 소속 사원들 중에서 급여를 가장 많이 받는 사원보다 급여가 더 많은 사원의 이름과 job, 급여
select ename as 이름, job as 직책, sal as 급여 from emp 
where sal>(select max(sal) from emp where deptno=30);	-- 답안 1
select ename as 이름, job as 직책, sal as 급여 from emp 
where sal>ALL(select sal from emp where deptno=30);	-- 답안 2(ALL의 유일한 사용 예)

--부서번호가 30번인 사원들의 급여 중에서 가장 낮은 급여보다 높은 급여를 받는 사원의 이름과 job, 급여
select ename as 이름, job as 직책, sal as 급여 from emp 
where sal>(select min(sal) from emp where deptno=30);	-- 답안 1
select ename as 이름, job as 직책, sal as 급여 from emp 
where sal>ANY(select sal from emp where deptno=30);	-- 답안 2

-- 영업사원(job='SALESMAN')들의 최소 급여보다 많이 받는 사원들의 이름과 급여와 직급, 급여를
-- 출력하되 영업사원은 출력하지 않습니다.
select ename as 이름, job as 직급, sal as 급여 from emp 
where sal>(select min(sal) from emp where job='SALESMAN') AND job <>'SALESMAN';

select ename as 이름, job as 직급, sal as 급여 from emp 
where sal>ANY(select sal from emp where job='SALESMAN') AND job <>'SALESMAN';