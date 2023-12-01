<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
<script src="/resources/js/dept.js"></script>
</head>
<body>
	<h1>Hello world!</h1>
	<P>The time on the server is ${serverTime}.</P>
	<h3><a href="/deptEmp">http://localhost/deptEmp</a></h3>
	<div>
		<form action="" method="get">
			empno: <input type="text" id="empno" name="empno" value="7369">
			<button type="button" id="idCheck" value="ID중복체크"></button>
			<br>
			<div id="check"></div>
			 ename: <input type="text" id="ename" name="ename" value="hong">
		</form>
	</div>

<script>
$(function(){
	$("#idCheck").on("click",function(){
		var empno = $("#empno").val()
		console.log(empno)
		$.ajax({
			url: "/idCheck",		//HomeAjaxController.java
			method: "GET",
			data: {empno: empno},
			dataType: "json",
			success: function(data, callback, xhr){
				//alert(data.idCheckResult)
				let check = data.idCheckResult;
				if (check == 1) {
					$("#check").empty()
					$("#check").append("<span>id가 중복입니다</span>")
				}else{
					$("#check").empty()
					$("#check").append("<span>사용 가능한 id 입니다</span>")
				}
			},
			error: function(xhr, errorType){
				alert(errorType)
			}
		})
	})
})
</script>

<!-- The Modal -->
<div id="add-modal" class="modal">
<!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header"> 
      <h2>Ajax 부서 추가</h2>
    </div>
    <div class="modal-body">
      <div class="group">
        <label>부서번호</label>
        <input type="text" class="short" name="deptno" value="50">
       </div>
       <div class="group">
           <label>부서명</label>
           <input type="text" class="short" name="dname" value="QC">
       </div>
       <div class="group">
           <label>지역명</label>
           <input type="text" class="short" name="loc" value="SEOUL">
       </div>
       <div>
           <button id="add-dept" type="button" class="ok">확인</button>
           <button type="button" class="delete">삭제</button>
       </div>
    </div>
    <div class="modal-footer">
      <h3>Modal Footer</h3>
    </div>
  </div> 
</div>

<script>
	$("#add-modal #add-dept").on("click",function(){
		let deptno = $("#add-modal :text[name=deptno]").val();
		let dname = $("#add-modal :text[name=dname]").val();
		let loc = $("#add-modal :text[name=loc]").val();
		console.log(deptno, dname, loc)
		let dept = {
			deptno: deptno,
			dname: dname,
			loc: loc
		}
		
		deptService.add(dept,function(result){
			if(result == 'SUCCESS'){
				alert(result);
			}
		})
		
	})
	
	$("#add-modal button.delete").on("click",function(){
		let deptno = $("#add-modal :text[name=deptno]").val();
		
		deptService.remove(deptno,function(result){
			if(result == 'SUCCESS'){
				alert(result);
			}
		})
	})
</script>

</body>
</html>
