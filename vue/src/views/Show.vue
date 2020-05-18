<template>
  <div class="show">
    <Header />
    <h1>User Name</h1>
    <div class="chart">
      <Chart />
    </div>
    <router-link to="/top" tag="button" class="totop">
      TOPへ
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

</style>
