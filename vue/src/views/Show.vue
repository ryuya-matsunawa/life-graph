<template>
  <div class="show">
    <Header />
    <h1>User Name</h1>
    <div class="chart">
      <Chart />
    </div>
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
.show {
  background-image: url(../assets/show.png);
  background-size: 25%;
  background-repeat: no-repeat;
  background-position: 5% 50%;
  background-position: fixed;
}

.chart {
  margin-top: 100px;
}
</style>
