<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Senior Entertainment</title>
	<link rel="icon" href='image/logo1.jpg'/>
	<link rel="stylesheet" href="StyleSheet.css"/>
</head>
<body>
	<div id="sidebody">
		<div id="header">
			<a href="search.jsp"><img src="image/logo1.jpg" border="0" width="100px"></a>
			<hr size="3px">
			<br>
	</div>
	<div id="content1">
			<div>
				<h1 id="bigtext">Searching Result</h1>
				<%
				String[][] orderList = (String[][])  request.getAttribute("query");
				for(int i =0 ; i < orderList.length;i++){%>
					<a style="font-size:20px;font-famliy:monospace;" href='<%= orderList[i][1] %>'><%= orderList[i][0] %></a><br><h style="font-size:14px;font-famliy:monospace;color:grey;"><%= orderList[i][1] %></h><br><br>
				<%
				}
				%>
			</div>
			<br><br>
			<div>
				<h1 id="bigtext">Related Keyword</h1>
				<%
				String[][] reList = (String[][])  request.getAttribute("related");
				for(int i =0 ; i < reList.length;i++){%>
					<a style="font-size:15px;font-famliy:monospace;" href='<%= reList[i][1] %>'><%= reList[i][0] %></a><br>
				<%
				}
				%>
			</div>
		</div>
		<div id="footer">
		</div>
	</div>	
</body>
</html>