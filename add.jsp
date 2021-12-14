<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livre</title>
<link rel="stylesheet" href="css/add5.css">
<script src="js/add3.js"></script>
</head>
<body>
	<header>
		<div class="inner">
			<h1>本の追加</h1>
			<nav>
				<ul>
					<li><div class="button-panel">
						<form action="/BookShelf/MainServlet">
							<input type="hidden" name="action" value="myPage"> <input
								class="button2" type="submit" value="マイページへ">
						</form>
					</div></li>
					<li><div class="button-panel">
						<form action="/BookShelf/LogoutServlet" method="get">
							<input class="button4" type="submit" value="ログアウト">
						</form>
					</div></li>
				</ul>
			</nav>
		</div>
	</header>

	<div class="form-wrapper">
		<h1>登録したい本を検索してください</h1>
		<c:if test="${not empty message}">
			<p class="message">${message}</p>
		</c:if>
		<form id="form">
			<ul>
				<li>
					<div class="form-item">
						<input type="text" id="isbn" placeholder="ISBN code">
					</div>
				</li>
				<li><div class="button-panel">
						<input type="button" class="button5" id="btn1" value="検索"
							onclick="clickBtn1()" />
					</div></li>
			</ul>
		</form>
		<form id="form2">
			<ul>
				<li>
					<div class="form-item">
						<input type="text" id="key" placeholder="keyword">
					</div>
				</li>
				<li>
					<div class="button-panel">
						<input type="button" class="button5" id="btn3" value="検索"
							onclick="clickBtn3()" />
					</div>
				</li>
			</ul>
		</form>
	</div>

	<div id="p1">
		<form action="/BookShelf/AddServlet" method="post">
			<div class="form-wrapper2">
				<h1>検索結果</h1>
				<div class="button-panel">
					<input type="button" class="button2" value="閉じる"
						onclick="clickBtn2()" />
				</div>
			</div>
			<table border="1" class="design">
				<tr>
					<th class="image">イメージ</th>
					<th class="title">タイトル</th>
					<th class="author">著者</th>
					<th class="price">価格</th>
					<th class="date">発売日</th>
					<th class="status">状態</th>
					<th class="add">追加</th>
				</tr>
				<tr>
					<td class="image"><img id="image2" src=""></img></td>
					<td class="title" id="title2">タイトル:</td>
					<td class="author" id="author2">著者:</td>
					<td class="price" id="price2">価格:</td>
					<td class="date" id="date2">発売日:</td>
					<td class="status"><input type="radio" name="status"
						value="未読" checked>未読<br> <input type="radio"
						name="status" value="既読">既読<br> <input type="radio"
						name="status" value="希望">希望<br></td>
					<td class="add"><input type="submit" value="追加"
						onclick="time()"></td>
				</tr>
			</table>

			<input type="hidden" name="title" value="" id="title"> <input
				type="hidden" name="image" value="" id="image"> <input
				type="hidden" name="author" value="" id="author"> <input
				type="hidden" name="input" value="" id="input"> <input
				type="hidden" name="price" value="" id="price"> <input
				type="hidden" name="date" value="" id="date"> <input
				type="hidden" name="register" value="" id="register">

		</form>
	</div>

	<div id="p2">
		<div class="form-wrapper2">
			<h1>keywordから検索</h1>
			<div class="button-panel">
				<input type="button" class="button2" value="閉じる"
					onclick="clickBtn4()" />
			</div>
		</div>
		<div id="keyResult"></div>
		<div class="button-panel">
			<input type="button" class="button2" value="閉じる"
				onclick="clickBtn4()" />
		</div>
	</div>



</body>
</html>