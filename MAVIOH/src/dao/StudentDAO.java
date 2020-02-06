package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Student;
import resource.DBStatus;
import resource.ExitStatus;
import setting.Setting;

/**
 * DBの生徒テーブルを担当するDAO
 * @author kkiku
 */
public class StudentDAO {

	/**
	 * DBに生徒を保存する関数
	 * @param student 保存する生徒
	 * @return 関数の終了ステータス
	 */
	public static boolean create(Student student) {
		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			// SQL文を用意
			String sql = "INSERT INTO STUDENTS (NAME, GRADE, GENDER, SCHOOL, DEVIATION_VALUE) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getGrade());
			preparedStatement.setInt(3, student.getGender());
			preparedStatement.setString(4, student.getSchool());
			preparedStatement.setDouble(5, student.getDeviationValue());

			// SQL文を実行
			int resultSet = preparedStatement.executeUpdate();

			if (resultSet == DBStatus.UPDATE_SUCCESS) {
				//##################################
				System.out.println("UPDATE成功");

				return ExitStatus.NORMAL;
			}
			else {
				//##################################
				System.out.println("UPDATE失敗");

				return ExitStatus.ABNORMAL;
			}
		}
		catch (SQLException exception) {
			exception.printStackTrace();
			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * DBの生徒を更新する関数
	 * @param student 更新する生徒
	 * @return 関数の終了ステータス
	 */
	public static boolean update(Student student) {
		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			// SQL文を用意
			String sql = "UPDATE STUDENTS SET NAME=?, GRADE=?, GENDER=?, SCHOOL=?, DEVIATION_VALUE=? WHERE ID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getGrade());
			preparedStatement.setInt(3, student.getGender());
			preparedStatement.setString(4, student.getSchool());
			preparedStatement.setDouble(5, student.getDeviationValue());
			preparedStatement.setInt(6, student.getId());

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
	 * IDをもとに生徒を読み込む関数
	 * @param id 生徒のID
	 * @return 生徒
	 */
	public static Student read(int id) {
		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			// SQL文を用意
			String sql = "SELECT * FROM STUDENTS WHERE ID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			// SQL文を実行
			ResultSet resultSet = preparedStatement.executeQuery();

			// 検索結果からインスタンスを生成
			if (resultSet.next() == DBStatus.RECORD_EXIST) {
				String name = resultSet.getString("NAME");
				int grade = resultSet.getInt("GRADE");
				int gender = resultSet.getInt("GENDER");
				String school = resultSet.getString("SCHOOL");
				double deviationValue = resultSet.getDouble("DEVIATION_VALUE");

				Student student = new Student(id, name, grade, gender, school, deviationValue);

				return student;
			}  // 生徒を読み込めない場合
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
	 * DBから生徒テーブルの全レコードを取得する関数
	 * @return 生徒テーブルの全レコード
	 */
	public static List<Student> readAll() {
		List<Student> studentList = new ArrayList<>();  // 生徒テーブルの全レコードの格納場所

		// DBに接続
		try (Connection connection = DriverManager.getConnection(
				Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			// SQL文を用意
			String sql = "SELECT * FROM STUDENTS ORDER BY ID";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			// 検索
			ResultSet resultSet = preparedStatement.executeQuery();

			// 検索結果をすべて格納
			while (resultSet.next() == DBStatus.RECORD_EXIST) {
				int id = resultSet.getInt("ID");
				String name = resultSet.getString("NAME");
				int grade = resultSet.getInt("GRADE");
				int gender = resultSet.getInt("GENDER");
				String school = resultSet.getString("SCHOOL");
				double deviationValue = resultSet.getDouble("DEVIATION_VALUE");

				// 生徒をリストに格納
				Student student = new Student(id, name, grade, gender, school, deviationValue);
				studentList.add(student);
			}

		}  // DBに接続できない場合
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return null;
		}
		return studentList;
	}

	/**
	 * DBの生徒を削除する関数
	 * @param id ID(主キー)
	 * @return
	 */
	public static boolean delete(int id) {
		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			// SQL文を用意
			String sql = "DELETE FROM STUDENTS WHERE ID=?";
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
