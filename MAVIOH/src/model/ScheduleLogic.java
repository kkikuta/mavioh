package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Event;
import beans.OneDaySchedule;
import beans.ScheduleInformation;
import dao.EventDAO;
import resource.ExitStatus;
import setting.Setting;

/**
 * スケジュールに関する処理を行うモデル
 * @author kkiku
 */
public class ScheduleLogic {
	/**
	 * イベントを新規作成する関数
	 * @param request HttpServletRequest
	 * @return 関数の終了ステータス
	 */
	public static boolean create(HttpServletRequest request) {
		String title = request.getParameter("title");
		String detail = request.getParameter("detail");
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int date = Integer.parseInt(request.getParameter("date"));

		if (ValidationLogic.validateEvent(title, detail) == ExitStatus.NORMAL) {
			final String FORMAT = "%4d-%02d-%02d";  // DBに保存する日時の形式

			String stringDate = String.format(FORMAT, year, month, date);

			Date sqlDate = Date.valueOf(stringDate);

			Event event = new Event(-1, title, detail, sqlDate);

			return EventDAO.create(event);
		}
		else {
			ErrorLogic.setErrorInformation(
					request, "入力が不正です。タイトルは" + Setting.MAX_EVENT_TITLE_LENGTH + "文字以内、詳細は" +
					Setting.MAX_EVENT_DETAIL_LENGTH + "文字以内、\n日にちは存在する数を入力してください。");

			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * イベントを削除する関数
	 * @param id 削除するイベントのID
	 * @return 関数の終了ステータス
	 */
	public static boolean delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));

		return EventDAO.delete(id);
	}

	/**
	 * イベントを編集する関数
	 * @param request HttpServletRequest
	 * @return 関数の終了ステータス
	 */
	public static boolean edit(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String detail = request.getParameter("detail");

		if (ValidationLogic.validateEvent(title, detail) == ExitStatus.NORMAL) {
			Event event = new Event(id, title, detail, null);  // 日にちの変更は行わないのでnull

			return EventDAO.update(event);
		}
		else {
			ErrorLogic.setErrorInformation(
					request, "入力が不正です。タイトルは" + Setting.MAX_EVENT_TITLE_LENGTH + "文字以内、詳細は" +
					Setting.MAX_EVENT_DETAIL_LENGTH + "文字以内で入力してください。");

			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * イベントをスコープに保存する関数
	 * @param request HttpServletRequest
	 * @return 関数の終了ステータス
	 */
	public static boolean prepareDetail(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));

		Event event = EventDAO.read(id);

		HttpSession session = request.getSession();
		session.setAttribute("event", event);

		return ExitStatus.NORMAL;
	}

	/**
	 * 編集前のイベントをスコープに保存する
	 * @param request HttpServletRequest
	 * @return 関数の終了ステータス
	 */
	public static boolean prepareEvent(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));

		Event event = EventDAO.read(id);

		if (event != null) {
			HttpSession session = request.getSession();
			session.setAttribute("event", event);

			return ExitStatus.NORMAL;
		}
		else {
			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * 月間スケジュールを保存する関数
	 * @param request HttpServletRequest
	 * @param changeMonth 表示する月を変更するかどうか
	 * @return 関数の終了ステータス
	 */
	public static boolean prepareMonthlySchedule(HttpServletRequest request, boolean changeMonth) {
		HttpSession session = request.getSession();
		List<OneDaySchedule> monthlySchedule = new ArrayList<>();  //月間スケジュール
		String[] dayOfWeekList = {"日", "月", "火", "水", "木", "金", "土"};  // 曜日のリスト
		int year;  // 表示する月の年
		int month;  // 表示する月
		int endOfMonth;  // 月末日
		Calendar calendar = Calendar.getInstance();

		// まだログイン後にスケジュールページを表示していない場合(現在の月のスケジュールページを表示)
		if (session.getAttribute("monthlySchedule") == null) {
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH) + 1;  // 月は、0～11が1～12月を表す
			endOfMonth = calendar.getActualMaximum(Calendar.DATE);
		}  // 表示する月を変更する場合
		else if (changeMonth == true) {
			year = Integer.parseInt(request.getParameter("year"));
			month = Integer.parseInt(request.getParameter("month"));
			calendar.set(year,month - 1,1);
			endOfMonth = calendar.getActualMaximum(Calendar.DATE);
		}  // 前回表示した月を表示する場合
		else {
			ScheduleInformation scheduleInformation = (ScheduleInformation) session.getAttribute("scheduleInformation");
			year = scheduleInformation.getYear();
			month = scheduleInformation.getMonth();
			calendar.set(year,month - 1,1);
			endOfMonth = calendar.getActualMaximum(Calendar.DATE);
		}

		// 月間スケジュール作成
		for (int date = 1; date <= endOfMonth; date++) {
			String dayOfWeek = dayOfWeekList[calendar.get(Calendar.DAY_OF_WEEK) - 1];  // 曜日は、0～6が日～土を表す

			List<Event> eventList = EventDAO.read(year, month, date);

			OneDaySchedule oneDaySchedule = new OneDaySchedule(date, dayOfWeek, eventList);

			monthlySchedule.add(oneDaySchedule);

			// 一日進める
			calendar.set(year, month - 1, calendar.get(Calendar.DATE) + 1);
		}

		session.setAttribute("monthlySchedule", monthlySchedule);

		ScheduleInformation scheduleInformation = new ScheduleInformation(year, month);
		session.setAttribute("scheduleInformation", scheduleInformation);

		return ExitStatus.NORMAL;
	}

}
