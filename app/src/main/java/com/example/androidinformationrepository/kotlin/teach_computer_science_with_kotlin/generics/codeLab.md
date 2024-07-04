# [CodeLab](https://developer.android.com/codelabs/basic-android-kotlin-compose-generics?hl=ja#1)

まあ要するに、`汎用クラスを作ろう`、というのが目的

```kotlin
class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: String
)

fun main() {
    val question1 = Question<String>("Quoth the raven ___", "nevermore", "medium")
    val question2 = Question<Boolean>("The sky is green. True or false", false, "easy")
    val question3 = Question<Int>("How many days are there between full moons?", 28, "hard")
}
```

こんな感じで、Question.answer のみ型を不定にしたい場合に使う。