-- DDL(Data Definition Language) --데이터 정의어

-- 테이블의 생성(CREATE)

-- CREATE TABLE 테이블 이름 (
--		필드명1 DATATYPE [DEFAULT값 제약조건 및 형식],
--		필드명2 DATATYPE [DEFAULT값 제약조건 및 형식],
--		필드명3 DATATYPE [DEFAULT값 제약조건 및 형식],
--		...
--	);

-- create table 명령의 특징 및 규칙
-- ★ 테이블 이름은 객체를 의미할 수 있는 적절한 이름을 사용합니다.
-- ★ 다른테이블과 중복되지 않아야 합니다.
-- ★ 한 테이블 내에서 필드 이름이 중복되지 않게 합니다.
-- ★ 각 필드는 ","로 구분하여 생성합니다
-- ★ create를 비롯한 모든 SQL명령은 ";"으로 끝납니다
-- ★ 필드 뒤에 DATATYPE은 반드시 지정하고 [ ] 안에 내용은 해당내용이 있을 때 작성하며, 생략 가능합니다.
-- ★ 테이블 명과 필드명은 반드시 문자로 시작해야 하고 예약어 명령어 등을 테이블 명과 필드명으로 쓸 수 없습니다.
-- ★ 테이블 생성시 대/소문자 구분은 하지 않습니다.(기본적으로 테이블이나 컬럼 명은 대문자로 만들어 집니다.)
-- ★ DATE 유형은 별도로 크기를 지정하지 않습니다.
-- ★ 문자 데이터 유형은 반드시 가질 수 있는 최대 길이를 표시해야 합니다.
-- ★ 컬럼과 컬럼의 구분은 콤마로 하되, 마지막 컬럼은 콤마를 찍지 않습니다.

-- 테이블 작성의 예(도서대여점 테이블)
create table BookList(
	BookNum varchar2(5) Not null,	-- 5글자의 문자열, Null 값이 될 수 없는 제약 조건 설정
	title varchar2(30) Not null,
	makeyear number(4),	-- 출판년도, 숫자 형식, 4바이트가 아니고 4자리 숫자라는 의미입니다.
	inprice number(6), 		-- 입고가격
	outprice number(6),		-- 출고가격
	constraint booklist_pk primary key(BookNum)
	-- 추가 제약조건 BookNum을 기본키로 설정
	-- 테이블의 외부에서 현재의 제약조건을 bookllist_pk로 접근가능합니다.
);
select * from booklist;	-- 테이블의 내용을 조회하는 명령
-- ★ 컬럼에 대한 제약조건이 있으면 CONSTRAINT를 이용하여 추가할 수 있다.
-- ★ 제약조건은, bookNum뒤에 Not Null을 기술한 것과 같은 필드 LEVEL방식과,
-- 	   CONSTRAINT로 테이블 생성 마지막에 모든 제약조건을 기술하는 테이블 LEVEL 방식이 있다.

-- 제약조건(CONSTRAINT)
-- PRIMARY KEY
-- 테이블에 저장된 레코드를 고유하게 식별하기 위한 키, 하나의 테이블에 하나의 기본키만 정의할 수 있습니다.
-- 여러 필드가 조합된 기본키가 생성 가능합니다. - 기본키는 중복된 값을 가질수 없으며 빈칸도 있을 수 없습니다.
-- PRIMARY KEY = UNIQUE KEY + NOT NULL
-- UNIQUE KEY
-- 테이블에 저장된 행 데이터를 고유하게 식별하기 위한 고유키를 정의합니다.
-- 단, NULL은 고유키 제약의 대상이 아니므로, NULL값을 가진 행 여러개가 UNIQUE KEY 제약에
-- 위한되지는 않습니다.
-- NOT NULL
-- 비어있는 상태, 아무것도 없는 상태를 허용하지 않습니다. - 입력 필수
-- CHECK
-- 입력할 수 있는 값의 범위를 제한한다. CHECK 제약으로는 TRUE or FALSE로 평가할 수 있는 논리식을
-- 지정합니다.
-- FOREIGN KEY
-- 관계형 데이터베이스에서 테이블 간의 관계를 정의하고 공통 필드간의 참조관계를 설정할 때 사용합니다.

-- 테이블 생성 2
-- 테이블 이름 : Person
-- 필드 : PersonNum, PersonName, Phone, Birth, Bpoint
-- 데이터 형식 : PersonNum : VARCHAR2(5), PersonName : VARCHAR2(12),
--				  Phone : VARCHAR2(13), Birth : DATE, Bpoint : NUMBER(6)
-- 제약조건 : PersonNum, PersonName, Phone 세 개의 필드 Not Null - 필드레벨로 설정
--			   PersonNum : Primary Key - 테이블 레벨로 설정
-- 기본값(Default) : Bpoint - 0 -> 레코드 추가시에 따로 값이 지정되지 않아도 자동으로 입력되는 값

create table Person(
	PersonNum	 varchar2(5) NOT null,
	PersonName  varchar2(12) NOT null,
	Phone  varchar2(13) NOT null,
	Birth  DATE,
	enterDate DATE default sysdate,	-- 시스템에서 얻어온 오늘 날짜를 기본으로 지정하라는 뜻
	Bpoint  number(6) default 0,
	
	constraint Person_pk primary key(PersonNum)
);
select * from Person;


CREATE table in_out(
	OUT_DATE DATE NOT NULL,	-- 대여날짜
	INDEXK NUMBER(3) NOT NULL, -- 순번
	BOOKNUM VARCHAR2(5) NOT NULL,	-- 대여도서 번호
	PERSONNUM VARCHAR2(5) NOT NULL,	-- 회원번호
	DISCOUNT NUMBER(6),	-- 할인금액
	
	CONSTRAINT in_out_pk PRIMARY KEY(OUT_DATE, INDEXK),
	CONSTRAINT FK1 FOREIGN KEY(BOOKNUM) REFERENCES BOOKLIST(BOOKNUM),
	CONSTRAINT FK2 FOREIGN KEY(PERSONNUM) REFERENCES PERSON(PERSONNUM)
);
SELECT * FROM IN_OUT;

select * from tabs;
-- out_date, indexk 두 개의 필드를 조합하여 기본키(in_out_pk)를 생성함
-- booknum은 in_out테이블의 외래키(fk1)로서, booklist 테이블의 booknum을 참조함
-- personnum은 in_out테이블의 외래키(fk2)로서, person 테이블의 personnum을 참조함
-- select * from tabs; 본 계정에서 보유하고 있는 모든 테이블 출력