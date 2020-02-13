package model;

import resource.ExitStatus;
import setting.Setting;

/**
 * 入力値を検証するモデル
 * @author kkiku
 */
public class ValidationLogic {
	/**
	 * 入力値の長さを検証する関数
	 * @param value 入力値
	 * @param maximumLength 入力値の長さの最大値
	 * @return 入力値の有効性
	 */
	public static boolean validateLength(String value, int maximumLength) {
		if (value.length() > maximumLength) {
			return ExitStatus.ABNORMAL;
		}
		return ExitStatus.NORMAL;
	}

	/**
	 * 入力値が空でないことを検証する
	 * @param value 入力値
	 * @return 入力値の有効性
	 */
	public static boolean validateInput(String value) {
		if (value.isEmpty() == true) {
			return ExitStatus.ABNORMAL;
		}
		return ExitStatus.NORMAL;
	}

	/**
	 * 文字列を検証する関数
	 * @param value 入力値
	 * @param maximumLength 入力値の長さの最大値
	 * @return 入力値の有効性
	 */
	public static boolean validate(String value, int maximumLength) {
		// 入力値を検証
		if (validateInput(value) == ExitStatus.ABNORMAL || validateLength(value, maximumLength) == ExitStatus.ABNORMAL) {
			return ExitStatus.ABNORMAL;
		}
		return ExitStatus.NORMAL;
	}

	/**
	 * 個目との入力値を検証する関数
	 * @param title タイトル
	 * @param body コメント本文
	 * @return 入力値の有効性
	 */
	public static boolean validateComment(String title, String body) {
		if (validate(title, Setting.MAX_COMMENT_TITLE_LENGTH) == ExitStatus.NORMAL &&
				validate(body, Setting.MAX_COMMENT_BODY_LENGTH) == ExitStatus.NORMAL) {
			return ExitStatus.NORMAL;
		}
		else {
			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * イベントの入力値を検証する関数
	 * @param title タイトル
	 * @param detail 詳細
	 * @return 入力値の有効性
	 */
	public static boolean validateEvent(String title, String detail) {
		if (validate(title, Setting.MAX_EVENT_TITLE_LENGTH) == ExitStatus.NORMAL &&
				validate(title, Setting.MAX_EVENT_DETAIL_LENGTH) == ExitStatus.NORMAL) {
			return ExitStatus.NORMAL;
		}
		else {
			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * 生徒情報の入力値を検証する関数
	 * @param 名前 生徒の名前
	 * @param school 高校名
	 * @return 入力値の有効性
	 */
	public static boolean validateStudent(String name, String school) {
		if (validate(name, Setting.MAX_STUDENT_NAME_LENGTH) == ExitStatus.NORMAL &&
				validate(school, Setting.MAX_STUDENT_SCHOOL_LENGTH) == ExitStatus.NORMAL) {
			return ExitStatus.NORMAL;
		}
		else {
			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * テストの問題番号の範囲を検証する関数
	 * @param startPosition 開始位置の番号
	 * @param endPosition 終了位置の番号
	 * @return 入力値の有効性
	 */
	public static boolean validateTestRange(int startPosition, int endPosition) {
		if (endPosition - startPosition + 1 >= Setting.NUMBER_OF_QUESTION &&
				startPosition > 0 && startPosition <= Setting.LAST_POSITION_OF_QUESTION &&
				endPosition > 0 && endPosition <= Setting.LAST_POSITION_OF_QUESTION) {
			return ExitStatus.NORMAL;
		}
		else {
			return ExitStatus.ABNORMAL;
		}
	}
}
