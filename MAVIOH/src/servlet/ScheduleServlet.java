package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ErrorLogic;
import model.ScheduleLogic;
import resource.ExitStatus;


/**
 * スケジュールに関する処理を行うコントローラ
 * @author kkiku
 */
@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ScheduleLogic.prepareMonthlySchedule(request, false) == ExitStatus.NORMAL) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/schedule/scheduleTop.jsp");
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

		if (process.equals("changeMonth")) {
			if (ScheduleLogic.prepareMonthlySchedule(request, true) == ExitStatus.NORMAL) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/schedule/scheduleTop.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		else if (process.equals("showCreatePage")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/schedule/scheduleCreate.jsp");
			requestDispatcher.forward(request, response);
		}
		else if (process.equals("showDetail")) {
			if (ScheduleLogic.prepareDetail(request) == ExitStatus.NORMAL) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/schedule/scheduleDetail.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		else if (process.equals("showEditPage")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/schedule/scheduleEdit.jsp");
			requestDispatcher.forward(request, response);
		}
		else if (process.equals("executeCreate")) {
			if (ScheduleLogic.create(request) == ExitStatus.NORMAL) {
				doGet(request, response);
			}
			else if (ErrorLogic.isNormalError(request) == true) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/schedule/scheduleEdit.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		else if (process.equals("executeEdit")) {
			if (ScheduleLogic.edit(request) == ExitStatus.NORMAL) {
				doGet(request, response);
			}
			else if (ErrorLogic.isNormalError(request) == true) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/schedule/scheduleEdit.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		else if (process.equals("executeDelete")) {
			if (ScheduleLogic.delete(request) == ExitStatus.NORMAL) {
				doGet(request, response);
			}
		}
		response.sendRedirect("ErrorServlet");
	}

}
