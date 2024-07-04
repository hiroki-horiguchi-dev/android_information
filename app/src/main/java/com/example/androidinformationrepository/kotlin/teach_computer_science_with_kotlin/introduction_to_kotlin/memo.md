# [Introduction to Kotlin](https://docs.google.com/presentation/d/18EB_yQ6O9hOiyyyxTqSr-4fWpU-8NvJSRqRosSWFsSE/edit#slide=id.g29017cd9362_27_0)

### the basics

```kotlin
fun com.example.androidinformationrepository.leetcode.LinkedList.main(args: Array<String>) {
    print("Hello")
    println(", world!")
}
```

- エントリーポイントは com.example.androidinformationrepository.leetcode.LinkedList.main のトップレベル関数
- ↑は、String 引数を受け取る
- print は引数を標準出力に出力する
- println は引数を表示して、改行を加える

### Variables (変数)
- var: mutable
- val: immutable
- 型推論ができるよ
- 割り当て延期ができるよ: lateinit のことかな
- const val/var: 定数、定数名称は全部アッパーケースでかく、コンパイル時に計算(計算てか、メモリに割り当て)できるよってことだろ

### Functions
- 特に学べる新しいことはないねえ

### if expression
- function の {} は = に代替できるよ
- one line でもかけるよ

### when expression
- when ね、いつものだね
```kotlin
when(x) {
    1 -> {}
    2 -> {}
    else -> {
    }
}

when {
    x < 0 -> {}
    x = 0 -> {}
}
```

### When statement
- when ブロックがステートメントとして使われている場合、else ブランチは省略できる
```kotlin
when (teaSack) {
    is OolongSack -> error("We don't serve Chinese tea like $teaSack!")
    in trialTeaSacks, teaSackBoughtLastNight ->
        error("Are you insane?! We cannot serve uncertified tea!") 
}
```

- [ステートメントってなんやねん](https://kotlinlang.org/docs/control-flow.html#when-expression)
- enumクラスの項目やsealedクラスのサブタイプなど、すべての可能なケースが分岐条件でカバーされていることをコンパイラが証明できる場合のこと

### && vs and
- && は短絡評価を行うが、and は行わない
- || と or も同様
- 短絡評価: 片方の評価を行なって、演算子の条件を満たさなければ片方の評価は行わない
  - 演算処理の回数が減る、という意味でいいことだと思うんだけど、、、なぜ and と or が存在する？


### Loop
- do...while は見ずらいので書く意味あんのか？と思った、可読性悪いよね？
- break, continue はまあいつも通り

### Range
- この書き方は初めて見た
```kotlin
for (x in 9 downTo 0 step 3) {
    print(x)
}
```
- downto, step は拡張関数
- in 1..4 の .. は T.rangeTo(that: T) のこと

### Null Safety
- null safe だよ
- nullable にするなら明示しないといけないよ
- エルビス演算子とか使えるよね

### Elvis operator ?:
- 左辺が null なら別を代入するよ、みたいなイメージで良い
- `val item = findItem(id) ?: return "replace"`

### safe call
- `item?.detail?.image?.url` みたいなやつね
- null でなければアクセスするよって感じ

### Unsafe call
- 基本的に使わなくていい

### TODO
- ToDo コメントかけるよ
- 確か期限も書くことができて、期限を過ぎた場合はビルドエラーで落とせる機能がついたはず

### String templates and the string builder
- $ と ${} で関数処理の中からアクセスできるよ
- String に append したいなら StringBuilder を作成して以下のようにしてね
```kotlin
val sb = StringBuilder()
sb.append("Hello")
sb.append(", world!")
println(sb.toString())
```

### Lambda expressions
- ラムダ式ね！
- Android だと retryAction などを Viewmodel で定義して、それを UI に渡す時に使うかな

