<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="shortcut icon" href="http://localhost/images/SiSt.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
<style>
 span.material-symbols-outlined{
    vertical-align: text-bottom;
 }
</style>
</head>
<body>
<header>
  <h1 class="main"><a href="#" style="position: absolute;top:30px;">pilot0311 Spring HOme</a></h1>
  <ul>
    <li><a href="#">로그인</a></li>
    <li><a href="#">회원가입</a></li>
  </ul>
</header>
<h3>
  <span class="material-symbols-outlined">spring</span> 
</h3>
<div>
  <xmp class="code">
    1. org.doit.ik.domain 패키지 추가
    2. org.doit.ik.controller
    3. org.doit.ik.persistence
    4. org.doit.ik.service
    5. 테이블 생성
    6. NoticeVO
    	MemverVO
    	NoticeDAO
    	MemberDAO
    7. WebContent -> src/main/webapp 에 복사
    8. web.xmp url-pattern 수정 *.htm 
    9. 컨트롤러 메서드 구현 X
    	JSP CommandHandler.java
    			process 추상메서드
    			
    		ListHandler implements CommandHandler
    			@Override
    			process(){}
    			
    	org.doit.ik.controller - NoticeController.java 추가
    									/customer/notice.htm 맵핑 처리
    								servlet-context.xml
   	10. 요청처리 작업 (게시글 상세보기)
  </xmp>
  <xmp class="code">
  테이블 생성 sql
  CREATE TABLE NOTICES
(
	"SEQ" VARCHAR2(10 BYTE), 
	"TITLE" VARCHAR2(200 BYTE), 
	"WRITER" VARCHAR2(50 BYTE), 
	"CONTENT" VARCHAR2(4000 BYTE), 
	"REGDATE" TIMESTAMP (6), 
	"HIT" NUMBER, 
	"FILESRC" VARCHAR2(500 BYTE)
);

CREATE TABLE "MEMBER"
(	
    "ID" VARCHAR2(50 BYTE), 
    "PWD" VARCHAR2(50 BYTE), 
    "NAME" VARCHAR2(50 BYTE), 
    "GENDER" VARCHAR2(10 BYTE), 
    "BIRTH" VARCHAR2(10 BYTE), 
    "IS_LUNAR" VARCHAR2(10 BYTE), 
    "CPHONE" VARCHAR2(15 BYTE), 
    "EMAIL" VARCHAR2(200 BYTE), 
    "HABIT" VARCHAR2(200 BYTE), 
    "REGDATE" DATE
);
 
select * from member;
  
  </xmp> 
</div>
<h3><a href="/">home</a></h3>
<script>
</script>
</body>
</html>