package model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Student;
import dao.StudentDAO;
import resource.ExitStatus;
import resource.Result;

/**
 * 生徒に関する処理をするモデル
 * @author kkiku
 */
public class StudentLogic {
	/**
	 * 編集前の生徒をスコープに保存する
	 * @param request HttpServletRequest
	 * @return 関数の終了ステータス
	 */
	public static boolean setStudent(HttpServletRequest request) {
		// 編集する生徒のIDを取得
		int id = Integer.parseInt(request.getParameter("id"));

		// 生徒の読み込み
		Student student = StudentDAO.read(id);

		if (student != null) {
			// 生徒を保存
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
	 * @return
	 */
	public static boolean setStudentList(HttpServletRequest request) {
		// 生徒一覧を取得
		List<Student> studentList = StudentDAO.readAll();

		if (studentList != null) {
			// 生徒一覧をスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("studentList", studentList);

			return ExitStatus.NORMAL;
		}
		else {
			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * 生徒を新規作成する関数
	 * @param request HttpServletRequest
	 * @return 関数の終了ステータス
	 */
	public static boolean executeCreate(HttpServletRequest request) {
		// 入力値を取得
		String name = request.getParameter("name");
		int grade = Integer.parseInt(request.getParameter("grade"));
		int gender = Integer.parseInt(request.getParameter("gender"));
		String school = request.getParameter("school");
		double deviationValue = Double.parseDouble(request.getParameter("deviationValue"));

		// 入力値が有効な場合
		if (ValidationLogic.validateStudent(name, school) == Result.IS_VALID) {
			// 生徒のインスタンスを生成
			Student student = new Student(-1, name, grade, gender, school, deviationValue);

			return StudentDAO.create(student);
		}
		else {

			return ExitStatus.ABNORMAL;
		}
	}


	/**
	 * 生徒を編集する関数
	 * @param request HttpServletRequest
	 * @return 関数の終了ステータス
	 */
	public static boolean executeEdit(HttpServletRequest request) {
		// 入力値を取得
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int grade = Integer.parseInt(request.getParameter("grade"));
		int gender = Integer.parseInt(request.getParameter("gender"));
		String school = request.getParameter("school");
		double deviationValue = Double.parseDouble(request.getParameter("deviationValue"));

		// 入力値が有効な場合
		if (ValidationLogic.validateStudent(name, school) == Result.IS_VALID) {
			// 生徒のインスタンスを生成
			Student student = new Student(id, name, grade, gender, school, deviationValue);

			return StudentDAO.update(student);
		}
		else {
			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * 生徒を削除する関数
	 * @param id 削除する生徒のID
	 * @return 関数の終了ステータス
	 */
	public static boolean executeDelete(HttpServletRequest request) {
		// パラメータを受け取る
		int id = Integer.parseInt(request.getParameter("id"));

		return StudentDAO.delete(id);
	}

}
