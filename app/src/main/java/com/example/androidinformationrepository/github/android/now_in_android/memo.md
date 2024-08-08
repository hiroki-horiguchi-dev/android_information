# [Now in Android](https://github.com/android/nowinandroid)
- compose-sample も勉強にはなるんだけど、こっちの方が見ていて勉強になるなあという部分があるからこっち見ておく
- コードを全て追うことはしない、コードを読むと脳のリソースを割かれるので

# Features
- Now in Androidは、Now in Androidシリーズのコンテンツを表示
-  ユーザーは、最近のビデオ、記事、その他のコンテンツへのリンクをブラウズすることができる
- また、ユーザーは興味のあるトピックをフォローすることができ、フォローしている興味と一致する新しいコンテンツが公開されると通知を受けることができる

# [Development Environment](https://github.com/android/nowinandroid?tab=readme-ov-file#development-environment)
- 現在Androidでは、Gradleビルドシステムを使用しており、Android Studioに直接インポートすることができる
- 実行設定をappに変更します
- 一度立ち上げたら、以下のラーニング・ジャーニーを参考にして、どのライブラリやツールが使用されているか、UI、テスト、アーキテクチャなどのアプローチの背後にある理由、
- そしてプロジェクトのこれらの異なる部分すべてがどのように組み合わされて完全なアプリが作成されるのかについて理解を深めてください


# Architecture
- Now in Androidアプリは、[公式のアーキテクチャ・ガイダンス](https://developer.android.com/topic/architecture?hl=ja)に従い、[アーキテクチャ・ラーニング・ジャーニー](https://github.com/android/nowinandroid/blob/main/docs/ArchitectureLearningJourney.md)で詳しく説明されている
- モジュール分けてるよ、ui, domain, data layer があるよなどなど

## [Now in Android App with Material 3](https://www.figma.com/community/file/1164313362327941158/now-in-android-case-study)
- Now in Androidアプリを通してMaterial 3を掘り下げる
- KotlinとJetpack Composeで構築され、Material 3でデザインされた高機能アプリ、Now in Androidアプリで使用されているテーマ、スタイル、コンポーネント、レイアウトをご覧ください
- このFigmaファイルは、デザイナーや開発者のためのサポート資料や参考資料となることを目的としており、Material Theme BuilderとMaterial 3 Design Kitを使用している
- Android Developersブログでは、AndroidにおけるNowのデザインの旅について詳しく説明している

## [Architecture Learning Journey](https://github.com/android/nowinandroid/blob/main/docs/ArchitectureLearningJourney.md)
- 別途ファイルを作成しているのでそちらで確認

# Modularization
- Now in Androidアプリは完全にモジュール化されており、モジュール化ラーニング・ジャーニーで使用されたモジュール化戦略の詳細なガイダンスと説明があります

## modularization learning journey
- 別途ファイルを作成してメモっているのでそっちを確認

# Build
- このアプリには、通常のデバッグビルドとリリースビルドのバリアントが含まれている
- さらに、アプリのベンチマークバリアントは、起動時のパフォーマンスをテストし、ベースラインプロファイルを生成するために使用される
- `app-nia-catalog`は、AndroidのNow向けにスタイライズされたコンポーネントのリストを表示するスタンドアロンアプリ
- demoフレーバーは静的なローカルデータを使用し、UIを即座に構築して探索することができる
- prodフレーバーはバックエンドサーバーに実際のネットワークコールを行い、最新のコンテンツを提供する
- 通常の開発には `demoDebug`
- UI のパフォーマンステストには `demoRelease` 

# Testing
- コンポーネントのテストを容易にするため、Now in AndroidではHiltによる依存性注入を採用している

- ほとんどのデータレイヤー・コンポーネントは、インターフェースとして定義される
- そして、（様々な依存関係を持つ）具体的な実装が、アプリ内の他のコンポーネントにこれらのインターフェースを提供するためにバインドされる
- テストでは、Now in Androidは特にモッキング・ライブラリを使用しない
- その代わりに、HiltのテストAPIを使用することで、本番の実装をテスト用ダブルに置き換えることができる（または、ViewModelテストの場合は手動でコンストラクタをインジェクションする）

- これらのテスト用ダブルスは、本番の実装と同じインターフェイスを実装し、一般に、テスト用のフックを追加した単純化された（しかし現実的な）実装を提供する
- この結果、モックに対して特定のコールを検証するだけでなく、より多くの実運用コードを検証することができる

#### Examples:


# UI
- このアプリはMaterial 3ガイドラインを使用してデザインされた
- デザインプロセスの詳細とデザインファイルの入手は、「Now in Android Material 3 Case Study」(デザインアセットはPDFでも入手可能)をご覧ください
- スクリーンとUIエレメントは、すべてJetpack Composeを使用して構築されています
- アプリには2つのテーマがあります: ダイナミックカラー - ユーザーの現在のカラーテーマ(サポートされている場合)に基づいた色を使用 デフォルトテーマ - ダイナミックカラーがサポートされていない場合、事前に定義された色を使用 各テーマはダークモードもサポートしている
- アプリは、[異なるスクリーンサイズをサポート](https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes)するためにアダプティブレイアウトを使用している
- UIアーキテクチャの詳細については、こちらを確認