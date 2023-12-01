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
  	p.511 전파와 격리 레벨 표 12-1 전파 개념 정리
  	전파: 트랜잭션 진행중 또다른 새로운 트랜잭션이 처리 되어야 할 경우
  	---------------------------
   	같은 클래스 내에서 다른 메소드를 호출하는 구조로는 AOP 방식으로 트랜잭션 처리가 되지 않는다는 것입니다. 
	따라서, 이 문제를 해결하려면 별도의 서비스 클래스를 만들어서 그 곳에서 게시글 등록과 포인트 증가 메소드를 호출하도록 해야 합니다.
	
	service 패키지 -> MemberShipServiec 인터페이스 추가
	
  </xmp>
  <xmp class="code">
  	// 2. 공지사항 등록 + 작성자 포인트 1증가
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException {
		// a. 공지사항 쓰기
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ))+1,1) FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		//a
		SqlParameterSource parameterSource  = new BeanPropertySqlParameterSource(notice);
		npJdbcTemplate.update(sql, parameterSource);
		
		// b. 작성자 포인트 1증가
		String sql2 = "UPDATE member SET point = point+1 WHERE id = :id ";
		//b
		MapSqlParameterSource parameterSource2 = new MapSqlParameterSource();
		parameterSource2.addValue("id", "pilot");
		int updateCount = npJdbcTemplate.update(sql2, parameterSource2);
		return updateCount;
	}
	
	// 6. 전파 방식 설명하기 위해 insertAndPointUpofMember 수정
	@Override
	//@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void insertAndPointUpofMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		insert(notice);
		
		insert(notice);
	}
  </xmp> 
</div>

<script>
</script>
</body>
</html>