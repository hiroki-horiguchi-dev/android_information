import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {

    val safeFrame = SafeFrame()

    while (true) {
        /// 時間経過をこっちで処理
        for (hour in 0..24) {
            runBlocking {
                delay(1000)
            }
            /// UI へ時間をセットしに行く
            safeFrame.setClock(hour)
        }
    }
}