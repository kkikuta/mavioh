package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Comment;
import resource.DBStatus;
import resource.ExitStatus;
import setting.Setting;


/**
 * コメントテーブルを担当するDAO
 * @author kkiku
 */
public class CommentDAO {
	/**
	 * DBからコメントテーブルの全レコードを取得する関数
	 * @return コメントテーブルの全レコード
	 */
	public static List<Comment> findAll() {
		List<Comment> commentList = new ArrayList<>();  // コメントテーブルの全レコードの格納場所

		try (Connection connection = DriverManager.getConnection(
				Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			String sql = "SELECT * FROM comments ORDER BY id DESC";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next() == DBStatus.RECORD_EXIST) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String body = resultSet.getString("body");
				int userId = resultSet.getInt("user_id");
				String postTime = resultSet.getString("post_time");

				Comment comment = new Comment(id, title, body, userId, postTime);
				commentList.add(comment);
			}

		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return null;
		}
		return commentList;
	}

	/**
	 * DBにコメントを保存する関数
	 * @param comment 保存するコメント
	 * @return 関数の終了ステータス
	 */
	public static boolean create(Comment comment) {
		try (Connection conn = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			String sql = "INSERT INTO comments (title, body, user_id, post_time) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, comment.getTitle());
			preparedStatement.setString(2, comment.getBody());
			preparedStatement.setInt(3, comment.getUserId());
			preparedStatement.setString(4, comment.getPostTime());

			int result = preparedStatement.executeUpdate();

			if (result == DBStatus.UPDATE_SUCCESS) {
				return ExitStatus.NORMAL;
			}
			else {
				return ExitStatus.ABNORMAL;
			}
		}
		catch (SQLException exception) {
			exception.printStackTrace();
			return ExitStatus.ABNORMAL;
		}
	}
}
