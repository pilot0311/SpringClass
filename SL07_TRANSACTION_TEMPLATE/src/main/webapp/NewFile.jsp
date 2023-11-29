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
<h3><a target="_blank" href="/customer/notice.htm">notice</a></h3>
  <xmp class="code">
  	1. root-context.xml -> transactionManager 등록
  	
  	2. 트랜잭션 처리
  		1) 게시글 새로 작성
  		2) 작성자의 포인트 1증가
  	3. 포인트 컬럼 추가 - member 테이블 X
  	4. 제약 조건 추가
  		- 제목 유일성 제약 조건
  		- 포인트 3 이상이면 체크 제약조건
  	5. join.jsp 수정/ 회원가입
  	6. 게시글 작성 + 작성자 포인트 1증가
  		1) NoticeDAO 인터페이스
  	7. 테스트
  		TEST-1 쓰기  pilot point = 1
  		TEST-2 쓰기  pilot point = 2
  		TEST-3 쓰기  pilot point = 3 제약조건 point<3
  		
  	8. 트랜잭션 처리
  		<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
     		<property name="dataSource"  ref="dataSource" />
   		</bean>
   		2) NoticeDAOImpl
   			@Autowired
			private DataSourceTransactionManager transactionManager;
	9. p.513 transactiontemplate 을 이용한 트랜잭션 처리
		1) transactiontemplate 빈 등록
		2) NoticeDAOImpl 수정
		
	p.505 스프링 트랜잭션 처리
  </xmp>
  <xmp class="code">
  
  </xmp> 
</div>

<script>
</script>
</body>
</html>