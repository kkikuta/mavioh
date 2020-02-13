package beans;

import java.io.Serializable;
import java.util.List;


/**
 * 一日分のスケジュールを表すJavaBeans
 * @author kkiku
 */
public class OneDaySchedule implements Serializable {
	/** 日にち */
	private int date;
	/** 曜日 */
	private String dayOfWeek;
	/** イベント */
	private List<Event> eventList;

	/**
	 * コンストラクタ
	 */
	public OneDaySchedule() { }
	/**
	 * コンストラクタ
	 * @param year 年
	 * @param month 月
	 * @param date 日
	 * @param dayOfWeek 曜日
	 * @param event イベント
	 */
	public OneDaySchedule(int date, String dayOfWeek, List<Event> eventList) {
		this.setDate(date);
		this.setDayOfWeek(dayOfWeek);
		this.setEventList(eventList);
	}

	/**
	 * @return date
	 */
	public int getDate() {
		return date;
	}
	/**
	 * @param date セットする date
	 */
	public void setDate(int date) {
		this.date = date;
	}
	/**
	 * @return dayOfWeek
	 */
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	/**
	 * @param dayOfWeek セットする dayOfWeek
	 */
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	/**
	 * @return event
	 */
	public List<Event> getEventList() {
		return eventList;
	}
	/**
	 * @param event セットする event
	 */
	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}


}
