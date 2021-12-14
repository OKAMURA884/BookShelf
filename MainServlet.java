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
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
  //add.jspファイルの画面遷移
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		 request.getRequestDispatcher("/WEB-INF/jsp/myPage.jsp").forward(request, response);
//		}else if(action.equals("add")){
//			request.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//本棚内でのキーワード検索//myPageへ遷移
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String keyword=request.getParameter("search");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		BookDAO bookDAO = new BookDAO();
		List<Book> list=bookDAO.search(user, keyword);
		session.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/jsp/myPage.jsp").forward(request, response);
	 }
}
