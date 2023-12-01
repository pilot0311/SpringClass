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
    SL08_TILES_TEST
    스프링 타일즈(tiles)
    	ㄴ 페이지의 모듈화(집중화)
    	ㄴ 공통적인 부분(헤더, 풋터, 사이드 메뉴 등등) -> 참조
    	
   tiles.apache.org
   	pom.xml  3.0.8 타일즈 모듈 4개 추가
   	<!-- 7. Tiles -->
      <dependency>
           <groupId>org.apache.tiles</groupId>
           <artifactId>tiles-extras</artifactId>
           <version>${org.apache.tiles-version}</version>
         </dependency>
         <dependency>
            <groupId>org.apache.tiles</groupId>
            <artifactId>tiles-core</artifactId>
            <version>${org.apache.tiles-version}</version>
          </dependency>  
          <dependency>
            <groupId>org.apache.tiles</groupId>
            <artifactId>tiles-servlet</artifactId>
            <version>${org.apache.tiles-version}</version>
          </dependency>
          <dependency>
            <groupId>org.apache.tiles</groupId>
            <artifactId>tiles-jsp</artifactId>
            <version>${org.apache.tiles-version}</version>
          </dependency>
          
          페이지 모듈화
          views
          	ㄴ layout
          		ㄴ header.jsp
          		ㄴ footer.jsp
          		ㄴ template.jsp
          	ㄴ city
          		ㄴ layout
          		
          	WEB-INF
          		ㄴ tiles
          			ㄴ tiles.xml
         
         root-context.xml DB연동 관련 빈 객체 등록
         security-context.xml 시큐리트(보안)
         servlet-context.xml
  </xmp> 
</div>
<h3><a href="/">home</a></h3>
<script>
</script>
</body>
</html>