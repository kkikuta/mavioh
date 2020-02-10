# MAVIOH

学習塾の社内サイト

# Features

塾で使う社員用のWEBサイトにあったら便利な機能を詰め込みました！
１．掲示板機能
　ちょっとしたメモから重要な連絡まで、なんでも書き込むことができます。
　相手が授業中、電話対応中、休日などで直接連絡できなくても書き込めばOK！
２．スケジュール管理機能
　予定を一目で確認できます。
　試験の日、会議の日なども全て登録しておけば忘れません！
３．生徒管理機能
　生徒の情報をまとめて保存できます。
　これで多くの塾生を抱えても困りません！
４．テスト作成機能
　ブラウザ上に、そのまま印刷できるテストのページを表示します。
　どんどんテストをして、成績を上げましょう！

# Requirement

・jstl-api-1.2
・jstl-impl-1.2
・postgresql-42.2.10

# Installation

jstl-api-1.2
https://mvnrepository.com/artifact/javax.servlet.jsp.jstl/jstl-api/1.2

jstl-impl-1.2
https://mvnrepository.com/artifact/org.glassfish.web/jstl-impl/1.2

postgresql-42.2.10
https://jdbc.postgresql.org/download.html

# Usage

・テスト作成機能について
1~1900までで出題する英単語の範囲を入力します。
作成ボタンを押すと、テストのページが表示されます。
最初に問題のページが表示されます。
ページ下部に解答のページへ移るボタンがあります。
それらのページを印刷してください。

# Note

不正な入力を行ったり、サイトのプログラムにエラーが起きると、強制的にログアウトされます。
もう一度ログインしてください。

# Author

* 菊田洸一
* 立教大学 コミュニティ福祉学部福祉学科
* kkikuta9873@outlook.jp
