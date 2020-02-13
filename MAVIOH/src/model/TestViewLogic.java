package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.WordData;
import resource.ExitStatus;
import resource.WordsAndMeanings;
import setting.Setting;

/**
 * テストに関する処理をするモデル
 * @author kkiku
 */
public class TestViewLogic {
	/**
	 * 表示するテストのデータをスコープに保存する関数
	 * @param startPosition テストの開始位置の番号
	 * @param endPosition テストの終了位置の番号
	 * @return 関数の終了ステータス
	 */
	public static boolean prepareTest(HttpServletRequest request) {
		List<WordData> wordDataList = new ArrayList<>();
		List<Integer> usedNumbers = new ArrayList<>();
		int startPosition;
		int endPosition;

		if (request.getParameter("startPosition") == null || request.getParameter("endPosition") == null) {
			ErrorLogic.setErrorInformation(request, "範囲が入力されていません。");

			return ExitStatus.ABNORMAL;
		}
		else {
			startPosition = Integer.parseInt(request.getParameter("startPosition"));
			endPosition = Integer.parseInt(request.getParameter("endPosition"));

			if (ValidationLogic.validateTestRange(startPosition, endPosition) == ExitStatus.ABNORMAL) {
				ErrorLogic.setErrorInformation(request, "入力が不正です。1～1900の中の範囲で入力してください。");

				return ExitStatus.ABNORMAL;
			}  // 入力値が正しい場合、単語テスト用のデータを用意して保存
			else {
				int number = 1;

				while (true) {
					Random random = new Random();
					int randomNumber = random.nextInt(endPosition - startPosition + 1) + startPosition;

					// 同じ単語ならスキップ
					if (usedNumbers.contains(Integer.valueOf(randomNumber))) {
						continue;
					}
					else {
						String word = WordsAndMeanings.words[randomNumber];
						String meaning = WordsAndMeanings.meanings[randomNumber];

						WordData wd = new WordData(number, word, meaning);
						wordDataList.add(wd);
						usedNumbers.add(Integer.valueOf(randomNumber));

						number++;

						// 指定の個数やったら終了
						if (wordDataList.size() >= Setting.NUMBER_OF_QUESTION) {
							break;
						}
					}
				}

				HttpSession session = request.getSession();
				session.setAttribute("wordDataList", wordDataList);

				return ExitStatus.NORMAL;
			}
		}
	}
}
