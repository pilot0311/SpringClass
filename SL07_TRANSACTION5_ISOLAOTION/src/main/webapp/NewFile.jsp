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
  	p.512 전파와 격리 레벨 표 12-1 전파 개념 정리
  	격리레벨: 두 개 이상의 트랜잭션이 동시에 같은 자원을 접근하게 되면 
  	각각의 트랜잭션 처리를 어떻게 서로 영향받지 않게 처리할까
	
	동시에 트랜잭션이 실행되더라도 서로 영향을
	받지 않게 격리 레벨(수준)을 설정
	
	실습
	- 멀티 스레드
	NoticeDAO
	공지사항 상세보기 - 조회수를 증가, hitUp()
	getHit() 조회수 얻어오는 메서드 추가
	
	용어
	1) Dirty Read
		seq=10 조회수 = 5
		hitUp(10)호출 //@T
		조회수 = 6					commit/[rollback]
		getHit(10)// @T
	2) Non-repeatable Read
		조건) 반드시 2번 조회수를 읽어가는 중에
		seq=10 조회수 = 5
		[1]getHit(10) 												[2]getHit(10)
					5																6			
									hitUp(10)
										6
	3) Phantom Read
		조건) 여러 개의 레코드를 한 번에 읽어 오는 상황 + 반복
		
		--------------------------
		//@Transactional(isolation = Isolation.READ_COMMITTED) Dirty read 상황 X
	// 오류 발생 이유: 오라클이 지원하는 격리 레벨만 사용 가능
	//						오라클 READ_UNCOMMITTED 지원X
	//@Transactional(isolation = Isolation.READ_UNCOMMITTED) Dirty read 상황 O
	
	//@Transactional(isolation = Isolation.REPEATABLE_READ) Non-REPEATABLE_READ 발생 X
	//@Transactional(isolation = Isolation.SERIALIZABLE) 팬텀리드 상황 X
	// Isolation.DEFAULT:  오라클 지원하는 기본 격리 레벨을 따라 설정
  </xmp>
  <xmp class="code">
  
  </xmp> 
</div>

<script>
</script>
</body>
</html>