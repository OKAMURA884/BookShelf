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
 * Servlet implementation class DetailServlet
 */
@WebServlet("/DetailServlet2")
public class DetailServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String title = request.getParameter("title");

		BookDAO bookDAO = new BookDAO();

		Book book = bookDAO.detail(user, title);
		session.setAttribute("book", book);
		String[] counts=bookDAO.status(user);
		session.setAttribute("counts", counts);
		request.getRequestDispatcher("/WEB-INF/jsp/detail.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String name = user.getName();

		if (action.equals("delete")) {
			String title = request.getParameter("title");
			String image = request.getParameter("image");
			String author = request.getParameter("author");
			String isbn = request.getParameter("isbn");
			String price = request.getParameter("price");
			String date = request.getParameter("date");
			String register = request.getParameter("register");
			String status = request.getParameter("status");
			String evaluation = request.getParameter("evaluation");
			String overview = request.getParameter("overview");
			String review = request.getParameter("review");

			// bookインスタンス生成
			Book book = new Book(name, image, title, author, isbn, price, date, register, status, evaluation, overview,
					review);

			BookDAO dao = new BookDAO();

			// 書籍情報の削除メソッド
			int count = dao.delete(book);

			if (count == 1) {
				session.removeAttribute("list");
				List<Book> list = dao.findAll(user);
				session.setAttribute("list", list);
				request.getRequestDispatcher("/WEB-INF/jsp/myPage.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "削除できませんでした");
				request.getRequestDispatcher("/WEB-INF/jsp/detail.jsp").forward(request, response);
			}
		} else if (action.equals("update")) {
			request.setCharacterEncoding("UTF-8");
			String title = request.getParameter("title");
			String date = request.getParameter("date");
			String status = request.getParameter("status");
			String evaluation = request.getParameter("evaluation");
			String overview = request.getParameter("overview");
			String review = request.getParameter("review");
			// bookインスタンス生成
			Book book = new Book(name, title,date,status,evaluation,overview,review);

			BookDAO bookDAO = new BookDAO();
			
			// 本の追加登録
			int count = bookDAO.update(book);
			if (count == 1) {
				request.setAttribute("message", "更新しました");
				book = bookDAO.detail(user, title);
				session.setAttribute("book", book);
				List<Book> list = bookDAO.findAll(user);
				session.setAttribute("list", list);
				request.getRequestDispatcher("/WEB-INF/jsp/detail.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "更新できませんでした");
				request.getRequestDispatcher("/WEB-INF/jsp/detail.jsp").forward(request, response);

			}
		}
	}
}
