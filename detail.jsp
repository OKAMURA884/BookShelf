<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String[] counts=(String[])session.getAttribute("counts"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/BookShelf/css/detail3.css">
<script src="js/detail.js"></script>
<title>Livre</title>

</head>
<body>

	<header>
		<div class="inner">
			<h1>詳細</h1>
			<nav>
				<ul>
					<li><div class="button-panel">
							<form action="/BookShelf/MainServlet" method="get">
								<input class="button2" type="submit" value="マイページへ">
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
<div class="container">
<main>
<div class="form-wrapper">
<div class="form-item">
<div class="font">
	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>
	</div>

	<c:if test="${book.image!=null}">
		<img src="<c:out value="${book.image}"/>">
		<input type="hidden" name="image" value=<c:out value="${book.image}"/>>
	</c:if>

<div class="button-panel">
	<form action="/BookShelf/DetailServlet2?action=update" method="post">
		<c:if test="${book.title!=null}">
			<p>タイトル：<c:out value="${book.title}" /><p>
			<input type="hidden" name="title" value="<c:out value="${book.title}"/>">
		</c:if>
		<c:if test="${book.title==null}">
			<p>タイトル：未入力</p>
		</c:if>

		<c:if test="${book.date!=null}">
			<p>発売日：<c:out value="${book.date}" /></p>
			<input type="hidden" name="date" value="<c:out value="${book.date}"/>">
		</c:if>
		<c:if test="${book.date==null}">
			<p>発売日：未入力</p>
		</c:if>

		<c:if test="${book.author!=null}">
			<p>著者：<c:out value="${book.author}" /></p>
		</c:if>
		<c:if test="${book.author==null}">
			<p>著者：未入力</p>
		</c:if>

		<c:if test="${book.register!=null}">
			<p>登録日：<c:out value="${book.register}" /></p>
		</c:if>
		<c:if test="${book.register==null}">
			<p>登録日：未入力</p>
		</c:if>


		<c:if test="${book.isbn!=null}">
			<p>ISBN：<c:out value="${book.isbn}" /></p>
		</c:if>
		<c:if test="${book.isbn==null}">
			<p>ISBN：未入力</p>
		</c:if>


		<c:choose>
			<c:when test="${book.status=='未読'}">
	状態：<input type="radio" name="status" value="未読" checked>未読
		  <input type="radio" name="status" value="既読">既読
		  <input type="radio" name="status" value="希望">希望
			
	</c:when>
			<c:when test="${book.status == '既読'}">
	状態：<input type="radio" name="status" value="未読">未読
		  <input type="radio" name="status" value="既読" checked>既読
		  <input type="radio" name="status" value="希望">希望

		</c:when>
			<c:otherwise>
	状態：<input type="radio" name="status" value="未読">未読
		  <input type="radio" name="status" value="既読">既読
		  <input type="radio" name="status" value="希望" checked>希望
		  </c:otherwise>
		</c:choose>

		<br>
		<c:if test="${book.price!=null}">
			<p>
				価格：
				<c:out value="${book.price}" />
			</p>
		</c:if>
		<c:if test="${book.price==null}">
			<p>
				価格：
				<c:out value="0" />
			</p>
		</c:if>


		<c:if test="${book.evaluation!=null}">
	評価：<input type="number" name="evaluation"
				value="<c:out value="${book.evaluation}"/>" placeholder="evaluation">
		</c:if>
		<c:if test="${book.evaluation==null}">
	評価：<input type="number" name="evaluation" value="0"
				placeholder="evaluation">
		</c:if>
	<br>

		<c:if test="${book.overview!=null}">
			<input type="hidden" name="overview" value="">

		</c:if>
		<c:if test="${book.overview==null}">
			<input type="hidden" name="overview" value="">


		</c:if>


		<c:if test="${book.review!=null}">
	レビュー：<input type="text" name="review" 
				value="<c:out value="${book.review}"/>" placeholder="review">
		</c:if>
		<c:if test="${book.review==null}">
	レビュー：<input type="text" name="review" value="未入力" placeholder="review">
		</c:if>

		<br> <input class="button3" type="submit" value="変更">
	</form></div>
	<br>
	<div class="button-panel">
	<form action="/BookShelf/DetailServlet2?action=delete" method="post">
		<input type="hidden" name="title"
			value="<c:out value="${book.title}"/>"> <input class="button3" type="submit"
			value="削除">
	</form></div>
	</div>
	</div>
	</main>
	<aside>
	<input type="hidden"id="unread"value="<%=counts[0] %>">
	<input type="hidden"id="read"value="<%=counts[1] %>">
	<input type="hidden"id="want"value="<%=counts[2] %>">
	<div class="aside-wrapper">
      <div class="chart-box">
        <canvas id="myChart"></canvas>
      </div>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js">
      </script>
      <script>
        var ctx = document.getElementById('myChart');
        let unread=document.getElementById('unread').value;
        let read=document.getElementById('read').value;
        let want=document.getElementById('unread').value;
        var myDoughnutChart = new Chart(ctx, {
          type: 'doughnut',
          data: {
            labels: ['未読', '既読', '読みたい'],
            datasets: [{
              data: [unread, read, want],
              backgroundColor: ["#053e66", "#2b91d9", "#78b3dd"]
            }]
          },
          options: {
          title: {
            display: true,
            text: '全件数内訳'
          }
        }
        });
      </script>
	</div>
	
	<div class="aside-wrapper">
	<div class="chart-box">
    <div id="chart">

    </div>
  </div>
  <script src="https://unpkg.com/frappe-charts@1.2.4/dist/frappe-charts.min.iife.js"></script>
  <script>
    const data={
      labels:["5日","6日","7日","8日","9日","10日","11日"],
      datasets:[
        {
          values:[3,0,1,12,8,11,0]
        }
      ]
    }
    const chart = new frappe.Chart("#chart",{
      title:"書籍登録数",
      data:data,
      type:'line'
    })
  </script>
	
	</div>
	</aside>
	</div>




</body>
</html>