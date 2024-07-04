import com.example.androidinformationrepository.designPattern.state.state.state.DayState
import com.example.androidinformationrepository.designPattern.state.state.state.State
import com.example.androidinformationrepository.designPattern.state.state.ui.Context

/// UI
class SafeFrame : Context {

    /// UI コンポーネントを設定
    private val buttonUse = Button("金庫使用")
    private val buttonAlarm = Button("非常ベル")
    private val buttonPhone = Button("通常通話")
    private val buttonExit = Button("終了")

    /// State の設定はここでやるらしい, ViewModel とかに隠した方が良さそうや...
    /// setClock は ViewModel でやれば不要だしね、うん。多分いけるはず。
    private var state: State = DayState

    override fun setClock(hour: Int) {
        /// UIへ時刻を表示する
        state.doClock(this, hour)
    }

    override fun changeState(state: State) {
        /// 昼 ⇔ 夜へ state が変化しましたを出力するやつ
        this.state = state
    }

    override fun callSecurityCenter(msg: String) {
        /// セキュリティセンター呼び出し
    }

    override fun recordLog(msg: String) {
        /// UI 操作、ログを記録
    }

    /// こんな感じで UI アクションの処理を state から呼び出して使ってあげる
    // buttonUse.setOnClickListener {
        // state.doUse(this)
    // }
}

class Button(private val text: String)