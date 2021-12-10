   	-- 테이블 생성
    create table spring_gallery(
        g_num number not null ,
        g_name varchar2(10) not null ,
        g_subject varchar2(50 char) not null,
        g_content varchar2(100 char) not null,
        g_thumb varchar2(100) not null,         
        g_file varchar2(100) not null, 
        g_pwd varchar2(18) not null ,
        g_date date default sysdate,
        constraint spring_gallery_pk primary key(g_num)
    );

    select * from spring_gallery;
    
    comment on table  spring_gallery is '갤러리 게시판 정보';
    comment on column spring_gallery.g_num is '갤러리게시판 순번';
    comment on column spring_gallery.g_name is '갤러리게시판 작성자';
    comment on column spring_gallery.g_subject is '갤러리게시판 제목';    
    comment on column spring_gallery.g_content is '갤러리게시판 내용';
    comment on column spring_gallery.g_thumb is '갤러리게시판 썸네일 이미지';    
    comment on column spring_gallery.g_file is '갤러리게시판 이미지';
    comment on column spring_gallery.g_pwd is '갤러리게시판 비밀번호';
    comment on column spring_gallery.g_date is '갤러리게시판 등록일';           
          
    create sequence spring_gallery_seq; 