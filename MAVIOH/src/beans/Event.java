package beans;

import java.io.Serializable;
import java.sql.Date;

/**
 * スケジュールの一日分のイベントを表すJavaBeans
 * @author kkiku
 */
public class Event implements Serializable {
	/** ID(主キー) */
	private int id;
	/** タイトル */
	private String title;
	/** 詳細 */
	private String detail;
	/** 日付 */
	private Date date;

	/**
	 * コンストラクタ
	 */
	public Event() { }

	/**
	 * コンストラクタ
	 * @param id 主キー
	 * @param title タイトル
	 * @param detail 詳細
	 * @param date 日付
	 */
	public Event(int id, String title, String detail, Date date) {
		this.setId(id);
		this.setTitle(title);
		this.setDetail(detail);
		this.setDate(date);
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title セットする title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail セットする detail
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date セットする date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
