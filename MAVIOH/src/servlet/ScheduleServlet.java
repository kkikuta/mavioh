package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ScheduleLogic;
import resource.ExitStatus;
import setting.Setting;


/**
 * スケジュールに関する処理を行うコントローラ
 * @author kkiku
 */
@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 現在の月のスケジュールページを表示する関数
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 月間スケジュールを作成してスコープに保存できた場合
		if (ScheduleLogic.setMonthlyCalendar(request, false) == ExitStatus.NORMAL) {
			// スケジュールページを表示
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/schedule/scheduleTop.jsp");
			requestDispatcher.forward(request, response);
		}  // 保存できなかった場合
		else {
			// エラーページを表示
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Setting.ERROR_URL);
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * DBの更新を含むスケジュールに関する処理を行う関数
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストされた処理の種類に応じて分岐
		String process = request.getParameter("process");

		if (process.equals("changeMonth")) {
			if (ScheduleLogic.changeMonth(request, true) == ExitStatus.NORMAL) {
				if (ScheduleLogic.setMonthlyCalendar(request, false) == ExitStatus.NORMAL) {
					doGet(request, response);
				}
			}
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Setting.ERROR_URL);
			requestDispatcher.forward(request, response);
		}
		else if (process.equals("showCreatePage")) {
			// イベント新規作成ページへフォワード
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/schedule/scheduleCreate.jsp");
			requestDispatcher.forward(request, response);
		}
		else if (process.equals("showEditPage")) {
			if (ScheduleLogic.setOldEvent(request) == ExitStatus.NORMAL) {
				// イベント編集ページへフォワード
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/schedule/scheduleEdit.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		else if (process.equals("executeCreate")) {
			// イベントを追加する
			if (ScheduleLogic.executeCreate(request) == ExitStatus.NORMAL) {
				doGet(request, response);
			}
			else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Setting.ERROR_URL);
				requestDispatcher.forward(request, response);
			}
		}
		else if (process.equals("executeEdit")) {
			// イベントを更新する
			if (ScheduleLogic.executeEdit(request) == ExitStatus.NORMAL) {
				doGet(request, response);
			}
			else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Setting.ERROR_URL);
				requestDispatcher.forward(request, response);
			}
		}
		else if (process.equals("executeDelete")) {
			// イベントを削除する
			if (ScheduleLogic.executeDelete(request) == ExitStatus.NORMAL) {
				doGet(request, response);
			}
			else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Setting.ERROR_URL);
				requestDispatcher.forward(request, response);
			}
		}
	}

}
