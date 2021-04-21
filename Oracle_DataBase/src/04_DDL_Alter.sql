-- 테이블의 수정(ALTER)

-- 1. 필드명의 변경
-- ALTER TABLE 이름 rename column 변경 전 이름 to 변경 후 이름;
alter table booklist rename column title to subject;
-- booklist 테이블의 title필드 이름을 subject로 수정하세요
select * from booklist;

-- 2. 필드 자료형의 변경
-- varchar2(12) 였던 person 테이블의 personname 필드를 varchar2(30)으로 변경
alter table person modify personname varchar2(30);
select * from person;

-- 3. 필드의 추가
-- ALTER TABLE 테이블명 ADD 필드명 자료형
-- booklist에 구매등급을 저장할 수 있는 grade필드를 varchar2(15)로 추가
alter table booklist add grade varchar2(15);
-- person 테이블에 성별(gender) 필드 추가 varchar2(3)
alter table person add gender varchar2(3);
-- person 테이블에 나이(age) 필드 추가 number(2)
alter table person add age number(2);
select * from booklist;
select * from person;

-- 4. 필드의 삭제
-- ALTER TABLE 테이블명 DROP COLUMN 필드명
-- person 테이블에서 성별 필드 제거
alter table person drop column gender;
select * from person;

-- 5. 제약 조건의 추가/제거
alter table person add gender varchar2(3);
-- ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명 제약조건식
-- person 테이블에서 성별 필드에 'F', 'M'만 입력되도록 제약조건 추가
alter table person add constraint check_gender check(gender in('F','M'));
-- 나이 필드에 120살이 초과되는 나이는 입력되지 못하게 제약 조건 추가. 제약조건 명 : check_age
alter table person add constraint che_age check(age <= 120);
-- 제약조건의 제거
-- alter table 테이블명 drop constraint 제약조건명;


-- in_out 테이블에서 booklist 테이블의 booknum을 참조하는 외래키 제거
alter table in_out drop constraint fk1;
-- in_out에서 기본키 제거
alter table in_out drop constraint in_out_pk;
-- in_out에서 기본키 다시 추가(제거한 기본키와 같은 이름, 같은 필드를 이용)
alter table in_out add constraint in_out_pk primary key (OUT_DATE, INDEXK);
-- in_out 테이블에서 booklist테이블의 booknum을 참조하는 외래키 다시 추가
alter table in_out add constraint fk1 foreign key (booknum) references booklist(booknum);
-- booklist의 grade 필드에 '전체' '청소년구매가능' '성인전용' 으로 입력을 제한
alter table booklist add constraint check_grade check(grade in('전체', '청소년구매가능', '성인전용'));
alter table booklist drop constraint check_grade;

