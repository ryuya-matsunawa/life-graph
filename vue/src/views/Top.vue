<template>
  <div class="top">
    <Header />
    <router-link to="/search" tag="button" class="searchButton">
      検索
    </router-link>
    <router-link to="/edit" tag="button" class="editButton">
      登録
    </router-link>
    <router-link to="/edit" tag="button" class="editButton">
      編集
    </router-link>
    <div class="chart">
      <Chart />
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
  }
}
</script>

<style scoped>
.top {
  background-image: url(../assets/top.png);
  background-size: 20%;
  background-repeat: no-repeat;
  background-position: 5% 30%;
  padding-top: 100px;
}

.searchButton {
  margin-right: 30px;
  margin-left: 40px;
  position: relative;
  display: inline-block;
  font-weight: bold;
  padding: 5px 11px 5px 15px;
  text-decoration: none;
  color: #8c8c8c;
  transition: .4s;
  border: none;
}
.searchButton:before {
  position: absolute;
  display: inline-block;
  content: '';
  width: 4px;
  height: 100%;
  top: 0;
  left: 0;
  border-radius: 3px;
  background:#B3FFD5;
}
.searchButton:after {
  position: absolute;
  display: inline-block;
  content: '';
  width: 4px;
  height: 100%;
  top:0;
  left: 100%;
  border-radius: 3px;
  background:#B3FFD5;
}
.searchButton:hover:before,.searchButton:hover:after {
  -webkit-transform: rotate(10deg);
  transform: rotate(10deg);
}

.editButton {
  margin-right: 30px;
  position: relative;
  display: inline-block;
  font-weight: bold;
  padding: 5px 11px 5px 15px;
  text-decoration: none;
  color: #8c8c8c;
  transition: .4s;
  border: none;
}
.editButton:before {
  position: absolute;
  display: inline-block;
  content: '';
  width: 4px;
  height: 100%;
  top: 0;
  left: 0;
  border-radius: 3px;
  background:#B3FFD5;
}
.editButton:after {
  position: absolute;
  display: inline-block;
  content: '';
  width: 4px;
  height: 100%;
  top:0;
  left: 100%;
  border-radius: 3px;
  background:#B3FFD5;
}
.editButton:hover:before,.editButton:hover:after {
  -webkit-transform: rotate(10deg);
  transform: rotate(10deg);
}
</style>
