set hidden param parseThreshold = 150000;

show user;
-- USER이(가) "HR"입니다.

------------------------------------------------------------------------------
-- 1) 학급테이블 생성
create table jdbc_tbl_class
(classno        number(3)
,classname      varchar2(100)
,teachername    varchar2(20)
,constraint PK_jdbc_tbl_class_classno primary key(classno)
);

create sequence jdbc_seq_classno
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

insert into jdbc_tbl_class(classno, classname, teachername) 
values(jdbc_seq_classno.nextval, '자바웹프로그래밍A', '김샘'); 

insert into jdbc_tbl_class(classno, classname, teachername) 
values(jdbc_seq_classno.nextval, '자바웹프로그래밍B', '이샘');

insert into jdbc_tbl_class(classno, classname, teachername) 
values(jdbc_seq_classno.nextval, '자바웹프로그래밍C', '서샘');

commit;

select *
from jdbc_tbl_class;


-- 2) 학생테이블 생성 
create table jdbc_tbl_student
(stno           number(8)               -- 학번
,name           varchar2(20) not null   -- 학생명
,tel            varchar2(15) not null   -- 연락처
,addr           varchar2(100)           -- 주소
,registerdate   date default sysdate    -- 입학일자
,fk_classno     number(3) not null      -- 학급번호
,constraint PK_jdbc_tbl_student_stno primary key(stno)
,constraint FK_jdbc_tbl_student_classno foreign key(fk_classno) 
                                        references jdbc_tbl_class(classno)
);    

-- 학번에 사용할 시퀀스 생성
create sequence jdbc_seq_stno
start with 9001
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

insert into jdbc_tbl_student(stno, name, tel, addr, registerdate, fk_classno)
values(jdbc_seq_stno.nextval, '이순신', '02-234-5678', '서울시 강남구 역삼동', default, 1);

insert into jdbc_tbl_student(stno, name, tel, addr, registerdate, fk_classno)
values(jdbc_seq_stno.nextval, '김유신', '031-345-8876', '경기도 군포시', default, 2);

insert into jdbc_tbl_student(stno, name, tel, addr, registerdate, fk_classno)
values(jdbc_seq_stno.nextval, '안중근', '02-567-1234', '서울시 강서구 화곡동', default, 2);

insert into jdbc_tbl_student(stno, name, tel, addr, registerdate, fk_classno)
values(jdbc_seq_stno.nextval, '엄정화', '032-777-7878', '인천시 송도구', default, 3);

insert into jdbc_tbl_student(stno, name, tel, addr, registerdate, fk_classno)
values(jdbc_seq_stno.nextval, '박순신', '02-888-9999', '서울시 마포구 서교동', default, 3);

commit;

select * 
from jdbc_tbl_student;

select count(*) 
from jdbc_tbl_student
where stno = 9001;  -- 1

select count(*) 
from jdbc_tbl_student
where stno = 9020;  -- 0

/*
  >>>> Stored Procedure 란? <<<<<
  Query 문을 하나의 파일형태로 만들거나 데이터베이스에 저장해 놓고 함수처럼 호출해서 사용하는 것임.
  Stored Procedure 를 사용하면 연속되는 query 문에 대해서 매우 빠른 성능을 보이며, 
  코드의 독립성과 함께 보안적인 장점도 가지게 된다.
*/

create or replace procedure pcd_student_select_one
(p_stno          IN   jdbc_tbl_student.stno%type
,o_name          OUT  jdbc_tbl_student.name%type
,o_tel           OUT  jdbc_tbl_student.tel%type
,o_addr          OUT  jdbc_tbl_student.addr%type
,o_registerdate  OUT  varchar2
,o_classname     OUT  jdbc_tbl_class.classname%type
,o_teachername   OUT  jdbc_tbl_class.teachername%type
)
is
   v_cnt  number(1);
begin
      select count(*) INTO v_cnt 
      from jdbc_tbl_student
      where stno = p_stno;
      
      if v_cnt = 0 then
         o_name := null;
         o_tel := null;
         o_addr := null;
         o_registerdate := null;
         o_classname := null;
         o_teachername := null;
         
      else
         select S.name, S.tel, S.addr, 
                to_char(S.registerdate, 'yyyy-mm-dd hh24:mi:ss'),
                C.classname, C.teachername
                INTO
                o_name, o_tel, o_addr, o_registerdate, o_classname, o_teachername 
         from jdbc_tbl_student S JOIN jdbc_tbl_class C
         on S.fk_classno = C.classno
         where S.stno = p_stno;
      end if;

end pcd_student_select_one;

exec pcd_student_select_one(9001);

select *
from jdbc_tbl_student;

select S.stno, S.name, S.tel, S.addr, to_char(S.registerdate, 'yyyy-mm-dd'),
       C.classname, C.teachername 
from ( select stno, name, tel, addr, registerdate, fk_classno
       from jdbc_tbl_student
       where addr like '%'||'서울'||'%' ) S
JOIN jdbc_tbl_class C
ON S.fk_classno = C.classno;

create or replace procedure pcd_student_select_many
(p_addr     IN      jdbc_tbl_student.addr%type
,o_data     OUT     SYS_REFCURSOR
)
is
begin
    open o_data for
    select S.stno, S.name, S.tel, S.addr, to_char(S.registerdate, 'yyyy-mm-dd') AS registerdate,
           C.classname, C.teachername 
    from ( select stno, name, tel, addr, registerdate, fk_classno
           from jdbc_tbl_student
           where addr like '%'|| p_addr ||'%' ) S
    JOIN jdbc_tbl_class C
    ON S.fk_classno = C.classno;
end pcd_student_select_many;
-- Procedure PCD_STUDENT_SELECT_MANY이(가) 컴파일되었습니다.


     --- *** tbl_member_test1 테이블에 insert 할 수 있는 요일명과 시간을 제한하겠습니다. *** ---
     --- tbl_member_test1 테이블에 insert 할 수 있는 요일명은 월,화,수,목,금 만 가능하며
     --- 또한 월,화,수,목,금 중에 오전 9시 이후 부터 오후 4시 59분 59초 까지만 가능하도록 한다.
     --- 그 이외는 영업마감 이므로 insert 를 할 수 없습니다. 라는 오류메시지를 띄우도록 한다.
     -- tbl_member_test1 테이블의 passwd 컬럼에는 
     -- 글자수가 최소 5글자 이상이면서 영문자 및 숫자 및 특수기호가 혼합되어져야 한다.
     -- 위의 조건에 맞아야만 insert 할 것이고, 위의 조건에 위배되면 insert 를 하지 않도록 할 것이다.
     
create or replace procedure pcd_tbl_member_test1_insert 
(p_userid   tbl_member_test1.userid%type    -- IN은 생략가능함.
,p_passwd   tbl_member_test1.passwd%type    -- IN은 생략가능함.
,p_name     tbl_member_test1.name%type      -- IN은 생략가능함.
)
is
   v_length        number(20);
   v_ch            varchar2(3);
   v_flagAlphabet  number(1) := 0;
   v_flagNumber    number(1) := 0;
   v_flagSpecial   number(1) := 0;
   
   error_dayTime   exception;
   error_insert    exception; 
   error_passwd    exception;
   
begin
      -- 오늘의 요일명을 알아오도록 한다.
      if ( to_char(sysdate, 'd') in('1','7') OR   -- to_char(sysdate, 'd') => '1'(일),'2'(월),'3'(화),'4'(수),'5'(목),'6'(금),'7'(토) 
           to_char(sysdate, 'hh24') < '09' OR to_char(sysdate, 'hh24') > '17'
         ) then
           raise  error_dayTime;
         
      else   
         
          v_length := length(p_passwd);
          
          if v_length < 5 then 
             raise error_insert;  -- 사용자가 정의하는 예외절(Exception)을 구동시켜라. 
          else
             for i in 1..v_length loop
                 v_ch := substr(p_passwd, i, 1);
                 
                 if (v_ch between 'a' and 'z') OR (v_ch between 'A' and 'Z') then  -- 영문자 이라면
                     v_flagAlphabet := 1;
                 elsif (v_ch between '0' and '9') then  -- 숫자 이라면 
                     v_flagNumber := 1;
                 else     -- 특수문자 이라면 
                     v_flagSpecial := 1;
                 end if;    
                     
             end loop;
             
             if(v_flagAlphabet * v_flagNumber * v_flagSpecial = 1) then
                insert into tbl_member_test1(userid, passwd, name)
                values(p_userid, p_passwd, p_name);
             else
                raise  error_passwd; -- 사용자정의 EXCEPTION 인 error_passwd 를 구동시켜라.
             end if;   
             
          end if; 
          
      end if;    
      
      exception 
         when  error_dayTime then 
               raise_application_error(-20002, '>> 현재 영업시간(월~금 09시 ~ 17시 이전까지)이 아니므로 데이터 입력이 불가합니다. <<');
      
         when  error_insert then 
               raise_application_error(-20003, '>> passwd 컬럼의 길이는 최소 5글자 이상이어야 합니다. <<');
              --    -20002 은 오류넘버(오류번호)로써 사용자가 정의하는 Exception은 항상 -20001 부터 -20999 이내의 값중 아무거나 쓰면 된다.  
         
         when  error_passwd then  
               raise_application_error(-20004, '>> passwd 컬럼의 값은 영문자, 숫자, 특수문자가 혼합되어져야만 합니다. <<');
               

end pcd_tbl_member_test1_insert;
-- Procedure PCD_TBL_MEMBER_TEST1_INSERT이(가) 컴파일되었습니다.     

select line, text
from user_source
where type='PROCEDURE' and name='PCD_TBL_MEMBER_TEST1_INSERT'; 

select *
from tbl_member_test1;


select *
from user_constraints
where table_name = 'TBL_MEMBER_TEST1';

select *
from user_cons_columns
where table_name = 'TBL_MEMBER_TEST1';


select *
from jdbc_tbl_student;

select *
from jdbc_tbl_class;

insert into jdbc_tbl_student(stno, name, tel, addr, fk_classno)
values(jdbc_seq_stno.nextval, 