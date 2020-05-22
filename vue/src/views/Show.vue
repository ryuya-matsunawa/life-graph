<template>
  <div class="show">
    <Header />
    <h1>User Name</h1>
    <div class="chart">
      <Chart :id="currentUserId" />
    </div>
    <div>
      <router-link to="/top" class="button1">
        TOPへ
      </router-link>
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
  },
  data () {
    return {
      currentUserId: parseInt(this.$route.params.userId)
    }
  }
}
</script>

<style scoped>
.chart {
  margin-top: 100px;
}

.h1 {
  font-family: 'Noto Serif JP', serif;
}
</style>
