package com.example.androidinformationrepository.leetcode.LinkedList

import utils.ListNode

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

/**
 * 問題: https://leetcode.com/problems/linked-list-cycle/
 */

fun main() {
    /// expected: true
    /// ⚠️ true が期待値だけど false 返すから ListNode が正しくないのか、初期化の方法が間違ってるのかわからんね。。
    /// hasCycle 自体はこれで正しいらしい。。
    val listNode = ListNode.quickList(listOf(3,2,0,-4))
    if (hasCycle(listNode)) {
        println("true")
    } else {
        println("false")
    }
}

private fun hasCycle(head: ListNode?): Boolean {
    /// set 用意
    val nodesSeen = mutableSetOf<ListNode>()
    /// currentNode に head を保存
    var currentNode = head
    println(currentNode.toString())

    while (currentNode != null) {
        if (nodesSeen.contains(currentNode)) {
            return true
        } else {
            nodesSeen.add(currentNode)
        }
        currentNode = currentNode.next
    }
    return false
}