<template>
  <div>
    <Header />
    <form action="/my-handling-form-page" method="post">
      <ul>
        <li>
          <label class=tag for="age">年齢</label>
          <input id="age" type="int" name="age">
        </li>
        <li>
          <label class=tag for="score">スコア</label>
          <input id="score" type="int" name="score">
        </li>
        <li>
          <label class=tag for="comment">コメント</label>
          <input id="comment" type="varchar(255)" name="comment">
        </li>
      </ul>
      <button type="submit">
        登録
      </button>
      <button type="submit">
        編集
      </button>
    </form>
    <button onclick="location.href='./">
      遷移
    </button>
    <div class="chart">
      <Chart />
    </div>
  </div>
</template>

<script>
import Chart from '../views/Chart.vue'
import Header from '../views/Header.vue'
export default {
  name: 'Top',
  components: {
    Chart,
    Header
  }
}
</script>

<style>
ul {
  list-style: none;
}
label{
  width: 40;
}
.tag{
  width: 40;
}

</style>
