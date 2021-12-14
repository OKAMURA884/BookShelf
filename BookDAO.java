package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.User;

//Bookテーブルを扱うDAOクラス
public class BookDAO {
	private final String JDBC_URL = "jdbc:mysql://172.16.71.121:3306/groupwork";
	private final String DB_USER = "groupb";
	private final String DB_PASS = "groupbpass";

	// コンストラクタでドライバをロード
	public BookDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	// ユーザー情報に基づく書籍を全件取得するメソッド//引数(User user)
	public List<Book> findAll(User user) {
		// 書籍情報を保存するリスト
		List<Book> list = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement st = con
						.prepareStatement("select*from book where name = ?" + " order by register desc");) {
			st.setString(1, user.getName());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String image = rs.getString("image");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String isbn = rs.getString("isbn");
				String price = rs.getString("price");
				String date = rs.getString("date");
				String register = rs.getString("register");
				String status = rs.getString("status");
				String evaluation = rs.getString("evaluation");
				String overview = rs.getString("overview");
				String review = rs.getString("review");
				Book book = new Book(name, image, title, author, isbn, price, date, register, status, evaluation,
						overview, review);
				list.add(book);
			}
			return list;
		} catch (Exception e) {
			System.out.print(e);
			return list;
		}
	}

//	public static void main(String[]args) {
//		BookDAO bookDAO=new BookDAO();
//		User user = new User("yamada","pass");
//		List<Book> list=bookDAO.findAll(user);
//		for(Book book:list) {
//			System.out.println(book.getTitle());
//		}
//	}
	// 詳細を表示したい本一冊分の情報を取得するメソッド
	public Book detail(User user, String title) {
		Book book = new Book();
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement st = con.prepareStatement("select*from book where name = ?" + "and title = ?");) {
			st.setString(1, user.getName());
			st.setString(2, title);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String image = rs.getString("image");
				// String title = rs.getString("title");
				String author = rs.getString("author");
				String isbn = rs.getString("isbn");
				String price = rs.getString("price");
				String date = rs.getString("date");
				String register = rs.getString("register");
				String status = rs.getString("status");
				String evaluation = rs.getString("evaluation");
				String overview = rs.getString("overview");
				String review = rs.getString("review");
				book = new Book(name, image, title, author, isbn, price, date, register, status, evaluation, overview,
						review);
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		return book;
	}

//	public static void main(String[] args) {
//		BookDAO bookDAO = new BookDAO();
//		User user = new User("yamada", "pass");
//		Book book = bookDAO.detail(user, "SQL");
//		System.out.println(book.getName());
//		System.out.println(book.getTitle());
//		System.out.println(book.getStatus());
//	}
	// 書籍情報を追加できればレコード数を返すメソッド
	public int add(Book book) {
		int count = 0;
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement st = con.prepareStatement("insert into book values(?,?,?,?,?,?,?,?,?,?,?,?,?)");) {
			st.setString(1, book.getName());
			st.setString(2, book.getImage());
			st.setString(3, book.getTitle());
			st.setString(4, book.getAuthor());
			st.setString(5, book.getIsbn());
			st.setString(6, book.getPrice());
			st.setString(7, book.getDate());
			st.setString(8, book.getRegister());
			st.setString(9, book.getStatus());
			st.setString(10, book.getEvaluation());
			st.setString(11, book.getOverview());
			st.setString(12, book.getReview());
			st.setString(13, book.getName() + book.getTitle());
			count = st.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			count = -1;
		} catch (Exception e) {
			System.out.print(e);
		}
		return count;
	}

//	public static void main(String[]args) {
//		Book book =new Book("yamada",null,"エルモア",null,null,null,null,null,"未読",null,null,null);
//		BookDAO bookDAO =new BookDAO();
//		int count = bookDAO.add(book);
//		System.out.print(count);
//	}
	// 書籍情報を削除するメソッド
	public int delete(Book book) {
		int count = 0;
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement st = con.prepareStatement("delete from book where usertitle = ?");) {
			st.setString(1, book.getName() + book.getTitle());
			count = st.executeUpdate();

		} catch (SQLException e) {
			System.out.print(e);
		}
		return count;
	}

//	public static void main(String[]args) {
//		BookDAO bookDAO=new BookDAO();
//		User user = new User("yamada","pass");
//		Book book = bookDAO.detail(user, "サーブレット");
//		int count = bookDAO.delete(book);
//		System.out.print(count);
//	}
	// 書籍情報を更新するメソッド
	public int update(Book book) {
		int count = 0;
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement st = con.prepareStatement("update book set date = ?,status = ? "
						+ " ,evaluation = ? ,overview = ?,review = ? where usertitle = ?");) {

			st.setString(1, book.getDate());
			st.setString(2, book.getStatus());
			st.setString(3, book.getEvaluation());
			st.setString(4, book.getOverview());
			st.setString(5, book.getReview());
			st.setString(6, book.getName() + book.getTitle());
			count = st.executeUpdate();
		} catch (Exception e) {
			System.out.print(e);
		}
		return count;
	}

	public static void main(String[] args) {
		BookDAO bookDAO = new BookDAO();
		Book book = new Book("yamada", "はじめてのPowerPoint 2019", "2019年02月23日頃", "未読", "2", "a", "yokiyoki");
		int count = bookDAO.update(book);
		System.out.print(count);
	}

	// 書籍を検索するメソッド
	public List<Book> search(User user, String keyword) {
		List<Book> list = new ArrayList<>();
		if (keyword == null) {
			keyword = "";
		}
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement st = con
						.prepareStatement("select * from book where name = ? and  (title like ? or author like ?)");) {
			st.setString(1, user.getName());
			st.setString(2, "%" + keyword + "%");
			st.setString(3, "%" + keyword + "%");

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String image = rs.getString("image");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String isbn = rs.getString("isbn");
				String price = rs.getString("price");
				String date = rs.getString("date");
				String register = rs.getString("register");
				String status = rs.getString("status");
				String evaluation = rs.getString("evaluation");
				String overview = rs.getString("overview");
				String review = rs.getString("review");
				Book book = new Book(name, image, title, author, isbn, price, date, register, status, evaluation,
						overview, review);
				list.add(book);
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		return list;
	}

//	public static void main(String[] args) {
//		BookDAO bookDAO = new BookDAO();
//		User user = new User("yamada","pass");
//		List<Book> list = bookDAO.search(user, null);
//		for(Book book:list) {
//			System.out.println(book.getTitle());
//		}
//	}
	public List<Book> findUnread(User user) {
		// 書籍情報を保存するリスト
		List<Book> list = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement st = con.prepareStatement("select*from book where name = ? and status ='未読'");) {
			st.setString(1, user.getName());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String image = rs.getString("image");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String isbn = rs.getString("isbn");
				String price = rs.getString("price");
				String date = rs.getString("date");
				String register = rs.getString("register");
				String status = rs.getString("status");
				String evaluation = rs.getString("evaluation");
				String overview = rs.getString("overview");
				String review = rs.getString("review");
				Book book = new Book(name, image, title, author, isbn, price, date, register, status, evaluation,
						overview, review);
				list.add(book);
			}
			return list;
		} catch (Exception e) {
			System.out.print(e);
			return list;
		}
	}
//	public static void main(String[] args) {
//		BookDAO bookDAO = new BookDAO();
//		User user = new User("yamada","pass");
//		List<Book> list =  bookDAO.findWant(user);
//		for(Book book:list) {
//		System.out.println(book.getTitle());}
//	}

	public List<Book> findRead(User user) {
		// 書籍情報を保存するリスト
		List<Book> list = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement st = con.prepareStatement("select*from book where name = ? and status ='既読'");) {
			st.setString(1, user.getName());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String image = rs.getString("image");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String isbn = rs.getString("isbn");
				String price = rs.getString("price");
				String date = rs.getString("date");
				String register = rs.getString("register");
				String status = rs.getString("status");
				String evaluation = rs.getString("evaluation");
				String overview = rs.getString("overview");
				String review = rs.getString("review");
				Book book = new Book(name, image, title, author, isbn, price, date, register, status, evaluation,
						overview, review);
				list.add(book);
			}
			return list;
		} catch (Exception e) {
			System.out.print(e);
			return list;
		}
	}

	public List<Book> findWant(User user) {
		// 書籍情報を保存するリスト
		List<Book> list = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement st = con.prepareStatement("select*from book where name = ? and status ='希望'");) {
			st.setString(1, user.getName());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String image = rs.getString("image");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String isbn = rs.getString("isbn");
				String price = rs.getString("price");
				String date = rs.getString("date");
				String register = rs.getString("register");
				String status = rs.getString("status");
				String evaluation = rs.getString("evaluation");
				String overview = rs.getString("overview");
				String review = rs.getString("review");
				Book book = new Book(name, image, title, author, isbn, price, date, register, status, evaluation,
						overview, review);
				list.add(book);
			}
			return list;
		} catch (Exception e) {
			System.out.print(e);
			return list;
		}
	}
	public String[] status(User user) {
		String[] counts= {"","",""}; 
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement st = con.prepareStatement("select count(status) from book where name = ? and status = '未読'");) {
			st.setString(1, user.getName());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String count = rs.getString("count(status)");
				counts[0]=count;
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement st = con.prepareStatement("select count(status) from book where name = ? and status = '既読'");) {
			st.setString(1, user.getName());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String count = rs.getString("count(status)");
				counts[1]=count;
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement st = con.prepareStatement("select count(status) from book where name = ? and status = '希望'");) {
			st.setString(1, user.getName());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String count = rs.getString("count(status)");
				counts[2]=count;
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		return counts;
	}
//	public static void main(String[] args) {
//		BookDAO bookDAO = new BookDAO();
//		User user = new User("yamada","pass");
//		String[] counts =  bookDAO.status(user);
//		for(String count:counts) {
//		System.out.println(count);}
//	}

}
