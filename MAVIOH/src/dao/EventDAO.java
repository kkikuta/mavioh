package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

			String sql = "INSERT INTO events (title, detail, date) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, event.getTitle());
			preparedStatement.setString(2, event.getDetail());
			preparedStatement.setDate(3, event.getDate());

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
	 * DBのイベントを削除する関数
	 * @param id
	 * @return
	 */
	public static boolean delete(int id) {
		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			String sql = "DELETE FROM events WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

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

			String sql = "UPDATE events SET title = ?, detail = ? WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, event.getTitle());
			preparedStatement.setString(2, event.getDetail());
			preparedStatement.setInt(3, event.getId());

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
	 * @return イベントのリスト
	 */
	public static List<Event> read(int year, int month, int date) {
		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			List<Event> eventList = new ArrayList<>();

			// DBの日付に対応する書式の文字列を作成
			final String format = "%4d-%02d-%02d";
			String stringDate = String.format(format, year, month, date);
			Date sqlDateForSQL = Date.valueOf(stringDate);

			String sql = "SELECT * FROM events WHERE date >= ? AND date <= ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDate(1, sqlDateForSQL);
			preparedStatement.setDate(2, sqlDateForSQL);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next() == DBStatus.RECORD_EXIST) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String detail = resultSet.getString("detail");
				Date sqlDate= resultSet.getDate("date");

				Event event = new Event(id, title, detail, sqlDate);

				eventList.add(event);
			}

			return eventList;
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

			String sql = "SELECT * FROM EVENTS WHERE ID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next() == DBStatus.RECORD_EXIST) {
				String title = resultSet.getString("TITLE");
				String detail = resultSet.getString("DETAIL");
				Date date= resultSet.getDate("DATE");

				Event oldEvent = new Event(id, title, detail, date);

				return oldEvent;
			}
			else {
				return null;
			}
		}
		catch (SQLException exception) {
			exception.printStackTrace();
			return null;
		}
	}

}
