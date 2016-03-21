
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>WebSocket</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- 
            <link rel="stylesheet" type="text/css" href="styles.css"> 
            -->
<script type="text/javascript">
	var ws, url = "ws://" + window.location.host + "/yt-web/ws/chat/", result;

	function initWebSocket() {
		var connStr = url + document.getElementById("room").value;
		console.log(connStr);
		result = document.getElementById("result");
		if ("WebSocket" in window) {
			ws = new WebSocket(connStr);
		} else if ("MozWebSocket" in window) {
			ws = new MozWebSocket(connStr);
		} else {
			result.innerHTML = "No support WebSocket !<br>";
		}

		ws.onopen = function() {
			result.innerHTML = "..# onopen" + "<br>";
			//ws.send("Hello WebSocket !");
		};
		ws.onmessage = function(event) {
			result.innerHTML += "..# onmessage : " + event.data + "<br>";
		};
		ws.onclose = function(event) {
			result.innerHTML += "..# onclose" + "<br>";
		};
		ws.onerror = function(event)
		{
			result.innerHTML += "..# onerror <br>";
		}
	}

	function send() {
		var text = document.getElementById("msg").value;
		console.log(text);
		var msg = '{type: "MESSAGE", messageType:"text/plain", notice: true, textMessage: "' + text + '"}';
		ws.send(msg);  
		console.log(msg);
		}
</script>
</head>

<body>
	<h1>WEBSOCKET</h1>
	<hr>
	<div>
		userId: 29 | 434
		<textarea rows="1" cols="20" id="room">place/6/</textarea>
		<button onclick="initWebSocket();">connect</button>
	</div>
	<hr>
	<div id="result"></div>
	<textarea rows="5" cols="20" id="msg"></textarea>
	<br>
	<button onclick="send()">send</button>
</body>
</html>
