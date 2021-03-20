---- *** 회원 테이블 생성하기 *** ----
select *
from user_tables
where table_name = 'JDBC_MEMBER';

create table jdbc_member
(userseq       number        not null    -- 회원번호
,userid        varchar2(30)  not null    -- 회원아이디
,passwd        varchar2(30)  not null    -- 회원암호
,name          varchar2(20)  not null    -- 회원명
,mobile        varchar2(20)              -- 연락처
,point         number(10) default 0      -- 포인트
,registerday   date default sysdate      -- 가입일자 
,status        number(1) default 1       -- status 컬럼의 값이 1 이면 정상, 0 이면 탈퇴 
,constraint PK_jdbc_member primary key(userseq)
,constraint UQ_jdbc_member unique(userid)
,constraint CK_jdbc_member check( status in(0,1) )
);

create sequence userseq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;


select *
from jdbc_member
order by userseq asc;


---- *** 게시판 테이블 생성하기 *** ----
create table jdbc_board
(boardno       number        not null          -- 글번호
,fk_userid     varchar2(30)  not null          -- 작성자아이디
,subject       varchar2(100) not null          -- 글제목
,contents      varchar2(200) not null          -- 글내용
,writeday      date default sysdate not null   -- 작성일자
,viewcount     number default 0 not null       -- 조회수 
,boardpasswd   varchar2(20) not null           -- 글암호 
,constraint PK_jdbc_board primary key(boardno)
,constraint FK_jdbc_board foreign key(fk_userid) references jdbc_member(userid) 
);


create sequence board_seq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

desc jdbc_board;

select *
from jdbc_board
order by boardno desc;



---- *** 댓글 테이블 생성하기 *** ----
create table jdbc_comment 
(commentno   number        not null    -- 댓글번호 
,fk_boardno  number        not null    -- 원글의 글번호 
,fk_userid   varchar2(30)  not null    -- 사용자ID
,contents    varchar2(200) not null    -- 댓글내용 
,writeday    date default sysdate      -- 작성일자
,constraint  PK_jdbc_comment  primary key(commentno) 
,constraint  FK_jdbc_comment_fk_boardno foreign key(fk_boardno) 
             references jdbc_board(boardno) on delete cascade 
,constraint  FK_jdbc_comment_fk_userid  foreign key(fk_userid) 
             references jdbc_member(userid) 
);


create sequence seq_comment
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;


select *
from jdbc_comment; 

-----------------------------------------------------------------------
select *
from jdbc_member
order by userseq desc;


select userseq, userid, passwd, name, mobile, point
     , to_char(registerday, 'yyyy-mm-dd') AS registerday, status 
from jdbc_member 
where userid = 'leess' and passwd = 'qwer1234$';


--------------------------------------------------------------------
/*
     Transaction(트랜잭션) 처리 실습을 위해서 
     jdbc_member 테이블의 point 컬럼의 값은 최대 30을 넘지 못하도록 check 제약을 걸도록 하겠습니다.
*/
alter table jdbc_member
add constraint CK_jdbc_member_point check( point between 0 and 30 );
-- Table JDBC_MEMBER이(가) 변경되었습니다.

select * 
from jdbc_board;

select *
from jdbc_member
order by userseq asc;


update jdbc_member set point = point + 10
where userid = 'leess';
-- 1 행 이(가) 업데이트되었습니다.

update jdbc_member set point = point + 10
where userid = 'leess';
-- 1 행 이(가) 업데이트되었습니다.

update jdbc_member set point = point + 10
where userid = 'leess';
-- 1 행 이(가) 업데이트되었습니다.

update jdbc_member set point = point + 10
where userid = 'leess';
/*
오류 보고 -
ORA-02290: check constraint (HR.CK_JDBC_MEMBER_POINT) violated
*/

rollback;



select B.boardno
     , case when length(B.subject) > 10 then substr(B.subject, 1, 8) || '..' else B.subject end AS SUBJECT     
     , M.name
     , to_char( B.writeday, 'yyyy-mm-dd hh24:mi:ss') AS WRITEDAY 
     , B.viewcount
from jdbc_board B JOIN jdbc_member M 
ON B.fk_userid = M.userid
order by 1 desc;


---- 글내용 보기 -----
select subject, contents, fk_userid
from jdbc_board
where boardno = '1';


---- 댓글쓰기 ----
insert into jdbc_comment(commentno, fk_boardno, fk_userid, contents)
values(seq_comment.nextval, 2342, 'eomjh', '자바와 오라클 붙이는 것이 재미 있어요~~');
/*
오류 보고 -
ORA-02291: integrity constraint (HR.FK_JDBC_COMMENT_FK_BOARDNO) violated - parent key not found
*/


select *
from jdbc_comment; 


-- ===> 댓글의 개수를 보여주는 SQL문 <=== --
select B.boardno 
     , B.subject
     , M.name 
     , to_char( B.writeday, 'yyyy-mm-dd hh24:mi:ss') AS WRITEDAY 
     , B.viewcount 
     , NVL(C.COMMENTCNT, 0) AS COMMENTCNT
from jdbc_board B JOIN jdbc_member M 
ON B.fk_userid = M.userid
LEFT JOIN
(
  select fk_boardno, count(*) AS COMMENTCNT
  from jdbc_comment
  group by fk_boardno
) C 
ON B.boardno = C.fk_boardno
order by 1 desc;



select *
from jdbc_comment; 

select *
from jdbc_comment
where fk_boardno = 8;

select *
from jdbc_comment
where fk_boardno = 6;


select *
from jdbc_comment
where fk_boardno = 1;


select *
from jdbc_board
order by boardno desc;


select C.contents, M.name, C.writeday 
from 
(
 select contents
      , to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS WRITEDAY
      , fk_userid
 from jdbc_comment
 where fk_boardno = '8'
) C JOIN jdbc_member M 
ON C.fk_userid = M.userid;



update jdbc_board set subject = '새제목'
                    , contents = '새내용'
where boardno = 8;
-- 1 행 이(가) 업데이트되었습니다.

select *
from jdbc_board
order by boardno desc;

rollback;

delete from jdbc_board
where boardno = 8 and boardpasswd = '1234';
-- 1 행 이(가) 삭제되었습니다.

select *
from jdbc_board
order by boardno desc;

select *
from jdbc_comment
order by commentno desc;

rollback;


-- ==== 최근 1주일간 일자별 게시글 작성건수 조회하기 ==== --
select *
from jdbc_board;

update jdbc_board set writeday = writeday - 3
where boardno = 1; 

commit;

select boardno, subject, writeday, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss')
from jdbc_board
order by boardno desc;

/*
     -----------------------------------------------------------------------------------------------
      TOTAL    PREVIOUS6    PREVIOUS5    PREVIOUS4    PREVIOUS3    PREVIOUS2    PREVIOUS1    TODAY     
     -----------------------------------------------------------------------------------------------
        4          0            0            3            0            0            0          1
*/

-- [퀴즈] 최근 1주일간의 게시글만 출력하세요. 

select boardno, subject, writeday, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss'), 
       sysdate - writeday, 
       to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd')    
from jdbc_board
order by boardno desc;


select writeday
     , decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,6,1) AS PREVIOUS6
     , decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,5,1) AS PREVIOUS5
     , decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,4,1) AS PREVIOUS4
     , decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,3,1) AS PREVIOUS3
     , decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,2,1) AS PREVIOUS2
     , decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,1,1) AS PREVIOUS1
     , decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,0,1) AS TODAY
from jdbc_board
where to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') < 7;


select writeday
     , decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,6,1 ,0) AS PREVIOUS6
     , decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,5,1 ,0) AS PREVIOUS5
     , decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,4,1 ,0) AS PREVIOUS4
     , decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,3,1 ,0) AS PREVIOUS3
     , decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,2,1 ,0) AS PREVIOUS2
     , decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,1,1 ,0) AS PREVIOUS1
     , decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,0,1 ,0) AS TODAY
from jdbc_board
where to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') < 7;


select count(*) AS TOTAL
     , SUM( decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,6,1 ,0) ) AS PREVIOUS6
     , SUM( decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,5,1 ,0) ) AS PREVIOUS5
     , SUM( decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,4,1 ,0) ) AS PREVIOUS4
     , SUM( decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,3,1 ,0) ) AS PREVIOUS3
     , SUM( decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,2,1 ,0) ) AS PREVIOUS2
     , SUM( decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,1,1 ,0) ) AS PREVIOUS1
     , SUM( decode( to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') ,0,1 ,0) ) AS TODAY
from jdbc_board
where to_date( to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date( to_char(writeday, 'yyyy-mm-dd'), 'yyyy-mm-dd') < 7;  


create or replace function func_midnight
(p_date   IN   date)
return  date
is
begin
     return to_date( to_char(p_date, 'yyyy-mm-dd'), 'yyyy-mm-dd'); 
end func_midnight;
-- Function FUNC_MIDNIGHT이(가) 컴파일되었습니다.

select to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss')
     , to_char( func_midnight(sysdate), 'yyyy-mm-dd hh24:mi:ss')
from dual;


select count(*) AS TOTAL
     , SUM( decode( func_midnight(sysdate) - func_midnight(writeday) ,6,1 ,0) ) AS PREVIOUS6
     , SUM( decode( func_midnight(sysdate) - func_midnight(writeday) ,5,1 ,0) ) AS PREVIOUS5
     , SUM( decode( func_midnight(sysdate) - func_midnight(writeday) ,4,1 ,0) ) AS PREVIOUS4
     , SUM( decode( func_midnight(sysdate) - func_midnight(writeday) ,3,1 ,0) ) AS PREVIOUS3
     , SUM( decode( func_midnight(sysdate) - func_midnight(writeday) ,2,1 ,0) ) AS PREVIOUS2
     , SUM( decode( func_midnight(sysdate) - func_midnight(writeday) ,1,1 ,0) ) AS PREVIOUS1
     , SUM( decode( func_midnight(sysdate) - func_midnight(writeday) ,0,1 ,0) ) AS TODAY
from jdbc_board
where func_midnight(sysdate) - func_midnight(writeday) < 7;  



select decode( grouping(to_char(writeday, 'yyyy-mm-dd')) , 0 , to_char(writeday, 'yyyy-mm-dd'), '전체') AS WRITEDAY 
     , count(*) AS CNT 
from jdbc_board
where to_char(writeday, 'yyyy-mm') = to_char(sysdate, 'yyyy-mm')
group by rollup( to_char(writeday, 'yyyy-mm-dd') );



select decode( grouping(to_char(writeday, 'yyyy-mm-dd')) , 0 , to_char(writeday, 'yyyy-mm-dd'), '전체') AS WRITEDAY 
     , count(*) AS CNT 
from jdbc_board
where to_char(writeday, 'yyyy-mm') = to_char( add_months(sysdate, 1), 'yyyy-mm')
group by rollup( to_char(writeday, 'yyyy-mm-dd') );



select *
from jdbc_member;


select userseq, userid, name, mobile, point, to_char(registerday, 'yyyy-mm-dd') AS REGISTERDAY
from jdbc_member
where userid != 'admin';











