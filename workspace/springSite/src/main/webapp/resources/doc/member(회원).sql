	-- 회원가입 테이블
	CREATE TABLE spring_member(
		idx number,
		userid varchar2(70) not null,
		userpw varchar2(100) not null,
		username varchar(50) not null,
		pinno varchar2(15) not null,
		email VARCHAR2(70) not null,
		phone VARCHAR2(13) not null,
		joindate date default sysdate,
		CONSTRAINT spring_member_pk PRIMARY KEY(idx),
		CONSTRAINT spring_member_uk UNIQUE(userid)
	);
	
	comment on table  spring_member is '회원 테이블 정보';
	comment on column spring_member.idx is '회원테이블 순번';
	comment on column spring_member.userid is '회원테이블 아이디';
	comment on column spring_member.userpw is '회원테이블 비밀번호';    
	comment on column spring_member.username is '회원테이블 이름';
	comment on column spring_member.pinno is '회원테이블 생년월일';    
	comment on column spring_member.email is '회원테이블 이메일'; 
	comment on column spring_member.phone is '회원테이블 핸드폰번호';     
	comment on column spring_member.joindate is '회원테이블 등록일'; 
	
	-- 회원 가입시 사용할 회원번호(시퀀스)
	CREATE SEQUENCE spring_member_seq;

	-- 해시함수 솔트값을 저장하기 위한 테이블(비밀번호 암호화)
	CREATE TABLE security(
	userid varchar2(70),
	salt varchar2(70),
	CONSTRAINT security_pk PRIMARY KEY(userid)
	);

	-- 로그인 정보 저장 테이블
	CREATE TABLE login_history (
		idx NUMBER,
		userid VARCHAR2(70),
		retry NUMBER DEFAULT 0,
		lastFailedLogin NUMBER DEFAULT 0,
		lastSuccessedLogin NUMBER DEFAULT 0,
		clientIp VARCHAR2(15),
		CONSTRAINT login_history_pk PRIMARY KEY(idx)
	);

	COMMENT ON TABLE  login_history is '로그인 정보 저장 테이블';
	COMMENT ON COLUMN login_history.idx is '순번';
	COMMENT ON COLUMN login_history.userid is '로그인 아이디';
	COMMENT ON COLUMN login_history.retry is '로그인 시도 횟수';
	COMMENT ON COLUMN login_history.lastfailedlogin is '마지막으로 실패한 로그인 시간';
	COMMENT ON COLUMN login_history.lastsuccessedlogin is '마지막으로 성공한 로그인 시간';
	COMMENT ON COLUMN login_history.clientip is '로그인한 사용자의 ip 주소';

	-- 로그인 정보 저장시 사용할 순번(시퀀스)
	CREATE SEQUENCE login_history_seq;