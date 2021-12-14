document.addEventListener('DOMContentLoaded', function() {
	document.getElementById("p1").style.display = "none";
	document.getElementById("p2").style.display = "none";
	document.getElementById("form").onkeypress = (e) => {
		// form1に入力されたキーを取得
		const key = e.keyCode || e.charCode || 0;
		// 13はEnterキーのキーコード
		if (key == 13) {
			// アクションを行わない
			e.preventDefault();
		}
	}
	document.getElementById("form2").onkeypress = (e) => {
		// form1に入力されたキーを取得
		const key = e.keyCode || e.charCode || 0;
		// 13はEnterキーのキーコード
		if (key == 13) {
			// アクションを行わない
			e.preventDefault();
		}
	}
}, false);
function time(){
        let now = new Date();
        let year = now.getFullYear();
        let month = now.getMonth() + 1;
        let day = now.getDate();
        let hh = now.getHours();
        let mm = now.getMinutes();
        let ss = now.getSeconds();
        if (0 <= month && month <= 9) {
            month = '0' + month;
        }
        if (0 <= day && day <= 9) {
            day = '0' + day;
        }
        if (0 <= hh && hh <= 9) {
            hh = '0' + hh;
        }
        if (0 <= mm && mm <= 9) {
            mm = '0' + mm;
        }
        if (0 <= ss && ss <= 9) {
            ss = '0' + ss;
        }
        document.getElementById("register").value = year + "" + month + "" + day + "" + hh + "" + mm + "" + ss;
}
function setTime(){
        let now = new Date();
        let year = now.getFullYear();
        let month = now.getMonth() + 1;
        let day = now.getDate();
        let hh = now.getHours();
        let mm = now.getMinutes();
        let ss = now.getSeconds();
        if (0 <= month && month <= 9) {
            month = '0' + month;
        }
        if (0 <= day && day <= 9) {
            day = '0' + day;
        }
        if (0 <= hh && hh <= 9) {
            hh = '0' + hh;
        }
        if (0 <= mm && mm <= 9) {
            mm = '0' + mm;
        }
        if (0 <= ss && ss <= 9) {
            ss = '0' + ss;
        }
        return year + "" + month + "" + day + "" + hh + "" + mm + "" + ss;
}
//初期表示は非表示
//document.getElementById("p1").style.display ="none";
function clickBtn1() {
	const p1 = document.getElementById("p1");
	const id = "1044715390094874245";

	p1.style.display = "block";
	const isbn = document.getElementById("form").isbn.value;
	console.log(isbn);
	isbnSearch(isbn, id);
	//9784295005940,1044715390094874245
}

function clickBtn2() {
	const p1 = document.getElementById("p1");
	p1.style.display = "none";
}
async function isbnSearch(isbn, id) {
	const res = await fetch("https://app.rakuten.co.jp/services/api/BooksBook/Search/20170404" +
		"?format=json&isbn=" + isbn + "&applicationId=" + id);
	const bookInfo = await res.json();
	console.log(bookInfo);
	//title
	const title = bookInfo.Items[0].Item.title;
	document.getElementById("title2").textContent = title;
	document.getElementById("title").value = title;
	//image
	const image = bookInfo.Items[0].Item.smallImageUrl;
	document.getElementById("image2").src = image;
	document.getElementById("image").value = image;
	//author
	const author = bookInfo.Items[0].Item.author;
	document.getElementById("author2").textContent = author;
	document.getElementById("author").value = author;
	//isbn
	document.getElementById("isbn").value = isbn;
	//price
	const price = bookInfo.Items[0].Item.itemPrice;
	document.getElementById("price").value = price;
	document.getElementById("price2").textContent = price;
	//date
	const date = bookInfo.Items[0].Item.salesDate;
	document.getElementById("date2").textContent = date;
	document.getElementById("date").value = date;
	//register
};
async function keySearch(key, id,time) {
	//https://app.rakuten.co.jp/services/api/BooksBook/Search/20170404
	//?format=json&title=%E5%A4%AA&applicationId=1028750289667141358
	const res = await fetch("https://app.rakuten.co.jp/services/api/BooksBook/Search/20170404" +
		"?format=json&title=" + key + "&applicationId=" + id);
	const bookInfo = await res.json();
	console.log(bookInfo);
	const keyResult = document.getElementById("keyResult");
	keyResult.innerHTML = "";
	for (let item of bookInfo.Items) {
		const isbn = item.Item.isbn;
		const image = item.Item.smallImageUrl;
		const title = item.Item.title;
		const author = item.Item.author;
		const price = item.Item.itemPrice;
		const date = item.Item.salesDate;
		// servletでactionからisbnをもとに検索することでマイページに追加する
		keyResult.innerHTML += "<form action=\"/BookShelf/AddServlet\"method=\"post\">"
			+ "<p><img src=" + image + "></p>"
			+ "<p>title:" + title + "</p>"
			+ "<p>author:" + author + "</p>"
			+ "<p>price:" + price + "</p>"
			+ "<p>date:" + date + "</p>"
			+"<input type=\"hidden\"name=\"title\"value=\""+title+"\"id=\"title\">"
            +"<input type=\"hidden\"name=\"image\"value=\""+image+"\"id=\"image\">"
            +"<input type=\"hidden\"name=\"author\"value=\""+author+"\"id=\"author\">"
            +"<input type=\"hidden\"name=\"isbn\"value=\""+isbn+"\"id=\"isbn\">"
            +"<input type=\"hidden\"name=\"price\"value=\""+price+"\"id=\"price\">"
            +"<input type=\"hidden\"name=\"date\"value=\""+date+"\"id=\"date\">"
            +"<input type=\"hidden\"name=\"register\"value=\""+time+"\"id=\"register\">"
            +"<input type=\"radio\"name=\"status\"value=\"未読\"checked>未読"
            +"<input type=\"radio\"name=\"status\"value=\"既読\">既読"
            +"<input type=\"radio\"name=\"status\"value=\"希望\">希望"
			+ "<p><input type=\"submit\"value=\"追加\"></p>"
			+ "</form>";
	}
};
function clickBtn3() {
	const p2 = document.getElementById("p2");
	const id = "1044715390094874245";

	p2.style.display = "block";
	const key = encodeURI(document.getElementById("form2").key.value);
	console.log(key);
	const time = setTime();
	keySearch(key, id,time);
	//9784295005940,1044715390094874245
}

function clickBtn4() {
	const p2 = document.getElementById("p2");
	p2.style.display = "none";
}
