<template>
  <div>
    <Header />
    <!-- 上部 -->
    <div id="search">
      検索条件
      <ul id="searchList">
        <li class="username">
          ユーザー名
          <input type="text">
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
        <tr v-for="item in filteredItems" :key="item.id">
          <td>{{ item.name }}</td>
          <td>{{ item.day }}</td>
          <td>
            <router-link :to="'/show/' + item.id" tag="button" class="viewButton">
              参照
            </router-link>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import Header from '../views/Header.vue'

export default {
  name: 'Search',
  components: {
    Header
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
      sortDesc: true
    }
  },
  computed: {
    arrow () {
      return {
        // いつでも存在するクラス
        arrow: true,
        // sortDescがtrueのときに追加される
        desc: this.sortDesc
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
    // 画面繋ぎ時に追加する必要あり？ this.$store.dispatch('search/アクション名')
    this.setItems()
  },
  methods: {
    setItems () {
      this.items = this.$store.state.search.items
    },
    active () {
      // 画面繋ぎ時に追加する必要あり？ this.$store.dispatch('search/アクション名')
      // 画面繋ぎ時に追加する必要あり？ this.setItems()
      this.isActive = true
      // XX〜YYまで検索した時に間に該当するものを表示する
      if (this.updatedFrom && this.updatedTo) {
        // split区切る join区切ったものをくっつける parseIntで数字に変える 型次第では不必要？
        const updatedFromNumber = parseInt(this.updatedFrom.split('-').join(''))
        const updatedToNumber = parseInt(this.updatedTo.split('-').join(''))
        this.filteredItems = this.items.filter((item) =>
          parseInt(item.day.split('-').join('')) >= updatedFromNumber &&
          parseInt(item.day.split('-').join('')) <= updatedToNumber
        )
      // XX〜 検索した時に該当するものを表示する
      } else if (this.updatedFrom) {
        const updatedFromNumber = parseInt(this.updatedFrom.split('-').join(''))
        this.filteredItems = this.items.filter((item) =>
          parseInt(item.day.split('-').join('')) >= updatedFromNumber
        )
      // 〜YY 検索した時に該当するものを表示する
      } else if (this.updatedTo) {
        const updatedToNumber = parseInt(this.updatedTo.split('-').join(''))
        this.filteredItems = this.items.filter((item) =>
          parseInt(item.day.split('-').join('')) <= updatedToNumber
        )
        // 何も指定しなければitemをそのまま返す
      } else {
        this.filteredItems = this.items
      }
      // 表示順を決める
      this.sortItems()
    },
    sortItems () {
      this.filteredItems.sort(this.compareFunc)
    },
    compareFunc (a, b) {
      const day1 = parseInt(a.day.split('-').join(''))
      const day2 = parseInt(b.day.split('-').join(''))
      // ? true:左 false:右
      return this.sortDesc ? (day2 - day1) : (day1 - day2)
    },
    toggleSort () {
      this.sortDesc = !this.sortDesc
    },
    // リセットボタン
    reset () {
      this.isActive = false
    }
  }
}
</script>

<style>
/* 上部 */
#search{
  text-align: center;
  padding: 0.5em 1em;
  margin: 100px;
  font-weight: bold;
  border: solid 3px #000000;
  width: 80%;
  height: auto;
  display: inline-block;
}
#searchList{
  list-style: none;
}
.username{
  margin: 40px;
}
.update{
  margin: 40px;
}
.view{
  margin: 40px;
}
/* 下部 */
button:hover {
  background-color: #59b1eb;
  cursor: pointer;
}
.viewButton{
  display: block;
  text-decoration: none;
}
table{
  margin: 0 auto;
  width: 60%;
}
.arrow {
  transform: rotate(180deg);
  height: 16px;
  margin-left: 4px;
}
.arrow.desc {
  transform: rotate(0deg);
}

</style>
