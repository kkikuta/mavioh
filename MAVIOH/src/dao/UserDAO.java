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
		// DBに接続
		try (Connection connection = DriverManager.getConnection(
				Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {
			// SQL分を用意
			String sql = "SELECT * FROM USERS WHERE name = ? AND password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);

			// 検索
			ResultSet resultSet = preparedStatement.executeQuery();

			// ユーザーが見つかった場合
			if (resultSet.next() == DBStatus.RECORD_EXIST) {
				// 不足しているレコードを取得
				int id = resultSet.getInt("ID");

				User user = new User(id, userName, password);

				return user;
			}  // ユーザーが見つからなかった場合
			else {
				return null;
			}

		}  // DBに接続できない場合
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
		List<User> userList = new ArrayList<>();
		// DBに接続
		try (Connection connection = DriverManager.getConnection(
				Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {
			// SQL分を用意
			String sql = "SELECT * FROM USERS";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			// 検索
			ResultSet resultSet = preparedStatement.executeQuery();

			// ユーザーが見つかった場合
			while (resultSet.next() == DBStatus.RECORD_EXIST) {
				int id = resultSet.getInt("ID");
				String name = resultSet.getString("NAME");
				String password = resultSet.getString("password");

				User user = new User(id, name, password);
				userList.add(user);

				//#############################################
				for (User userD : userList) {
					System.out.println(userD.getId());
					System.out.println(userD.getName());
				}
			}
			return userList;

		}  // DBに接続できない場合
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return null;
		}
	}

}
