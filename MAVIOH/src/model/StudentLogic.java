package model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Student;
import dao.StudentDAO;
import resource.ExitStatus;
import setting.Setting;

/**
 * 生徒に関する処理をするモデル
 * @author kkiku
 */
public class StudentLogic {
	/**
	 * 生徒を新規作成する関数
	 * @param request HttpServletRequest
	 * @return 関数の終了ステータス
	 */
	public static boolean create(HttpServletRequest request) {
		String name = request.getParameter("name");
		int grade = Integer.parseInt(request.getParameter("grade"));
		int gender = Integer.parseInt(request.getParameter("gender"));
		String school = request.getParameter("school");
		float deviationValue = Float.parseFloat(request.getParameter("deviationValue"));

		if (ValidationLogic.validateStudent(name, school) == ExitStatus.NORMAL) {
			// 第一引数(主キー)は指定する必要がないため、仮の数
			Student student = new Student(-1, name, grade, gender, school, deviationValue);

			return StudentDAO.create(student);
		}
		else {
			ErrorLogic.setErrorInformation(
					request, "入力が不正です。名前は" + Setting.MAX_STUDENT_NAME_LENGTH + "文字以内、高校名は" +
					Setting.MAX_STUDENT_SCHOOL_LENGTH + "文字以内、偏差値は3桁(小数第1位まで)で入力してください。");

			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * 生徒を削除する関数
	 * @param id 削除する生徒のID
	 * @return 関数の終了ステータス
	 */
	public static boolean delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));

		return StudentDAO.delete(id);
	}

	/**
	 * 生徒を編集する関数
	 * @param request HttpServletRequest
	 * @return 関数の終了ステータス
	 */
	public static boolean edit(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int grade = Integer.parseInt(request.getParameter("grade"));
		int gender = Integer.parseInt(request.getParameter("gender"));
		String school = request.getParameter("school");
		float deviationValue = Float.parseFloat(request.getParameter("deviationValue"));

		if (ValidationLogic.validateStudent(name, school) == ExitStatus.NORMAL) {
			Student student = new Student(id, name, grade, gender, school, deviationValue);

			return StudentDAO.update(student);
		}
		else {
			ErrorLogic.setErrorInformation(
					request, "入力が不正です。名前は" + Setting.MAX_STUDENT_NAME_LENGTH + "文字以内、高校名は" +
					Setting.MAX_STUDENT_SCHOOL_LENGTH + "文字以内、偏差値は3桁(小数第1位まで)で入力してください。");

			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * 編集するの生徒をスコープに保存する
	 * @param request HttpServletRequest
	 * @return 関数の終了ステータス
	 */
	public static boolean prepareStudent(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));

		Student student = StudentDAO.read(id);

		if (student != null) {
			HttpSession session = request.getSession();
			session.setAttribute("student", student);

			return ExitStatus.NORMAL;
		}
		else {
			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * 生徒一覧を取得してスコープに保存する関数
	 * @param request
	 * @return 関数の終了ステータス
	 */
	public static boolean prepareStudentList(HttpServletRequest request) {
		List<Student> studentList = StudentDAO.readAll();

		if (studentList != null) {
			HttpSession session = request.getSession();
			session.setAttribute("studentList", studentList);

			return ExitStatus.NORMAL;
		}
		else {
			return ExitStatus.ABNORMAL;
		}
	}

}
