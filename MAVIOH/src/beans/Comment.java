package beans;

import java.io.Serializable;


/**
 * 掲示板に投稿されたコメントを表すJavaBeans
 * @author kkiku
 */
public class Comment implements Serializable {
	/** ID (主キー) */
	private int id;
	/** コメントのタイトル */
	private String title;
	/** コメント本文 */
	private String body;
	/** 投稿したユーザーのID */
	private int userId;
	/** 投稿日時 */
	private String postTime;

	/**
	 * コンストラクタ
	 */
	public Comment() { }

	/**
	 * コンストラクタ
	 * @param id ID (主キー)
	 * @param title コメントのタイトル
	 * @param body コメント本文
	 * @param userId 投稿したユーザーのID
	 * @param postTime 投稿日時
	 */
	public Comment(int id, String title, String body, int userId, String postTime) {
		this.setId(id);
		this.setTitle(title);
		this.setBody(body);
		this.setUserId(userId);
		this.setPostTime(postTime);
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
	 * @return body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body セットする body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId セットする userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return postTime
	 */
	public String getPostTime() {
		return postTime;
	}

	/**
	 * @param postTime セットする postTime
	 */
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

}
