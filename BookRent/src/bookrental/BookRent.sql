-- 1. isbn 테이블(부모테이블)
create table book_isbn
(
bookisbn       varchar2(30)  not null  -- 도서ISBN
,bookctg       varchar2(20)  not null  -- 도서카테고리
,booktitle     varchar2(100) not null  -- 도서명
,authorname    varchar2(20)  not null  -- 작가명
,publishcom    varchar2(20)  not null  -- 출판사
,price         number(20)              -- 가격
,constraint PK_book_isbn primary key(bookisbn)
);

--도서 id 추가
alter table tbl_emp
add address varchar2(400);

-- 조회
select *
from book_isbn;

--- 2. 사서정보

create table lib_admin
(adminseq      number        not null    -- 사서번호
,adminid       varchar2(30)  not null    -- 사서아이디
,passwd        varchar2(30)  not null     -- 사서암호
,status        number(1) default 1        -- status 컬럼의 값이 1 이면 정상, 0 이면 탈퇴 
,constraint PK_lib_admin primary key(adminseq)
,constraint UQ_lib_admin unique(adminid)
,constraint CK_lib_admin check( status in(0,1) )
);

create sequence adminseq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

-- 조회
select *
from lib_admin;

--- 3. 일반회원
create table lib_member
(userseq       number        not null    -- 회원번호
,userid        varchar2(30)  not null    -- 회원아이디
,passwd        varchar2(30)  not null    -- 회원암호
,username      varchar2(20)  not null    -- 회원명
,mobile        varchar2(20)              -- 연락처
,totalrent     number        default 0   -- 총대여권수
,status        number(1)     default 1   -- status 컬럼의 값이 1 이면 정상, 0 이면 탈퇴 
,constraint PK_lib_member primary key(userseq)
,constraint UQ_lib_member unique(userid)
,constraint CK_lib_member check( status in(0,1) )
);

create sequence userseq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

-- 조회
select *
from lib_member;

--- 4. 도서목록

create table book_list
(
bookisbn       varchar2(30)  not null   -- 도서ISBN
,bookid        varchar2(30)            -- 도서아이디   
,status        number(1) default 0     -- status 0이면 비치, 1이면 대여 
,constraint FK_book_isbn foreign key(bookisbn) references book_isbn(bookisbn)
,constraint UQ_book_id   unique(bookid)
,constraint CK_book_list check( status in(0,1) )
);

--bookid 널값제약 추가
alter table book_list 
modify bookid not null;

--조회
select *
from book_list;


--- 5. 도서대여

create table book_rent
(
rentseq        number        not null           -- 대여번호  
,bookid        varchar2(30)  not null
,userid        varchar(30)   not null           -- 회원아이디        
,rentday       date        default sysdate      -- 대여일자
,returnday     date        default sysdate+14   -- 반납예정일
,rentstatus    number(1) default 1              -- 1이면 대여, 0이면 반납
,constraint    PK_book_rent primary key(rentseq)
,constraint    CK_book_rent check( rentstatus in(0,1) )
,constraint    FK_rent_book foreign key(bookid) references book_list(bookid)
,constraint    FK_rent_id foreign key(userid) references lib_member(userid)
);
     
create sequence rentseq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;


-- 해당 제약조건 제거
alter table book_rent
drop constraint FK_rent_book;    

-- on delete cascade 조건 추가한 제약조건 추가
alter table book_rent
add constraint FK_rent_book foreign key(bookid) 
    references book_list(bookid) on delete cascade;


-- 조회

select *
from book_rent;
