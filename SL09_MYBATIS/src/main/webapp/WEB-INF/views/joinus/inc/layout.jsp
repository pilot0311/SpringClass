<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>index</title>
		<!-- <link href="join.css" type="text/css" rel="stylesheet" /> -->
		<link href="${pageContext.request.contextPath }<tiles:getAsString name="css" />" type="text/css" rel="stylesheet" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	</head>
	<body>
	<!-- header -->
	<tiles:insertAttribute name="header" />
		<!-- visual -->
		<tiles:insertAttribute name="visual" />
		</div>
		<div id="main">
			<div class="top-wrapper clear">
				<!-- content -->
				<tiles:insertAttribute name="content" />
				<!-- nav -->
				<tiles:insertAttribute name="aside" />
			</div>
		</div>
		<!--  footer  -->
		<tiles:insertAttribute name="footer" />
		</body>
</html>
