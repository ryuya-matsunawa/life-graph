<template>
  <div class="show">
    <Header />
    <h1>User Name</h1>
    <div class="Chart">
      <Chart />
    </div>
    <router-link to="/Top">
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
.toTop {
  background: #f16272;
  border: none;
  color: #fff;
}
.Chart {
  width: 50%;
  margin-left: auto;
  margin-right: auto;
}
</style>
