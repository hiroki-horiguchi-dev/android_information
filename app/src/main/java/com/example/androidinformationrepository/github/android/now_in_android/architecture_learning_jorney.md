[Architecture Learning Journey](https://github.com/android/nowinandroid/blob/main/docs/ArchitectureLearningJourney.md)

- この学習の過程では、Now in Android アプリのアーキテクチャ、つまりそのレイヤー、主要なクラス、およびそれらの間の相互作用について学習

## goals and requirements
- アプリ アーキテクチャの目標は次のとおりです
    - 公式のアーキテクチャ ガイダンスにできる限り忠実に従ってください
    - 開発者にとって理解しやすく、実験的すぎるものではありません
    - 同じコードベースで作業する複数の開発者をサポートする
    - 開発者のマシン上と継続的インテグレーション (CI) の両方で、ローカル テストとインストルメント テストを容易に実行できる
    - ビルド時間を最小限に抑える

## Architecture Overview
- アプリ アーキテクチャには、データ レイヤー、ドメイン レイヤー、UI レイヤーの3 つのレイヤーがあります。
- ![img.png](img.png)
    - 上位層は下位層の変化に反応
    - イベントは下流に流れる
    - データは上へ流れる
    - わかりやすいねえ
    - データフローは Kotlin Flows を使用して実現する

## Example: show news on ForYou(Recommend) Screen
なるほどね、、図を見た感じ、2つのデータを combine して表示する、みたいな起こり得る一番面倒くさそうなやつを例にしてくれている感じやな

- アプリを初めて実行すると、リモート サーバーからニュース リソースのリストを読み込もうとします
    - (prodビルド フレーバーが選択されている場合、demoビルドはローカル データを使用します)
- 読み込まれると、ユーザーが選択した興味に基づいて、これらが表示される
- 次の図は、発生するイベントと、これを実現するために関連オブジェクトからデータがどのように流れるかを示している
- ![img_1.png](img_1.png)
- 各ステップで起こっていることを説明する

1. アプリの起動時に、全てのリポジトリを同期する WorkManager ジョブがキューに入れられる
2. ForyouViewModel は GetUserNewsResourcesUseCase を呼び出し、ブックマーク/保存された状態のニュースリソースのストリームを取得する
    1. ユーザーとニュースリポジトリの両方がアイテムを発行するまで、この太リームにアイテムは発行されない
    2. 待ち状態の間、フィードの状態は Loading に設定される
3. ユーザーデータリポジトリは Proto DataStore によってバックアップされたローカルデータソースから UserData オブジェクトのストリームを取得する
4. WorkManager は同期ジョブを実行し、OfflineFIrstNewsRepositoryを呼び出してリモートデータソースとのデータ同期を開始する