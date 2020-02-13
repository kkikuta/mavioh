package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import resource.ExitStatus;

/**
 * ログアウトに関する処理をするモデル
 * @author kkiku
 */
public class LogoutLogic {
	/**
	 * ログアウトをする関数
	 * @param request HttpServletRequest
	 * @return 関数の終了ステータス
	 */
	public static boolean logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");

		return ExitStatus.NORMAL;
	}
}
