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
            <input id="editAge" v-model="editAge" type="number" min="0" max="101">
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
          @click="updateGraphData()"
        >
          更新
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
      editComment: null,
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
  },
  mounted () {
    this.setDate()
  },
  methods: {
    setDate () {
      this.date.created_at = this.$store.state.account.account.created_at
      this.date.updated_at = this.$store.state.account.account.updated_at
    },
    updateGraphData () {
      const ageList = this.$store.state.chart.contents.map(obj => obj.age)
      const result = ageList.indexOf(parseInt(this.editAge))
      if (result === -1) {
        this.$store.dispatch('chart/register',
          {
            userId: this.$store.state.account.account.user_id,
            age: parseInt(this.editAge),
            score: parseInt(this.editScore),
            comment: this.editComment
          }
        )
      } else {
        const currentUserId = this.$store.state.chart.contents[result].age
        this.$store.dispatch('chart/register',
          {
            id: currentUserId,
            userId: this.$store.state.account.account.user_id,
            age: this.editAge,
            score: this.editScore,
            comment: this.editComment
          }
        )
      }
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
