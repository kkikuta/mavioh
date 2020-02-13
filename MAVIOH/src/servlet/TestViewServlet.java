package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ErrorLogic;
import model.TestViewLogic;
import resource.ExitStatus;

/**
 * テストに関する処理を行うコントローラ
 * @author kkiku
 */
@WebServlet("/TestViewServlet")
public class TestViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/testView/testViewTop.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String process = request.getParameter("process");

		if (process.equals("createTest")) {
			if (TestViewLogic.prepareTest(request) == ExitStatus.NORMAL) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/testView/testViewQuestion.jsp");
				requestDispatcher.forward(request, response);
			}
			else if (ErrorLogic.isNormalError(request) == true) {
				doGet(request, response);
			}
		}
		else if (process.equals("showQuestionPage")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/testView/testViewQuestion.jsp");
			requestDispatcher.forward(request, response);
		}
		else if (process.equals("showAnswerPage")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/testView/testViewAnswer.jsp");
			requestDispatcher.forward(request, response);
		}
		response.sendRedirect("ErrorServlet");
	}

}
