	-- 테이블 생성
    CREATE TABLE SPRING_BOARD(
        B_NUM NUMBER NOT NULL ,
        B_NAME VARCHAR2(10) NOT NULL ,
        B_TITLE  VARCHAR2(1000) NOT NULL ,
        B_CONTENT CLOB, 
        B_PWD VARCHAR2(18) NOT NULL ,
        B_DATE DATE DEFAULT SYSDATE
    ) ;
    
    ALTER TABLE SPRING_BOARD  
    ADD CONSTRAINT SPRING_BOARD_PK PRIMARY KEY(B_NUM);
    
    -- 파일 업로드시 서버에 저장한 파일명
    ALTER TABLE SPRING_BOARD  
    ADD(B_FILE VARCHAR2(500));  
    
    COMMENT ON TABLE  SPRING_BOARD IS '게시판 정보';
    COMMENT ON COLUMN SPRING_BOARD.B_NUM IS '게시판순번';
    COMMENT ON COLUMN SPRING_BOARD.B_NAME IS '게시판작성자';
    COMMENT ON COLUMN SPRING_BOARD.B_TITLE IS '게시판제목';
    COMMENT ON COLUMN SPRING_BOARD.B_CONTENT IS '게시판내용';
    COMMENT ON COLUMN SPRING_BOARD.B_PWD IS '게시판비밀번호';
    COMMENT ON COLUMN SPRING_BOARD.B_FILE IS '게시판첨부파일';
    COMMENT ON COLUMN SPRING_BOARD.B_DATE IS '게시판등록일';           
          
    CREATE SEQUENCE SPRING_BOARD_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

    SELECT b_num, b_name, b_title, TO_CHAR(b_date,'YYYY-MM-DD HH24:MI:SS') AS b_date 
    FROM 
        (            
             SELECT lst.*, ROWNUM AS RNUM 
             FROM (
                    SELECT b_num, b_name, b_title, b_date
                    FROM SPRING_BOARD
                    WHERE 1=1 
                    ORDER BY b_num desc
             )lst
         )
    WHERE 1=1  AND RNUM BETWEEN 1 AND 20

