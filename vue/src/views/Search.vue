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
          <input type="date">
        </li>
        <li class="view">
          表示順
          <input type="text">
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
      <table class="table">
        <th>リスト</th>
        <th>更新日</th>
        <tr v-for="item in items" :key="item.id">
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
  // 最初は何も表示させないためにdataでfalseを返す
  data () {
    return {
      isActive: false,
      items: [
        {
          id: 1,
          name: 'Yamada',
          day: '2016-01-14'
        },
        {
          id: 2,
          name: 'Suzuki',
          day: '2016-01-15'
        },
        {
          id: 3,
          name: 'Tanaka',
          day: '2016-01-16'
        }
      ]
    }
  },
  // クリックしたときにtrueになるようにする
  methods: {
    active () {
      this.isActive = true
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
  width: auto;
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
  width: 300px;
}

</style>
