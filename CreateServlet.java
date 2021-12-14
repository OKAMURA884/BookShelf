package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/create.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//パラメータ取得
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String path ="";

		//Userインスタンス生成
		User user = new User(name, pass);
		//UserDAOインスタンス生成
		UserDAO userDAO = new UserDAO();

		//新規登録できるか判定する変数(isLogin)
		boolean isLogin = userDAO.create(user);
		//isLoginがtrueならば、login.jspへ画面遷移
		if (isLogin) {
			request.setAttribute("message", "新規登録できました");
			path="/WEB-INF/jsp/login.jsp";
			//isLoginがfalseならば、create.jspへ画面遷移
		} else {
			//メッセージをセッションに保存
			request.setAttribute("message", "このユーザー名は使用できません。ユーザー名を変更してください");
			path="/WEB-INF/jsp/create.jsp";

		} request.getRequestDispatcher(path).forward(request, response);
	}
}


