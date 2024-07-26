/*
 * @lc app=leetcode id=141 lang=java
 *
 * [141] Linked List Cycle
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
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        boolean isCycle = false;

        /// 循環リストかどうか判定する
        while (fast != null) {
            if (fast.next.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }

            slow = slow.next;
            if (slow == fast) {
                isCycle = true;
            }
        } 

        return isCycle;
    }
}
// @lc code=end

