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
    스프링 게시판
    글보기/검색
    N-tier 방식
    3-tier 방식
    웹 프로젝트 3-tier 방식
    1) 화면 계층 - Presentation Tier
    2) 비지니스(로직)계층 - Business Tier
    3) 데이터 계층 == 영속 계층 - Persistence Tier
    -- 스프링 MVC 패턴 개발 --
    -- 패키지 --
    org.doit.ik
    	ㄴ config: 설정관련 정보
    	ㄴ domain: VO, DTO 클래스
    	ㄴ persistence: DAO, MyBatis 인터페이스, 클래스 -- (mapper)
    	ㄴ controller:
    	ㄴ service
    	ㄴ exception
    	ㄴ aop
    	ㄴ util
    	ㄴ security
    	
    	1. DB 환경 확인 + MyBatis
    		ㄴ pom.xml 의존 모듈 확인
    		ㄴ root-context.xml DB 연동 모든 스프링 빈 객체 생성 + 등록 + 연결
    			- 스프링, MyBatis 에서 DB연동 할 때 DataSource 객체 사용
    	2. web.xml
    	3. domain 패키지 추가
    		ㄴ BoardVO
    	4. 게시글 목록
    		org.doit.ik.mapper 패키지
    			ㄴ BoardMapper.java 인터페이스
    		resources
    			ㄴ org.doit.ik.mapper
    				ㄴ BoardMapper.xml
    	5. HomeController.java 복사 붙이기 -> BoardController.java
    	6 home_original
    	
    	7 org.doio.ik.service
    		ㄴ BoardService 인터페이스
    		ㄴ BoardServiceImpl 클래스
    		
    	8 servlet-context.xml
    	<context:component-scan base-package="org.doit.ik" />
    	org.doit.ik 패키지 및 하위 패키지를 찾아서
    	component들을 자동으로 스캔해서 스프링 빈 객체로 등록 + 연결
    	
    	9 ViewResolver
    	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
			<beans:property name="prefix" value="/WEB-INF/views/" />
			<beans:property name="suffix" value=".jsp" />
		</beans:bean>
		
		/board/list -ViewResolver-> /WEB-INF/views/board/list.jsp 응답 페이지
		
		10 webapp
				ㄴ board
					ㄴ list
					
		11 글쓰기
			1) 글쓰기 페이지 응답
			2) BoardController.java  get컨트롤러 메서드 추가 -> register.jsp 응답
			
			3)
  </xmp> 
  <xmp class="code">
  ------ 게시판 DB 쿼리 ------
  생성
  CREATE TABLE tbl_board
    (
      bno number(10)
      , title varchar2(200) not null
      , content varchar2(2000) not null
      , writer varchar2(50) not null
      , regdate date default sysdate
      , updatedate date default sysdate
    );
    
    alter table tbl_board add constraint pk_tblboard_bno primary key(bno);
    
     CREATE SEQUENCE seq_board;  
  </xmp>
</div>
<h3><a href="/board/list">게시글목록</a></h3>
<script>
</script>
</body>
</html>