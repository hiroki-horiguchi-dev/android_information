/*
 * @lc app=leetcode id=83 lang=java
 *
 * [83] Remove Duplicates from Sorted List
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
    /// この書き方だと少しわかりにくいので、別解を下に書いておくよ
//     public ListNode deleteDuplicates(ListNode head) {
//         if (head == null || head.next == null) return head;

//         ListNode prev = head;
//         ListNode temp = head.next;

//         while (temp != null) {
//             if (prev.val == temp.val) {
//                 temp = temp.next;
//             } else {
//                 prev.next = temp;
//                 prev = temp;
//                 temp = temp.next;
//             }
//         }

//         prev.next = temp;
//         return head;
//     }
    public ListNode deleteDuplicates(ListNode head) {
        /// 先頭を固定
        ListNode copy = head;
        while (copy != null && copy.next != null) {
            /// 比較したノードの value が一致している場合
            if (copy.val == copy.next.val) {
                /// copy.next ノードのポインターを copy.next.next のポインターに置き換える
                copy.next = copy.next.next;
            } else {
                /// 比較したノードの value が一致していない場合
                /// copy に保存したノードをひとつだけずらす
                copy = copy.next;
            }
        }
        return head;
    }

    /// 復習と別解 (Accepted)
    /// 重複があった場合、ポインタを付け直した後に、ノードの進行も必要なのでは？
    public ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) return null;

        ListNode prs = head;

        while (prs != null && prs.next != null) {
            if (prs.val == prs.next.val) {
                if (prs.next.next != null) {
                    /// ポインタ付け替え
                    prs.next = prs.next.next;
                    /// 付け直したノードまで進行
                    prs = prs.next.next;
                } else if (prs.next.next == null) {
                    prs.next = null;
                }
            } else {
                /// ノードを次に進行
                prs = prs.next;
            }
        }

        return head;
    }

}
// @lc code=end

