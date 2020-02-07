package beans;

import java.io.Serializable;

/**
 * 英単語テストの問題１つ分の情報を保持するJavaBeans
 * @author kkiku
 */
public class WordData implements Serializable {
	/** 問題番号 */
	private int number;
	/** 英単語 */
	private String word;
	/** 意味(日本語訳) */
	private String meaning;

	/**
	 * コンストラクタ
	 */
	public WordData() {}
	/**
	 * コンストラクタ
	 * @param number 問題番号
	 * @param word 英単語
	 * @param meaning 意味(日本語訳)
	 */
	public WordData(int number, String word, String meaning) {
		this.setNumber(number);
		this.setWord(word);
		this.setMeaning(meaning);
	}

	/**
	 * @return number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @param number セットする number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * @return word
	 */
	public String getWord() {
		return word;
	}
	/**
	 * @param word セットする word
	 */
	public void setWord(String word) {
		this.word = word;
	}
	/**
	 * @return meaning
	 */
	public String getMeaning() {
		return meaning;
	}
	/**
	 * @param meaning セットする meaning
	 */
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

}
