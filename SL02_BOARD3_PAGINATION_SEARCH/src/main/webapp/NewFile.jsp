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
    스프링 게시판 검색기능
    	1) 단일 항목 검색: 제목/내용/작성자
    	2) 다중 항목 검색: 제목+내용, 제목+작성자, 제목+내용+작성자
    select 쿼리 + 검색 조건축가
    <select id="getListWithPaging" resultType="org.doit.ik.domain.BoardVO">
       <![CDATA[
      SELECT bno, title, content, writer, regdate, updatedate
      FROM (
               SELECT /*+INDEX_DESC(tbl_board pk_tblboard_bno )*/
                      rownum rn, bno, title, content, writer, regdate, updatedate
               FROM tbl_board 
               WHERE 
               -- 검색 조건 추가
               -- 제목 + 내용 검색
               (title LIKE '%검색어%' OR content LIKE '%검색어%')
               AND rownum <= #{ pageNum} * #{ amount }
            )
      WHERE rn > (#{ pageNum } - 1) * #{ amount }
      ]]>
     </select>
    
    3. MyBatis 동적 쿼리 작성
    	ㄴ if, choose(when,then,other,wise), trim(where, set), foreach 동적 태그들
    	<if test="type='T',toString()">
    		(title LIKE '%||#{keyword }||%')
    	</if>
    	<choose>
    		<when test="type='T',toString()">
    			(title LIKE '%||#{keyword }||%')
    		</when>
    		<when test="type='C',toString()">
    			(content LIKE '%||#{keyword }||%')
    		<otherwise>
    			(content LIKE '%||#{keyword }||%')
    		</otherwise>
    		</when>
    	</choose>
     trim, where, set 태그는 단독으로 사용되지 않고
     if, choose와 같은 태그들을 내포하여 sql을 연결해주고 앞뒤에 필요한 구문들(AND,OR,WHERE 등등)을 추가하거나 생략하는 역활
     예) WHERE rownum <= 20 OR rownum >= 10
     SELECT *
     FROM tbl_board
     <where>
     	<if test="bno!=null">
     		bno = #{bno }
     	</if>
     </where>
     ----------
      SELECT *
     FROM tbl_board
     <where>
     	<if test="bno!=null">
     		bno = #{bno }
     	</if>
     	<trim prefixOverrides = "AND">
     		rownum=1
     	</trim>
     </where>
     
     4. Criteria.java 필드 추가
     5. list.jsp 검색 추가
     6. BoardMapper.xml 검색 구현 추가
     7. 검색 버튼 클릭
  </xmp> 
  <xmp class="code">
  
  </xmp>
</div>
<h3><a href="/board/list">게시글목록</a></h3>
<script>
</script>
</body>
</html>