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
    REST 방식과 Ajax를 이용하는 댓글 처리
    1. 하나의 게시글 보면
     	한 줄 댓글
    	한 줄 댓글
    	한 줄 댓글
    2. REST
    	1) REST(Representational State Transfer)
    		- 로이 필딩(Roy Fielding)의 2000년 박사학위 논문에서 소개
    		- 엄격한 의미로 REST는 네트워크 아키텍처 원리의 모음
    		- 네트워크 아키텍처 원리의 모음: 자원을 정의하고 자원에 대한 주소를 지정하는 방법 전반을 일컫는다.
    	2) RESTful
    		- REST의 원리를 따르는 시스템
    3. 모바일 시대 변화
    	[웹]						앱, 웹+앱
    	[앱]		송/수신		순수한 XML, JSON 데이터
    4. URL/URI  REST
    	- 하나의 URI는 하나의 고유한 자원(Resource)를 대표하도록 설계된다는 개념의 전송 방법(방식)
    5. 스프링 REST 방식으로 데이터를 처리하는 어노테이션을 제공
    	1) @RestController	: Controller인데 REST 방식으로 처리.
    	2) @RequestBody		: JSON 데이터를 -> 클래스 객체 변환(바인딩)
    	3) @ResponseBody	: JSP와 같은 뷰로 전달 X, 데이터 자체를 전달
    	4) @PathVariable		: URL 주소 속에 파라미터 값을 추출해서 사용
    	5) @CrossOrigin		: Ajax의 크로스 도메인 문제를 해결해 주는 어노테이션
    6. 전송방법
    	1) GET		:	READ(SELECT)		+ /members/{id} 								회원조회
    	2) POST	:	CREATE(INSERT) 	+ /members/new 								회원등록
    	3) PUT		:	UPDATE					+ /members/{id} + body(json데이터)	회원수정
    	4) DELETE: 	DELETE					+ /members/{id} 								회원삭제
    	등등
    7. pom.xml
    	1) jackson-databind : JSON 데이터를 처리
    <dependency>
         <groupId>com.fasterxml.jackson.core</groupId>
         <artifactId>jackson-databind</artifactId>
         <version>2.9.5</version>
      </dependency>

		2) jackson-dataformat-xml : XML 데이터를 처리
      <dependency>
         <groupId>com.fasterxml.jackson.dataformat</groupId>
         <artifactId>jackson-dataformat-xml</artifactId>
         <version>2.9.5</version>
      </dependency>
      
      	3) gson : java 객체를 JSON 문자열로 변환
      	<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.2</version>
</dependency>
      	
      8. 예제
  
  </xmp>
</div>
<h3><a href="/board/list">게시글목록</a></h3>
<script>
</script>
</body>
</html>