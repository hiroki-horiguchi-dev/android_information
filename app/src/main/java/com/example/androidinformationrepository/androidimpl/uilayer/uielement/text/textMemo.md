### Text (remember の使い方)で描画、1行超過したら2行にする

コードを初見で見た時に、remember 要らなくね？ってなったけど、あ、そういうことね！ってなったやつ

1. `text = remember {} maxLine = remember {mutableStateOf(1)}` をまず定義
2. by ではなく = を使っているのは再代入を可能にするため
3. 実際にテキストを描画するタイミングで、onTextLayout{} の `hasVisualOverflow` で1行に収まりきらなければ、2行に変更する
4. その際に、text の指定箇所に改行コードを挿入する
5. text, maxLine が remember で定義されているので、再描画が走り、text = 改行コード入りの text, maxLines = 2 で再度描画される
6. remember を使う意味なくね？と思っていたけど、こういう事情なのでOk

```kotlin
        val text = remember { mutableStateOf(order.getPrice() +
                "　" + order.getlabel()) }
        val maxLine = remember { mutableStateOf(1) }
        Text(
            ...
            onTextLayout = {
                if (it.hasVisualOverflow) {
                    maxLine.value = 2
                    text.value = order.getPrice() +
                            "\n" + order.getlabel()
                }
            }
                ...
        )
```