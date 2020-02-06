package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StudentLogic;
import resource.ExitStatus;
import setting.Setting;


/**
 * 生徒に関する処理を行うサーブレット
 * @author kkiku
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (StudentLogic.setStudentList(request) == ExitStatus.NORMAL) {
			// 生徒画面へフォワード
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/student/studentTop.jsp");
			requestDispatcher.forward(request, response);
		}
		else {
			// エラーページを表示
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Setting.ERROR_URL);
			requestDispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストされた処理の種類に応じて分岐
		String process = request.getParameter("process");

		if (process.equals("showCreatePage")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/student/studentCreate.jsp");
			requestDispatcher.forward(request, response);
		}
		else if (process.equals("showEditPage")) {
			if (StudentLogic.setStudent(request) == ExitStatus.NORMAL) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/student/studentEdit.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		else if (process.equals("executeCreate")) {
			if (StudentLogic.executeCreate(request) == ExitStatus.NORMAL) {
				doGet(request, response);
			}
		}
		else if (process.equals("executeEdit")) {
			if (StudentLogic.executeEdit(request) == ExitStatus.NORMAL) {
				doGet(request, response);
			}
		}
		else if (process.equals("executeDelete")) {
			if (StudentLogic.executeDelete(request) == ExitStatus.NORMAL) {
				doGet(request, response);
			}
		}
		/*
		// エラーページを表示
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(Setting.ERROR_URL);
		requestDispatcher.forward(request, response);
		*/
	}

}
