<template>
  <div class="top">
    <Header />
    <div class="title">
      あなたの人生グラフ、どう変えますか。
    </div>
    <router-link to="/search" tag="button" class="searchButton">
      検索へ
    </router-link>
    <router-link to="/edit" tag="button" class="editButton">
      登録または編集へ
    </router-link>
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
    <img src="../assets/top.jpeg" class="sakura">
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
  },
  data () {
    return {
      currentUserId: this.$store.state.account.account.id
    }
  }
}
</script>

<style scoped>
.top {
  background-size: 20%;
  background-repeat: no-repeat;
  padding-top: 100px;
  position: relative;
}

.title {
  color: black;
  font-size: 22px;
  /* eslint-disable-next-line to ignore the next line. */
  font-family: 'Noto Serif JP', serif;
  margin-top: 60px;
}

.sakura {
  position: absolute;
  top: 300px;
  left: 0;
  z-index: -2;
  opacity: 0.8;
}

.searchButton {
  color: #353434;
  border-color: #a39d9d;
  position: absolute;
  top: 150px;
  left: 30px;
  font-size: 16px;
  cursor: pointer;
  height: 50px;
  width: 100px;
  border-radius: 8px;
}

.editButton {
  color:#353434;
  border-color:#a39d9d;
  position: absolute;
  cursor: pointer;
  font-size: 16px;
  top: 210px;
  left: 30px;
  height: 50px;
  width: 200px;
  border-radius: 8px;
}

.chart {
  margin-top: 80px;
  z-index: 3;
  background-color: #fff;
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
