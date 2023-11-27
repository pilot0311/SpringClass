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
    스프링 게시판 페이징 처리
    ?현재페이지=2&한페이지출력게시글수=10&검색조건=T&검색어=홍길동
    
    1. Criteria.java
    	PageDTO.java
    	
    2. BoardMapper.xml 수정
    	2개 추가
    3. BoardMapper.java
    	2개 추가
    4. BoardService.java 수정
    	BoardServiceImple.java 수정
    	
    5. BoardController.java
    	/board/list 요청시 페이징 처리된 list.jsp 응답
    	
    6. list.jsp 수정
  </xmp> 
  <xmp class="code">
  
  </xmp>
</div>
<h3><a href="/board/list">게시글목록</a></h3>
<script>
</script>
</body>
</html>