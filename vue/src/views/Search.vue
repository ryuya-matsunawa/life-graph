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
            <input v-model="searchWord" type="text" @keyup.enter="active">
          </li>
          <li class="update">
            更新日
            <input v-model="updatedFrom" type="date" @keyup.enter="active">
            〜
            <input v-model="updatedTo" type="date" @keyup.enter="active">
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
          <tr v-for="(item, index) in filteredItems" :key="item.id">
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
    <div class="home__b">
      <div class="home__b1" />
      <div class="home__b2" />
      <div class="home__b3" />
      <div class="home__b4" />
      <div class="home__b5" />
      <div class="home__b6" />
      <div class="home__b7" />
    </div>
    <img src="../assets/search.jpg" class="build">
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
      return moment(date).format('YYYY/MM/DD HH:mm')
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
      this.filteredItems.sort((a, b) => moment(b.updated_at).diff(a.updated_at))
      if (!this.sortDesc) this.filteredItems.reverse()
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
  background-position: 4% 30%;
  background-position: fixed;
  background-repeat: no-repeat;
  background-size: 20%;
  padding-top: 100px;
}

.file {
  background-color: #efeeee;
  border: 1px solid;
  border-color: #c0c0c0;
  border-radius: 8px;
  margin: 0 auto;
  margin-top: 20px;
  padding: 30px;
  width: 600px;
}

.build {
  left: 0;
  opacity: 0.8;
  position: absolute;
  top: 250px;
  width: 500px;
  z-index: -2;
}

/* 上部 */

#search {
  display: inline-block;

  /* eslint-disable-next-line to ignore the next line. */
  font-family: 'Noto Serif JP', serif;
  font-size: 18px;
  font-weight: bold;
  height: auto;
  padding: 0.5em 1em;
  text-align: center;
  width: 80%;
}

#searchList {
  list-style-type: none;
  text-align: center;
}

.username {
  margin: 30px;
}

.update {
  margin: 56px 30px;
}

.view {
  margin: 40px;
}

/* 下部 */

button {
  border-color: #a39d9d;
  border-radius: 8px;
  color: #353434;
  cursor: pointer;

  /* eslint-disable-next-line to ignore the next line. */
  font-family: 'Noto Serif JP', serif;
  font-size: 15px;
  margin-left: 15px;
  margin-top: 30px;
  padding: 20px 15px;
  position: relative;
  text-align: center;
  width: 100px;
}

.viewButton {
  display: block;
  text-decoration: none;
}

table {
  margin-left: 90px;
  margin-top: 58px;
  width: 65%;
}

td {
  width: 75%;
}

.arrow {
  height: 16px;
  margin-left: 4px;
  transform: rotate(180deg);
}

.arrow.desc {
  transform: rotate(0deg);
}

.sakura {
  left: 0;
  position: absolute;
  top: 80px;
  width: 300px;
  z-index: -1;
}

.home__b {
  height: 100vh;
  left: 0;
  opacity: 0.2;
  position: absolute;
  top: 0;
  width: 100%;
  z-index: -1;
}

.home__b1 {
  background-color: #90b0b4;
  border-radius: 110px;
  height: 110px;
  left: 70px;
  position: absolute;
  top: 70px;
  width: 110px;
}

.home__b2 {
  background-color: #a97f76;
  border-radius: 200px;
  height: 200px;
  left: 800px;
  position: absolute;
  top: 0;
  width: 200px;
}

.home__b4 {
  background-color: darkgray;
  border-radius: 60px;
  height: 60px;
  left: 40%;
  position: absolute;
  top: 20%;
  width: 60px;
}

.home__b5 {
  background-color: darkgray;
  border-radius: 20px;
  height: 40px;
  left: 80%;
  position: absolute;
  top: 30%;
  width: 40px;
}

.home__b6 {
  background-color: #a97f76;
  border-radius: 110px;
  height: 110px;
  left: 10%;
  position: absolute;
  top: 70%;
  width: 110px;
}

.home__b7 {
  background-color: #90b0b4;
  border-radius: 270px;
  height: 270px;
  left: 80%;
  position: absolute;
  top: 70%;
  width: 270px;
}
</style>
