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

			String sql = "INSERT INTO students (name, grade, gender, school, deviation_value) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getGrade());
			preparedStatement.setInt(3, student.getGender());
			preparedStatement.setString(4, student.getSchool());
			preparedStatement.setFloat(5, student.getDeviationValue());

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
	 * DBの生徒を削除する関数
	 * @param id ID(主キー)
	 * @return
	 */
	public static boolean delete(int id) {
		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			String sql = "DELETE FROM students WHERE id = ?";
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
	 * IDをもとに生徒を読み込む関数
	 * @param id 生徒のID
	 * @return 生徒
	 */
	public static Student read(int id) {
		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			String sql = "SELECT * FROM students WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next() == DBStatus.RECORD_EXIST) {
				String name = resultSet.getString("name");
				int grade = resultSet.getInt("grade");
				int gender = resultSet.getInt("gender");
				String school = resultSet.getString("school");
				float deviationValue = resultSet.getFloat("deviation_value");

				Student student = new Student(id, name, grade, gender, school, deviationValue);

				return student;
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

	/**
	 * DBから生徒テーブルの全レコードを取得する関数
	 * @return 生徒テーブルの全レコード
	 */
	public static List<Student> readAll() {
		List<Student> studentList = new ArrayList<>();  // 生徒テーブルの全レコードの格納場所

		try (Connection connection = DriverManager.getConnection(
				Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			String sql = "SELECT * FROM students ORDER BY id";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next() == DBStatus.RECORD_EXIST) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int grade = resultSet.getInt("grade");
				int gender = resultSet.getInt("gender");
				String school = resultSet.getString("school");
				float deviationValue = resultSet.getFloat("deviation_value");

				Student student = new Student(id, name, grade, gender, school, deviationValue);
				studentList.add(student);
			}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return null;
		}
		return studentList;
	}

	/**
	 * DBの生徒を更新する関数
	 * @param student 更新する生徒
	 * @return 関数の終了ステータス
	 */
	public static boolean update(Student student) {
		try (Connection connection = DriverManager.getConnection(Setting.JDBC_URL, Setting.DB_USER, Setting.DB_PASSWORD)) {

			String sql = "UPDATE students SET name = ?, grade = ?, gender = ?, school = ?, deviation_value=? WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getGrade());
			preparedStatement.setInt(3, student.getGender());
			preparedStatement.setString(4, student.getSchool());
			preparedStatement.setDouble(5, student.getDeviationValue());
			preparedStatement.setInt(6, student.getId());

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
