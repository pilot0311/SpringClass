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
	<select>
		<option>10</option>
		<option>20</option>
		<option>30</option>
		<option>40</option>
	</select>
	<table>
      <caption style="text-align: right;">
         <a href="/board/register">목록</a>
      </caption>
      <thead>
         <tr>
            <th>1</th>
            <th>2</th>
            <th>3</th>
            <th>4</th>
            <th>5</th>
            <th>6</th>
            <th>7</th>
            <th>8</th>
         </tr>
      </thead>
      <tbody>
         <c:choose>
            <c:when test="${ empty  list }">
               <tr>
                  <td colspan="8">no emp...</td>
               </tr>
            </c:when>
            <c:otherwise>
               <c:forEach items="${ list }" var="emp">
                  <tr>
                     <td><c:out value="${ emp.empno}" /></td>
                     <td><c:out value="${ emp.ename}" /></td>
                     <td><c:out value="${ emp.job}" /></td>
                     <td><c:out value="${ emp.mgr}" /></td>
                     <td><c:out value="${ emp.hiredate}" /></td>
                     <td><c:out value="${ emp.sal}" /></td>
                     <td><c:out value="${ emp.comm}" /></td>
                     <td><c:out value="${ emp.deptno}" /></td>
                  </tr>
               </c:forEach>
            </c:otherwise>
         </c:choose>
      </tbody>
      <tfoot>
      </tfoot>
   </table>
	<script>
		$(function() {
			$("select").on("change", function() {
				let deptno = $(this).val();
				console.log(deptno)
				$.ajax({
					url : "/deptEmp/" + deptno,
					dataType: "json",
					//contentType: "application/json; charset=utf-8",
					method : "get",
					cache : false,
					success : function(result, status, xhr) {
						updateTable(result)
					},
					error : function(xhr, status, er) {
						alert("12334")
					}
				})
			})
		})
		
		function updateTable(list) {
    var tbody = $("table tbody");
    tbody.empty();

    if (list.length === 0) {
        tbody.append("<tr><td colspan='8'>no emp...</td></tr>");
    } else {
        $.each(list, function(index, list) {
            var row = $("<tr>");
            row.append("<td>" + list.empno + "</td>");
            row.append("<td>" + list.ename + "</td>");
            row.append("<td>" + list.job + "</td>");
            row.append("<td>" + list.mgr + "</td>");
            row.append("<td>" + list.hiredate + "</td>");
            row.append("<td>" + list.sal + "</td>");
            row.append("<td>" + list.comm + "</td>");
            row.append("<td>" + list.deptno + "</td>");
            tbody.append(row);
        });
    }
}
		
	</script>

</body>
</html>
