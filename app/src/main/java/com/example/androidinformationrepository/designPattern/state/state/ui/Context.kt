package com.example.androidinformationrepository.designPattern.state.state.ui

import com.example.androidinformationrepository.designPattern.state.state.state.State

/// 金庫時の状態変化を管理し、警備センターとの連絡を取るインタフェース
/// 上記のように書くとわかりづらいやね、UI からできること、にした方がいい
interface Context {
    fun setClock(hour: Int)
    fun changeState(state: State)
    fun callSecurityCenter(msg: String)
    fun recordLog(msg: String)
}