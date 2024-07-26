/*
 * @lc app=leetcode id=82 lang=java
 *
 * [82] Remove Duplicates from Sorted List II
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
    public ListNode deleteDuplicates(ListNode head) {
        /// ダミーノードの作成、先頭の重複がある場合を考慮する
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode ptr = dummyHead;
        while (ptr.next != null && ptr.next.next != null) {
            /// 重複がある場合
            if (ptr.next.val == prt.next.next.val) {
                /// 重複がある最後のノードを見つけたい
                ListNode copy = ptr.next;
                while (copy.next != null && copy.val == copy.next.val) {
                    copy = copy.next;
                }
                ptr.next = copy.next;
            } else { /// 重複がない場合
                ptr = ptr.next;
            }
        }

        return dummyHead.next;
    }
}
// @lc code=end

