<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <h1 class="main"><a href="/board/list" style="position: absolute;top:30px;">pilot0311 Spring HOme</a></h1>
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
    게시글 목록
  </xmp> 
  
      <table>
      <caption style="text-align: right;">
          <a href="/board/register">글쓰기</a>
      </caption>
      <thead>
         <tr>
           <th>#번호</th>
           <th>제목</th>
           <th>작성자</th>
           <th>작성일</th>
           <th>수정일</th>        
         </tr>
      </thead>
      <tbody>        
        <c:choose>
           <c:when test="${ empty  list }">
              <tr>
                <td colspan="5" style="text-align: center;">게시글이 없습니다</td>
              </tr>
           </c:when>
           <c:otherwise>
              <c:forEach items="${ list }"  var="board">
                 <tr>
           <td><c:out value="${ board.bno }" /></td>
           <td><%-- <a href="/board/get?bno=${ board.bno }"><c:out value="${ board.title }" /> --%></a>
           		<a class="move" href="${ board.bno }"><c:out value="${ board.title }" /></a>
           		</td>
           <td><c:out value="${ board.writer }" /></td>
           <td><fmt:formatDate value="${ board.regdate }" pattern="yyyy-MM-dd"/> </td>
           <td><fmt:formatDate value="${ board.updatedate }" pattern="yyyy-MM-dd"/> </td> 
                 </tr>
              </c:forEach> 
           </c:otherwise>
        </c:choose>
        
        	<!-- 검색부분 -->
       	<tr>
          <td colspan="5" align="center">
            <form id="searchForm" action="/board/list" method="get">
              <select id="type" name="type">
                <option value="T"  <c:out value="${pageMaker.criteria.type eq 'T' ?'selected':''}" />>제목</option>
                <option value="C" <c:out value="${pageMaker.criteria.type eq 'C' ?'selected':''}"/>>내용</option>
              	<option value="W" <c:out value="${pageMaker.criteria.type eq 'W' ?'selected':''}"/>>작성자</option>
              	<option value="TC" <c:out value="${pageMaker.criteria.type eq 'TC' ?'selected':''}"/>>제목 or 내용</option>
              	<option value="TW" <c:out value="${pageMaker.criteria.type eq 'TW' ?'selected':''}"/>>제목 or 작성자</option>
              	<option value="TWC" <c:out value="${pageMaker.criteria.type eq 'TWC' ?'selected':''}"/>>제목 or 작성자 or 내용</option>
              </select>
              <input type="text" name="keyword" style="padding: 7px" value='<c:out value="${pageMaker.criteria.keyword}"/>' >  
              <input type="hidden" name="pageNum" value="${ pageMaker.criteria.pageNum }">
      		  <input type="hidden" name="amount" value="${ pageMaker.criteria.amount }">
              <button type="button" style="min-height: 32px">Search</button>
            </form>
          </td>
        </tr>
        
      </tbody>
      <tfoot>
      	<tr>
      		<td colspan='5'>
      		<div class="center">
      			<div class="pagination">
      				<c:if test="${pageMaker.prev }"><a href="${pageMaker.startPage -1 }">&laquo;</a></c:if>
      					<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="num">
      						<a href="${num }" class='${num eq pageMaker.criteria.pageNum ? "active": "" }'>${num }</a>
      					</c:forEach>
      				<c:if test="${pageMaker.next }"><a href="${pageMaker.endPage +1 }">&raquo;</a></c:if>
      			</div>
      		</div>
      		</td>
      	</tr>
      </tfoot>
    </table>
  	<form id="actionform" action="/board/list" method="get">
  		<input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum}">
  		<input type="hidden" name="amount" value="${pageMaker.criteria.amount}">
  		<input type="hidden" name="type" value="${pageMaker.criteria.type}">
  		<input type="hidden" name="keyword" value="${pageMaker.criteria.keyword}">
  	</form>
</div>
<script>
   $(function() {
      var result = '<c:out value="${result}"/>';
      //checkModal(result);

      
      
		if (${not empty result}) {
			alert("${result}");
		}
		
      function checkModal(result) {
         if(result =="" || history.state) return;
         if ( parseInt(result) > 0  ) {
            alert(`\${result}번이 등록 되었습니다`);
         }
      }
      var actionForm = $("#actionform");
      $("a.move").on("click", function (event){ 
          event.preventDefault();
          let bno = $(this).attr("href");
          actionForm
             .attr("action","/board/get")
            .append("<input type='hidden' name='bno' value='" + $(this).attr("href") +"'>");
          
          //actionForm.find("input[name=bno]").val(bno);
          actionForm.submit();
          
       });
      $(".pagination a").on("click",function(event){
    	  event.preventDefault();
    	  let pageNum = $(this).attr("href");
    	  actionForm.attr("action"," /board/list");
    	  actionForm.find("input[name=pageNum]").val(pageNum);
    	  actionForm.submit();
      })
      
      // 3. 검색 버튼 클릭 이벤트 처리
      var searchform = $("#searchForm");
      $("#searchForm button").on("click",function(event){
    	  if (!searchform.find("input[name='keyword']").val()) {
			alert("검색어 입력")
			return false;
		}
    	  searchform.find("input[name='pageNum']").val("1");
    	  event.preventDefault();
    	  searchform.submit();
      })
      
   }); // readyfunction
</script>
</body>
</html>