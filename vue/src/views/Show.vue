<template>
  <div class="show">
    <Header />
    <h1>User Name</h1>
    <div class="chart">
      <Chart :userid="currentUserId" />
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
  /* eslint-disable-next-line to ignore the next line. */
  font-family: 'Noto Serif JP', serif;
}

.button {
  position: fixed;
  bottom: 50px;
  right: 30px;
  padding: .75rem 1.25rem;
  border-radius: 5px;
  width: 90px;
  color: #fff;
  text-transform: uppercase;
  font-size: 1rem;
  letter-spacing: .15rem;
  cursor: pointer;
  background-color: #a7d28d;
  /* eslint-disable-next-line to ignore the next line. */
  font-family: 'Playfair Display', serif;
}
</style>
