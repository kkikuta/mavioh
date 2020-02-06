package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Comment;
import beans.ErrorInformation;
import beans.User;
import dao.CommentDAO;
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
		HttpSession session = request.getSession();

		// 入力されたデータを取得する
		String title = request.getParameter("title");
		String body = request.getParameter("body");

		ErrorInformation errorInformation = new ErrorInformation();  // エラー情報

		// 入力値のチェック
		if (ValidationLogic.validate(title, Setting.MAX_COMMENT_TITLE_LENGTH) == ExitStatus.NORMAL &&
				ValidationLogic.validate(body, Setting.MAX_COMMENT_BODY_LENGTH) == ExitStatus.ABNORMAL) {
			// 投稿したユーザーのidを取得する
			User loginUser = (User) session.getAttribute("loginUser");
			int userId = loginUser.getId();

			// 投稿日時を取得する
			LocalDateTime localDateTime = LocalDateTime.now();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分");
			String postTime = dateTimeFormatter.format(localDateTime);

			// コメントをDBに保存する (第一引数(主キー)は指定する必要がないため、仮の数)
			Comment comment = new Comment(-1, title, body, userId, postTime);

			return CommentDAO.create(comment);
		}
		else {
			// エラーメッセージを保存
			request.setAttribute("errorInformation", errorInformation);

			return ExitStatus.ABNORMAL;
		}
	}

	/**
	 * 全コメントを読み込む関数
	 * @param request HttpServletReuqest
	 * @return 関数の終了ステータス
	 */
	public static boolean readAll(HttpServletRequest request) {
		// DBから全コメントを取得
		List<Comment> commentList = CommentDAO.findAll();

		// SQLの実行に成功した場合
		if (commentList != null) {
			// 全コメントをスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("commentList", commentList);

			return ExitStatus.NORMAL;
		}
		else {
			return ExitStatus.ABNORMAL;
		}
	}
}
