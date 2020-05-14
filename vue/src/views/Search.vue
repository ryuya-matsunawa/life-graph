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
          更新日時
          <input type="text">
        </li>
        <li class="view">
          表示順
          <input type="text">
        </li>
      </ul>
      <button>検索</button>
    </div>
    <!-- 下部 -->
    <div class="result1">
      bbb
      <router-link to="/show" tag="button" class="viewButton">
          参照
      </router-link>
    </div>
    <div class="result2" >
      bbb
      <router-link to="/show" tag="button" class="viewButton">
          参照
      </router-link>
    </div>
  </div>
</template>

<script>
import Header from '../views/Header.vue'

export default {
  name: 'Search',
  components: {
    Header
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
.result1{
  padding: 1em 1em;
  font-weight: bold;
  text-align: center;
  box-shadow: 0 0 8px black;
  width: 80%;
  height: auto;
  margin: 20px auto;
}
.result2{
  padding: 1em 1em;
  font-weight: bold;
  text-align: center;
  box-shadow: 0 0 8px black;
  width: 80%;
  height: auto;
  margin: 20px auto;

}
button:hover {
  background-color: #59b1eb;
  cursor: pointer;
}
.viewButton{
  float: right;
  clear: both;
  display: block;
  text-decoration: none;
}
</style>
