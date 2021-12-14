<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String message = (String) request.getAttribute("message");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Livre</title>
<link rel="stylesheet" href="css/create.css">
</head>
<body>
	<div class="form-wrapper">
		<h1>新規登録</h1>
		<%
		if (message != null) {
		%>
		<p><%=message%></p>
		<%
		}
		%>
		<form action="/BookShelf/CreateServlet" method="post">
			<div class="text"><p>＊ユーザー名は10文字以内、半角英数字</p></div> 
			<div class="form-item">
				<input type="text" name="name" required placeholder="ユーザー名を入力して下さい">
			</div>
			<div class="text"><p>＊パスワードは16文字以内、半角英数字</p></div>
			<div class="form-item">
				<input type="password" name="pass" required
					placeholder="パスワードを入力して下さい">
			</div>
			<div class="button-panel">
				<input class="button" type="submit" value="新規登録">
			</div>
			<div class="form-footer">
				<p>
					<a href="/BookShelf/LoginServlet">ログイン画面へ</a>
				</p>
			</div>
		</form>
	</div>
</body>
</html>