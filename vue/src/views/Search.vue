<template>
  <div>
    <div id="search">
      検索条件
      <ul id="searchList">
        <li class="username">
          ユーザー名
          <input v-model="textInput" type="text">
        </li>
        <li class="update">
          更新日時
          <input v-model="textInput" type="text">
        </li>
        <li class="view">
          表示順
          <input v-model="textInput" type="text">
        </li>
      </ul>
      <button>検索</button>
    </div>
    <!--  -->
    <div id="result">
      <ul id="resultUser">
        <li>
          oneくん
          <button class="viewButton">
            参照
          </button>
        </li>
        <li>
          twoくん
          <button class="viewButton">
            参照
          </button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
</script>

<style>
#search{
  text-align: center;
  padding: 0.5em 1em;
  margin: 50px;
  font-weight: bold;
  border: solid 3px #000000;
  width: auto;
  height: 300px;
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
#result{
  padding: 1em 1em;
  font-weight: bold;
  border: solid 3px #000000;
}
#resultUser{
  list-style: none;
}
button:hover {
  background-color: #59b1eb;
  cursor: pointer;
}
.viewButton{
  float: right;
}
.viewButton + * {
  clear: both;;
}
</style>
