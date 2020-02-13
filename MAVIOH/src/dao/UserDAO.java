package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import resource.DBStatus;
import setting.Setting;


/**
 * ユーザーテーブルを担当するDAO
 * @author kkiku
 */
public class UserDAO {
	/**
	 * DBから指定のユーザーを取得する関数
	 * @param userName ユーザー名
	 * @param argumentPassword パスワード
	 * @return 指定したユーザーが存在するか
	 */
	public static User find(String userName, String password) {
		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {
			String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();

			// ユーザーが見つかった場合
			if (resultSet.next() == DBStatus.RECORD_EXIST) {
				int id = resultSet.getInt("ID");
				User user = new User(id, userName, password);

				return user;
			}  // ユーザーが見つからなかった場合
			else {
				return null;
			}

		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return null;
		}
	}

	/**
	 * 全ユーザー関数
	 * @return 全ユーザーのリスト
	 */
	public static List<User> readAll() {
		List<User> userList = new ArrayList<>();  // 全ユーザーを格納するリスト

		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {
			String sql = "SELECT * FROM USERS";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();

			// 取得した全ユーザーを格納
			while (resultSet.next() == DBStatus.RECORD_EXIST) {
				int id = resultSet.getInt("ID");
				String name = resultSet.getString("NAME");
				String password = resultSet.getString("password");

				User user = new User(id, name, password);
				userList.add(user);
			}
			return userList;
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return null;
		}
	}

}
