<template>
  <div class="edit">
    <div>
      <Header />
    </div>
    <div class="file">
      <div class="formOut">
        <p>
          <validation-provider v-slot="{ errors }" name="年齢" rules="required">
            <label class="tag" for="editAge">年齢</label>
            <input id="editAge" v-model="editAge" type="number" min="0" max="25">
            <!-- テスト用表示 -->
            <!-- <p>{{ editAge }}</p> -->
            <span>{{ errors[0] }}</span>
          </validation-provider>
        </p>
        <p>
          <validation-provider v-slot="{ errors }" name="スコア" rules="required">
            <label class="tag" for="editScore">スコア</label>
            <input id="editScore" v-model="editScore" type="number" min="-100" max="100">
            <!-- テスト用表示 -->
            <!-- <p>{{ editScore }}</p> -->
            <span>{{ errors[0] }}</span>
          </validation-provider>
        </p>
        <p>
          <label class="tag" for="editComment">コメント</label>
          <input id="editComment" v-model="editComment" type="text" maxlength="200">
          <!-- テスト用表示 -->
          <!-- <p>{{ editComment }}</p> -->
        </p>
        <p>登録日 {{ parseInt(date.updated_at.split('-', 3).join('')) }}</p>
        <p>更新日 {{ parseInt(date.created_at.split('-', 3).join('')) }}</p>
        <button
          class="graphRegister"
          href="#!"
          @click="add()"
        >
          登録
        </button>
        <button
          class="graphEdit"
          href="#!"
          @click="edit()"
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
    </div>
    <img src="../assets/edit.png" class="hima">
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
      editComment: '',
      date: {
        created_at: '',
        updated_at: ''
      }
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
  mounted () {
    this.setDate()
  },
  methods: {
    setDate () {
      this.date.created_at = this.$store.state.account.account.created_at
      this.date.updated_at = this.$store.state.account.account.updated_at
    },
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
  font-family: 'Hannari', serif;
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
  font-family: 'Hannari', serif;
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
  font-family: 'Hannari',serif;
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
</style>
