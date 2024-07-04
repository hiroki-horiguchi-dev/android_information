# C++ キャッチアップ
無料で使えるのが paiza だったので、そこで基礎的な部分をキャッチアップしていく

## 文字の扱い
```objectivec
int com.example.androidinformationrepository.leetcode.LinkedList.main() {
  cout << "Hello world" << endl;
}
```

## 数値の扱い
```objectivec
int com.example.androidinformationrepository.leetcode.LinkedList.main() {
  cout << "Hello world" << endl;
  cout << 100 + 30;
}
```

⚠️ `endl` は改行っぽいね

## 計算
```objectivec
int com.example.androidinformationrepository.leetcode.LinkedList.main() {
    /// sum 
    cout << 100 + 30 << endl;
    /// minus
    cout << 100 - 30 << endl;
    /// product
    cout << 100 * 30 << endl;
    /// quotient
    cout << 100 / 30 << endl;
    
    /// 計算順序は () で優先できる
}
```

## 変数にデータ入れる
```objectivec
int com.example.androidinformationrepository.leetcode.LinkedList.main() {
   string greeting = "Hello world";
   cout << greeting << endl;
   greeting = "Hello C++";
   cout << greeting << endl;
   
   
   int number = 100;
   cout << number << endl;
}
```
これで変数にデータ入れて出力可能だよ
基本的に kotlin, Java と同じ感じだね

## 標準入力の受け取り
```objectivec
int com.example.androidinformationrepository.leetcode.LinkedList.main() {
  string name;
  cin >> name;
  cout << "Hello " << name << endl;
  
  int number;
  cin >> number;
  cout << number + 100 << endl;
}
```

```text
/// input
yeah!
120

/// output
Hello yeah!
220
```
こんな感じで順番に対応して値を取ってくれる感じだね

## 標準入力、出力
```objectivec
int com.example.androidinformationrepository.leetcode.LinkedList.main() {
    /// cin: 標準入力を受け取る、読み方は「しーいん」
    /// cout: 標準出力でデータを出力する、これ 「しーあうと」って読むのね。print と同じじゃんねえ
}
```

## 条件分岐
```objectivec
int com.example.androidinformationrepository.leetcode.LinkedList.main() {
  string name;
  cin >> name;
  
  if (name == "c++") {
      cout << "Welcome" << endl;
  } else {
      cout << "Hello " << name << endl;
  }
}
```

```text
/// input
Welcome

/// 比較演算子
いつも通り
```

## 条件に合わせて処理を変える(1)
```objectivec
int com.example.androidinformationrepository.leetcode.LinkedList.main() {
  string name;
  cin >> name;
  
  if (name == "c++") {
      cout << "Welcome" << endl;
  } else if (name == "hoge") {
      cout << "Hello " << name << endl;
  }
}
```

kotlin と同じね

## 数値に合わせて処理を変える(2) 数値
```objectivec
int com.example.androidinformationrepository.leetcode.LinkedList.main() {
  int number;
  cin >> number;
  cout << number << endl;

  if (number == 10) {
    cout << number << "は10に等しい" << endl;
  } else if (number > 10) {
    cout << number << "は10より大きい" << endl;
  } else {
    cout << number << "は10未満" << endl;
  }
}
```
こんな感じね

## 繰り返し (for の基本　+ カウンタ変数の表示)
```objectivec
int com.example.androidinformationrepository.leetcode.LinkedList.main() {
  string greeting = "Hello paiza";

  for (int i = 0; i < 3; i++) {
    cout << greeting << i  << endl;
  }
}
```

## 処理の繰り返し回数を指定する
```objectivec
int com.example.androidinformationrepository.leetcode.LinkedList.main() {
  int count;
  cin >> count;
  cout << count << endl;

  string greeting = "Hello world";

  for (int i = 0; i < count; i++) {
    cout << greeting << endl;
  }
}
```

## 複数のデータを受け取る
```objectivec
int com.example.androidinformationrepository.leetcode.LinkedList.main() {
  int count;
  cin >> count;

  string name;

  for (int i = 0; i < count; i++) {
    cin >> name;
    cout << "Hello " << name << endl;
  }
}
```

最初に指定された回数分、標準入力を受け取る感じだね
競プロで絶対必要なやつやんね
あとはリストとかマップとかセットとかのコレクション操作を知りたいね

## 複数データを分類する
複数の整数データを受け取るパターン
イメージはこんな感じ
total 5
2
3
4
5
6

```objectivec
int com.example.androidinformationrepository.leetcode.LinkedList.main() {
  int count;
  cin >> count;

  int number;
  for (int i = 0; i < count; i++) {
    cin >> number;
    cout << number << endl;
  }
}
```

組み合わせ技ね
```objectivec
int com.example.androidinformationrepository.leetcode.LinkedList.main() {
  int count;
  cin >> count;

  int number;
  for (int i = 0; i < count; i++) {
    cin >> number;
    cout << number << endl;

    if (number == 10) {
      cout << number << "は10に等しい" << endl;
    } else if (number > 10) {
      cout << number << "は10より大きい" << endl;
    } else {
      cout << number << "は10未満" << endl;
    }
  }
}
```

え、終わり？
これじゃあ足りない、、、ということで AtCoder が出している入門があったからそっちやる
https://atcoder.jp/contests/APG4b