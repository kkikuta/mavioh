package setting;


/**
 * サイトの設定情報を集めたクラス
 * @author kkiku
 */
public class Setting {

	/** 文字コード({@value}) */
	public static final String CHARACTER_CODE = "UTF-8";

	// DBへの接続情報
	/** DBへの接続URL({@value}) */
	public static final String JDBC_URL = "jdbc:postgresql://ec2-184-72-235-159.compute-1.amazonaws.com:5432/d8qejs8r8gc4q";
	//public static final String JDBC_URL = "jdbc:h2:C:/pleiades/database/MAVIOH";
	/** DBへの接続時のユーザー名({@value}) */
	public static final String DB_USER = "iyxltwhsqxuyqb";
	//public static final String DB_USER = "root";
	/** DBへの接続時のパスワード({@value}) */
	public static final String DB_PASSWORD = "156598e79afc61725fab4e61184d5f5c870d8ea802ec47d38aeeb27558a088b5";
	//public static final String DB_PASSWORD = "";


	/** エラー時の遷移先URL({@value}) */
	public static final String ERROR_URL = "/WEB-INF/jsp/error/error.jsp";

	// 有効な入力値
	/** コメントのタイトルの長さの最大値({@value}) */
	public static final int MAX_COMMENT_TITLE_LENGTH = 32;
	/** コメントの本文の長さの最大値({@value}) */
	public static final int MAX_COMMENT_BODY_LENGTH = 255;
	/** イベントのタイトルの長さの最大値({@value}) */
	public static final int MAX_EVENT_TITLE_LENGTH = 32;
	/** イベントの詳細の長さの最大値({@value}) */
	public static final int MAX_EVENT_DETAIL_LENGTH = 255;
	/** 生徒の名前の長さの最大値({@value}) */
	public static final int MAX_STUDENT_NAME_LENGTH = 32;
	/** 生徒の高校名の長さの最大値({@value}) */
	public static final int MAX_STUDENT_SCHOOL_LENGTH = 32;
	/** テストで指定できる問題番号の最大値 */
	public static final int LAST_POSITION_OF_QUESTION = 1900;
	/** テストで表示する問題の数 */
	public static final int NUMBER_OF_QUESTION = 25;

}
