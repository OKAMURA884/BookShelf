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
import dao.UserDAO;
import model.Book;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */// ログインへの画面遷移
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// データーベースにユーザーが登録されているかの判定
		// ログイン時の変数(name, pass)
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String path = "";
		// userインスタンスをUserDAOクラスに代入
		User user = new User(name, pass);
		UserDAO userDAO = new UserDAO();
		BookDAO bookDAO = new BookDAO();
		List<Book> list = bookDAO.findAll(user);
		// ログイン時のユーザー判定
		boolean login = userDAO.check(user);
		HttpSession session = request.getSession();
		if (login && list.size()!=0) {
			session.setAttribute("user", user); // ログインの成功時のユーザー保存
			session.setAttribute("list", list);
			path = "/WEB-INF/jsp/myPage.jsp";
		} else if (login && list.size()==0) {
			session.setAttribute("user", user); // ログインの成功時のユーザー保存
			request.setAttribute("message", "登録情報がありません");
			path = "/WEB-INF/jsp/myPage.jsp";
		} else {
			request.setAttribute("message", "ユーザー名またはパスワードの入力に誤りがあります");
			path = "/WEB-INF/jsp/login.jsp";
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

}
