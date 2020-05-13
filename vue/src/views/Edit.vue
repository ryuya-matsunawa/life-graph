<template>
  <div>
    <Header />
    <div class="formOut">
      <!-- formではなくbindで書き換えてJSに渡す -->
    <form action="#" method="post">
      <ul>
        <li>
          <label class="tag" for="editAge">年齢</label>
          <input id="editAge" type="number" name="editAge">
        </li>
        <li>
          <label class="tag" for="editScore">スコア</label>
          <input id="editScore" type="number" name="editScore">
        </li>
        <li>
          <label class="tag" for="editComment">コメント</label>
          <input id="editComment" type="text" name="editComment">
        </li>
      </ul>
      <button class="graphRegister" type="submit">
        登録
      </button>
      <button class="graphEdit" type="submit">
        編集
      </button>
    </form>
    </div>
    <div class="chart">
      <Chart />
    </div>
        <router-link to="/top">
      <button class="toTop">
        TOPへ
      </button>
    </router-link>

  </div>
</template>

<script>
// これを参考にしながらstoreとの連携かく
// https://qiita.com/Takoyaki9/items/b6638fa1aec41464fdd1

import Chart from '../views/Chart.vue'
import Header from '../views/Header.vue'
export default {
  name: 'Edit',
  components: {
    Chart,
    Header
  }
}
</script>

<style scoped>
.formOut{
  display: inline-block;
  text-align: center;
  width: 250px;

}

ul {
  list-style: none;
  text-align: center;
  width: 240px;
}

ul li{
  margin: 10px 5px 10px 5px;
}

form button{
  margin: 10px 5px 10px 5px;
}

/* ラベルを枠で囲みたい
ボックスデザイン集:https://saruwakakun.com/html-css/reference/box */
label{
  display:inline-block;
  vertical-align: middle;
  text-align: left;
  /* float:left; */
  width:80px;
}

.graphRegister {
  background: #f16272;
  border: none;
  color: #fff;
}

.graphEdit {
  background: #f16272;
  border: none;
  color: #fff;
}

.toTop {
  float: left;
  background: #f16272;
  border: none;
  color: #fff;
  margin: 50px;
  padding: 5px 10px 5px 10px !important;
}

</style>
