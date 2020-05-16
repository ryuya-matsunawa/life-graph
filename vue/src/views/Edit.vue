<template>
  <div>
    <div>
      <Header />
    </div>
    <!-- パターン１ -->
    <!-- フォームの中身を一括で送信するため -->
    <!-- <form class="formOut" @submit="onSubmit"> -->
    <!-- パターン２ -->
    <!-- <form @submit.prevent="formSubmit"> -->
    <!-- イベントリスナーを追加するときにキャプチャモードで使います
        言い換えれば、内部要素を対象とするイベントは、その要素によって処理される前にここで処理されます
        <div v-on:click.capture="doThis">...</div> -->
    <!-- パターン３ -->
    <!-- ただ、ディブで囲む -->
    <div class="formOut">
      <ul>
        <li>
          <label class="tag" for="editAge">年齢</label>
          <input id="editAge" v-model="editAge" type="number">
        <!-- テスト用表示 -->
        <!-- <p>{{ editAge }}</p> -->
        </li>
        <li>
          <label class="tag" for="editScore">スコア</label>
          <input id="editScore" v-model="editScore" type="number">
        <!-- テスト用表示 -->
        <!-- <p>{{ editScore }}</p> -->
        </li>
        <li>
          <label class="tag" for="editComment">コメント</label>
          <input id="editComment" v-model="editComment" type="text">
        <!-- テスト用表示 -->
        <!-- <p>{{ editComment }}</p> -->
        </li>
      </ul>
      <!-- 上の@submit="onSubmit"がる場合 -->
      <!-- <button
        href="#!"
        class="graphRegister"
        type="submit"
      > -->
      <button
        class="graphRegister"
        href="#!"
        @click="add"
      >
        登録
      </button>
      <button
        class="graphEdit"
        href="#!"
        @click="edit"
      >
        編集
      </button>
    </div>
    <!-- 上のフォームを消したので -->
    <!-- </form> -->
    <div v-if="loaded" class="chart">
      <Chart />
    </div>
    <router-link to="/top" tag="button" class="toTop">
      TOPへ
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
  },
  data () {
    return {
      // storeにつなぐ代わりにここで値を管理
      // intなので''ではなくnullにした
      editAge: null,
      editScore: null,
      editComment: ''
    }
  },
  // 値が変わるたびに計算し直してくれるらしい
  computed: {
    // チャートのレンダリングの際、読み込んでから表示できるようにするのskill.vueの時と同じ
    loaded () {
      // console.log(this.$store.state.chart.age)
      return this.$store.state.chart.loaded
    }
    // sotreからidを元にデータを引っ張ってくる（今はいらない）
    // getId () {
    // }
    // 項目を入力するたび、$store.state.chart.contentsに要素（オブジェクト)を追加する
    // postContent (editAge,editScore,editComment){
    //   newContent = {age: editAge,lifeScores: editScore,comment: editComment}
    //   // https://vuex.vuejs.org/ja/guide/mutations.html
    //   this.$store.commit('chart/getComtent')
    // }
  },
  // チャートのレンダリングの際、読み込んでから表示できるようにするのskill.vueの時と同じ
  mounted () {
    this.$store.dispatch('chart/load')
  },
  methods: {
    // formSubmit (event) {
    //   console.log(event)
    //   console.log(this.age)
    // this.$store.dispatch('chart/submit',event)
    // @submit="onSubmit"パターンの時
    // onSubmit () {
    //   console.log('送信されました')
    //   console.log(this.editAge)
    //   console.log(this.editScore)pろ
    //   console.log(this.editComment)
    // },
    add () {
      // console.logはリントに怒られるのでpushの時には消す
      // console.log('登録されました')
      // console.log(this.editAge)
      // console.log(this.editScore)
      // console.log(this.editComment)
      // console.log({ age: this.editAge, score: this.editScore, comment: this.editComment })
      // storeに送りたい
      // this.$store.dispatch('chart/register',{editAge,editScore,editComment})
    },
    edit () {
      // console.log('編集されました')
      // console.log(this.editAge)
      // console.log(this.editScore)
      // console.log(this.editComment)
      // console.log(this.editComment)
      // // storeに送りたい
      // this.$store.dispatch('chart/edit',{editAge,editScore,editComment})
    }
  }
}
</script>

<style scoped>
.formOut{
  display: inline-block;
  text-align: center;
  width: 250px;
  margin: 100px 0px 0px 0px;
}

ul {
  list-style: none;
  text-align: center;
  width: 240px;
}

ul li{
  margin: 10px 5px 10px 5px;
}

button{
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

</style>
