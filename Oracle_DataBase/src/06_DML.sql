-- DML(Data Management Language)
-- 데이터 조작 언어

-- INSERT(추가)
-- UPDATE(수정)
-- DELETE(삭제)
-- SELECT(조회 및 선택)

-- [1] 샘플 테이블 생성
create table exam01(
	deptno	number(2),		--부서 번호
	dname	varchar2(14),	-- 부서명
	loc		varchar2(14)		-- 위치
);
select * from exam01;

-- [2] 레코드 추가
-- 레코드 추가 명령 사용1
-- insert into 테이블 이름 (필드명1, 필드명 2, ...) values(값1, 값2, ...)
-- 필드명에는 null 값을 허용하는 필드와 기본값이 지정된 필드는 쓰지 않아도 무방합니다.
-- 값은 문자와 숫자를 구분하여 입력합니다(숫자:123, 문자:'abc')

-- 레코드 추가 명령 사용2
-- insert into 테이블 이름 values (전체 column(필드, 열)에 넣을 value들);

-- 첫 번째 방식은 필드명과 입력되어야 하는 값을 1:1로 매핑하여 입력합니다
-- 두 번째 방식은 모든 필드에 해당하는 값을 생략하고 입력하는 경우로서 필드명들을 명령어 속에
-- 나열하지 않아도 되지만, 테이블 생성 당시 나열했던 필드의 순서대로 빠짐없이 데이터가 나열되어야
-- 하는 불편함도 있습니다.

-- 필드명 나열 1:1 입력
insert into exam01(deptno, dname, loc) values(10, 'account', 'new york');
-- 필드명 생력 모든 필드값 입력
insert into exam01 values(30, 'sales', 'chichago');
select * from exam01;

-- null 값의 입력
-- 필드명을 안쓰는 경우
insert into exam01 values(40, 'operation', null);
-- 필드명을 쓰는 경우
insert into exam01(deptno, dname) values(20, 'marketing');
select * from exam01;

-- 데이터 입력(insert) # 연습1
-- 1번형식

create sequence booklist_seq start with 1 increment by 1;
alter table booklist modify grade varchar2(30);

insert into booklist(booknum, subject, makeyear, inprice, outprice, grade )
values(booklist_seq.nextVal, '좀비아이', 2020, 12000, 25000, '전체');
insert into booklist(booknum, subject, makeyear, inprice, outprice, grade )
values(booklist_seq.nextVal, '봉제인형 살인사건', 2020, 12000, 25000, '전체');
insert into booklist(booknum, subject, makeyear, inprice, outprice, grade )
values(booklist_seq.nextVal, '쇼코의 미소', 2019, 10800, 25000, '성인전용');
insert into booklist(booknum, subject, makeyear, inprice, outprice, grade )
values(booklist_seq.nextVal, '가면산장 살인사건', 2018, 13320, 15000, '청소년구매가능');
insert into booklist(booknum, subject, makeyear, inprice, outprice, grade )
values(booklist_seq.nextVal, '일곱해의 마지막', 2020, 12150, 20000, '전체');

-- 2번형식

insert into booklist values(booklist_seq.nextVal, '일곱해의 마지막', 2020, 12150, 20000, '전체');
insert into booklist values(booklist_seq.nextVal, '나미야 잡화점의 기적', 2017, 13320, 20000, '성인전용');
insert into booklist values(booklist_seq.nextVal, '이것이자바다', 2017, 30000, 30000, '성인전용');
insert into booklist values(booklist_seq.nextVal, 'JSP웹프로그래밍', 2016, 25000, 25000, '청소년구매가능');
insert into booklist values(booklist_seq.nextVal, '오라클데이터베이스', 2020, 30000, 30000, '전체');

-- 1번과 2번형식으로 각 다섯개(위의 예문 포함)의 레코드들을 추가 하세요 (총 10개 레코드)
-- grade 의 제약사항 '전체', '청소년구매가능', '성인전용' 을 준수하세요

select * from booklist;

-- 데이터 입력(insert) 연습 #2
-- person_seq 를 만들고

create sequence person_seq start with 1 increment by 1;

-- person 테이블에 10명의 회원을 추가하세요

INSERT INTO PERSON(personnum, personname, phone, birth, bpoint , age, gender) 
VALUES(person_seq.nextVal, '홍길동', '010-1234-1234', '80/06/05', 240, 30, 'M');
INSERT INTO PERSON(personnum, personname, phone, birth, bpoint , age, gender) 
VALUES(person_seq.nextVal, '박지성', '010-9876-1234', '81/04/04', 140, 29, 'F');
INSERT INTO PERSON(personnum, personname, phone, birth, bpoint , age, gender) 
VALUES(person_seq.nextVal, '구자철', '010-5555-1234', '82/05/05', 230, 25, 'M');
INSERT INTO PERSON(personnum, personname, phone, birth, bpoint , age, gender) 
VALUES(person_seq.nextVal, '지동원', '010-8787-1234', '83/06/06', 150, 35, 'F');
INSERT INTO PERSON(personnum, personname, phone, birth, bpoint , age, gender) 
VALUES(person_seq.nextVal, '추신수', '010-5656-1234', '84/07/07', 240, 28, 'M');
INSERT INTO PERSON(personnum, personname, phone, birth, bpoint , age, gender) 
VALUES(person_seq.nextVal, '류현진', '010-3333-1234', '83/08/08', 142, 27, 'F');
INSERT INTO PERSON(personnum, personname, phone, birth, bpoint , age, gender) 
VALUES(person_seq.nextVal, '손흥민', '010-4444-1234', '82/09/23', 220, 23, 'M');
INSERT INTO PERSON(personnum, personname, phone, birth, bpoint , age, gender) 
VALUES(person_seq.nextVal, '이청용', '010-6666-1234', '81/06/14', 440, 36, 'F');
INSERT INTO PERSON(personnum, personname, phone, birth, bpoint , age, gender) 
VALUES(person_seq.nextVal, '이영표', '010-2580-1234', '82/03/16', 140, 31, 'M');
INSERT INTO PERSON(personnum, personname, phone, birth, bpoint , age, gender) 
VALUES(person_seq.nextVal, '최지만', '010-7777-1234', '83/04/14', 340, 29, 'F');

select * from person;


-- 데이터 입력 #연습 3
select * from booklist;
select * from person;
select * from in_out;
INSERT INTO IN_OUT VALUES('2020/06/25', 1, 2, 3, 100);
INSERT INTO IN_OUT VALUES('2020/06/25', 2, 4, 4, 200);
INSERT INTO IN_OUT VALUES('2020/06/25', 3, 4, 5, 200);
INSERT INTO IN_OUT VALUES('2020/06/25', 4, 5, 3, 300);
INSERT INTO IN_OUT VALUES('2020/07/01', 1, 6, 4, 100);
INSERT INTO IN_OUT VALUES('2020/07/01', 2, 2, 5, 200);
INSERT INTO IN_OUT VALUES('2020/07/01', 3, 5, 6, 300);
INSERT INTO IN_OUT VALUES('2020/07/10', 1, 6, 7, 200);
INSERT INTO IN_OUT VALUES('2020/07/10', 2, 7, 6, 100);
INSERT INTO IN_OUT VALUES('2020/07/10', 3, 8, 1, 50);

-- 데이터 변경 - 수정(UPDATE)
-- UPDATE 테이블명 SET 변경내용 [WHERE 검색 조건]
-- 명령문에 WHERE 이후 구문은 생략이 가능합니다.
-- 다만 이 부분을 생략하면 모든 레코드를 대상으로 해서 UPDATE명령이 실행되어 모든 레코드가 수정됩니다.
-- 검색조건 : 필드명 (비교-관계연산자) 조건값 으로 이루어진 조건연산이며, 흔히 자바에서 if() 괄호 안에
-- 사용했던 연산을 그대로 사용하는 것이 보통입니다. 나이가 29세 이상 -> WHERE AGE>=29

-- 데이터 수정의 실제 예
-- exam01 테이블에서 deptno 값을 모두 30으로 수정
update exam01 set deptno = 30;
-- exam01 테이블에서 dname이 'ACCOUNT'인 레코드의 deptno를 10으로 수정
update exam01 set deptno = 10 where dname = 'account';
select*from exam01;
-- exam01 테이블에서 dname이 'sales'인 레코드의 deptno를 20으로 수정
update exam01 set deptno = 20 where dname = 'sales';

-- 데이터 수정(update) 명령 연습
-- booklist 테이블의 제목 '봉제인형 살인사건' 도서의 grade를 '성인전용으로 수정'
update booklist set grade = '성인전용' where subject = '봉제인형 살인사건';
-- emp테이블의 모든 사원의 sal 값을 10%씩 인상
update emp set sal=sal+(sal*0.1);
-- emp 테이블에서 sal 값이 3000 이상인 사원의 급여 10% 삭감
update emp set sal=sal-(sal*0.1) where sal>=3000;
-- hiredate가 2002년 이전인 사원의 급여를 +2000 -> (2001-12-31보다 작거나 같은)
update emp set sal=sal+2000 where hiredate<='2001-12-31';
-- ename 이 j로 시작하는 사원의 job을 manager로 변경
update emp set job='MANAGER' where ename like 'J%';
select*from emp;
-- person 테이블에서 bpoint가 200이 넘는 사람만 bpoint*100으로 변경
update person set bpoint = bpoint*100 where bpoint>=200;
-- in_out 테이블에서 할인금액이 100원이 넘으면 모두 할인금액을 90으로 변경
update in_out set outprice=90 where outprice>100;


-- 레코드의 삭제
-- delete from 테이블명 [where 조건식]
-- in_out 테이블에서 discount가 100 이하의 레코드를 삭제
delete from in_out where discount<100;


-- 삭제의 제한
delete from booklist where subject = '봉제인형 살인사건';
-- 해당 도서가 대여내역 (in_out)에 존재하고 대여내역의 도서번호가 booklist의 도서번호를
-- 참조하는 외래키가 설정되어 있다면 booklist의 해당도서는 삭제할 수 없습니다.
-- 도서가 지워지는 순간 in_out의 외래키 규칙에 위배되므로 삭제하는것 자체가 불가능합니다.
-- 삭제하려면 참조하고 있는 외래키의 옵션을, 도서를 삭제하면 대여내역도 함께 삭제되는 옵션으로 변경해야합니다.

-- 외래키의 참조값 동시삭제를 위한 옵션 수정
-- 기존 외래키 제약 사항 삭제
alter table in_out drop constraint fk1;
-- 새로운 외래키 추가
alter table in_out add constraint fk1 FOREIGN KEY(booknum)
REFERENCES booklist(booknum) on delete cascade;
-- on delete cascade : booklist의 도서가 삭제되면 in_out의 해당도서 대여내역도 함께 삭제하는 옵션
delete from booklist where subject = '봉제인형 살인사건';
select*from booklist;
select*from in_out;

-- person 테이블에서 회원 한 명을 삭제하면, in_out 테이블에서도 해당 회원이 대여한 기록을 같이 삭제하도록
-- 외래키 설정을 바꿔주세요(외래키 제약조건 삭제 후 재생성)

delete from person where personName='홍길동';
alter table in_out drop constraint fk2;
alter table in_out add constraint fk2 foreign key(personnum)
references person(personnum) on delete cascade;
delete from person where personName='홍길동';
select * from person;
select * from in_out;