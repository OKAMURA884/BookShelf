package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import model.Book;
import model.User;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// myPage.jspからadd.jspへ画面遷移
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String name = user.getName();
		
		String title = request.getParameter("title");
		String image = request.getParameter("image");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		if(isbn==null) {
			isbn=request.getParameter("input");
		}
		String price = request.getParameter("price");
		String date = request.getParameter("date");
		String register = request.getParameter("register");
		String status = request.getParameter("status");
		
		Book book = new Book(name, image, title, author, isbn, price, date, register, status);

		BookDAO bookDAO = new BookDAO();
		int count = bookDAO.add(book);
		// countが１ならば、add.jspへ画面遷移
		if (count == 1) {
			List<Book> list= bookDAO.findAll(user);
			session.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/jsp/myPage.jsp").forward(request, response);
			// countができなければ、add.jspの画面
		} else if(count==-1){
			request.setAttribute("message", "タイトルが同じ書籍は追加できません");
			request.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(request, response);
		}else {
			request.setAttribute("message", "追加できませんでした");
			request.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(request, response);
		}
	}

}
