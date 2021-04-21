-- 오라클 명령어	그룹함수
-- Internal Function 들의 연산 대상이 필드 또는 레코드였다면
-- 그룹함수는 테이블 전체(필드값 전체)를 대상으로 하는 함수를 칭합니다.
-- 학생들의 성적(필드명 : 이름 국어 영어 수학)을 저장하고있는 테이블에서 과목 총점을 내거나
-- 과목 평균을 내야하는 경우, 또는 특정 조건에 의해 필터링 된 그룹들의 총점 평균 등을 계산할 때 사용합니다.

-- 일반적인 한 명의 학생의 총점 : select 국어 + 영어 + 수학 as 총점 from 성적;
-- => 학생수만큼의 결과가 출력
-- 그룹 함수를 이용한 과목 총점 : select sum(국어) as 국어과목총점 from 성적
-- => 결과가 한 개 출력


-- sum() : 필드의 합계
select * from emp;
select sal from emp;
select sum(sal) as 총급여 from emp;

-- count() : 갯수
select count(*) as 사원수 from emp;
-- avg() : 평균
select round( avg(sal), 2 ) as 평균급여 from emp;
-- max() : 최대값
select max(sal) as 최대급여 from emp;
-- min() : 최소값
select min(sal) as 최소급여 from emp;

-- group by 절 : 직업별 평균 급여
select * from emp;
select job, round( avg(sal), 2 ) as 평균급여 from emp group by job;

-- Having 절 : 그룹화한 데이터들에 조건을 부여하여 필터링
-- 직업별 급여 평균 + 급여평균이 2000 이상인 데이터만 표시
select job, round( avg(sal), 2 ) as 평균급여 from emp group by job having avg(sal)>2000;
