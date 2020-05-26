<template>
  <div class="show">
    <Header />
    <h1>User Name</h1>
    <div class="chart">
      <Chart :id="currentUserId" />
    </div>
    <div class="home__b">
      <div class="home__b1" />
      <div class="home__b2" />
      <div class="home__b3" />
      <div class="home__b4" />
      <div class="home__b5" />
      <div class="home__b6" />
      <div class="home__b7" />
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
  width: 50%;
}

.h1 {
  font-family: 'Noto Serif JP', serif;
}

.home__b {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100vh;
  opacity: 0.2;
  z-index: -1;
}

.home__b1 {
    position: absolute;
    top: 70px;
    left: 70px;
    background-color: #90b0b4;
    width: 110px;
    height: 110px;
    border-radius: 110px;
}

.home__b2 {
    position: absolute;
    top: 0;
    left: 800px;
    background-color: #a97f76;
    width: 200px;
    height: 200px;
    border-radius: 200px;
}

.home__b4 {
    position: absolute;
    top: 20%;
    left: 40%;
    background-color: darkgray;
    width: 60px;
    height: 60px;
    border-radius: 60px;
}

.home__b5 {
    position: absolute;
    top: 30%;
    left: 80%;
    background-color: darkgray;
    width: 40px;
    height: 40px;
    border-radius: 20px;
}

.home__b6 {
    position: absolute;
    top: 70%;
    left: 10%;
    background-color: #a97f76;
    width: 110px;
    height: 110px;
    border-radius: 110px;
}

.home__b7 {
    position: absolute;
    top: 70%;
    left: 80%;
    background-color: #90b0b4;
    width: 270px;
    height: 270px;
    border-radius: 270px;
}
</style>
