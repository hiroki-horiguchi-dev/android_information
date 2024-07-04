# [Generics](https://docs.google.com/presentation/d/1R7n5plsn5caGpYrI9omxbEuX6pazjDj2d9X0IQ2AdLg/edit#slide=id.g170f5c4616b_4_39)

全体を通して、なぜ必要なのか？何で使えるのか？というのがはっきりしなかった。。

### What? Why? How?
- Kotlin の数値を継承している型は Int, Double, Byte, Float, Long, Short
- 例えばこれらの型を用いて作成された list があった時、ソートプログラムを書くときはそれぞれの型に対応したソートプログラムが必要なのか？
- NO!
- ソートアルゴリズムは、実際に何をソートしているかに関わらず、`2つの値を互いに比較できれば良い` --> これが本質やな
- Generics はどのような方でも動作するコード、いくつかのルールを満たす必要があるがそれ以外の制限を受けない型を扱うコードをかけるようにするもの

### Constraints(制約)
- 時には任意の型を扱いたくないのに、その型が何かしらの機能を提供してくれることを期待することがあるが、この場合、上限値おいう形の型制約が使われる
```kotlin
class Pilot<T : Movable>(val vehicle: T) { 
	fun go() { vehicle.move() }
}

val ryanGosling = Pilot<Car>(Car("Chevy", "Malibu"))
val sullySullenberger = Pilot<Plane>(Plane("Airbus", "A320"))
```

### Constraints continued
- パラメータ方は複数存在することができて、Generic class は継承に参加できる
- 複数の制約が存在することもある(制約 == 型パラメータが複数のインタフェースを実装しなければいけない.かな？)

### Star-projection
- パラメータタイプに拘らない場合は、スタープロジェクション(== ＊)(Any?/Nothing)を使用できる
```kotlin
fun printKeys(map: MutableMap<*, *>) { ... }
```

### Let's go back
```kotlin
open class A
open class B : A()
class C : B()

Nothing <: C <: B <: A <: Any
```
- うん、みたままだね

### What is next?
- 基本的な例を考えよう
```kotlin
interface Holder<T> {
	fun push(newValue: T) // consumes an element

fun pop(): T // produces an element

fun size(): Int // does not interact with T
}
```
- 型プロジェクションがあるよ --> さっきの consumes あたりを明示的にかけるってことやな
```kotlin
G<T> // invariant, can consume and produce elements
G<in T> // contravariant, can only consume elements
G<out T> // covariant, can only produce elements
G<*> // star-projection, does not interact with T
```

### 例
```kotlin
G<T> // invariant, can consume and produce elements

interface Holder<T> {
	fun push(newValue: T)// consumes an element: OK

fun pop(): T // produces an element: OK

fun size(): Int // does not interact with T: OK
}
```

```kotlin
G<in T> // contravariant, can only consume elements

interface Holder<in T> {
	fun push(newValue: T) // consumes an element: OK

fun pop(): T // produces an element: ERROR: [TYPE_VARIANCE_CONFLICT_ERROR] Type parameter T is declared as 'in' but occurs in 'out' position in type T

fun size(): Int // does not interact with T: OK
}
```

```kotlin
G<out T> // covariant, can only produce elements

interface Holder<out T> {
	fun push(newValue: T) // consumes an element: ERROR: [TYPE_VARIANCE_CONFLICT_ERROR] Type parameter T is declared as 'out' but occurs in 'in' position in type T

fun pop(): T // produces an element: OK

fun size(): Int // does not interact with T: OK
}
```

```kotlin
interface Holder<T> {
	fun push(newValue: T) // consumes an element: OK
fun pop(): T // produces an element: OK
fun size(): Int // does not interact with T: OK
}

fun <T> foo1(holder: Holder<T>, t: T) {
	holder.push(t) // OK
}

fun <T> foo2(holder: Holder<*>, t: T) {
	holder.push(t) // ERROR: [TYPE_MISMATCH] Type mismatch. Required: Nothing. Found: T
}
```
- 型プロジェクションを使用することで、その interface の振る舞いを固定できるってことね、Error はビルドエラーで落ちてくれるってことかな、いいね

### Subtyping
```kotlin
open class A
open class B : A()      —--->  Nothing <: C <: B <: A <: Any
class C : B()

class Holder<T>(val value: T) { ... }
```

![img.png](img.png)

- c は b の子なので B のインスタンスに保存できるけど、
- Holder で引数 Generics で受け取った場合、継承関係は保たれない、つまり不変だよってことね

```kotlin
open class A
open class B : A()      —--->  Nothing <: C <: B <: A <: Any
class C : B()

class Holder<T>(val value: T) { ... }

val holderC: Holder<C> = Holder(C())
val holderB: Holder<B> = holderC //ERROR: Type mismatch. Required: Holder<B>. Found: Holder<C>.
```

- これはさっきやったことの復讐だね、エラーが出てだめ
- BUT!!!
```kotlin
val holderB: Holder<B> = Holder(C()) // OK, because of casting
```
- こういう書き方は OK！

```kotlin
class Holder<T> (var value: T?) {
   fun pop(): T? = value.also { value = null }
   fun push(newValue: T?): T? = value.also { value = newValue }
   fun steal(other: Holder<T>) { value = other.pop() }
   fun gift(other: Holder<T>) { other.push(pop()) }
}

Holder<Nothing> <:> Holder<C> <:> Holder<B> <:> Holder<A> <:> Holder<Any>

val holderB: Holder<B> = Holder(B())
val holderA: Holder<A> = Holder(null)
holderA.steal(holderB) // ERROR: Type mismatch. Required: Holder<A>. Found: Holder<B>.
holderB.gift(holderA) // ERROR: Type mismatch. Required: Holder<B>. Found: Holder<A>.
```

- これは NG なんだねえ

### Type projections: in, out
```kotlin
class Holder<T> (var value: T?) {
   ...
   fun gift(other: Holder<in T>) { other.push(pop()) }
}
holderB.gift(holderA) // OK

Type projection: other is a restricted (projected) generic. You can only call methods that accept the type parameter T, which in this case means that you can only call push(). 

This is contravariance:
Nothing <: C <: B <: A <: Any
Holder<Nothing> :> Holder<C> :> Holder<B> :> Holder<A> :> Holder<Any>
```

```kotlin
class Holder<T> (var value: T?) {
   ...
   fun steal(other: Holder<out T>) { value = other.pop() }
}
holderA.steal(holderB) // OK

Type projection: other is a restricted (projected) generic. You can only call methods that return the type parameter T, which in this case means that you can only call pop(). 

This is covariance:
Nothing <: C <: B <: A <: Any
Holder<Nothing> <: Holder<C> <: Holder<B> <: Holder<A> <: Holder<Any>
```

- 上で見た interface の動作制御と同じだと思う、、ちょっと怪しいので復習が必要だな

### Type projections
```kotlin
class Holder<T> (var value: T?) {
   fun steal(other: Holder<out T>) { 
      val oldValue = push(other.pop())
	 other.push(oldValue) // ERROR: Type mismatch. Required: Nothing?. Found: T?.
   }
   fun gift(other: Holder<in T>) { 
      val otherValue = other.push(pop())
      push(otherValue) // ERROR: Type mismatch. Required: T?. Found: Any?.
   }
}
```
- `out T`: Tにキャストできる何かを返し、文字通りNothingを受け入れる
  - steal メソッドは返り値の型として Nothing? を定義しろって言ってるってことか？ようわからんなこれ
- `in T` : Tにキャストできる何かを受け取り、無意味な Any? を返却する 
  - gift メソッドは返り値として Any? を書きなよって言ってるってことか？難しいなこれ

### Type erasure
- 実行時、ジェネリック型のインスタンスは実際の方引数に関する情報を一歳保持しない
- 各テンプレ＝とが提供される方引数ごとに別々にコンパイルされる C++ とは対照的に、ジェネリックの全ての使用において同じバイトコードが使用される
  - 引数の型が不明な状態ってことだよね？ヒープ領域でどうにかするって話なのか。。それとも Any でメモリを取っておくのか、どっちかね
- Kotlin/JVM では、ジェネリック型のパラメータを変更して関数をオーバーライドすることはできない
```kotlin
fun quickSort(collection: Collection<Int>) { ... }
fun quickSort(collection: Collection<Double>) { ... }
```
- これはどちらも `quickSort(collection: Collection<*>)` こうなってしまうみたい
- ただし、JvmName アノテーションを使用すれば防げるみたい

### Nullability in generics
- T は nullable 、non null ではない
このくらいでいいかな、使うイメージが湧かない。。

### Inline functions
- 第一級オブジェクトとして使用される場合、関数はオブジェクトとして格納されるため、メモリ割り当てが必要となり、実行時のオーバーヘッドが発生する
```kotlin
fun foo(str: String, call: (String) -> Unit) {
    call(str) 
}

public static final void foo(@NotNull String str, @NotNull Function1 call) {
    Intrinsics.checkNotNullParameter(str, "str");
    Intrinsics.checkNotNullParameter(call, "call");
    call.invoke(str);
}

public static final void main() {
    foo("Top level function with lambda example", (Function1)foo$call$lambda$1.INSTANCE);
}

```
- 急にラムダ式の話になったけど、何これ。。。ラムダ式の関数を Java のバイトコードで見るとこうなるよって話か？
```kotlin
public static final void foo(@NotNull String str, @NotNull Function1 call) {
    Intrinsics.checkNotNullParameter(str, "str");
    Intrinsics.checkNotNullParameter(call, "call");
    call.invoke(str);
}


foo("...", new Function() {
    @Override
        public void invoke() {}
    }
);
```
- インライン関数については理解するのが少々難しいので、「呼び出し元のオーバーヘッドを軽減する」ではなくて、呼び出し元関数の方に呼び出し先関数を展開しちゃう方法で、処理が早くなるよ、で覚えた方がいいね
- Kotlin では、inline をつけると呼び出し元の(main)に処理を展開してくれるので、オーバーヘッド少なく済むよ！ってだけやな
```kotlin
inline fun foo(str: String, call: (String) -> Unit) {
	call(str)
}
fun main() {
	foo("Top level function with lambda example", ::print)
}

public static final void main() {
String str$iv = "Top level function with lambda example";
     int $i$f$foo = false;
     int var3 = false;
     System.out.print(str$iv);
}
```
- inline は関数そのものだけではなく、引数として渡される全てのラムダにも影響する
- インライン関数に渡されるラムダのいくつかをインライン化したくない場合(大きな関数の場合)、関数のパラメータのいくつかを noinline 修飾子でマークすることができる
```kotlin
inline fun foo(str: String, call1: (String) -> Unit, noinline call2: (String) -> Unit) {
	call1(str) // Will be inlined
    call2(str) // Will not be inlined
}
```
- 理解がむずいな。。大きい処理だからこそ逆に inline して呼び出し元にコピーした方がオーバーヘッドの削減という意味でいいのかと思ったけど、違うんやな
  - コピーするところに負荷がかかってオーバーヘッドとの釣り合いでむしろマイナス、みたいな感じなのかなと想像、あくまで想像
- inline されたラムダ式ないで return を使うことができるけど、非ローカルリターンと言われて予期しない動作になる可能性がある
  - 使わない方がいいってことなのかな
```kotlin
inline fun foo(crossinline call1: () -> Unit, call2: () -> Unit) {
	call1() 
    call2() 
}

fun main() {
  println("Step#1")
  foo({ println("Step#2")
    return }, // ERROR: 'return' is not allowed here						
    { println("Step#3") })
  println("Step#4")
}

```
- crossinline 修飾子をつけると、ラムダ式ないの return が NG になってエラーになる
- crossinline 修飾子は [Runnable](https://developer.android.com/reference/kotlin/java/lang/Runnable) みたいに、スレッドを立てて非同期(と言ってしまっていいか？)処理との相性がいいよってお話
```kotlin
inline fun drive(crossinline specialCall: (String) -> Unit, call: (String) -> Unit) {
   val nightCall = Runnable { specialCall("There's something inside you") }
   call("I'm giving you a nightcall to tell you how I feel")
   thread { nightCall.run() }
   call("I'm gonna drive you through the night, down the hills")
}
fun main() {
   drive({ System.err.println(it) }) { println(it) }
}
```

### Inline reified functions
- パラメータとして渡された方にアクセスする必要がある場合もあるよね？
```kotlin
fun <T: Animal> foo() {
	println(T::class) // ERROR: Cannot use 'T' as reified type parameter. Use a class instead —--> add a param: t: KClass<T>
}
```
- 上記だと NG
```kotlin
inline fun <reified T: Animal> foo() {
	println(T::class) // OK
}
```
- reified キーワードを使用することで T へのアクセスが可能になる


