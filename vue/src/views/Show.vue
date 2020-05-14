<template>
  <div class="show">
    <Header />
    <h1>User Name</h1>
    <div class="chart">
      <Chart />
    </div>
    <router-link to="/top" tag="button">
      <button class="toTop">
        TOPへ
      </button>
    </router-link>
  </div>
</template>

<script>
import Header from '../views/Header.vue'
import Chart from '../views/Chart.vue'

export default {
  name: 'Show',
  components: {
    Header,
    Chart
  }
}
</script>

<style scoped>
  /* App.vueに移動させました！
  理解できたら、消しておいてください！
  .toTop {
  background: #f16272;
  border: none;
  color: #fff;
} */
</style>
