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
          <validation-provider v-slot="{ errors }" name="年齢" rules="required" class="validation">
            <label class="tag" for="editAge">年齢</label>
            <input id="editAge" v-model="editAge" type="number" min="0" max="99">
            <span>{{ errors[0] }}</span>
          </validation-provider>
        </p>
        <p>
          <validation-provider v-slot="{ errors }" name="スコア" rules="required" class="validation">
            <label class="tag" for="editScore">スコア</label>
            <input id="editScore" v-model="editScore" type="number" min="-100" max="100">
            <span>{{ errors[0] }}</span>
          </validation-provider>
        </p>
        <p>
          <label class="tag" for="editComment">コメント</label>
          <input id="editComment" v-model="editComment" type="text" maxlength="200">
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
          class="graphRegister"
          href="#!"
          @click="click()"
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
                class="graphRegister"
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
      // storeにつなぐ代わりにここで値を管理
      // intなので''ではなくnullにした
      editAge: null,
      editScore: null,
      editComment: null,
      date: {
        created_at: '',
        updated_at: ''
      }
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
    updateGraphData () {
      const idList = this.contents.map(obj => obj.id)
      const indexId = idList.indexOf(this.editId)
      // if:まだ登録されていない年齢の場合
      // else:すでにある年齢を更新
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
    },
    deleteItem (id, age) {
      // todo_あとで消す
      // confirmのカスタマイズもできるらしい。
      // https://techacademy.jp/magazine/32797
      if (confirm(age + '歳の情報を本当に削除してもよろしいですか?')) {
        this.$store.dispatch('chart/deleteItem', id)
        // どうやら、配列を操作するものらしい。今回はいらないかな？
        // this.filteredItems.splice(index, 1)
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

.table th {
  padding: 10px 20px;
}

.table td {
  padding: 10px;
}

.chart {
  width: 50%;
}
</style>
