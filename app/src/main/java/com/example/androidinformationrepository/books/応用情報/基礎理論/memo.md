## メモ
- 集合の話
- 命題の話
- 情報量
  - 情報量I(J) = -log2P(J) 
    - P(J) は I(J) の生起確立
    - 生起確立が大きい場合は情報量が少なくなり、小さい場合は情報量が大きくなる
  - 情報量の加法性
    - 生起確立が互いに独立している場合は、和で表せる
    - 独立していない場合は積だね
  - 平均情報量
    - なるほどねって感じ
  - 情報源符号化
    - `ハフマン符号化`、`ハフマン木`
      - なるほどね
      - ハフマン木の作成方法はわかった、27 ページの左の参考と分類学っぽい図を見るとわかる
  - `ランレングス符号化`
    - データ列の冗長度にchくあもくし、同じデータ値が連続する部分をその反復回数とデータ値の組に置き換えることによって、データ長をも自覚する圧縮方法
    - 画像データとかを圧縮する方法でよく見るやつだ
    - `AAAAABBBCCCD` を `4A2B2C0D` みたいに置き換える方法
  - デジタル化符号
    - `パルス符号変調(PCM)`: アナログデータをデジタルデータに変換する
      - PCM による符号化手順
        - 標本化: アナログ信号を一定時間間隔で切り出す、一秒間にサンプリングする回数をサンプリング周波数という。
        - 量子化: サンプリングしたアナログ血をデジタル値に変換する。この時一回のサンプリングで生成されるビット数を量子化ビット数といい、例えばりょうしかビット数が8ビットであれば 0_255 の数値に変換される
        - 符号化: 量子化で作られたデジタル値を2新数符号家一式に変換し、符号化ビット列を得る。例えば、デジタル値が 180 なら、、、、(2進数に直すだけ)
      - 例: 音声データをバイトになおせ
        - 音声サンプリング周波数10kHz
        - 量子化ビット数16 ビット
        - 時間は4秒間
        - サンプリングした場合、得られる音声データのデータ量が何kバイトとなるか？
        - 16ビットなので 2^16 で表現されるよねというのは置いといて)
        - (10 * 10^3) * 16 * 4 [ビット] / 8 = 80kバイト
  - 有限オートマトン
    - 図で見るのが一番早い
    - ほんで、要するに2進数で、先頭から見て行った時に最終的な状態が何になるんですか？って話でしかない
    - 細いところは後で見ればいいよ
  - 有限オートマトンと正規表現
    - 正規表現によって表される言語を正規言語と呼ぶ
    - 有限オートマトンは正規言語を認識するために利用される
    - ⚠️初見はようわからんな
    - (0|100)*1 を見て、有限オートマトンの図をかければ良いみたいだね、、うーん困ったにゃ
  - 形式言語
    - むずい、何っているかわからん
    - 多分、プログラム言語を書いた後にどうやってそれを認識するかっていう話をしていると思う
    - コンパイラとかの話につながりそうな予感
  - BNF 記法
    - むずいなあこれ
    - 塩基配列のところは知っているからここから攻めたほうがいいなあ