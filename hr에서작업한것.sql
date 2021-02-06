show user;
-- USER이(가) "HR"입니다.

select *
from dba_users;
-- ORA-00942: table or view does not exist
-- 관리자만 조회할 수 있는 것이지 일반 사용자인 hr은 조회가 불가하다.

-- *** ORACLE은 관계형 데이터베이스(Relation DataBase) 관리 시스템(Management System)이다. ***
--              RDBMS (Relation DataBase Management System)
--     관계형 데이터베이스는 데이터를 열(Column, Field)과 행(Row, Record, tuple) 으로 이루어진 테이블(Table, entity, 개체) 형태로 저장한다.
 
-- ** 현재 오라클 서버에 접속되어진 사용자(지금은 hr)가 만든(소유의) 테이블(Table) 목록을 조회하겠다.
select * 
from tab; 
/*
COUNTRIES	        TABLE
DEPARTMENTS	        TABLE
EMPLOYEES	        TABLE
EMP_DETAILS_VIEW	VIEW (view는 테이블은 아니지만 SELECT 되어진 결과물을 마치 테이블처럼 보는 것)
JOBS	            TABLE
JOB_HISTORY	        TABLE
LOCATIONS	        TABLE
REGIONS	            TABLE
*/

select *
from DEPARTMENTS; -- sql 명령어는 대,소문자를 구분하지 않습니다.

select *
from departments;

SELECT *
from depARTMents;

select *
from departments
where department_name = 'Sales'; -- 테이블명과 컬럼명도 대,소문자를 구분하지 않습니다.

select *
from departments
WHERE department_name = 'SALES'; -- 저장되어진 데이터값 만큼은 대,소문자를 구분합니다.!! 

select *
from departments
WHERE department_name = 'IT';

select *
from departments
WHERE department_name = 'it';

-----------------------------------------------------------------------------------
select *
from DEPARTMENTS; -- "부서" 테이블

describe DEPARTMENTS; -- DEPARTMENTS 테이블의 컬럼(column)의 정보를 알려주는 것이다.
--또는
desc DEPARTMENTS; 

/*
이름                                널?       유형           
---------------                 -------- ------------ 
DEPARTMENT_ID   (부서번호)       NOT NULL   NUMBER(4) => 숫자타입     NOT NULL은 반드시 데이터를 입력해야 한다는 뜻이다.
DEPARTMENT_NAME (부서명)         NOT NULL   VARCHAR2(30) => 문자열타입 
MANAGER_ID      (부서장의사원번호)            NUMBER(6)               없는 것이나 NULL은 같은데 데이터를 입력하든 입력하지 않든 모두 허용한다는 뜻이다.   
LOCATION_ID     (부서위치ID)                NUMBER(4)   
*/

select *
from EMPLOYEES; -- "사원" 테이블

describe EMPLOYEES;
/*
이름                            널?           유형           
--------------               --------    ------------ 
EMPLOYEE_ID     (사원번호)     NOT NULL    NUMBER(6)    
FIRST_NAME      (이름)                    VARCHAR2(20) 
LAST_NAME       (성)          NOT NULL    VARCHAR2(25) 
EMAIL           (이메일)       NOT NULL    VARCHAR2(25) 
PHONE_NUMBER    (연락처)                   VARCHAR2(20) 
HIRE_DATE       (입사일자)     NOT NULL    DATE  => 날짜타입       
JOB_ID          (직종ID)      NOT NULL    VARCHAR2(10) 
SALARY          (기본급여)                 NUMBER(8,2)  
COMMISSION_PCT  (커미션[수당]퍼센티지)       NUMBER(2,2)   예를 들어, 커미션[수당]퍼센티지가 0.2라 함은 기본급여의 20%라는 말이다.   
MANAGER_ID      (직속상관[사수]의사원번호)    NUMBER(6)    
DEPARTMENT_ID   (본인이 근무하는 부서번호)    NUMBER(4)   
*/

select *
from locations; -- 부서의 위치정보를 알려주는 테이블 

select *
from countries; -- 국가정보를 알려주는 테이블

select *
from regions; -- 대륙정보를 알려주는 테이블

/*
  == ★★★★★아주아주아주 중요!!!!★★★★★ ==
  == !!!!필수 암기!!!! ==
  
  == ★어떤한 테이블(또는 뷰)에서 데이터정보를 꺼내와 보는 명령어인 select의 처리순서★ ==
  
    select 컬럼명, 컬럼명    -- 5  컬럼명 대신에 *(아스테리크)을 쓰면 모든 컬럼을 뜻하는 것이다.
    from 테이블명(또는 뷰명)  -- 1  
    where 조건절            -- 2  where 조건절이 뜻하는 것은 테이블명(또는 뷰명)에서 조건에 만족하는 행(row)을 메모리(RAM)에 로딩(올리는것) 해주는 것이다.
    group by 절            -- 3
    having 그룹함수조건절    -- 4
    order by 절            -- 6
    
*/

-------------------------------------------------------------------------------

--- *** NULL 을 처리해주는 함수 *** ---
--- NULL은 존재하지 않는 것이므로 4칙연산(+ - * /)에 NULL이 포함되어지면 그 결과는 무조건 NULL이 된다.

1. NVL

select '안녕하세요' -- 오라클에서 문자열은 무조건 ''로 나타낸다.
from dual;  -- dual 은 select 다음에 나오는 값들을 화면에 보여주기 위한 용도로 쓰이는 가상테이블이다.

select 1+2, 1+null, 3*0, null*0, 2-1, 2-null, 5/2, 5/null
from dual;

select NVL(7,3),NVL(null,3),
       NVL('이순신','거북선'), NVL(null,'거북선') -- 첫번째 값이 null이 아니면 첫번째 값을 그대로 나타내고, 첫번째 값이 null이면 두번째 값을 나타낸다.
from dual;

2. NVL2

select NVL2(7,3,2),NVL2(null,3,2),
       NVL2('이순신','거북선','구국영웅'), NVL2(null,'거북선'.'구국영웅')
       -- 1번째 값이 null이 아니면 2번째 값, null이면 3번째 값이 나온다.
       -- '이순신'이 null이 아니므로 '거북선' 출력
from dual;


select *        -- *이므로 모든 컬럼들을 추출(select)하라는 말이다.
from employees; -- where 절이 없으므로 employees 테이블에 저장되어져 있던 모든 행들을 메모리(RAM)에 퍼올린다(Loading).

select employee_id, first_name, last_name -- employee_id, first_name, last_name 컬럼들만 추출(select)하라는 말이다.
from employees; -- where 절이 없으므로 employees 테이블에 저장되어져 있던 모든 행들을 메모리(RAM)에 퍼올린다(Loading).

-- 월급은 기본월급(salary)+수당(commission_pct) 이다. 수당은 commission_pct 컬럼의 값인데 예를들어 commission_pct 컬럼의 값이 null이면 수당이 없다는 말이다.
-- commission_pct 컬럼의 값이 0.2라 함은 기본월급의 20%이므로 실제 수당금액은 0.2*salary 이다.

select employee_id as "사원번호",  -- 별칭(별명) 
        first_name as "이름",  -- 별칭(별명) alias 에서 as는 생략가능함.
        last_name as "성", --  별칭(별명) alias 에서 " "는 생략가능함.
        salary "기본 급여", --  별칭(별명) alias 에서 공백을 주고자 한다라면 반드시 " "로 해주어야 한다.
        commission_pct 커미션, 
        NVL(salary+(salary*commission_pct), salary) as monthsal_1,
        NVL2(commission_pct,salary+(salary*commission_pct), salary) as "MonthSal_2"
from employees;

   ----- *** 비교연산자 *** -----
   1. 같다        = 
   2. 같지않다    !=  <>  ^= 
   3. 크다.작다.  >  < 
   4. 같거나크다. 같거나작다  >=   <= 
   5. NULL 은 존재하지 않는 것이므로 비교대상이 될수가 없다.
      그러므로 비교연산( = != <> ^= > < >= <= )을 할수가 없다.
      그래서 비교연산을 하려면 nvl()함수, nvl2()함수를 사용하여 처리한다.

-- employees 테이블에서 부서번호 30번에 근무하는 사원들만
-- 사원번호, 사원명, 월급, 부서번호를 나타내세요.

-- 오라클에서 컬럼들을 붙일때(연결할 때)는 숫자 타입이든 문자 타입인든 관계 없이 || 사용
select '대한민국' || ' ' || '서울시' || 123 || ' ' || sysdate
from dual;

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       salary 월급, 
       department_id 부서번호
from employees
where department_id=30;

---------------------------------------------------------------------------

-- employees 테이블에서 부서번호가 NULL인 사원들만
-- 사원번호, 사원명, 월급, 부서번호를 나타내세요.

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
where department_id=null;
--> 데이터가 출력되지 않는다.
--> 왜냐하면 null은 존재하지 않는 것이므로 비교대상이 될 수가 없다.

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       nvl(department_id, -9999) 부서번호
from employees;

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
where NVL(department_id,-9999)=-9999;

-- 또는

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
where department_id is null; -- null은 is 연산자를 사용하여 구한다.
                             -- department_id 컬럼의 값이 null 인 행들만 RAM(메모리)에 퍼올리는 것이다.
 
 ---------------------------------------------------------------------------                            
        
-- employees 테이블에서 부서번호 30번이 아닌 사원들만
-- 사원번호, 사원명, 월급, 부서번호를 나타내세요.
select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
where department_id!=30;
-- where department_id<>30;
-- where department_id^=30;
-- 이렇게 하면 부서번호가 null값인 사원은 포함되지 않으므로 틀린 구하기 방식이다.

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
where nvl(department_id,-9999)!=30;
-- 위와 같이 구하면 부서번호가 null값인 사원도 -9999 값을 가져서 30이 아니게 되므로
-- 구하고자 하는 결과에 포함이 된다.

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
where not nvl(department_id,-9999) = 30;
-- 아니면 where 뒤에 부정어 not을 추가하여 구하는 방법도 있다.

---------------------------------------------------------------------------

-- employees 테이블에서 부서번호가 NULL인 사원들만
-- 사원번호, 사원명, 월급, 부서번호를 나타내세요.

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
where department_id is not null;

-- 또는

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
where not department_id is null;

---------------------------------------------------------------------------
---------------------------------------------------------------------------

-- 정렬(오름차순정렬, 내림차순정렬)을 할때 null 은 존재하지 않는 것이므로 
-- 오라클에서는 가장 큰것으로 간주를 해주고, MS-SQL 에서는 가장 작은것으로 간주를 해버린다.

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
order by first_name || ' ' || last_name ASC;  -- ASC; 이 오름차순 정렬


select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
order by first_name || ' ' || last_name DESC;  -- DESC; 이 내림차순 정렬


select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
order by first_name || ' ' || last_name; -- ASC;나 DESC; 이 없으면 기본은 오름차순 정렬


select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
order by 사원명; 

-- 또는

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
order by 2 DESC;
-- 오라클에서 컬럼은 1부터 세기 시작한다.

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
order by 4;
-- 부서번호 순으로 오름차순 정렬하면,
-- 오라클에서는 NULL 값을 가장 큰 값으로 인지하므로, NULL 값을 가진 사원이 가장 마지막에 오게 된다.

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
order by 4 ASC, 3 DESC;
--       1차정렬  2차정렬
--> 4번째 부서번호의 오름차순 먼저 해준 뒤, 동일한 부서 내에서 월급의 내림차순으로 정렬

-- 또는
select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
order by 4, 3 DESC;
--> 위와 같이 4번째 부서번호의 오름차순 먼저 해준 뒤, 동일한 부서 내에서 월급의 내림차순으로 정렬
-- 4 뒤에 ASC가 생략되었음

---------------------------------------------------------------------------

-- employees 테이블에서 수당퍼센티지가 null 인 사원들만 
-- 사원번호, 사원명, 월급(기본급여+수당금액), 부서번호를 나타내되 
-- 부서번호의 오름차순으로 정렬한 후 동일한 부서번호내에서는 월급의 내림차순으로 나타내세요.

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
where commission_pct is null
order by 4 ASC, 3 DESC;

-- employees 테이블에서 수당퍼센티지가 null 인 아닌 사원들만 
-- 사원번호, 사원명, 월급(기본급여+수당금액), 부서번호를 나타내되 
-- 부서번호의 오름차순으로 정렬한 후 동일한 부서번호내에서는 월급의 내림차순으로 나타내세요.

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
where commission_pct is not null
order by 4 ASC, 3 DESC;

-- 또는

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
where not commission_pct is null
order by 4 ASC, 3 DESC;

---------------------------------------------------------------------------

-- employees 테이블에서 월급(기본급여+수당금액)이 10000 보다 큰 사원들만
-- 사원번호, 사원명, 월급(기본급여+수당금액), 부서번호를 나타내되 
-- 부서번호의 오름차순으로 정렬한 후 동일한 부서번호내에서는 월급의 내림차순으로 나타내세요.

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
where NVL(salary+(salary*commission_pct), salary)>10000
order by 4 ASC, 3 DESC;

---------------------------------------------------------------------------

-- employees 테이블에서 부서번호가 50번 부서가 아닌 사원들만
-- 사원번호, 사원명, 월급(기본급여+수당금액), 부서번호를 나타내되 
-- 부서번호의 오름차순으로 정렬한 후 동일한 부서번호내에서는 월급의 내림차순으로 나타내세요.

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
where NVL(department_id, -9999) !=  50 
order by 4 ASC, 3 DESC;

-- 또는

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)  월급, 
       department_id 부서번호
from employees
where not NVL(department_id, -9999) =  50 
order by 4 ASC, 3 DESC;

---------------------------------------------------------------------------
---------------------------------------------------------------------------

/*
   === AND, OR, NOT 연산자가 혼용되어지면 우선순위를 따르는데 
       NOT > AND > OR 의 순위를 따라간다. 
       우선순위에 있어서 최우선은 () 괄호 이다.
       
       OR 연산자와 같은 것이 IN() 이다. IN()는 괄호가 있는 OR 이다.
       
       범위연산자가 있는데 between A and B 이 A 이상 B 이하를 나타내는 것이다.
*/

select 2+3*4 -- 14
from dual;

/*
  employees 테이블에서 부서번호가 30, 50, 60번 부서에 근무하는 사원들중에 
  연봉(월급*12)이 20000 이상 60000 이하인 사원들만
  사원번호, 사원명, 연봉(월급*12), 부서번호를 나타내되
  부서번호의 오름차순으로 정렬한 후, 연봉의 내림차순으로 정렬하여 출력하세요.
*/

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)*12  연봉, 
       department_id 부서번호
from employees
where department_id = 30 or department_id = 50 or department_id = 60
      and nvl(salary+(salary*commission_pct),salary)*12>=20000
      and nvl(salary+(salary*commission_pct),salary)*12<=60000
order by 4, 3 desc;
-- !!!! AND와 OR가 혼용되어지면 우선순위는 AND가 먼저 실행된다.
-- 부서번호 60인 사원들에게만 연봉의 제약이 있고, 30번 부서나 50번 부서에는 연봉 제약이 없다.

select employee_id as 사원번호,
       first_name || ' ' || last_name 사원명, 
       NVL(salary+(salary*commission_pct), salary)*12  연봉, 
       department_id 부서번호
from employees
where (department_id = 30 or department_id = 50 or department_id = 60)
      and nvl(salary+(salary*commission_pct),salary)*12>=20000
      and nvl(salary+(salary*commission_pct),salary)*12<=60000
order by 4, 3 desc;
-- 이제 모든 부서에 연봉의 제약이 적용된다.
-- !!! 연산자의 우선순위에서 괄호()가 제일 우선한다. !!! -- 
