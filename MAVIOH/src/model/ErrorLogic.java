package model;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ErrorInformation;
import setting.Setting;

/**
 * エラーに関する処理をするモデル
 * @author kkiku
 */
public class ErrorLogic {
	public static void showErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(Setting.ERROR_URL);
		requestDispatcher.forward(request, response);
	}

	/**
	 * 表示するエラーメッセージをスコープに保存する関数
	 * @param request HttpServletRequest
	 * @param message エラーメッセージ
	 */
	public static void setErrorInformation(HttpServletRequest request, String message) {
		ErrorInformation errorInformation = new ErrorInformation(message);
		request.setAttribute("errorInformation", errorInformation);
	}

	/**
	 * エラーの種類を確認する関数
	 * @param request HttpServletRequest
	 * @return 正常なエラーであるかどうか
	 */
	public static boolean isNormalError(HttpServletRequest request) {
		if (request.getAttribute("errorInformation") != null) {
			return true;
		}
		else {
			return false;
		}
	}
}
