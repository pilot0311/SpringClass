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
  	1. 컴포넌트 스캔 수정
  	2. 
  		공지사항 목록: NoticeController.java
  		공지사항 상세보기: NoticeDetailController.java
  				↓ 대체
  	3. 컨트롤러 메서드(목록, 상세보기, 수정, 삭제)
  		CustomerController.java
  		 	ㄴ 컨트롤러메서드 구현
  		 	ㄴ 컨트롤러메서드 구현
  		 	ㄴ 컨트롤러메서드 구현
  	4. 공지사항 작성
  		1) notice.jsp -> 글쓰기 버튼 클릭
  		2) /customer/noticeReg.htm 요청 -> CustomerController 메서드 추가
  			 a링크 get방식 -> noticeReg.jsp 페이지 응답
  	5. 공지사항 수정 noticeEdit.htm?seq=${notice.seq }
  		CustomerColtroller에 메서드 추가 -> notice.jsp 응답
  		저장 버튼 클릭식 수정 완료
  </xmp>
  <xmp class="code">
  
  </xmp> 
</div>
<h3><a href="/">home</a></h3>
<script>
</script>
</body>
</html>