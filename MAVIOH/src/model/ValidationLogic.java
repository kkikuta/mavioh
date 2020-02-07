package model;

import resource.Result;
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
			return Result.IS_INVALID;
		}
		return Result.IS_VALID;
	}

	/**
	 * 入力値が空でないことを検証する
	 * @param value 入力値
	 * @return 入力値の有効性
	 */
	public static boolean validateInput(String value) {
		if (value.isEmpty() == true) {
			return Result.IS_INVALID;
		}
		return Result.IS_VALID;
	}

	/**
	 * 文字列を検証する関数
	 * @param value 入力値
	 * @param maximumLength 入力値の長さの最大値
	 * @return 入力値の有効性
	 */
	public static boolean validate(String value, int maximumLength) {
		// 入力値を検証
		if (validateInput(value) == Result.IS_INVALID || validateLength(value, maximumLength) == Result.IS_INVALID) {
			return Result.IS_INVALID;
		}
		return Result.IS_VALID;
	}

	/**
	 * イベントの入力値を検証する関数
	 * @param title タイトル
	 * @param detail 詳細
	 * @return 入力値の有効性
	 */
	public static boolean validateEvent(String title, String detail) {
		if (validate(title, Setting.MAX_EVENT_TITLE_LENGTH) == Result.IS_VALID &&
				validate(title, Setting.MAX_EVENT_DETAIL_LENGTH) == Result.IS_VALID) {
			return Result.IS_VALID;
		}
		else {
			return Result.IS_INVALID;
		}
	}

	public static boolean validateStudent(String name, String school) {
		if (validate(name, Setting.MAX_STUDENT_NAME_LENGTH) == Result.IS_VALID &&
				validate(school, Setting.MAX_STUDENT_SCHOOL_LENGTH) == Result.IS_VALID) {
			return Result.IS_VALID;
		}
		else {
			return Result.IS_INVALID;
		}
	}

	/**
	 * テストの問題番号の範囲を検証する関数
	 * @param startPosition 開始位置の番号
	 * @param endPosition 終了位置の番号
	 * @return 入力値の有効性
	 */
	public static boolean validateTestRange(int startPosition, int endPosition) {
		if (endPosition - startPosition >= Setting.NUMBER_OF_QUESTION &&
				startPosition > 0 && startPosition <= Setting.LAST_POSITION_OF_QUESTION &&
				endPosition > 0 && endPosition <= Setting.LAST_POSITION_OF_QUESTION) {
			return Result.IS_VALID;
		}
		else {
			return Result.IS_INVALID;
		}
	}
}
