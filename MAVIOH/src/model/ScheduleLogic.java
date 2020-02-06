package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Event;
import beans.OneDaySchedule;
import dao.EventDAO;
import resource.ExitStatus;
import resource.Result;

/**
 * スケジュールに関する処理を行うモデル
 * @author kkiku
 */
public class ScheduleLogic {

	/**
	 * イベントを新規作成する関数
	 * @param request HttpServletRequest
	 * @param change 月を変更するかどうか
	 * @return 関数の終了ステータス
	 */
	public static boolean executeCreate(HttpServletRequest request) {
		// 入力値を取得
		String title = request.getParameter("title");
		String detail = request.getParameter("detail");
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int date = Integer.parseInt(request.getParameter("date"));

		// 入力値が有効な場合
		if (ValidationLogic.validateEvent(title, detail) == Result.IS_VALID) {

			// DBに保存する日時の形式
			final String FORMAT = "%4d-%02d-%02d";

			// 指定の形式に変換
			String stringDate = String.format(FORMAT, year, month, date);

			// Date型のイベントの日時を取得
			Date sqlDate = Date.valueOf(stringDate);

			// イベントのインスタンスを生成
			Event event = new Event(-1, title, detail, sqlDate);

			return EventDAO.create(event);
		}
		else {
			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * イベントを編集する関数
	 * @param request HttpServletRequest
	 * @return 関数の終了ステータス
	 */
	public static boolean executeEdit(HttpServletRequest request) {
		// 入力値の取得
		String title = request.getParameter("title");
		String detail = request.getParameter("detail");
		Date date = Date.valueOf(request.getParameter("date"));

		// 入力値が有効な場合
		if (ValidationLogic.validateEvent(title, detail) == Result.IS_VALID) {

			Event event = new Event(-1, title, detail, date);

			return EventDAO.update(event);
		}
		else {
			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * イベントを削除する関数
	 * @param id 削除するイベントのID
	 * @return 関数の終了ステータス
	 */
	public static boolean executeDelete(HttpServletRequest request) {
		// パラメータを受け取る
		int id = Integer.parseInt(request.getParameter("id"));

		return EventDAO.delete(id);
	}

	/**
	 * 月間スケジュールを保存する関数
	 * @param request HttpServletRequest
	 * @return 関数の終了ステータス
	 */
	public static boolean setMonthlyCalendar(HttpServletRequest request, boolean change) {
		HttpSession session = request.getSession();
		List<OneDaySchedule> monthlySchedule = new ArrayList<>();  //月間スケジュール
		String[] dayOfWeekList = {"日", "月", "火", "水", "木", "金", "土"};  // 曜日のリスト
		int year;
		int month;
		int endOfMonth;
		Calendar calendar = Calendar.getInstance();

		// 表示中の年と月を取得
		List<OneDaySchedule> monthlyScheduleOld = (List<OneDaySchedule>) session.getAttribute("monthlySchedule");

		// まだスケジュールが保存されていない場合
		if (monthlyScheduleOld == null) {
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH) + 1;
			endOfMonth = calendar.getActualMaximum(Calendar.DATE);
		}  // 表示する月を変更する場合
		else if (change == true) {
			year = Integer.parseInt(request.getParameter("year"));
			month = Integer.parseInt(request.getParameter("month"));
			endOfMonth = calendar.getActualMaximum(Calendar.DATE);
		}  // 前回表示した月を表示する場合
		else {
			year = monthlyScheduleOld.get(0).getYear();
			month = monthlyScheduleOld.get(0).getMonth();
			endOfMonth = monthlyScheduleOld.get(monthlyScheduleOld.size() - 1).getDate();
			calendar.set(year, month - 1, 1);

			// 古いスケジュールを削除
			session.removeAttribute("monthlySchedule");
		}

		// 月間カレンダー作成
		for (int i = 1; i <= endOfMonth; i++) {
			// 曜日を取得(曜日は日～土まで1～7で返されるため、-1で調整)
			String dayOfWeek = dayOfWeekList[calendar.get(Calendar.DAY_OF_WEEK) - 1];

			// イベントを取得
			Event event = EventDAO.read(year, month, i);

			// 1日分のスケジュールを作成
			OneDaySchedule oneDaySchedule = new OneDaySchedule(year, month, i, dayOfWeek, event);

			// 月間スケジュールに追加
			monthlySchedule.add(oneDaySchedule);

			// 一日進める
			calendar.set(year, month - 1, calendar.get(Calendar.DATE) + 1);
		}

		// スコープに保存
		session.setAttribute("monthlySchedule", monthlySchedule);

		// スケジュールが正しく保存されている場合
		if (session.getAttribute("monthlySchedule") != null) {
			return ExitStatus.NORMAL;
		}  // 保存されていない場合
		else {
			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * 編集前のイベントをスコープに保存する
	 * @param request HttpServletRequest
	 * @return 関数の終了ステータス
	 */
	public static boolean setOldEvent(HttpServletRequest request) {
		// 編集するイベントのIDを取得
		int id = Integer.parseInt(request.getParameter("eventId"));

		// イベントの読み込み
		Event oldEvent = EventDAO.read(id);

		if (oldEvent != null) {
			// イベントを保存
			HttpSession session = request.getSession();
			session.setAttribute("oldEvent", oldEvent);

			return ExitStatus.NORMAL;
		}
		else {
			return ExitStatus.ABNORMAL;
		}
	}

	public static boolean changeMonth(HttpServletRequest request, boolean change) {
		return setMonthlyCalendar(request, true);
	}

}
