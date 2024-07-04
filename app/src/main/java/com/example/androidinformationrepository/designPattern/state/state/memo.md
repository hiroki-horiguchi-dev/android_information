# State をやってみた感想

なるほどねって感じだった。この本の読み方を根本的に間違えていたっぽい。
常に UI は存在するものとして考えて良い。

- スマホアプリでやるならば、SafeFrame が UI なので Compose でかく
- ViewModel で時間を取得して、時間を SafeFrame から監視させる
- ViewModel のプロパティに State を持たせて UI 操作時のハンドリングを任せる