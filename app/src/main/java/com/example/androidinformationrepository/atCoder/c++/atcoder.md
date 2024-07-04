# やっててこれ認識と違うねってなったやつまとめ

```objectivec
#include <bits/stdc++.h>
using namespace std;

int com.example.androidinformationrepository.leetcode.LinkedList.main() {
  int x = 3;
  int a = 2;
  int b = 5;

  // 1.の出力
  int increment = x++;
  cout << increment << endl;
}
```
これ、increment は 3のままになってまう。
これはあれだね、右辺よりさきに左辺の代入が行われてしまうからだね。

# while ループ

```objectivec
#include <bits/stdc++.h>
using namespace std;

int com.example.androidinformationrepository.leetcode.LinkedList.main() {
  int A = 10, B = 10;
//   cin >> A >> B;

  // ここにプログラムを追記
  int i = 0;
  cout << "A:";
  while (i < A) {
    cout << "]";
    i++;
  }
  cout << endl;
  
  i = 0;
  cout << "B:";
  while (i < B) {
    cout << "]";
    i++;
  }
  cout << endl;
}
```

絶対最後に改行必須マンで詰まるのマジで勘弁してけれ

# for break

```objectivec
#include <bits/stdc++.h>
using namespace std;

int com.example.androidinformationrepository.leetcode.LinkedList.main() {

  int j = 0;
  while (j < 3) {
    cout << "Hello while: " << j << endl;
    j++;
  }

  for (int i = 0; i < 3; i++) {
    cout << "Hello for: " << i << endl;
  }

}
```

２重ループ

```objectivec
#include <bits/stdc++.h>
using namespace std;

int com.example.androidinformationrepository.leetcode.LinkedList.main() {

  for (int i = 0; i < 2; i++) {
    for (int j = 0; j < 2; j++) {
      cout << "i: " << i << ", j:" << j << endl;
    }
  }

}
```

# String, Char

String : 文字列
Char: 一文字

String の文字に対する処理は Char で行う必要があるよ

## B問題 [mineSweeper](https://atcoder.jp/contests/abc075/tasks/abc075_b)
これ普通にむずかった
近傍の座標をどうすればいいの？常套テク
これは後で挑戦したいね。大体わかったので。
```text
変数dx, dy を用意して、それぞれ dx = [-1,0,1], dy = [-1,0,1]とする
すると、(x,y)近傍の座標 (xx, yy) は xx = x + dx, yy = y + dy とできて単純なループで近傍を指定できる
```

これを解放の方針として言語化するとこんな感じだな

```objectivec
fun com.example.androidinformationrepository.leetcode.LinkedList.main() {
    // input としてこんな感じの奴らが来る
    // H, W
    // H: 高さ、 W: はば
    int H,W;
    cin >> H >> W;
    
    // #####.
    // #.#.##
    // ####.#
    // .#..#.
    // #.##..
    // #.#...
    // ⚠️ ルールはシンプルで、# が爆弾、. が空きこま。空きこまが隣接している 3~8 つの周りの空間にどれだけの数の爆弾(#) があるかを数え上げて、. の代わりにその数を置換せよ
    // って問題

    // 方針
    // こんな感じで配列を作るよ
    // んで dx, dy みたいに前後左右の近傍座標情報を表す Array も用意するよ
    val xAxisNeighboringCoordinate = arrayOf(-1, 0, 1)
    val yAxisNeighboringCoordinate = arrayOf(-1, 0, 1)

    // あとは matrix をぐるぐる回しながら、その中で x座標とy座標を回しながら近傍のマスを探索し、インクリメントしてあげれば良さそう
}
```