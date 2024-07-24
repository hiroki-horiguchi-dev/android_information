# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    # head is slow pointer == かめ
    # fast is fast pointer == うさぎ
    def hasCycle(self, head):
        fast = head
        while fast and fast.next:
            head = head.next
            fast = fast.next.next
            if head is fast:
                return True

        return False

# うさぎと亀アルゴリズム動画解説こちら
# https://www.youtube.com/watch?v=RRSItF-Ts4Q&t=180s