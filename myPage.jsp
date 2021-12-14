<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Book,java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/myPage5.css">
<title>Livre</title>
</head>
<body>

	<header>
		<div class="inner">
			<h1>マイページ</h1>
			<nav>
				<ul>
					<li><div class="button-panel">
							<form action="/BookShelf/AddServlet" method="get">

								<input class="button2" type="submit" value="追加画面へ">
							</form>
						</div></li>
					<li><div class="button-panel">
							<form action="/BookShelf/LogoutServlet" method="get">
								<input class="button4" type="submit" value="ログアウト">
							</form>
						</div></li>
					<li>
						<form id="form1" action="/BookShelf/MainServlet" method="post">
							<input id="sbox" type="text" name="search" placeholder="キーワード検索">
							<input id="sbtn" type="submit" value="検索">
						</form>
					</li>

				</ul>
			</nav>
		</div>
	</header>


<div class="container">
	<form action="/BookShelf/ListServlet?action=all" method="post">
		<div class="button-panel"><input type="submit"class="button3" value="全件検索"></div>
	</form>

	<form action="/BookShelf/ListServlet?action=unread" method="post">
		<div class="button-panel"><input type="submit"class="button3" value="未読リスト"></div>
	</form>

	<form action="/BookShelf/ListServlet?action=read" method="post">
		<div class="button-panel"><input type="submit"class="button3" value="既読リスト"></div>
	</form>

	<form action="/BookShelf/ListServlet?action=want" method="post">
		<div class="button-panel"><input type="submit"class="button3" value="読みたいリスト"></div>
	</form>
	</div>
	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>

	<c:if test="${not empty list}">

		<table border="1" class="design">
			<tr>
				<th>イメージ</th>
				<th>タイトル</th>
				<th>著者</th>
				<th>価格</th>
				<th>状態</th>
				<th>編集・詳細</th>
			</tr>


			<c:forEach var="book" items="${list}">
				<tr>
					<c:choose>
						<c:when test="${book.image!=null}">
							<td><img src="<c:out value="${book.image}"/>"></td>
						</c:when>
						<c:otherwise>
							<td>未入力</td>
						</c:otherwise>
					</c:choose>


					<c:choose>
						<c:when test="${book.title!=null}">
							<td><c:out value="${book.title}" /></td>
						</c:when>
						<c:otherwise>
							<td>未入力</td>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${book.author!=null}">
							<td><c:out value="${book.author}" /></td>
						</c:when>
						<c:otherwise>
							<td>未入力</td>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${book.price!=null}">
							<td><c:out value="${book.price}" /></td>
						</c:when>
						<c:otherwise>
							<td>未入力</td>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${book.status!=null}">
							<td><c:out value="${book.status}" /></td>
						</c:when>
						<c:otherwise>
							<td>未入力</td>
						</c:otherwise>
					</c:choose>

					<td><form action="/BookShelf/DetailServlet2">
							<div class="button-panel">
								<input type="hidden" name="title" value="${book.title}">
								<input class="button3" type="submit" value="詳細・編集">
							</div>
						</form></td>

				</tr>
			</c:forEach>



		</table>
	</c:if>


</body>
</html>