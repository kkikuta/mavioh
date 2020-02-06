package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.ErrorInformation;
import beans.User;
import dao.UserDAO;
import resource.ExitStatus;


/**
 * ログインに関する処理を行うモデル
 * @author kkiku
 */
public class LoginLogic {
	/**
	 * DBのユーザーを検索し、ログインの可否の判定をする関数
	 * @param request HttpServletRequest
	 * @return 関数の終了ステータス
	 */
	public static boolean login(HttpServletRequest request) {
		// フォームに入力されたデータを取得
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		// ユーザーを検索して結果を返す
		User user = UserDAO.find(userName, password);

		if (user != null) {
			// スコープにログイン中のユーザーとして保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);

			return ExitStatus.NORMAL;
		}
		else {
			// エラーメッセージをスコープに保存
			ErrorInformation errorInformation = new ErrorInformation("入力内容に誤りがあります。");
			request.setAttribute("errorInformation", errorInformation);

			return ExitStatus.ABNORMAL;
		}
	}
}
