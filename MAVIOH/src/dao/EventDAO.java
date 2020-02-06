package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Event;
import resource.DBStatus;
import resource.ExitStatus;
import setting.Setting;

/**
 * イベントテーブルを担当するDAO
 * @author kkiku
 */
public class EventDAO {
	/**
	 * DBに新しいイベントを追加する関数
	 * @param event 追加するイベント
	 * @return 関数の終了ステータス
	 */
	public static boolean create(Event event) {
		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			// SQL文を用意
			String sql = "INSERT INTO EVENTS (TITLE, DETAIL, DATE) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, event.getTitle());
			preparedStatement.setString(2, event.getDetail());
			preparedStatement.setDate(3, event.getDate());

			// SQL文を実行
			int resultSet = preparedStatement.executeUpdate();

			if (resultSet == DBStatus.UPDATE_SUCCESS) {
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

	/**
	 * DBのイベントを更新する関数
	 * @param event 更新するイベント
	 * @return 関数の終了ステータス
	 */
	public static boolean update(Event event) {
		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			// SQL文を用意
			String sql = "UPDATE EVENTS SET TITLE=?, DETAIL=? WHERE DATE=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, event.getTitle());
			preparedStatement.setString(2, event.getDetail());
			preparedStatement.setDate(3, event.getDate());

			// SQL文を実行
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

	/**
	 * 指定した日にちのイベントを取得する関数
	 * @param year 年
	 * @param month 月
	 * @param date 日
	 * @return イベントまたはイベントが見つからなければ null
	 */
	public static Event read(int year, int month, int date) {
		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			// DBの日付に対応する書式の文字列を作成
			final String format = "%4d-%02d-%02d";
			String stringDate = String.format(format, year, month, date);

			// SQL文を用意
			String sql = "SELECT * FROM EVENTS WHERE DATE >= ? AND DATE <= ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, stringDate);
			preparedStatement.setString(2, stringDate);

			// SQL文を実行
			ResultSet resultSet = preparedStatement.executeQuery();

			// 検索結果を配列に格納する
			if (resultSet.next() == DBStatus.RECORD_EXIST) {
				int id = resultSet.getInt("ID");
				String title = resultSet.getString("TITLE");
				String detail = resultSet.getString("DETAIL");
				Date sqlDate= resultSet.getDate("DATE");

				Event event = new Event(id, title, detail, sqlDate);

				return event;
			}  // イベントがない場合
			else {
				return null;
			}

		}
		catch (SQLException exception) {
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * 指定したIDのイベントを取得する関数
	 * @param id イベントのID
	 * @return イベントまたはイベントが見つからなければ null
	 */
	public static Event read(int id) {
		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			// SQL文を用意
			String sql = "SELECT * FROM EVENTS WHERE ID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			// SQL文を実行
			ResultSet resultSet = preparedStatement.executeQuery();

			// 検索結果を配列に格納する
			if (resultSet.next() == DBStatus.RECORD_EXIST) {
				String title = resultSet.getString("TITLE");
				String detail = resultSet.getString("DETAIL");
				Date date= resultSet.getDate("DATE");

				Event oldEvent = new Event(id, title, detail, date);

				return oldEvent;
			}  // イベントがない場合
			else {
				return null;
			}

		}
		catch (SQLException exception) {
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * DBのイベントを削除する関数
	 * @param id
	 * @return
	 */
	public static boolean delete(int id) {
		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			// SQL文を用意
			String sql = "DELETE FROM EVENTS WHERE ID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			// SQL文を実行
			int resultSet = preparedStatement.executeUpdate();

			if (resultSet == DBStatus.UPDATE_SUCCESS) {
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
