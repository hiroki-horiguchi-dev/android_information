package com.example.androidinformationrepository.designPattern.state.state.state

import com.example.androidinformationrepository.designPattern.state.state.ui.Context

/// 金庫の状態を表すクラス
interface State {
    fun doClock(context: Context, hour: Int) // 時刻設定
    fun doUse(context: Context)   // 金庫使用
    fun doAlarm(context: Context) // アラーム
    fun doPhone(context: Context) // 通常電話
}