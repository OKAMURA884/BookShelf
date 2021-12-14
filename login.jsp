<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livre</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div class="form-wrapper">
		<h1>ログイン</h1>
		<%
		if (message != null) {
		%>
		<p><%=message%></p>
		<%
		}
		%>
		<form action="/BookShelf/LoginServlet" method="post">
			<div class="form-item">
				<input type="text" name="name" required placeholder="ユーザー名を入力して下さい">
			</div>
			<div class="form-item">
				<input type="password" name="pass" required placeholder="パスワードを入力して下さい">
			</div>
			<div class="button-panel">
				<input class="button"type="submit" value="ログイン">
			</div>
		</form>
		<div class="form-footer">
			<p>
				<a href="/BookShelf/CreateServlet">新規登録</a>
			</p>
		</div>
	</div>
</body>
</html>