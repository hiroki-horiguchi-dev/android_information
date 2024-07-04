package com.example.androidinformationrepository.designPattern.state.state.state

import com.example.androidinformationrepository.designPattern.state.state.ui.Context

/// 夜の状態を表す com.example.androidinformationrepository.designPattern.state.state.state.State

object NightState : State {
    override fun doClock(context: Context, hour: Int) {
        if (9 <= hour && hour < 17) {
            context.changeState(DayState);
        }
    }

    override fun doUse(context: Context) {
        context.recordLog("非常：夜間の金庫使用");
    }

    override fun doAlarm(context: Context) {
        context.callSecurityCenter("非常ベル(夜間)");
    }

    override fun doPhone(context: Context) {
        context.callSecurityCenter("夜間の通話録音");
    }

    override fun toString() = "[夜間]"

}