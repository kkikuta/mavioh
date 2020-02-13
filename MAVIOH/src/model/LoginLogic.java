package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		User user = UserDAO.find(userName, password);

		// ユーザーが見つかった場合(ログインできる場合)
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);

			return ExitStatus.NORMAL;
		}
		else {
			ErrorLogic.setErrorInformation(request, "ユーザー名またはパスワードが違います。");

			return ExitStatus.ABNORMAL;
		}
	}
}
