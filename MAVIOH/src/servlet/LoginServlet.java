package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ErrorLogic;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (LoginLogic.login(request) == ExitStatus.NORMAL) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/welcome/welcome.jsp");
			requestDispatcher.forward(request, response);
		}
		else if (ErrorLogic.isNormalError(request) == true) {
			doGet(request, response);
		}
		else {
			response.sendRedirect("ErrorServlet");
		}
	}

}
