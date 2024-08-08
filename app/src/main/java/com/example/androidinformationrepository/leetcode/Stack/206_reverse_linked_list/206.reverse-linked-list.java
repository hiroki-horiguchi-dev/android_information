/*
 * @lc app=leetcode id=206 lang=java
 *
 * [206] Reverse Linked List
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        /// ソートしろって話しだよね、要するに
        if (head == null) {
            return null;
        }

        Stack<ListNode> stack = new Stack();

        while (head.next != null) {
            stack.push(head);
            head = head.next;
        }

        while (!stack.isEmpty()) {
            ListNode cur = stack.pop();
            cur.next.next = cur;
            cur.next = null;
        }

        return head; 
    }
}
// @lc code=end

