# [kotlin generics <in, out, where> terms with examples](https://betulnecanli.medium.com/kotlin-generics-in-out-where-terms-with-examples-445dc0bb45d6) 📝

こっちのがわかりいいわ

### In

- 総称型が入力型であることを指定するために使用される、つまり関数やクラスの引数としてのみ使用されることを意味する

```kotlin
interface Consumer<in T> {
    fun consume(item: T)
}

class StringConsumer : Consumer<String> {
    override fun consume(item: String) {
        println("Consuming string: $item")
    }
}

class AnyConsumer : Consumer<Any> {
    override fun consume(item: Any) {
        println("Consuming any type: $item")
    }
}

fun main() {
    val stringConsumer = StringConsumer()
    stringConsumer.consume("Hello") // prints "Consuming string: Hello"

    val anyConsumer: Consumer<Any> = AnyConsumer()
    anyConsumer.consume("Hello") // prints "Consuming any type: Hello"
    anyConsumer.consume(123) // prints "Consuming any type: 123"
}
```

- Consumer: T型の引数を取る単一のメソッド consume を持つインターフェース
- 型パラメータ T は in キーワードで宣言され、入力型としてのみ使用されることを示している
- StringConsumer, AnyConsumer: Consumer インタフェースを実装する二つのクラスで、どちらもそれぞれの方のインスタンスを消費するために使用できる
- 👀Repository パターンで実装する時につかえないかね？どうかね？と妄想してみる


### Out
- outキーワードは、総称型が「出力」型であることを指定するために使用される、つまり、関数やクラスからの戻り型としてのみ使用される

```kotlin
interface Producer<out T> {
    fun produce(): T
}

class StringProducer : Producer<String> {
    override fun produce(): String = "Hello"
}

class AnyProducer : Producer<Any> {
    override fun produce(): Any = "Hello"
}

fun main() {
    val stringProducer = StringProducer()
    println(stringProducer.produce()) // prints "Hello"

    val anyProducer: Producer<Any> = AnyProducer()
    println(anyProducer.produce()) // prints "Hello"
}
```

- Producer: T型の値を返す単一のメソッド produce を持つインターフェース
- 型パラメータTは、出力型としてのみ使用されることを示す out キーワードで宣言されている
- StringProducer,AnyProducer: どちらも Producer を実装し、それぞれの方のインスタンスを生成するために使用できる

### Where
- 初めて見たんだけども。。
- 引数や戻り値として使用できる方の制約を制定するために使用する

```kotlin
interface Processor<T> where T : CharSequence, T : Comparable<T> {
    fun process(value: T): Int
}

class StringProcessor : Processor<String> {
    override fun process(value: String): Int = value.length
}
```
- 

```kotlin
interface Processor<T> where T : CharSequence, T : Comparable<T> {
    fun process(value: T): Int
}

class StringProcessor : Processor<String> {
    override fun process(value: String): Int = value.length
}

fun main() {
    val stringProcessor = StringProcessor()
    println(stringProcessor.process("Hello")) // prints "5"
}
```
- Processor: T型の引数を取り、Int を返却する1つのメソッドを持つインターフェース
- 型パラメータ T は where キーワードで宣言され、2つの制約が指定されている
- T は CharSequenceインターフェースを実装し、それ自身と Comparable でなければいけない
  - [Comparable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/) って何？
  - このインターフェースを継承したクラスは、インスタンス間の順序の合計が定義されている
  - 順序の合計って何？
  - [compareTo](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/compare-to.html) メソッドについて
  - オブジェクト同士が等しい場合は 0, 比較対象より小さい場合は負の数、大きい場合は正の数を返却する
  - 小さい、大きいって何を比較してる？がようわからん
  
