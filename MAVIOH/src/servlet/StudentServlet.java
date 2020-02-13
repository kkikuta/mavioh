package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ErrorLogic;
import model.StudentLogic;
import resource.ExitStatus;


/**
 * 生徒に関する処理を行うコントローラ
 * @author kkiku
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (StudentLogic.prepareStudentList(request) == ExitStatus.NORMAL) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/student/studentTop.jsp");
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
		String process = request.getParameter("process");

		if (process.equals("showCreatePage")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/student/studentCreate.jsp");
			requestDispatcher.forward(request, response);
		}
		else if (process.equals("showEditPage")) {
			if (StudentLogic.prepareStudent(request) == ExitStatus.NORMAL) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/student/studentEdit.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		else if (process.equals("executeCreate")) {
			if (StudentLogic.create(request) == ExitStatus.NORMAL) {
				doGet(request, response);
			}
			else if (ErrorLogic.isNormalError(request) == true) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/student/studentCreate.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		else if (process.equals("executeEdit")) {
			if (StudentLogic.edit(request) == ExitStatus.NORMAL) {
				doGet(request, response);
			}
			else if (ErrorLogic.isNormalError(request) == true) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/student/studentEdit.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		else if (process.equals("executeDelete")) {
			if (StudentLogic.delete(request) == ExitStatus.NORMAL) {
				doGet(request, response);
			}
		}
		response.sendRedirect("ErrorServlet");
	}

}
