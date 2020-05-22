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
  background-position: 4% 60%;
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

.searchButton {
  color: #353434;
  border-color: #a39d9d;
  position: absolute;
  top: 232px;
  left: 30px;
  font-size: 16px;
  cursor: pointer;
  width: 250px;
  padding: 20px 10px;
  border-radius: 8px;
  background-image: url('../assets/top.png');
  background-size: 80px;
  background-repeat: no-repeat;
}

.editButton {
  color:#353434;
  border-color:#a39d9d;
  position: absolute;
  cursor: pointer;
  font-size: 16px;
  margin-left: 17px;
  top: 370px;
  left: 30px;
  width: 250px;
  padding: 20px 80px;
  border-radius: 8px;
  background-image: url('../assets/edit.png');
  background-size: 80px;
  background-repeat: no-repeat;
}

.chart {
  margin-top: 80px;
}
</style>
