<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${list }" var="list">
			${list.empno },${list.hiredate },${list.comm } <br>
		</c:forEach>