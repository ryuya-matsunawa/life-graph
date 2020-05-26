<template>
  <div class="edit">
    <div>
      <Header />
    </div>
    <div class="topbuttons">
      <button
        class="graphRegister"
        @click="addChange()"
      >
        登録
      </button>
      <button
        class="graphRegister"
        @click="editChange()"
      >
        編集
      </button>
    </div>
    <div v-if="addGraph" class="file">
      <div class="formOut">
        <p>
          <validation-provider v-slot="{ errors }" name="年齢" rules="required|between:0,99" class="erroe">
            <label class="tag" for="editAge">年齢</label>
            <input id="editAge" v-model="editAge" type="number" min="0" max="99" @keydown.69.prevent>
            <br>
            <span v-if="!isErrorAge">{{ errors[0] }}</span>
          </validation-provider>
          <span v-if="isErrorAge" class="erroe">年齢は0から99の間でなければなりません</span>
        </p>
        <p>
          <validation-provider v-slot="{ errors }" name="スコア" rules="between:-100,100" class="erroe">
            <label class="tag" for="editScore">スコア</label>
            <input id="editScore" v-model="editScore" type="number" min="-100" max="100" @keydown.69.prevent>
            <br>
            <span v-if="!isErrorScore">{{ errors[0] }}</span>
          </validation-provider>
          <span v-if="isErrorScore" class="erroe">スコアは-100から100の間でなければなりません</span>
        </p>
        <p>
          <label class="tag" for="editComment">コメント</label>
          <input id="editComment" v-model="editComment" type="text" maxlength="200" @keyup.enter="updateGraphData">
          <br>
          <span v-if="isErrorComment" class="erroe">コメントは200文字以下で入力してください</span>
        </p>
        <p class="tag">
          登録日時 {{ date.created_at | moment }}
        </p>
        <p class="tag">
          更新日時 {{ date.updated_at | moment }}
        </p>
        <button
          class="graphRegister"
          href="#!"
          @click="updateGraphData()"
        >
          更新
        </button>
        <button
          class="graphRegister"
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
          <th class="edittag">
            年齢
          </th>
          <th class="edittag">
            スコア
          </th>
          <th class="edittag">
            コメント
          </th>
          <tr v-for="item in contents" :key="item.id">
            <td class="details">
              {{ item.age }}
            </td>
            <td class="details">
              {{ item.score }}
            </td>
            <td class="details">
              {{ item.comment }}
            </td>
            <td>
              <button
                class="graphRegister"
                @click="editData(item.id)"
              >
                編集
              </button>
            </td>
            <td>
              <button
                class="graphRegister"
                @click="deleteItem(item.id,item.age)"
              >
                削除
              </button>
            </td>
          </tr>
        </table>
      </div>
    </div>
    <div class="home__b">
      <div class="home__b1" />
      <div class="home__b2" />
      <div class="home__b3" />
      <div class="home__b4" />
      <div class="home__b5" />
      <div class="home__b6" />
      <div class="home__b7" />
    </div>
    <div class="chart">
      <Chart :id="currentUserId" />
    </div>
    <img src="../assets/emo.jpg" class="flower">
  </div>
</template>

<script>
import Chart from '../views/Chart.vue'
import Header from '../views/Header.vue'
import moment from 'moment'
// import * as moment from 'moment-timezone'
// moment.tz.setDefault('Asia/Tokyo')

export default {
  name: 'Edit',
  components: {
    Chart,
    Header
  },
  filters: {
    moment: function (date) {
      return moment(date).local.format('YYYY/MM/DD HH:mm')
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
    },
    update () {
      return this.$store.state.chart.update
    },
    loaded () {
      return this.$store.state.chart.loaded
    }
  },
  watch: {
    account (newAccount) {
      this.setDate()
    },
    update () {
      const userId = this.$store.state.auth.userId
      this.$store.dispatch('chart/fetchGraph', userId)
    },
    loaded () {
      this.setContents()
    }
  },
  mounted () {
    this.setContents()
    this.setDate()
  },
  methods: {
    clear () {
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
    activeButton () {
      // 全部のバリデーションが正常に動いているかチェックするため
      // trueの数を数えるvalidationChech
      let validationCheck = 0
      /**
       * editAgeが10-99もしくは1-9であるかどうかの判定
       * 決められた範囲ならtrue
       */
      if (/^([1-9][0-9]?|0[1-9]?)$/.test(this.editAge)) {
        // 大丈夫なら+1
        validationCheck++
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
        validationCheck++
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
        validationCheck++
        // エラーの表示on
        this.isErrorComment = false
      } else {
        // エラーの表示on
        this.isErrorComment = true
      }
      return validationCheck
    },
    updateGraphData () {
      const idList = this.contents.map(obj => obj.id)
      const indexId = idList.indexOf(this.editId)
      // if:まだ登録されていない年齢の場合
      // else:すでにある年齢を更新
      if (this.activeButton() === 3) {
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
        this.editId = null
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
.flower {
  position: absolute;
  top: 300px;
  left: 0;
  z-index: -2;
  opacity: 0.8;
  width: 500px;
}

.topbuttons{
  margin: 5px 0px 10px 0px;
}

.erroe{
  font-size: 10px;
  color: gray;
}
/* 使ってない ToDoあとで消す */
/* .validation{
  font-size: 10px;
  color: gray;
} */
.tag{
  font-size: 15px;
  color:#333333;
}

.edittag{
  width: 100px;
  font-size: 15px;
  color:#333333;
  padding: 0px;
}

.details{
  vertical-align: middle;
  font-size: 12px;
  color:#333333;
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
  /* margin-top:30px; */
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

.table th {
  width: 1000px;
  padding: 10px 20px;
}

.table td {
  height: 40px;
  vertical-align: middle;
  padding: 10px;
}

.chart {
  width: 50%;
  background-color: #fff;
}

.home__b {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100vh;
  opacity: 0.2;
  z-index: -1;
}

.home__b1 {
    position: absolute;
    top: 70px;
    left: 70px;
    background-color: #90b0b4;
    width: 110px;
    height: 110px;
    border-radius: 110px;
}

.home__b2 {
    position: absolute;
    top: 0;
    left: 800px;
    background-color: #a97f76;
    width: 200px;
    height: 200px;
    border-radius: 200px;
}

.home__b4 {
    position: absolute;
    top: 20%;
    left: 40%;
    background-color: darkgray;
    width: 60px;
    height: 60px;
    border-radius: 60px;
}

.home__b5 {
    position: absolute;
    top: 30%;
    left: 80%;
    background-color: darkgray;
    width: 40px;
    height: 40px;
    border-radius: 20px;
}

.home__b6 {
    position: absolute;
    top: 70%;
    left: 10%;
    background-color: #a97f76;
    width: 110px;
    height: 110px;
    border-radius: 110px;
}

.home__b7 {
    position: absolute;
    top: 70%;
    left: 80%;
    background-color: #90b0b4;
    width: 270px;
    height: 270px;
    border-radius: 270px;
}
</style>
