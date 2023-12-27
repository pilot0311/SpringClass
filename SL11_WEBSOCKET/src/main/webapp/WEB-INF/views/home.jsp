<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<h1>
	Hello world!  
</h1>
<P>  The time on the server is ${serverTime}. </P>
<h3><a href="/time">time</a></h3>

<input type="text" id="name">
<input type="button" id="enterBtn" value="참가"><br>
<input type="text" id="message">
<input type="button" id="sendBtn" value="전송"><input type="button" id="extBtn" value="나가기">
<br>
<div id="test"></div>
<script>
$(document).ready(function(){
	$("#sendBtn").keypress(function(event){
		var keycode = (event.keyCode ? event.keyCode:event.which);
		if(keycode == '13'){
			send();
		}
		event.stopPropagation();
	});
	
	$("#sendBtn").click(function(){send();})
	$("#enterBtn").click(function(){connect();})
	$("#extBtn").click(function(){disconnect();})
})

var wsocket;

function connect(){
	wsocket = new WebSocket("ws://" + window.location.host + "/ws");
	wsocket.onopen = onOpen;
	wsocket.onmessage = onMessage;
	wsocket.onclose = onClose;
}

function disconnect(){
	wsocket.close();
}
function onOpen(evt){
	appendMessage("연결됨")
}
function onMessage(evt){
	var data = evt.data
	if(data.substring(0,4)=='msg:'){
		appendMessage(data.substring(4))
	}	
}

function onClose(evt){
	appendMessage("연결끝")
}

function send(){
	var name = $("#name").val();
	var msg = $("#message").val();
	wsocket.send("msg:"+name+":"+msg);
	$("#message").val("");
}

function appendMessage(msg){
	$("#test").append(msg+"<br>")
}


</script>
</body>
</html>
