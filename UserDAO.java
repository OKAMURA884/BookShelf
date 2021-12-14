package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import model.Hash;
import model.User;

//Userテーブルを扱うDAOクラス
public class UserDAO {
	private final String JDBC_URL = "jdbc:mysql://172.16.71.121:3306/groupwork";
	private final String DB_USER = "groupb";
	private final String DB_PASS = "groupbpass";
	//コンストラクタでドライバをロード
	public UserDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	// ユーザーがログインできるか判定するメソッド
	public boolean check(User user) {
		Hash hash = new Hash();
		//ユーザーの情報からハッシュ値を取得
		String userHash = hash.setHash(user);
		//データベースからハッシュ値を保存する変数を準備
		String userDBHash = "";
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement st = con.prepareStatement("select * from user where name =?");) {
			st.setString(1, user.getName());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				userDBHash = rs.getString("hash");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userDBHash.equals(userHash);
	}
//	public static void main(String[]args) {
//		User user=new User("yamada","pass");
//		UserDAO userDAO=new UserDAO();
//		boolean tf=userDAO.check(user);
//		System.out.print(tf);
//	}

	// ユーザー登録が可能か判定するメソッド
	public boolean create(User user) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement st = con.prepareStatement("insert into user values(?,?,?)");) {
			Hash hash=new Hash();
			st.setString(1, user.getName());
			st.setString(2, user.getPass());
			st.setString(3, hash.setHash(user));
			con.setAutoCommit(false);
			st.executeUpdate();
			con.commit();
			return true;
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.print("同じユーザー名は使えません");
			return false;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}
	public static void main(String[]args) {
		User user=new User("satou","satou");
		UserDAO userDAO=new UserDAO();
		boolean tf=userDAO.create(user);
		System.out.print(tf);
	}

}
