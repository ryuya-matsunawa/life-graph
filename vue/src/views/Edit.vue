<template>
  <div class="edit">
    <div>
      <Header />
    </div>
    <div class="file">
      <div class="formOut">
        <p>
          <label class="tag" for="editAge">年齢</label>
          <input id="editAge" v-model="editAge" type="number" min="0" max="25">
          <!-- テスト用表示 -->
          <!-- <p>{{ editAge }}</p> -->
        </p>
        <p>
          <label class="tag" for="editScore">スコア</label>
          <input id="editScore" v-model="editScore" type="number" min="-100" max="100">
          <!-- テスト用表示 -->
          <!-- <p>{{ editScore }}</p> -->
        </p>
        <p>
          <label class="tag" for="editComment">コメント</label>
          <input id="editComment" v-model="editComment" type="text" maxlength="200">
          <!-- テスト用表示 -->
          <!-- <p>{{ editComment }}</p> -->
        </p>
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
        <button
          class="graphClear"
          href="#!"
          @click="click"
        >
          クリア
        </button>
      </div>
    </div>
    <img src="../assets/edit.png" class="hima">
    <div v-if="loaded" class="chart">
      <Chart />
    </div>
    <div>
      <router-link to="/top" class="button">
        TOPへ
      </router-link>
    </div>
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
    //   console.log(this.editScore)
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
.edit {
  background-size: 20%;
  background-repeat: no-repeat;
  background-position: 4% 30%;
  background-position: fixed;
  padding-top: 100px;
}

.file {
  width: 600px;
  margin: 0 auto;
  margin-top: 30px;
  border: 1px solid;
  padding: 30px;
  border-radius: 5px;
  border-radius: 8px;
  border-color: #c0c0c0;
  width: 50%;
}

.formOut{
  display: inline-block;
  text-align: center;
  width: 250px;
  font-weight: bold;
  font-size: 18px;
  /* eslint-disable no-unused-vars */
  font-family: 'Noto Serif JP', serif;
}

p{
  margin: 4px 15px 8px 5px;
  list-style: none;
  text-align: center;
  width: 240px;
}

label{
  display:inline-block;
  vertical-align: middle;
  text-align: left;
  width:85px;}

.graphRegister {
  color:#a39d9d;
  border-color:#a39d9d;
  max-width:960px;
  text-align:center;
  position:relative;
  margin-top:30px;
  cursor: pointer;
  /* eslint-disable no-unused-vars */
  font-family: 'Hannari';
}

.graphRegister:hover{
  color:#fff;
  background-color:#a39d9d;
  border-color:#a39d9d;
}

.graphEdit {
  color:#a39d9d;
  border-color:#a39d9d;
  max-width:960px;
  text-align:center;
  position:relative;
  margin-top:30px;
  cursor: pointer;
  /* eslint-disable no-unused-vars */
  font-family: 'Hannari';
}

.graphEdit:hover {
  color:#fff;
  background-color:#a39d9d;
  border-color:#a39d9d;
}

.graphClear {
  color:#a39d9d;
  border-color:#a39d9d;
  max-width:960px;
  text-align:center;
  position:relative;
  margin-top:30px;
  cursor: pointer;
  /* eslint-disable no-unused-vars */
  font-family: 'Hannari';
}

.graphClear:hover{
  color:#fff;
  background-color:#a39d9d;
  border-color:#a39d9d;
}

.hima {
  position: absolute;
  width: 200px;
  left: 0;
  top: 200px;
  z-index: -1;
}

.chart {
  width: 60%;
}

.button {
  position: fixed;
  bottom: 50px;
  right: 30px;
  padding: .75rem 1.25rem;
  border-radius: 5px;
  width: 90px;
  color: #fff;
  text-transform: uppercase;
  font-size: 1rem;
  letter-spacing: .15rem;
  cursor: pointer;
  background-color: #a7d28d;
  /* eslint-disable no-unused-vars */
  font-family: 'Playfair Display', serif;
}
</style>
