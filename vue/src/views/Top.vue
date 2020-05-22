<template>
  <div class="top">
    <Header />
    <div class="title">
      あなたの人生グラフ、どう変えますか。
    </div>
    <router-link to="/search" tag="button" class="searchButton">
      検索
    </router-link>
    <router-link to="/edit" tag="button" class="editButton">
      登録 / 編集
    </router-link>
    <img src="../assets/top.png" class="sakura">
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
  font-size: 19px;
  /* eslint-disable-next-line to ignore the next line. */
  font-family: 'Noto Serif JP', serif;
}

.searchButton {
  color:#a39d9d;
  border-color:#a39d9d;
  max-width:960px;
  text-align:center;
  position:relative;
  margin-top:30px;
  cursor: pointer;
}

.searchButton:hover {
  color:#fff;
  background-color:#a39d9d;
  border-color:#a39d9d;
}

.editButton {
  color:#a39d9d;
  border-color:#a39d9d;
  max-width:960px;
  text-align:center;
  position:relative;
  margin:auto;
  cursor: pointer;
  margin-left: 17px;
}

.editButton:hover {
  color:#fff;
  background-color:#a39d9d;
  border-color:#a39d9d;
}

.sakura {
  position: absolute;
  width: 300px;
  left: 0;
  top: 80px;
  z-index: -1;

}
</style>
