<template>
  <div class="edit">
    <div>
      <Header />
    </div>
    <button @click="addChange()">
      登録
    </button>
    <button @click="editChange()">
      編集
    </button>
    <div v-if="addGraph" class="file">
      <div class="formOut">
        <p>
          <!-- <validation-provider v-slot="{ errors }" name="年齢" rules="required" class="validation"> -->
          <label class="tag" for="editAge">年齢</label>
          <input id="editAge" v-model="editAge" type="number" min="0" max="99">
          <!-- <span>{{ errors[0] }}</span> -->
          <!-- </validation-provider> -->
          <br>
          <span v-if="isErrorAge">年齢は0から99で入力してください</span>
        </p>
        <p>
          <!-- <validation-provider v-slot="{ errors }" name="スコア" rules="required" class="validation"> -->
          <label class="tag" for="editScore">スコア</label>
          <input id="editScore" v-model="editScore" type="number" min="-100" max="100">
          <!-- <span>{{ errors[0] }}</span> -->
          <!-- </validation-provider> -->
          <br>
          <span v-if="isErrorScore">スコアは-100から100で入力してください</span>
        </p>
        <p>
          <label class="tag" for="editComment">コメント</label>
          <input id="editComment" v-model="editComment" type="text" maxlength="200">
          <br>
          <span v-if="isErrorComment">コメントは200文字以下で入力してください</span>
        </p>
        <p>登録日時 {{ date.created_at | moment }}</p>
        <p>更新日時 {{ date.updated_at | moment }}</p>
        <button
          class="graphRegister"
          href="#!"
          @click="updateGraphData()"
        >
          更新
        </button>
        <button
          class="graphClear"
          href="#!"
          @click="clear()"
        >
          クリア
        </button>
      </div>
    </div>
    <div v-if="editGraph" class="file">
      <div class="formOut">
        <table class="table">
          <th>年齢</th>
          <th>スコア</th>
          <th>コメント</th>
          <tr v-for="item in contents" :key="item.id">
            <td>{{ item.age }}</td>
            <td>{{ item.score }}</td>
            <td>{{ item.comment }}</td>
            <td>
              <button
                @click="editData(item.id)"
              >
                編集
              </button>
            </td>
            <td>
              <button @click="deleteItem(item.id,item.age)">
                削除
              </button>
            </td>
          </tr>
        </table>
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
import moment from 'moment'

export default {
  name: 'Edit',
  components: {
    Chart,
    Header
  },
  filters: {
    moment: function (date) {
      return moment(date).format('YYYY/MM/DD HH:mm')
    }
  },
  data () {
    return {
      addGraph: true,
      editGraph: false,
      contents: [],
      editId: null,
      currentUserId: this.$store.state.account.account.id,
      editAge: null,
      editScore: null,
      editComment: null,
      date: {
        created_at: '',
        updated_at: ''
      },
      isButton: null,
      isErrorAge: false,
      isErrorScore: false,
      isErrorComment: false
    }
  },
  computed: {
    account () {
      return this.$store.state.account.account
    }
    // 本当は、computedでバリデーションを行ってリアルタイムで修正したい
    // activButton (age, score, comment) {
    //   let bot = 0
    //   if (/^([1-9][0-9]?|0[1-9])$/.test(age)) {
    //     bot++
    //     // this.isButton++
    //   } else {
    //     console.log('0-99')
    //   }
    //   if (/^[+,-]?(100|[1-9][0-9]?|0)$/.test(score)) {
    //     bot++
    //     // this.isButton++
    //   } else {
    //     console.log('-100から100')
    //   }
    //   if (/^.{0,200}$/.test(comment)) {
    //     bot++
    //     // this.isButton++
    //     console.log('0-200字')
    //   } else {
    //     console.log('200文字以下')
    //   }
    //   // console.log(this.isButton)
    //   console.log(bot)
    //   // this.isButton = -this.isButton
    //   return bot
    // }
  },
  watch: {
    account (newAccount) {
      this.setDate()
      this.setContents()
    }
  },
  mounted () {
    this.setContents()
    this.setDate()
  },
  methods: {
    click () {
      this.editId = null
      this.editAge = null
      this.editScore = null
      this.editComment = null
    },
    addChange () {
      this.addGraph = true
      this.editGraph = false
    },
    editData (id) {
      // idだけの配列を作る
      const idList = this.contents.map(obj => obj.id)
      // 編集したいidがある場所のインデックス番号を取得
      const indexId = idList.indexOf(id)
      // 更新の画面に変える
      this.addChange()
      // inputに編集したいデータが入る
      this.editAge = this.contents[indexId].age
      this.editScore = this.contents[indexId].score
      this.editComment = this.contents[indexId].comment
      // 編集で使うようにdataに入れとく
      this.editId = id
    },
    editChange () {
      this.setContents()
      this.addGraph = false
      this.editGraph = true
    },
    setContents () {
      this.contents = this.$store.state.chart.contents
    },
    setDate () {
      this.date.created_at = this.$store.state.account.account.created_at
      this.date.updated_at = this.$store.state.account.account.updated_at
    },
    activButton () {
      // 全部のバリデーションが正常に動いているかチェックするため
      // trueの数を数えるvalidationChech
      let validationChech = 0
      /**
       * editAgeが10-99もしくは1-9であるかどうかの判定
       * 決められた範囲ならtrue
       */
      if (/^([1-9][0-9]?|0[1-9]?)$/.test(this.editAge)) {
        // 大丈夫なら+1
        validationChech++
        // エラーの表示off
        this.isErrorAge = false
      } else {
        // エラーの表示on
        this.isErrorAge = true
        // console.log('0-99')
      }
      /**
       * editAgeが+or-の,100もしくは0~99であるかどうかの判定
       * 決められた範囲ならtrue
       */
      if (/^[+,-]?(100?|[0-9][0-9]?)$/.test(this.editScore)) {
        // 大丈夫なら+1
        validationChech++
        // エラーの表示off
        this.isErrorScore = false
      } else {
        // エラーの表示on
        this.isErrorScore = true
      }
      /**
       * コメントが200字以下であるかの判定
       * 決められた範囲ならtrue
       */
      if (/^.{0,200}$/.test(this.editComment)) {
        // 大丈夫なら+1
        validationChech++
        // エラーの表示on
        this.isErrorComment = false
      } else {
        // エラーの表示on
        this.isErrorComment = true
      }
      return validationChech
    },
    updateGraphData () {
      const idList = this.contents.map(obj => obj.id)
      const indexId = idList.indexOf(this.editId)
      // if:まだ登録されていない年齢の場合
      // else:すでにある年齢を更新
      if (this.activButton() === 3) {
        if (indexId === -1) {
          this.$store.dispatch('chart/register',
            {
              userId: this.$store.state.auth.userId,
              age: parseInt(this.editAge),
              score: parseInt(this.editScore),
              comment: this.editComment
            }
          )
        } else {
          this.$store.dispatch('chart/register',
            {
              id: this.editId,
              userId: this.$store.state.auth.userId,
              age: this.editAge,
              score: this.editScore,
              comment: this.editComment
            }
          )
        }
        this.editAge = null
        this.editScore = null
        this.editComment = null
      }
    },
    deleteItem (id, age) {
      // todo_あとで消す
      // confirmのカスタマイズもできるらしい。
      // https://techacademy.jp/magazine/32797
      if (confirm(age + '歳の情報を本当に削除してもよろしいですか?')) {
        this.$store.dispatch('chart/deleteItem', id)
      }
    }
  }
}
</script>

<style scoped>
.validation{
  font-size: 10px;
  color: gray;
}
.tag{
  font-size: 20px;
  color:black;
}

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
  border-radius: 8px;
  border-color: #c0c0c0;
  width: 50%;
  background-color:  #efeeee;
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
  color:#353434;
  border-color:#a39d9d;
  max-width:960px;
  text-align:center;
  position:relative;
  margin-top:30px;
  cursor: pointer;
  font-family: 'Hannari', serif;
  border-radius: 8px;
}

.graphRegister:hover{
  color:#fff;
  background-color:#a39d9d;
  border-color:#a39d9d;
}

.graphEdit {
  color:#353434;
  border-color:#a39d9d;
  max-width:960px;
  text-align:center;
  position:relative;
  margin-top:30px;
  cursor: pointer;
  font-family: 'Hannari', serif;
  border-radius: 8px;
}

.graphEdit:hover {
  color:#fff;
  background-color:#a39d9d;
  border-color:#a39d9d;
}

.graphClear {
  color:#353434;
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
  left: 30px;
  top: 200px;
  width: 230px;
  z-index: -1;
}

.chart {
  width: 50%;
}
</style>
