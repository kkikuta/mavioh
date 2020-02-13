package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ErrorLogic;
import model.ForumLogic;
import resource.ExitStatus;


/**
 * 掲示板に関する処理を担当するコントローラ
 * @author kkiku
 */
@WebServlet("/ForumServlet")
public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ForumLogic.prepareCommentList(request) == ExitStatus.NORMAL || ErrorLogic.isNormalError(request) == true) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/forum/forumTop.jsp");
			requestDispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("ErrorServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ForumLogic.create(request) == ExitStatus.NORMAL || ErrorLogic.isNormalError(request) == true) {
			doGet(request, response);
		}
		else {
			response.sendRedirect("ErrorServlet");
		}
	}

}
