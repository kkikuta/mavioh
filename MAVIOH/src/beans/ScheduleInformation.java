package beans;

import java.io.Serializable;

/**
 * スケジュールページで表示中の月を保持するJavaBeans
 * @author kkiku
 */
public class ScheduleInformation implements Serializable {
	/** 年 */
	private int year;
	/** 月 */
	private int month;

	/**
	 * コンストラクタ
	 */
	public ScheduleInformation() { }
	/**
	 * コンストラクタ
	 * @param year 年
	 * @param month 月
	 */
	public ScheduleInformation(int year, int month) {
		this.setYear(year);
		this.setMonth(month);
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

}
