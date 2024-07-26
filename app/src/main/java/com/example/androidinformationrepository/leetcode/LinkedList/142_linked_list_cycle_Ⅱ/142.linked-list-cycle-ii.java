/*
 * @lc app=leetcode id=142 lang=java
 *
 * [142] Linked List Cycle II
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        
        if (head == null) return null;

        ListNode fast = head;
        ListNode slow = head;

        /// 循環リストかどうか判定する
        while (fast != null) {
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }

            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }

        /// fast の null チェックが最後のループで検証できていないのでここでチェック
        if (fast == null) {
            return null;
        }

        // 循環リストだとわかったので、slow, fast にそれぞれ必要な操作を施す
        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

}
// @lc code=end