<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
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
			</div>
			<div id="sidebar_left">
			</div>
			<div id="sidebar_right">
			</div>
			<div id="content">
				<a href="search.jsp"><img style="size:180px;" src="image/logo1.jpg" border="0" ></a>
				<br>
				<form action='Gui' method='get'>
					<input type='text' name='keyword' placeholder = 'Please enter keywords'/>
					<select class="select" name="kind" id="kind">
						<option value="">...</option>
						<option value="sports">sports</option>
						<option value="dating">dating</option>
						<option value="games">games</option>
						<option value="music">music</option>
					</select>
					<br><br>
					<input type='submit' value='submit' />
				</form>
			</div>
			<div id="footer">
			</div>
		</div>	
</body>
</html>