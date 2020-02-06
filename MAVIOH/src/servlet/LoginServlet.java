package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginLogic;
import resource.ExitStatus;


/**
 * ログインに関する処理を行うコントローラ
 * @author kkiku
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 使用しない
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

	/**
	 * ログインに関する処理を行う関数
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインに成功した場合
		if (LoginLogic.login(request) == ExitStatus.NORMAL) {
			// ログイン結果画面へフォワード
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/login/loginResult.jsp");
			requestDispatcher.forward(request, response);
		}  // ログインに失敗した場合
		else {
			// ログイン画面へフォワード
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
