/*
 * @lc app=leetcode id=2 lang=java
 *
 * [2] Add Two Numbers
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /// 返却用の listNode を作成
        ListNode root = new ListNode(0);
        ListNode ans = root;
        int carry = 0; /// 桁上がりの数字管理

        /// l1,l2の両方 null の場合を弾く
        while (l1 != null || l2 != null) {
            int n = 0;
            if (l1 != null && l2 != null) {
                /// 繰り上げも含めて足した時のあまり
                n = (l1.val + l2.val + carry) % 10;
                /// 足した時に10 で割った時の商
                carry = (l1.val + l2.val + carry) / 10;
                /// それぞれノードを進める
                l1 = l1.next;
                l2 = l2.next;
            } else if (l1 != null) {
                n = (l1.val + carry) % 10;
                carry = (l1.val + carry) / 10;
                l1 = l1.next; 
            } else if (l2 != null) {
                n = (l2.val + carry) % 10;
                carry = (l2.val + carry) / 10;
                l2 = l2.next;
            } else {
                /// throw Exception()
            }
            ans.val = n;
            
            if (l1 != null || l2 != null) {
                ans.next = new ListNode(0);
                ans = ans.next;
            }
        }
        if(carry == 1) {
            ans.next = new ListNode(carry);
        }

        /// ans に対して操作を施すけど、最後に返却するのは root なんだ
        return root;
    }
}
// @lc code=end