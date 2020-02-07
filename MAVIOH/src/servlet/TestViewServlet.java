package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TestViewLogic;
import resource.ExitStatus;

/**
 * テストに関する処理を行うサーブレット
 * @author kkiku
 */
@WebServlet("/TestViewServlet")
public class TestViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * テスト作成フォームを表示する関数
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/testView/testViewTop.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * テストページを表示する関数
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String process = request.getParameter("process");

		if (process.equals("showTest")) {
			//# ここでテスト用のデータをスコープに保存
			if (TestViewLogic.setTestData(request) == ExitStatus.NORMAL) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/testView/testViewQuestion.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		else if (process.equals("showQuestion")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/testView/testViewQuestion.jsp");
			requestDispatcher.forward(request, response);
		}
		else if (process.equals("showAnswer")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/testView/testViewAnswer.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
