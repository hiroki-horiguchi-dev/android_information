# やったこと
- 応用情報該当箇所、アルゴリズムとプログラミングの初っ端
    - 2つの連結リストが与えられたとき、それぞれのリストノードの和を計算し、10を超えた場合は次の桁に繰り上げる

# 思いつく解法
- 与えられるリストノードの長さが違うこともあるのでそれに配慮する
- 和をとったときに10オーバーになった場合に隣の桁に1足すこと
- とりあえず自分で組んでみる
- なんとなくこんな感じでやればいいんじゃないの？って思って書いてみたけど、Print が実行されてなくてつらいな
```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 != null || l2 != null) return null;
        ListNode copy_l1 = l1;
        ListNode copy_l2 = l2;

        while (copy_l1 != null && copy_l2 != null) {
            int sum = copy_l1.val + copy_l2.val;
            if (sum <= 10) {
                System.out.println(String.valueOf(sum));
            } else {
                /// 繰り上げをごにゃごにゃする
                System.out.println(String.valueOf(sum));
            }
            copy_l1 = copy_l1.next;
            copy_l2 = copy_l2.next;
        }

        /// どうやって新規の LinkedList を作ればいいのかちょい不明だったな。。
        return copy_l1;
    }
}
```

# アルゴリズム解説動画はこれ
- [[LeetCode 解説] Add two numbers](https://www.youtube.com/watch?v=VU_B3j-Mvps)
- 動画内で気になったこと
    - なんでこんな問題をやるのか？
    - 大きな桁数の数字を表したい、またはそれらを使用して計算をしたいときに役に立つから、ふーん。
    - エッジケース
        - 1, 999, みたいな入力があったときに、 0001 にしないといけないので注意

# アルゴリズムについて

# 感想