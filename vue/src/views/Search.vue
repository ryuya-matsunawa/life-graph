<template>
  <div>
    <Header />
    <!-- 上部 -->
    <div class="paper">
      <div class="lines">
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
          <button>
            削除
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
      <div class="holes hole-top" />
      <div class="holes hole-middle" />
      <div class="holes hole-bottom" />
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
  async mounted () {
    // 画面繋ぎ時に追加する必要あり？ this.$store.dispatch('search/アクション名')
    await this.$store.dispatch('search/fetchSearch')
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
          return row.name.toLowerCase().indexOf(filterWord) > -1
        })
      }
      // 画面繋ぎ時に追加する必要あり？ this.$store.dispatch('search/アクション名')
      // 画面繋ぎ時に追加する必要あり？ this.setItems()

      // XX〜YYまで検索した時に間に該当するものを表示する
      if (this.updatedFrom && this.updatedTo) {
        // split区切る join区切ったものをくっつける parseIntで数字に変える 型次第では不必要？
        const updatedFromNumber = parseInt(this.updatedFrom.split('-').join(''))
        const updatedToNumber = parseInt(this.updatedTo.split('-').join(''))
        this.filteredItems = this.filteredItems.filter((item) =>
          parseInt(item.day.split('-').join('')) >= updatedFromNumber &&
          parseInt(item.day.split('-').join('')) <= updatedToNumber
        )
      // XX〜 検索した時に該当するものを表示する
      } else if (this.updatedFrom) {
        const updatedFromNumber = parseInt(this.updatedFrom.split('-').join(''))
        this.filteredItems = this.filteredItems.filter((item) =>
          parseInt(item.day.split('-').join('')) >= updatedFromNumber
        )
      // 〜YY 検索した時に該当するものを表示する
      } else if (this.updatedTo) {
        const updatedToNumber = parseInt(this.updatedTo.split('-').join(''))
        this.filteredItems = this.filteredItems.filter((item) =>
          parseInt(item.day.split('-').join('')) <= updatedToNumber
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
.paper {
  position: fixed;
  height: 500px;
  width: 400px;
  background: rgba(255,255,255,0.9);
  box-shadow: 0px 0px 5px 0px #888;
  left: 35%;
  top: 20%;
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

/* 上部 */
#search{
  text-align: center;
  padding: 0.5em 1em;
  font-size: 18px;
  font-weight: bold;
  width: 80%;
  height: auto;
  display: inline-block;
}
#searchList{
  text-align: left;
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
  text-align: center;
  width: 9rem;
  height: 3rem;
  padding-left: 1rem;
  overflow: hidden;
  box-shadow: .25rem 0 .25rem hsla(0, 0%, 0%, .1);
  background-image:
    linear-gradient(90deg, hsla(0, 0%, 45%, .1) 2rem, hsla(0, 100%, 100%, 0) 2.5rem)
  , linear-gradient(90deg, hsla(60, 100%, 85%, 1), hsla(60, 100%, 85%, 1));
  font-size: 1rem;
  font-weight: bold;
  line-height: 1.5;
  transition: all 1s;
  margin-left: 5px;
  margin-top: 10px;
}

button:hover {
  transition-delay: 50ms;
  transform: rotate3d(0,0,1,-13deg);
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
  top: 48%;
}
.hole-bottom {
  bottom: 10%;
}
</style>
