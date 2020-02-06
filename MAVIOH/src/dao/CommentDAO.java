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

		// DBに接続
		try (Connection connection = DriverManager.getConnection(
				Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			// SQL文を用意
			String sql = "SELECT * FROM COMMENTS ORDER BY ID DESC";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			// 検索
			ResultSet resultSet = preparedStatement.executeQuery();

			// 検索結果をすべて格納
			while (resultSet.next() == DBStatus.RECORD_EXIST) {
				// 全てのキーを取得
				int id = resultSet.getInt("ID");
				String title = resultSet.getString("TITLE");
				String body = resultSet.getString("BODY");
				int userId = resultSet.getInt("USER_ID");
				String postTime = resultSet.getString("POST_TIME");

				// コメントをリストに格納
				Comment comment = new Comment(id, title, body, userId, postTime);
				commentList.add(comment);
			}

		}  // DBに接続できない場合
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
			// SQL文を用意
			String sql = "INSERT INTO COMMENTS (TITLE, BODY, USER_ID, POST_TIME) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, comment.getTitle());
			preparedStatement.setString(2, comment.getBody());
			preparedStatement.setInt(3, comment.getUserId());
			preparedStatement.setString(4, comment.getPostTime());

			//SQL文を実行
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
