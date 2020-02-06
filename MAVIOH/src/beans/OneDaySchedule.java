package beans;

import java.io.Serializable;


/**
 * 一日分のスケジュールを表すJavaBeans
 * @author kkiku
 */
public class OneDaySchedule implements Serializable {
	/** 年 */
	private int year;
	/** 月 */
	private int month;
	/** 日にち */
	private int date;
	/** 曜日 */
	private String dayOfWeek;
	/** イベント */
	private Event event;

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
	public OneDaySchedule(int year, int month, int date, String dayOfWeek, Event event) {
		this.setYear(year);
		this.setMonth(month);
		this.setDate(date);
		this.setDayOfWeek(dayOfWeek);
		this.setEvent(event);
	}
	/**
	 * @return year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year セットする year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month セットする month
	 */
	public void setMonth(int month) {
		this.month = month;
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
	public Event getEvent() {
		return event;
	}
	/**
	 * @param event セットする event
	 */
	public void setEvent(Event event) {
		this.event = event;
	}


}
