# API レスポンスが帰った時に URL 部分のみ青字かつクリック可能で外部ブラウザを表示する方法

### 一般的な方法(Gemini に聞いてもほぼ同じ回答が返ってきた)

こんな感じで書くのが一般的かな。。

```kotlin
@Composable
fun UrlText(text: String) {
    val annotatedString = buildAnnotatedString {
        val startIndex = text.indexOf("https://")
        if (startIndex != -1) {
            val endIndex = startIndex + "https://".length + text.substring(startIndex + "https://".length).indexOf(' ')
            pushStringAnnotation(tag = "URL", annotation = text.substring(startIndex, endIndex))
            withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                append(text.substring(startIndex, endIndex))
            }
            pop()
        }
        append(text.substring(endIndex))
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            val annotation = annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset).firstOrNull()
            if (annotation != null) {
                val url = annotation.item
                // Open the URL in a browser
            }
        }
    )
}
```

### AndroidView で描画する方法
Compose を使って TextView を描画する方法って感じ。

```kotlin
        Box(
            modifier = Modifier
                ...
        ) {
            val mCustomLinkifyText = remember { TextView(context) }
            AndroidView(factory = { mCustomLinkifyText }) { textView ->
                textView.text = 
                textView.textSize = Float 値で指定 (14F) など
                textView.setTextColor()
                LinkifyCompat.addLinks(textView, Linkify.WEB_URLS)
                textView.movementMethod = LinkMovementMethod.getInstance()
            }
        }
```

