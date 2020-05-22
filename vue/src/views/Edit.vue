<template>
  <div class="edit">
    <div>
      <Header />
    </div>
    <div class="paper">
      <div class="lines">
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
            @click="click()"
          >
            クリア
          </button>
        </div>
        <div class="holes hole-top" />
        <div class="holes hole-middle" />
        <div class="holes hole-bottom" />
      </div>
    </div>
    <div class="chart">
      <Chart :id="currentUserId" />
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
      currentUserId: this.$store.state.account.account.id,
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
  background-image: url(../assets/human.png);
  background-size: 20%;
  background-repeat: no-repeat;
  background-position: 4% 30%;
  background-position: fixed;
  padding-top: 100px;
}

.paper {
  position: fixed;
  height:450px;
  width: 400px;
  background: rgba(255,255,255,0.9);
  box-shadow: 0px 0px 5px 0px #888;
  left: 75%;
  top: 60%;
}

.paper::before {
  content: '';
  position: absolute;
  left: 45px;
  height: 100%;
  width: 2px;
  background: rgba(255,0,0,0.4);
}

.lines {
  margin-top: 40px;
  height: calc(100% - 40px);
  width: 100%;
  background-image: repeating-linear-gradient(white 0px, white 24px, steelblue 25px);
}

.formOut{
  display: inline-block;
  text-align: center;
  width: 250px;
  font-weight: bold;
  font-size: 18px;
}

ul {
  list-style: none;
  text-align: center;
  width: 240px;
}

ul li{
  margin: 4px 15px 8px 5px;
}

.holes {
  position: absolute;
  left: 10px;
  height: 25px;
  width: 25px;
  background: #fff;
  border-radius: 50%;
  box-shadow: inset 0px 0px 2px 0px #888;
}
.hole-top {
  top: 10%;
}
.hole-middle {
  top: 43%;
}
.hole-bottom {
  bottom: 10%;
}

label{
  display:inline-block;
  vertical-align: middle;
  text-align: left;
  width:85px;}

.graphRegister {
  text-align: center;
  width: 5rem;
  height: 3rem;
  padding-left: 1rem;
  padding-top: 1rem;
  overflow: hidden;
  box-shadow: .25rem 0 .25rem hsla(0, 0%, 0%, .1);
  background-image:
    linear-gradient(90deg, hsla(0, 0%, 45%, .1) 2rem, hsla(0, 100%, 100%, 0) 2.5rem)
  , linear-gradient(90deg, hsla(60, 100%, 85%, 1), hsla(60, 100%, 85%, 1));
  font-size: 1rem;
  font-weight: bold;
  line-height: 1.5;
  transition: all 1s;
  margin: 10px 15px;
  cursor: pointer;
}

.graphRegister:hover{
  transition-delay: 50ms;
  transform: rotate3d(0,0,1,-13deg);
}

.graphEdit {
  text-align: center;
  width: 5rem;
  height: 3rem;
  padding-left: 1rem;
  padding-top: 1rem;
  overflow: hidden;
  box-shadow: .25rem 0 .25rem hsla(0, 0%, 0%, .1);
  background-image:
    linear-gradient(90deg, hsla(0, 0%, 45%, .1) 2rem, hsla(0, 100%, 100%, 0) 2.5rem)
  , linear-gradient(90deg, hsla(60, 100%, 85%, 1), hsla(60, 100%, 85%, 1));
  font-size: 1rem;
  font-weight: bold;
  line-height: 1.5;
  transition: all 1s;
  margin-right: 5px;
  cursor: pointer;
}

.graphEdit:hover {
  transition-delay: 50ms;
  transform: rotate3d(0,0,1,-13deg);
}

.graphClear {
  text-align: center;
  width: 5rem;
  height: 3rem;
  padding-left: 1rem;
  padding-top: 1rem;
  overflow: hidden;
  box-shadow: .25rem 0 .25rem hsla(0, 0%, 0%, .1);
  background-image:
    linear-gradient(90deg, hsla(0, 0%, 45%, .1) 2rem, hsla(0, 100%, 100%, 0) 2.5rem)
  , linear-gradient(90deg, hsla(60, 100%, 85%, 1), hsla(60, 100%, 85%, 1));
  font-size: 1rem;
  font-weight: bold;
  line-height: 1.5;
  transition: all 1s;
  margin: 10px 15px;
  cursor: pointer;
}

.graphClear:hover{
  transition-delay: 50ms;
  transform: rotate3d(0,0,1,-13deg);
}
</style>
