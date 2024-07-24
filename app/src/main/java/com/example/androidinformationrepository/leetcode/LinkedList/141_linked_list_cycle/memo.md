# やったこと
- 亀とウサギのアルゴリズムの実装
- 応用情報該当箇所、アルゴリズムとプログラミングの初っ端
  - 連結リストにおける単方向リストと循環リストの違いを出力させるのが今回の問題の目的


# うさぎと亀アルゴリズム動画解説こちら
- [LeetCode #141: Linked List Cycle | Floyd's Tortoise and Hare Algorithm](https://www.youtube.com/watch?v=RRSItF-Ts4Q&t=180s)


# Python
```python
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
```

# Kotlin
- while の and 演算子が何をしているのかわからなかったので、ChatGpt で Kotlin に書き直させると以下のようになった。。
- ヌルチェックをしているんだけど、Python に null って概念あるんだっけ。。
- None って概念が null の代わりにあるみたい
- あとは head への代入ができないので
```kotlin
fun hasCycle(head: ListNode?): Boolean {
    var fast = head
    var slow = head

    while (fast != null && fast.next != null) {
        slow = slow?.next
        fast = fast.next?.next
        if (slow == fast) {
            return true
        }
    }
    return false
}
```
- ちょっと思うところがあるのは while の条件式の中でやっている null チェックの部分なんだよな
- このリンクリストについて、要素が null だった場合の扱いが書いてないから混乱する。。。