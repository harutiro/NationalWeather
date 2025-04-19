NationalWeather (お天気アプリ)
===

## 概要
全国の天気予報を取得したり、詳細の天気を取得したり、お気に入り保存をすることができるアプリです。

## バージョン関係
- org.jetbrains.kotlin.android 2.1.20
- Java 17
- Android Studio Meerkat | 2024.3.1 Patch 1

## 機能
- 全国の天気予報を取得
- 詳細の天気を取得
- お気に入り保存

## お天気API
以下のライブラリを使用している。
なぜか、温度がnullになる時があるバグが発生しているため、いずれ変更を行いたい。
https://weather.tsukumijima.net/

## 主なライブラリ構成
- Retrofit2
  - 通信ライブラリ
- moshi
  - JSONパーサー
- Room
  - ローカルデータベース
- Jetpack Compose
  - UIライブラリ
- coil
  - 画像ローダーライブラリ
- navigation-compose
  - ナビゲーションライブラリ
- lifecycle-viewmodel-compose
  - ViewModelライブラリ
- material-icons-extended
  - アイコンライブラリ

## アーキテクチャ
- MVVM + Clean Architectureを採用
- FeatureFirstのディレクトリ構成で行った

![アーキテクチャ](./ReadmeImage/CleanArchitecture.png)

## CI/CDについて
- GitHub Actionsを使用してやっていきたいが、まだ設定していない
- Api・Repository間、Repository・UseCase間はDIを行っているためMock化も容易に行える


## linterについて

このプロジェクトはktlintを用いて静的コード解析を行なっている。

ルールとして、パッケージ名に"_"を使うのは許可するものとしている。
理由としては、コーディングテストのパッケージ名が変わってしまうとアプリとして別物となってしまい、
リファクタリングの趣旨としてそぐわないと判断したため

コードは以下の二つがある適宜PRを出す前にチェックをすること。
```bash
# 自動でフォーマットをかける
make ktlint-format

# コードのルール違反をチェックする
make ktlint-check
```

## パッケージ名の変更方法
以下のURLが参考になります。
https://codeforfun.jp/android-studio-how-to-change-package-name/



