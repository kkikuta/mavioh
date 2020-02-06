package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * エラー情報を保持するJavaBeans。
 * @author kkiku
 */
public class ErrorInformation implements Serializable {
	/** エラーあり */
	public static final boolean IS_ERROR = true;

	private List<String> messages = new ArrayList<>();  // エラー内容

	/**
	 * コンストラクタ
	 */
	public ErrorInformation() { }

	/**
	 * コンストラクタ
	 * @param message エラーメッセージ
	 */
	public ErrorInformation(String message) {
		this.addMessage(message);
	}

	/**
	 * @return messages
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * @param messages セットする messages
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	/**
	 * エラーメッセージを追加する関数
	 * @param message 追加するエラーメッセージ
	 */
	public void addMessage(String message) {
		if (message != null) {
			messages.add(message);
		}
	}


	/**
	 * エラーメッセージがあるか確認する関数
	 * @return エラーメッセージの有無
	 */
	public boolean find() {
		if (messages.size() == 0) {
			return false;
		}
		return true;
	}
}
