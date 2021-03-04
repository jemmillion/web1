set hidden param parseThreshold = 150000;

show user;
-- USER이(가) "HR"입니다.

create table jdbc_tbl_memo
(no        number(4)
,name      varchar2(20) not null
,msg       varchar2(200) not null
,writeday  date default sysdate
,constraint PK_jdbc_tbl_memo_no primary key(no)
);

create sequence jdbc_seq_memo
start with 1
increment by 1 
nomaxvalue
nominvalue
nocycle
nocache;

select *
from jdbc_tbl_memo
order by no desc;

insert into jdbc_tbl_memo(no, name, msg)
values(jdbc_seq_memo.nextval, ?, ?)

select *
from user_db_links;

select *
from jdbc_tbl_memo@TEACHERSERVER
order by no desc;

select no, name, msg, to_char(writeday,'yyyy-mm-dd hh24:mi:ss') as writeday
from jdbc_tbl_memo
order by no desc;


select no, name, msg, to_char(writeday,'yyyy-mm-dd hh24:mi:ss') as writeday
from jdbc_tbl_memo
where no '1'
order by no desc;

select no, name, msg, to_char(writeday,'yyyy-mm-dd hh24:mi:ss') as writeday
from jdbc_tbl_memo
where msg like '%안녕%'
order by no desc;

select no, name, msg, to_char(writeday,'yyyy-mm-dd hh24:mi:ss') as writeday
from jdbc_tbl_memo
where msg like '%'||'안녕'||'%'
order by no desc;

select *
from jdbc_tbl_examtest;

-----------------------------------------------------------------------------

select *
from user_tables
where table_name = 'JDBC_TBL_EXAMTEST';



select *
from user_sequences
where sequence_name = 'JDBC_SEQ_EXAMTEST';

drop table jdbc_tbl_examtest purge;
drop sequence jdbc_seq_examtest;