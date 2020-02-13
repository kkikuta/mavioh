package beans;

import java.io.Serializable;


/**
 * エラー情報を保持するJavaBeans
 * @author kkiku
 */
public class ErrorInformation implements Serializable {
	/** エラーメッセージ */
	private String message;

	/**
	 * コンストラクタ
	 */
	public ErrorInformation() { }
	/**
	 * コンストラクタ
	 * @param message エラーメッセージ
	 */
	public ErrorInformation(String message) {
		this.setMessage(message);
	}

	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message セットする message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
