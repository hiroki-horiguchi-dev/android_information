# [Parallel & Concurrent Programming](https://docs.google.com/presentation/d/1n8rTULotZHei3ktajyupwRpKdPDACBAZeBo2GqwYhHY/edit#slide=id.p1)

## Definition
- ウィキペディアによれば、並列コンピューティングとは、「多くの計算や処理が同時に実行される」コンピューティングの一種
- コンカレント・コンピューティングとは、複数の計算を逐次的に実行するのではなく、時間的に重なりながら同時に実行するコンピューティングの一形態
- 同時並行性なしに並列性を持たせることも、並列性なしに同時並行性を持たせることも可能

## Parallelism vs concurrency
![img.png](img.png)

## Concurrency: processes vs threads
![img_1.png](img_1.png)

## Preemptive vs cooperative scheduling
- 割り込み型と協調型があるみたい
![img_2.png](img_2.png)

## Parallel and concurrent Programming in the JVM
- JVMは独自のスケジューラを持っている
- OSのスケジューラから独立しているJVMスレッド != OSスレッド
- => マルチスレッドのJVMアプリは、シングルスレッドOS上で実行できる
- (DOS）JVMスレッドはデーモンかユーザースレッド

- すべてのユーザー・スレッドが終了するとアプリは停止
- JVMはデーモンスレッドの終了を待たない

## Parallel programming in the JVM
- Java
  - java.langには基本的なプリミティブが含まれています： Runnable、Threadなど 
  - java.util.concurrentには同期プリミティブと並行データ構造が含まれる
- Kotlin
  - kotlin.concurrent - Javaクラスのラッパーと拡張

## Throwback: Single abstract method interfaces
```kotlin
@FunctionalInterface
public interface Runnable {
   public abstract void run();
}

class RunnableWrapper(val runnable: Runnable)

val myWrapperObject =
   RunnableWrapper(
       object : Runnable {
           override fun run() {
               println("I run")
           }
       }
   )
val myWrapperLambda = RunnableWrapper { println("yo") }
```
- 前回の講義で取り上げた単一抽象メソッド・インターフェースについて考える
- これらは、その単一の抽象メソッドの実装だけをラムダとして提供することで、インスタンス化することができる
- 次のトピックであるRunnableは、JVM並列プログラミングで広く使われている非常に一般的なインターフェース

## Ways to create threads
- スレッドはJVMのクラスで、個別のスレッドで実行できる作業を表すためのもの
- Threadは前のスライドで述べたRunnableインターフェースを実装
- Threadクラスを継承し、runメソッドを実装し、アプリケーション全体で使用することができる
```kotlin
class MyThread : Thread() {
   override fun run() {
       println("${currentThread()} is running")
   }
}

fun main() {
   val myThread = MyThread()
   myThread.start()
}
```

## run vs start
- スレッドはRunnableを実装しているので、runを呼び出すことはできるが、呼び出すべきではない
- スレッドで動作すべきコードに過ぎないが、それを呼び出すと、呼び出したスレッドでそのコードが実行されてしまう（並列性がない）
- その代わりに、スレッドはstartを使用して起動する必要がある
- startは、runの実行を別のスレッドに移し、startを呼び出したスレッドをブロックしない
```kotlin
fun main() {
   val myThread1 = MyThread()
   myThread1.start() // OK
   val myThread2 = MyThread()
   myThread2.run() // Current thread gets blocked
}
```

## Ways to create threads
- Runnableインターフェイスを実装し、それをスレッドに渡すことができる
- 同じRunnableを複数のスレッドに渡すことができる
```kotlin
fun main() {
   val myRunnable = Runnable { println("Sorry, gotta run!") }
   val thread1 = Thread(myRunnable)
   thread1.start()
   val thread2 = Thread(myRunnable)
   thread2.start()
}
```
- もっと簡単な方法は、Runnableインターフェイスを実装し、できたクラスをスレッドに渡すこと
- Threadはクラスなので、Threadを継承しても他のクラスを継承することはできない
- インターフェースなので、実装したクラスは他のどの階層にも参加することができる
- ここでもうひとついいのは、同じRunnableインスタンスを複数のスレッドに渡せること
- ただし、これらのrunnableや/threadが共有する可能性のあるリソースには（いつものように）注意しなければならない

- Kotlinには、スレッドを作成するさらにシンプルな方法があるが、ボンネットの下では、昔と同じスレッドが作成され、開始される
- 
```kotlin
import kotlin.concurrent.thread

fun main() {
   val kotlinThread = thread {
       println("I start instantly, but you can pass an option to start me later")
   }
}
```
- これがスレッドを作成する望ましい方法
- スレッドはKotlinの高次関数で、ラムダ（実行メソッドの実装）を受け取って新しいスレッドを作成し、即座に開始される
- 詳しくはこちらのドキュメントを参照
- threadはいくつかの引数も受け取りますが、それらはスレッドのプロパティに対応
- thread(start = false, name = "Threadripper") { ... } は、即座には開始されず、 "Threadripper" という名前を持つスレッドを作成

## Thread properties
- スレッドのプロパティは、開始後に変更することはできない
```text
スレッドの主なプロパティ
id： Long - これはスレッドの識別子です。
name: 文字列
priority: 文字列： Int - 1から10の範囲で、値が大きいほど優先度が高いことを示す。
デーモン： ブール値
状態： スレッド状態
isAlive： ブール値
```
- Priorityは、与えられたスレッドに対してより多くの、あるいはより少ないプロセッサ時間を割り当てるようにスケジューラに要求する方法

## State of a thread
![img_3.png](img_3.png)
- Stateは特殊で、isAliveは理解しやすいフラグで、スレッドが何かを実行していることを知らせるだけ
- スレッドが作成されたが開始されていない場合、スレッドは何も実行できず、生きていない
- もちろん、スレッドがすべての処理を終了したときや、エラーに遭遇したときも、スレッドは生きていない
- スレッドはさまざまな理由でブロックされるため、さまざまな「ブロックされた」状態がある
- ブロックされた状態とは、ソケットへの書き込みなど、OSのイベントを待っている状態
- 待機中とは、ロックや条件など、何らかのリソースを待っている状態を意味する
- 時間待ちとは、スレッドがスリープしているか、タイムアウトを伴うブロッキング処理を実行していることを意味する
- ![img_4.png](img_4.png)
- Runnableは、スレッドが実行可能であることを示す状態であり、実行するかどうかはスケジューラ次第であることを意味する
- スケジューラは、コードの任意のタイミング／ステートメントで、スレッドをプロセスから離す（パークする）ことができる
- 実行中のボックスが破線になっているのは、仮想状態と考えることができるから
- Running "のために別のThread.stateを持つのは意味がない
- なぜなら、この情報を得るまでに、スケジューラはすでにスレッドをRunnableに戻している可能性が高いから
- スレッドがWaiting状態やBlocked状態に移行できるのは、Running状態からだけ

## Ways to manipulate a thread's state
```kotlin
val myThread = thread { ... } — Creates a new thread
myThread.start() — Starts a thread
myThread.join() — Causes the current thread to wait for another thread to finish
sleep(...) —  Puts the current thread to sleep 
yield() — Tries to step back `
myThread.interrupt() — Tries to interrupt a thread
myThread.isInterrupted() — Checks whether thread was interrupted 
interrupted() — Checks and clears the interruption flag
```
- ここで重要なのは、sleepはThreadクラスのスタティック・メソッドだということ
- こう書きたくなるかもしれない
```kotlin
val myThread = thread { ... }
// some work
myThread.sleep(...)
```
- しかし、これは静的メソッドなので、myThreadではなく、現在のスレッドをスリープさせることになる
- yield()とinterrupted()も静的メソッド
- yield()は、指定されたスレッドを実行から移すようスケジューラに助言するが、スケジューラはこの助言を無視する自由がある

## sleep, join, yield, interrupt
- sleepメソッドとyieldメソッドは現在のスレッドにのみ適用されるため、他のスレッドを中断することはできない
- すべてのブロッキング・メソッドと待機メソッドはInterruptedExceptionを投げることができる
- ブロッキング・メソッドと待機メソッドには、sleepとjoin、そして後で説明するリソースを待機するさまざまなメソッドがある

## Classic worker
```kotlin
class ClassicWorker : Runnable {
   override fun run() {
       try {
           while (!Thread.interrupted()) {
               // do stuff
           }
       } catch (e: InterruptedException) {} // absolutely legal empty catch block
   }
}
```
- InterruptedExceptionは、ループ内で待機またはブロックしている操作がある場合にスローされる
- ここでの要点は、スレッドの割り込みに対応するのは我々の責任だということ
- 誰かが割り込みシグナルを送ったからといって、スレッドが動作を停止することはない

## Parallelism and shared memory: Examples of problematic interleaving
- 並列スレッドは同じ共有メモリにアクセスできる
- このため、シングルスレッド環境では起こりえない問題がしばしば発生する
```kotlin
class Counter {
   private var c = 0

   fun increment() {
       c++
   }
   fun decrement() {
       c--
   }
   fun value(): Int {
       return c
   }
}
```
- cに対する操作はどちらも単一の単純なステートメント
- しかし、単純なステートメントであっても、仮想マシンによって複数のステップに変換され、それらのステップをインターリーブすることができる
  - interleave: 閉じ込む、差し込む、挟む

- 共有メモリーを使った並列実行は、非常にエラーを起こしやすいアプローチ
- 以下は、この方法を使用した場合に起こりうる問題の簡単な例
  - 変数cに対する両方の操作は、単一の単純なステートメントのように見える
  - しかし、単純なステートメントであっても、仮想マシンによって複数のステップに変換され、スケジューラがスレッドの実行を切り替えて、それらの操作がインターリーブすることがある
  - その結果、この例のように、アプリケーションのロジックに対して有効でない状態に遭遇することがある
  - この例では、incrementを2回呼び出すと、cの値が期待される2ではなく1になる
    - え？まじ？
  - このような問題は、共有された変更可能な状態があるときに発生する
- スレッド#1とスレッド#2が同時にインクリメントを実行したとする
- 初期値が0である場合、両者のインタリーブ動作は次のようになる
  - ![img_5.png](img_5.png)
  - 文章で書かれるとアレだけど、これは直感でわかるね
  - ![img_6.png](img_6.png)
  - mermaid で書くとこんな感じやな

## Synchronization mechanisms
- Lockやsynchronizedキーワードなどの相互排除
- 並行データ構造と同期プリミティブ
- 共有メモリを直接扱うアトミック（危険地帯）

## Locks
```kotlin
class LockedCounter {

   private var c = 0

   private val lock = ReentrantLock()

   fun increment() {
       lock.withLock { c++ }
   }

   // same for other methods
   …
}
```
- オラクルのドキュメントより：ロックは、複数のスレッドが共有するリソースへのアクセスを制御するためのツール
- 通常、ロックは共有リソースへの排他的なアクセスを提供する
- 一度に1つのスレッドだけがロックを取得でき、共有リソースへのすべてのアクセスは最初にロックを取得する必要がある、1つのスレッドのみ⇒相互排他
```kotlin
lock.withLock { block }
```
- とほぼ同じで、Kotlinの便利な高階関数
```kotlin
lock.lock()
block
lock.unlock()
```
- ロックを獲得し、それを解放していないコードは「クリティカルセクション」と呼ばれ、プログラムの中で他のスレッドと同期されるべき部分

## The lock interface
- ![img_7.png](img_7.png)
- ロックの取得は、そのロックが他のスレッドによって保持されていない場合にのみ可能
- 他のスレッドがすでにロックを保持している場合、現在のスレッドはロックを取得できるまで（または割り込まれるまで）ブロックされる（割り込まれた場合はInterruptedExceptionがスロー)
- tryLockは、ロック取得に失敗してもスレッドをブロックしない

## Conditions
```kotlin
class PositiveLockedCounter {
   private var c = 0
   private val lock = ReentrantLock()
   private val condition = lock.newCondition()

   fun increment() {
       lock.withLock {
           c++
           condition.signal()
       }
   }

   fun decrement() {
       lock.withLock {
           while (c == 0) { 
               condition.await() 
           }
           c--
       }
   }

   fun value(): Int {
       return lock.withLock { c }
   }
}
```
- 条件によって、ロックを保持しているスレッドは、他のスレッドが特定のイベントに関してシグナルを送るまで待つことができる
- 内部的には、awaitメソッドは呼び出しと同時に関連するロックを解放し、最後に再びロックを返す前にそれを取得する
  - await: ロックの解放と、あと何かやるんだね
- Cがロックに接続されている場合、ロックを保持しているスレッドだけがcondition.await()またはcondition.signal()を呼び出すことができる
  - condition.signal と condition.await は withLoack の中だけでしか使えない
- decrement関数で何が起こるか見てみよう：
  - あるスレッドTがdecrement()を呼び出したとする
  - スレッドTがdeclment()を呼び出すと、まずロックの取得を試み、成功するまでブロックされる
  - ロックを獲得した後、cの値をチェックし、それがゼロであることを確認し、condition.await()を呼び出す
  - これは、Tがロックを解放して待ち状態に入ることを意味する
  - ある時点で、他のスレッドがincrement()を呼び出し、そこでcondition.signal()を実行するかもしれない
  - これによりTは目覚めるが、クリティカル・セクションにあるため、すぐには実行されない
  - 続行するには、ロックを再度取得しなければならない
  - signalを呼び出したスレッドはロックを保持している（そうでなければ、そもそもsignalを呼び出すことはできない）
  - そのスレッドはロックを解放するが、他のスレッドがTより先にロックを獲得するかもしれない
  - ある時点で、Tはロックを取り戻し、Cをデクリメントする
    - 使うかこれ？？？

## The ReentrantLock class
- ReentrantLock - 同じスレッドが複数回ロックを取得できるようにする
- lock.getHoldCount() - 現在のスレッドがこのロックを保持している数を取得 
- lock.queuedThreads() - このロックを待機しているスレッドのコレクションを取得する 
- lock.isFair() - ロックの公平性をチェックする

## The synchronized statement
- JVMでは、すべてのオブジェクトに固有のロックが関連付けられている（別名モニター）
```kotlin
class Counter {
   private var c = 0

   fun increment() {
       synchronized(this) { c++ }
   }

   …
}
```
- JVMでは、すべてのオブジェクトはその内部に「隠された」ロック（intrinsic lock）を持っている
- このロックに直接アクセスすることはできないが、synchronized高次関数（Javaのキーワード）を使って操作することができる
- synchronized(...)の中では、どんなオブジェクトでも使用できる

## Synchronized method
- ![img_8.png](img_8.png)
- クラスのsynchronizedメソッドは、synchronized(this)でラップされたメソッドである

## The ReadWriteLock class
- ReadWriteLockは、複数のリーダが同時にリソースにアクセスすることを許可しますが、単一のライタのみがそのリソースを変更することを許可
- ![img_9.png](img_9.png)

## The ReadWriteLock Class
```kotlin
class PositiveLockedCounter {
   private var c = 0
   private val rwLock = ReadWriteReentrantLock()

   fun increment() {
       rwLock.write { c++ }
   }

   fun decrement() {
       rwLock.write { c-- }
   }

   fun value(): Int {
       return rwLock.read { c }
   }
}
```
- これは、ReadWriteLockを使ってスレッドセーフなカウンターを作る例
- 値 `c` の取得には何の変更も必要ないので、read { ... } で行うことができ、複数のスレッドが一度に value() を呼び出すことができる

## Concurrent blocking collections
- java.util.concurrentは、以下のようなブロッキングおよびノンブロッキングの並行コレクションを実装するJavaパッケージ
- ![img_10.png](img_10.png)
- スレッド間の情報共有の問題を解決するのにロックだけでは不十分な場合は、java.util.concurrent.Collectionが提供する並行（スレッドセーフ）コレクションを使用することができる
- このスライドでは、このパッケージから人気のあるコレクションをいくつか紹介する
- "ブロッキング "とは、例えば、スレッドが空のコレクションから何かを取り出そうとしたり、すでに最大容量に達しているコレクションに何かを入れようとしたりすると、目的の操作を正常に実行できるようになるまでブロックされることを意味する

## Concurrent non-blocking collections
- jdk.util.concurrent は、以下のようなブロッキングおよびノンブロッキングの並行コレクションを実装するJavaパッケージ
- ![img_11.png](img_11.png)
- Java.util.concurrentには、いくつかのノンブロッキングコレクションもある
- これらのコレクションは、スレッドが空のコレクションから何かを取り出そうとしても、実行をブロックしない
- 待ちのないアルゴリズムを使用することで、これを可能にしている
- 例えば、ConcurrentLinkedQueueから要素を取得するには、誰かがキューに何かを入れるのを待つのではなく、キューが空の場合にnullを返すpoll()を呼び出す必要がある
- nullはコレクションが空であることを意味するため、nullをキューに入れることは禁止されている
- ConcurrentSkipListMapはTreeMapに似ていますが、ツリーの代わりにスキープリストをベースにしている
- 

## Synchronization primitives
- java.util.concurrentは、並行データ構造と同期プリミティブも実装している
- Exchanger - ブロック交換
- Phaser - バリア同期
- Exchangerは次のような単一のメソッド交換を提供
  - Phaserは、複数のスレッドを登録できる再利用可能な同期バリア
  - スレッドが(arriveまたはarriveAndAwaitAdvanceを介して)その到着をphaserに通知するたびに、そのフェーズ(intカウンタ)がインクリメントされる
  - スレッドが arriveAndAwaitAdvance を呼び出すと、フェイザーは、登録されている他のすべてのスレッドがこのフェーズに到達するまで待機 (ブロック) 
  - スレッドはフェイザーから登録を解除することができる
  - CountDownLatchは同様の「バリア」同期プリミティブで、より単純で一般的ですが、柔軟性も劣る

## Java Memory Model: Weak behaviors
```kotlin
class OrderingTest {
   var x = 0
   var y = 0
   fun test() {
       thread {
           x = 1
           y = 1
       }
       thread {
           val a = y
           val b = x
           println("$a, $b")
       }
   }
}
```
- ![img_12.png](img_12.png)
- 言いたいことはこれだけで大体わかる

```kotlin
class ProgressTest {
   var flag = false
   fun test() {
       thread {
           while (!flag) {}
           println("I am free!")
       }
       thread { flag = true }
   }
}
```
- ![img_13.png](img_13.png)
- デフォルトの設定では、スレッド1がフラグの変化を見る保証はない
- コンパイラーは、スレッド1が決してフラグを変更しないことを見るかもしれないので、while(!flag)をwhile(!false)に変更し、またwhile(true)に変更するかもしれない

```kotlin
class ProgressTest {
   var flag = false
   fun test() {
       thread {
           while (true) {}
           println("I am free!")
       }
       thread { flag = true }
   }
}
```
- ![img_14.png](img_14.png)

## JMM: Data-Race-Freedom Guarantee
- しかし、JMMは何を保証しているのだろうか？
- うまく同期されたプログラムは単純なインターリーブ・セマンティクスを持つ
- ロビン・ミルナーの言葉に "Well typed programs cannot go wrong "というのがある
- これは、型推論が成功したプログラムは予期せぬエラーを投げないという意味
- JMMも同様の概念を保証している
- 「よく同期されたプログラムは単純なインターリーブ・セマンティクスを持つ

## JMM: Data-Race-Freedom Guarantee
- しかし、JMMは何を保証しているのだろうか？
- よく同期されたプログラムは単純なインターリーブ・セマンティクスを持つ
- よく同期している = データ・レースがない
- 単純なインターリーブ・セマンティクス = 順次一貫したセマンティクス
- データ・レース・フリーのプログラムは逐次一貫したセマンティクスを持つ
- この場合の "同期がとれている "とは、非同期の共有非原子変数への同時アクセスがないことを意味する

## JMM: Volatile fields
- ![img_15.png](img_15.png)
- Volatileは、変数の値が処理されるたびにメモリから再読み込みを強制する
- このおかげで、while(!flag)がwhile(!false)になることはない
- なぜなら、スレッドはwhileの状態をチェックするためにアクセスするたびにフラグを読まなければならないから

## JMM: Volatile fields
- 揮発性フィールドってタイトルなので、y がどこかのタイミングでメモリというか主記憶装置から消える可能性があるってことなのか？
```kotlin
class OrderingTest {
   var x = 0
   @Volatile var y = 0
   fun test() {
       thread {
           x = 1
           y = 1
       }
       thread {
           val a = y
           val b = x
           println("$a, $b")
       }
   }
}
```

## JMM: Happens-before relation
次ここから