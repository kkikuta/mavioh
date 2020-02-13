package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Comment;
import beans.User;
import dao.CommentDAO;
import dao.UserDAO;
import resource.ExitStatus;
import setting.Setting;

/**
 * 掲示板に関する処理を行うモデル
 * @author kkiku
 */
public class ForumLogic {
	/**
	 * 投稿されたコメントを保存する関数
	 * @param request HttpServletRequest
	 * @param title コメントのタイトル
	 * @param body コメント本文
	 * @return 関数の終了ステータス
	 */
	public static boolean create(HttpServletRequest request) {
		String title = request.getParameter("title");
		String body = request.getParameter("body");

		if (ValidationLogic.validateComment(title, body) == ExitStatus.NORMAL) {
			HttpSession session = request.getSession();

			// 投稿したユーザーのidを取得
			User loginUser = (User) session.getAttribute("loginUser");
			int userId = loginUser.getId();

			// 投稿日時を取得
			LocalDateTime localDateTime = LocalDateTime.now();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分");
			String postTime = dateTimeFormatter.format(localDateTime);

			// 第一引数(主キー)は指定する必要がないため、仮の数
			Comment comment = new Comment(-1, title, body, userId, postTime);

			return CommentDAO.create(comment);
		}
		else {
			ErrorLogic.setErrorInformation(
					request, "文字数が不正です。タイトルは" + Setting.MAX_COMMENT_TITLE_LENGTH + "文字以内、コメントは" +
					Setting.MAX_COMMENT_BODY_LENGTH + "文字以内で入力してください。");

			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * 全コメントをスコープに保存する関数
	 * @param request HttpServletReuqest
	 * @return 関数の終了ステータス
	 */
	public static boolean prepareCommentList(HttpServletRequest request) {
		List<Comment> commentList = CommentDAO.findAll();  // コメント本体
		List<User> userList = UserDAO.readAll();  // コメントの投稿者

		// SQLの実行に成功した場合
		if (commentList != null && userList != null) {
			HttpSession session = request.getSession();

			session.setAttribute("commentList", commentList);
			session.setAttribute("userList", userList);

			return ExitStatus.NORMAL;
		}
		else {
			return ExitStatus.ABNORMAL;
		}
	}
}
