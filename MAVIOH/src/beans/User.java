package beans;

import java.io.Serializable;


/**
 * ユーザーを表すJavaBeans
 * @author kkiku
 */
public class User implements Serializable {
	/** ID (主キー) */
	private int id;
	/** ユーザー名 */
	private String name;
	/** パスワード */
	private String password;

	/**
	 * コンストラクタ
	 */
	public User() { }

	/**
	 * コンストラクタ
	 * @param id ID (主キー)
	 * @param name ユーザー名
	 * @param password パスワード
	 */
	public User(int id, String name, String password) {
		this.setId(id);
		this.setName(name);
		this.setPassword(password);
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
