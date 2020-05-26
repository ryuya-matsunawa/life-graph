<template>
  <div class="searchvue">
    <Header />
    <div class="file">
      <!-- 上部 -->
      <div id="search">
        検索条件
        <ul id="searchList">
          <li class="username">
            ユーザー名
            <input v-model="searchWord" type="text">
          </li>
          <li class="update">
            更新日
            <input v-model="updatedFrom" type="date">
            〜
            <input v-model="updatedTo" type="date">
          </li>
        </ul>
        <button @click="active()">
          検索
        </button>
        <button @click="reset()">
          リセット
        </button>
      </div>
      <!-- 下部 -->
      <div v-if="isActive">
        <!-- テーブルを作る（表みたいな感じ） -->
        <table class="table">
          <th>リスト</th>
          <th>更新日<img src="../assets/triangle.png" :class="arrow" @click="toggleSort"></th>
          <th v-show="filteredItems.length > 0">
            結果は{{ filteredItems.length }}件です
          </th>
          <th v-show="filteredItems.length == 0">
            ---0件です---
          </th>
          <tr v-for="item in filteredItems" :key="item.id">
            <td>{{ item.username }}</td>
            <td>{{ item.updated_at | moment }}</td>
            <td>
              <router-link :to="'/show/' + item.id" tag="button" class="viewButton">
                参照
              </router-link>
            </td>
            <td v-if="roleActive">
              <button @click="deleteGraphData(index, item.id, item.username)">
                削除
              </button>
            </td>
          </tr>
        </table>
      </div>
    </div>
    <img src="../assets/top.png" class="sakura">
  </div>
</template>

<script>
import Header from '../views/Header.vue'
import moment from 'moment'

export default {
  name: 'Search',
  components: {
    Header
  },
  filters: {
    moment: function (date) {
      return moment(date).format('YYYY/MM/DD')
    }
  },
  data () {
    return {
      updatedFrom: null,
      updatedTo: null,
      isActive: false,
      // storeからもらう情報
      items: [],
      // 検索条件だけに沿った配列
      filteredItems: [],
      // デフォルトを降順にする
      sortDesc: true,
      // 検索文字列
      searchWord: ''
    }
  },
  computed: {
    arrow () {
      return {
        // いつでも存在するクラス
        arrow: true,
        // sortDescがtrueのときに追加される
        desc: this.sortDesc,
        // 検索したときの件数表示
        filteredItems: function () {
          return this.items.filter(function (item) {
            return item.indexOf('helllo') > -1
          })
        }
      }
    },
    roleActive () {
      if (this.$store.state.account.account.name === 'ROLE_USER') {
        return false
      } else {
        return true
      }
    }
  },
  // データを監視するもの
  watch: {
    // sortDescのtrue/falseを監視している
    sortDesc: function () {
      this.sortItems()
    }
  },
  mounted () {
    this.$store.dispatch('search/fetchItems')
    this.setItems()
  },
  methods: {
    setItems () {
      this.items = this.$store.state.search.items
    },
    active () {
      // ユーザー名の検索
      // まずfilteredItemsにstoreの情報をいれる
      this.filteredItems = this.$store.state.search.items
      // filterWordに検索された物が入る
      const filterWord = this.searchWord && this.searchWord.toLowerCase()
      // filterWordに文字が入っているかを判断
      if (filterWord) {
        this.filteredItems = this.filteredItems.filter(function (row) {
          // str.indexOf(文字列)で、文字列が見つかれば文字列が見つかった場所(0以上)、文字列が見つからなければ-1が返ることを利用する。
          // 整数をビット反転演算子にかけると、符号を反転してマイナス1した数になる。
          return row.username.toLowerCase().indexOf(filterWord) > -1
        })
      }
      // XX〜YYまで検索した時に間に該当するものを表示する
      if (this.updatedFrom && this.updatedTo) {
        // split区切る join区切ったものをくっつける parseIntで数字に変える
        const updatedFromNumber = parseInt(this.updatedFrom.split('-').join(''))
        const updatedToNumber = parseInt(this.updatedTo.split('-').join(''))
        this.filteredItems = this.filteredItems.filter((item) =>
          parseInt(item.updated_at.split('-', 3).join('')) >= updatedFromNumber &&
          parseInt(item.updated_at.split('-', 3).join('')) <= updatedToNumber
        )
      // XX〜 検索した時に該当するものを表示する
      } else if (this.updatedFrom) {
        const updatedFromNumber = parseInt(this.updatedFrom.split('-').join(''))
        this.filteredItems = this.filteredItems.filter((item) =>
          parseInt(item.updated_at.split('-', 3).join('')) >= updatedFromNumber
        )
      // 〜YY 検索した時に該当するものを表示する
      } else if (this.updatedTo) {
        const updatedToNumber = parseInt(this.updatedTo.split('-').join(''))
        this.filteredItems = this.filteredItems.filter((item) =>
          parseInt(item.updated_at.split('-', 3).join('')) <= updatedToNumber
        )
      }
      // 表示順を決める
      this.sortItems()
      // activeの処理を終えてから
      this.isActive = true
    },
    sortItems () {
      this.filteredItems.sort(this.compareFunc)
    },
    compareFunc (a, b) {
      const day1 = parseInt(a.updated_at.split('-', 3).join(''))
      const day2 = parseInt(b.updated_at.split('-', 3).join(''))
      // ? true:左 false:右
      return this.sortDesc ? (day2 - day1) : (day1 - day2)
    },
    toggleSort () {
      this.sortDesc = !this.sortDesc
    },
    // リセットボタン
    reset () {
      this.isActive = false
      this.searchWord = ''
      this.updatedFrom = ''
      this.updatedTo = ''
    },
    // 削除ボタン
    deleteGraphData (index, userId, username) {
      // \nは改行コードです
      if (confirm(username + 'さんのライフグラフを削除します。\n本当に削除してもよろしいですか?')) {
        this.$store.dispatch('search/deleteGraphData', userId)
        this.filteredItems.splice(index, 1)
      }
    }
  }
}
</script>

<style scoped>
.searchvue {
  background-size: 20%;
  background-repeat: no-repeat;
  background-position: 4% 30%;
  background-position: fixed;
  padding-top: 100px;
}

.file {
  width: 600px;
  margin: 0 auto;
  margin-top: 20px;
  border: 1px solid;
  padding: 30px;
  border-color: #c0c0c0;
  border-radius: 8px;
  background-color: #efeeee;
}
/* 上部 */
#search{
  text-align: center;
  padding: 0.5em 1em;
  font-size: 18px;
  font-weight: bold;
  width: 80%;
  height: auto;
  display: inline-block;
  /* eslint-disable-next-line to ignore the next line. */
  font-family: 'Noto Serif JP', serif;
}
#searchList{
  text-align: center;
  list-style-type: none;
}
.username{
  margin: 30px;
}
.update{
  margin: 56px 30px;
}
.view{
  margin: 40px;
}
/* 下部 */
button {
  color:#353434;
  border-color:#a39d9d;
  width:100px;
  text-align:center;
  position:relative;
  margin-top:30px;
  cursor: pointer;
  margin-left: 15px;
  font-size: 15px;
  /* eslint-disable-next-line to ignore the next line. */
  font-family: 'Noto Serif JP', serif;
  border-radius: 8px;
  padding: 20px 15px;
}

.viewButton{
  display: block;
  text-decoration: none;
}
table{
  margin-top: 58px;
  margin-left: 90px;
  width: 65%;
}
td{
  width: 75%;
}
.arrow {
  transform: rotate(180deg);
  height: 16px;
  margin-left: 4px;
}
.arrow.desc {
  transform: rotate(0deg);
}

.sakura {
  position: absolute;
  width: 300px;
  left: 0;
  top: 80px;
  z-index: -1;
}
</style>
