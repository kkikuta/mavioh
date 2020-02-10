package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;


/**
 * ユーザーテーブルを担当するDAO
 * @author kkiku
 */
public class UserDAO {
	/**
	 * 全ユーザー関数
	 * @return 全ユーザーのリスト
	 * @throws ClassNotFoundException
	 */
	public static List<User> readAll() throws ClassNotFoundException {
		//Class.forName("org.postgresql.Driver");
		List<User> userList = new ArrayList<>();
		// DBに接続
		try (Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://ec2-184-72-235-159.compute-1.amazonaws.com:5432d8qejs8r8gc4q", "iyxltwhsqxuyqb", "156598e79afc61725fab4e61184d5f5c870d8ea802ec47d38aeeb27558a088b5")) {
			// PostgreSQL JDBC ドライバロード
		    //Class.forName("org.postgresql.Driver");


			// SQL分を用意
			String sql = "SELECT * FROM USERS";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			// 検索
			ResultSet resultSet = preparedStatement.executeQuery();

			// ユーザーが見つかった場合
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String password = resultSet.getString("password");

				User user = new User(id, name, password);
				userList.add(user);
			}
			return userList;

		}  // DBに接続できない場合
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return null;
		}
	}

}
