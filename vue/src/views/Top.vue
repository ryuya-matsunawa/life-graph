<template>
  <div class="top">
    <Header />
    <!-- <router-link to="/">
      Home
    </router-link>
    <router-link to="/search">
      Search
    </router-link>
    <router-link to="/edit">
      Edit
    </router-link>
    <router-link to="/show">
      Show
    </router-link>
    <h1>This is an about page</h1> -->

    <router-link to="/search">
      <button type="button" class="searchButton">
        検索
      </button>
    </router-link>

    <router-link to="/edit">
      <button type="button" class="editButton">
        登録
      </button>
    </router-link>

    <router-link to="/edit">
      <button type="button" class="editButton">
        編集
      </button>
    </router-link>

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

.chart{
  width: 600px;
  margin: auto;
}

.searchButton,.editButton{
  margin: 10px;
}
</style>
