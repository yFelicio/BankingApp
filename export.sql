--------------------------------------------------------
--  File created - Wednesday-March-14-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence ACCOUNT_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "YFELICIO"."ACCOUNT_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 81 CACHE 20 NOORDER  NOCYCLE   ;
--------------------------------------------------------
--  DDL for Sequence APPLICATION_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "YFELICIO"."APPLICATION_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE   ;
--------------------------------------------------------
--  DDL for Sequence LOG_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "YFELICIO"."LOG_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE   ;
--------------------------------------------------------
--  DDL for Sequence USER_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "YFELICIO"."USER_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 51 CACHE 50 NOORDER  NOCYCLE   ;
--------------------------------------------------------
--  DDL for Table ACCOUNTS
--------------------------------------------------------

  CREATE TABLE "YFELICIO"."ACCOUNTS" 
   (	"ACCOUNT_ID" NUMBER(*,0), 
	"BALANCE" NUMBER DEFAULT 0.0, 
	"ACTIVE" NUMBER(1,0) DEFAULT 1
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table APPLICATIONS
--------------------------------------------------------

  CREATE TABLE "YFELICIO"."APPLICATIONS" 
   (	"APPLICATION_ID" NUMBER(*,0), 
	"STATUS" VARCHAR2(55 BYTE), 
	"USER_ID_FK" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table LOGS
--------------------------------------------------------

  CREATE TABLE "YFELICIO"."LOGS" 
   (	"LOG_ID" NUMBER(*,0), 
	"LOG_TIME" VARCHAR2(100 BYTE), 
	"LOG_MESSAGE" VARCHAR2(255 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TYPE
--------------------------------------------------------

  CREATE TABLE "YFELICIO"."TYPE" 
   (	"TYPE_ID" NUMBER(*,0), 
	"TYPE" VARCHAR2(55 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "YFELICIO"."USERS" 
   (	"USER_ID" NUMBER(*,0), 
	"USERNAME" VARCHAR2(55 BYTE), 
	"PASSWORD" VARCHAR2(55 BYTE), 
	"TYPE_ID_FK" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table USERS_ACCOUNTS
--------------------------------------------------------

  CREATE TABLE "YFELICIO"."USERS_ACCOUNTS" 
   (	"USER_ID" NUMBER(*,0), 
	"ACCOUNT_ID" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into YFELICIO.ACCOUNTS
SET DEFINE OFF;
Insert into YFELICIO.ACCOUNTS (ACCOUNT_ID,BALANCE,ACTIVE) values (61,100,1);
Insert into YFELICIO.ACCOUNTS (ACCOUNT_ID,BALANCE,ACTIVE) values (52,0,1);
Insert into YFELICIO.ACCOUNTS (ACCOUNT_ID,BALANCE,ACTIVE) values (53,800,0);
Insert into YFELICIO.ACCOUNTS (ACCOUNT_ID,BALANCE,ACTIVE) values (62,100,1);
Insert into YFELICIO.ACCOUNTS (ACCOUNT_ID,BALANCE,ACTIVE) values (63,150,1);
REM INSERTING into YFELICIO.APPLICATIONS
SET DEFINE OFF;
Insert into YFELICIO.APPLICATIONS (APPLICATION_ID,STATUS,USER_ID_FK) values (41,'approved',5);
Insert into YFELICIO.APPLICATIONS (APPLICATION_ID,STATUS,USER_ID_FK) values (43,'approved',7);
Insert into YFELICIO.APPLICATIONS (APPLICATION_ID,STATUS,USER_ID_FK) values (21,'approved',5);
Insert into YFELICIO.APPLICATIONS (APPLICATION_ID,STATUS,USER_ID_FK) values (22,'approved',5);
Insert into YFELICIO.APPLICATIONS (APPLICATION_ID,STATUS,USER_ID_FK) values (42,'approved',7);
REM INSERTING into YFELICIO.LOGS
SET DEFINE OFF;
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (2,'12/03/2018 13:13:03','Info: Application id: 41 has been approved');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (3,'12/03/2018 13:13:13','Debug: customerid: 5');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (5,'12/03/2018 13:14:45','Debug: customerid: 5');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (10,'12/03/2018 14:01:01','Info: Application id: 42 has been approved');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (14,'12/03/2018 14:04:00','Info: Account 52 balance is now: 0.0');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (16,'12/03/2018 21:23:57','Info: deposit amount = 150.0');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (17,'12/03/2018 21:24:08','Debug: account id: 53 balance: 800.0');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (9,'12/03/2018 14:00:56','Info: Application id: 43 has been approved');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (11,'12/03/2018 14:02:51','Debug: customerid: 5');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (15,'12/03/2018 21:23:29','Info: balance = 650.0');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (1,'12/03/2018 12:26:11','Debug: customerid: 5');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (7,'12/03/2018 14:00:13','Info: yuri2 has applied for an account');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (8,'12/03/2018 14:00:16','Info: yuri2 has applied for an account');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (12,'12/03/2018 14:02:52','Info: 63 has added yuri as an owner of the account');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (13,'12/03/2018 14:04:00','Debug: account id: 52 balance: 0.0');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (18,'12/03/2018 21:24:09','Info: new balance = 800.0');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (19,'12/03/2018 21:24:10','Debug: account id: 53 balance: 800.0');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (4,'12/03/2018 13:14:27','Debug: customerid: 5');
Insert into YFELICIO.LOGS (LOG_ID,LOG_TIME,LOG_MESSAGE) values (6,'12/03/2018 13:16:55','Debug: customerid: 5');
REM INSERTING into YFELICIO.TYPE
SET DEFINE OFF;
Insert into YFELICIO.TYPE (TYPE_ID,TYPE) values (1,'CUSTOMER');
Insert into YFELICIO.TYPE (TYPE_ID,TYPE) values (2,'EMPLOYEE');
Insert into YFELICIO.TYPE (TYPE_ID,TYPE) values (3,'ADMIN');
REM INSERTING into YFELICIO.USERS
SET DEFINE OFF;
Insert into YFELICIO.USERS (USER_ID,USERNAME,PASSWORD,TYPE_ID_FK) values (3,'admin','pass',3);
Insert into YFELICIO.USERS (USER_ID,USERNAME,PASSWORD,TYPE_ID_FK) values (4,'employee','pass',2);
Insert into YFELICIO.USERS (USER_ID,USERNAME,PASSWORD,TYPE_ID_FK) values (7,'yuri2','pass',1);
Insert into YFELICIO.USERS (USER_ID,USERNAME,PASSWORD,TYPE_ID_FK) values (5,'yuri','pass',1);
REM INSERTING into YFELICIO.USERS_ACCOUNTS
SET DEFINE OFF;
Insert into YFELICIO.USERS_ACCOUNTS (USER_ID,ACCOUNT_ID) values (5,52);
Insert into YFELICIO.USERS_ACCOUNTS (USER_ID,ACCOUNT_ID) values (5,53);
Insert into YFELICIO.USERS_ACCOUNTS (USER_ID,ACCOUNT_ID) values (5,61);
Insert into YFELICIO.USERS_ACCOUNTS (USER_ID,ACCOUNT_ID) values (5,63);
Insert into YFELICIO.USERS_ACCOUNTS (USER_ID,ACCOUNT_ID) values (7,52);
Insert into YFELICIO.USERS_ACCOUNTS (USER_ID,ACCOUNT_ID) values (7,62);
Insert into YFELICIO.USERS_ACCOUNTS (USER_ID,ACCOUNT_ID) values (7,63);
--------------------------------------------------------
--  DDL for Index ACCOUNTS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "YFELICIO"."ACCOUNTS_PK" ON "YFELICIO"."ACCOUNTS" ("ACCOUNT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger LOG_ID_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "YFELICIO"."LOG_ID_TRIGGER" 
BEFORE INSERT ON LOGS
FOR EACH ROW
BEGIN
 SELECT LOG_ID_SEQ.NEXTVAL INTO :new.LOG_ID FROM DUAL;
END;
/
ALTER TRIGGER "YFELICIO"."LOG_ID_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TRG_ACCOUNT_ID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "YFELICIO"."TRG_ACCOUNT_ID" 
    before insert on accounts
    for each row
    begin
    select account_id_seq.nextval
    into :new.account_id
    from dual;
    end;

/
ALTER TRIGGER "YFELICIO"."TRG_ACCOUNT_ID" ENABLE;
--------------------------------------------------------
--  DDL for Trigger USER_ID_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "YFELICIO"."USER_ID_TRIGGER" 
BEFORE INSERT ON USERS
FOR EACH ROW 
BEGIN
    SELECT USER_ID_SEQ.NEXTVAL INTO :new.USER_ID FROM DUAL;
END;

/
ALTER TRIGGER "YFELICIO"."USER_ID_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Procedure TRANSFER_PROC
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "YFELICIO"."TRANSFER_PROC" 
(
    V_ACCOUNT_FROM NUMBER,
    V_ACCOUNT_TO NUMBER,
    V_AMOUNT NUMBER
) AS 
BEGIN
    UPDATE ACCOUNTS SET BALANCE = BALANCE - V_AMOUNT WHERE ACCOUNT_ID = V_ACCOUNT_FROM;
    UPDATE ACCOUNTS SET BALANCE = BALANCE + V_AMOUNT WHERE ACCOUNT_ID = V_ACCOUNT_TO;
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Function CUSTOM_AUTH
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "YFELICIO"."CUSTOM_AUTH" (p_username in VARCHAR2, p_password in VARCHAR2)
return BOOLEAN
is
  l_password varchar2(4000);
  l_stored_password varchar2(4000);
  l_expires_on date;
  l_count number;
begin
-- First, check to see if the user is in the user table
select count(*) into l_count from demo_users where user_name = p_username;
if l_count > 0 then
  -- First, we fetch the stored hashed password & expire date
  select password, expires_on into l_stored_password, l_expires_on
   from demo_users where user_name = p_username;

  -- Next, we check to see if the user's account is expired
  -- If it is, return FALSE
  if l_expires_on > sysdate or l_expires_on is null then

    -- If the account is not expired, we have to apply the custom hash
    -- function to the password
    l_password := custom_hash(p_username, p_password);

    -- Finally, we compare them to see if they are the same and return
    -- either TRUE or FALSE
    if l_password = l_stored_password then
      return true;
    else
      return false;
    end if;
  else
    return false;
  end if;
else
  -- The username provided is not in the DEMO_USERS table
  return false;
end if;
end;


/
--------------------------------------------------------
--  DDL for Function CUSTOM_HASH
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "YFELICIO"."CUSTOM_HASH" (p_username in varchar2, p_password in varchar2)
return varchar2
is
  l_password varchar2(4000);
  l_salt varchar2(4000) := 'F869S1S99NUGL5Y2V2N3XY4MSR2PIU';
begin

-- This function should be wrapped, as the hash algorhythm is exposed here.
-- You can change the value of l_salt or the method of which to call the
-- DBMS_OBFUSCATOIN toolkit, but you much reset all of your passwords
-- if you choose to do this.

l_password := utl_raw.cast_to_raw(dbms_obfuscation_toolkit.md5
  (input_string => p_password || substr(l_salt,10,13) || p_username ||
    substr(l_salt, 4,10)));
return l_password;
end;


/
--------------------------------------------------------
--  Constraints for Table TYPE
--------------------------------------------------------

  ALTER TABLE "YFELICIO"."TYPE" ADD PRIMARY KEY ("TYPE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table LOGS
--------------------------------------------------------

  ALTER TABLE "YFELICIO"."LOGS" ADD PRIMARY KEY ("LOG_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table APPLICATIONS
--------------------------------------------------------

  ALTER TABLE "YFELICIO"."APPLICATIONS" ADD PRIMARY KEY ("APPLICATION_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "YFELICIO"."USERS" ADD PRIMARY KEY ("USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table USERS_ACCOUNTS
--------------------------------------------------------

  ALTER TABLE "YFELICIO"."USERS_ACCOUNTS" ADD PRIMARY KEY ("USER_ID", "ACCOUNT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ACCOUNTS
--------------------------------------------------------

  ALTER TABLE "YFELICIO"."ACCOUNTS" MODIFY ("BALANCE" NOT NULL ENABLE);
  ALTER TABLE "YFELICIO"."ACCOUNTS" MODIFY ("ACTIVE" NOT NULL ENABLE);
  ALTER TABLE "YFELICIO"."ACCOUNTS" MODIFY ("ACCOUNT_ID" NOT NULL ENABLE);
  ALTER TABLE "YFELICIO"."ACCOUNTS" ADD CONSTRAINT "ACCOUNTS_PK" PRIMARY KEY ("ACCOUNT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "YFELICIO"."ACCOUNTS" ADD CONSTRAINT "CK_BALANCE" CHECK (BALANCE >= 0) ENABLE;
