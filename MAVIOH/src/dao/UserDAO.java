package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
