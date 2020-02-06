package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ForumLogic;
import resource.ExitStatus;
import setting.Setting;


/**
 * 掲示板に関する処理を担当するコントローラ
 * @author kkiku
 */
@WebServlet("/ForumServlet")
public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * コメント一覧を含めた掲示板を表示する関数
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// コメント一覧の読みだしに成功した場合
		if (ForumLogic.readAll(request) == ExitStatus.NORMAL) {
			// 掲示板トップ画面へフォワード
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/forum/forumTop.jsp");
			requestDispatcher.forward(request, response);
		}  // 失敗した場合
		else {
			// エラー画面へリダイレクト
			response.sendRedirect(Setting.ERROR_URL);
		}
	}

	/**
	 * 投稿されたコメントを保存する関数
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ForumLogic.create(request) == ExitStatus.NORMAL) {
			// 掲示板画面を表示するため、doGet関数を呼び出すようにリダイレクト
			response.sendRedirect("ForumServlet");
		}
		else {
			// 掲示板トップ画面へフォワード
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/forum/forumTop.jsp");
			requestDispatcher.forward(request, response);
		}

	}

}
