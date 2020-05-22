<template>
  <div class="top">
    <Header />
    <div class="paper">
      <div class="lines">
        <div class="title">
          TOP
        </div>
        <div class="explain">
          下のふせんから各ページに飛べるよ！
        </div>
        <router-link to="/search" tag="button" class="searchButton">
          検索
        </router-link>
        <router-link to="/edit" tag="button" class="editButton">
          登録
        </router-link>
        <router-link to="/edit" tag="button" class="editButton">
          編集
        </router-link>
      </div>
      <div class="holes hole-top" />
      <div class="holes hole-middle" />
      <div class="holes hole-bottom" />
    </div>
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
  background-image: url(../assets/top.png);
  background-size: 20%;
  background-repeat: no-repeat;
  background-position: 4% 60%;
  padding-top: 100px;
}

.paper {
  position: fixed;
  height: 200px;
  width: 400px;
  background: rgba(255,255,255,0.9);
  box-shadow: 0px 0px 5px 0px #888;
  left: 75%;
  top: 60%;
}

.paper::before {
  content: '';
  position: absolute;
  left: 45px;
  height: 100%;
  width: 2px;
  background: rgba(255,0,0,0.4);
}

.lines {
  margin-top: 40px;
  height: calc(100% - 40px);
  width: 100%;
  background-image: repeating-linear-gradient(white 0px, white 24px, steelblue 25px);
}

.title {
  color: black;
  font-size: 30px;
  font-weight: bold;
}

.explain {
  color: #8c8c8c;
  font-size: 15px;
  font-weight: bold;
  margin: 24px;
}

.holes {
  position: absolute;
  left: 10px;
  height: 25px;
  width: 25px;
  background: #fff;
  border-radius: 50%;
  box-shadow: inset 0px 0px 2px 0px #888;
}
.hole-top {
  top: 10%;
}
.hole-middle {
  top: 43%;
}
.hole-bottom {
  bottom: 10%;
}

.searchButton {
  text-align: center;
  width: 5rem;
  height: 3rem;
  padding-left: 1rem;
  padding-top: 1rem;
  overflow: hidden;
  box-shadow: .25rem 0 .25rem hsla(0, 0%, 0%, .1);
  background-image:
    linear-gradient(90deg, hsla(0, 0%, 45%, .1) 2rem, hsla(0, 100%, 100%, 0) 2.5rem)
  , linear-gradient(90deg, hsla(60, 100%, 85%, 1), hsla(60, 100%, 85%, 1));
  font-size: 1rem;
  font-weight: bold;
  line-height: 1.5;
  transition: all 1s;
  margin-right: 5px;
  cursor: pointer;
}

.searchButton:hover {
  transition-delay: 50ms;
  transform: rotate3d(0,0,1,-13deg);
}

.editButton {
  text-align: center;
  width: 5rem;
  padding-left: 1rem;
  padding-top: 1rem;
  overflow: hidden;
  box-shadow: .25rem 0 .25rem hsla(0, 0%, 0%, .1);
  background-image:
    linear-gradient(90deg, hsla(0, 0%, 10%, .1) 2rem, hsla(0, 100%, 100%, 0) 2.5rem)
  , linear-gradient(90deg, hsla(30, 100%, 85%, 1), hsla(30, 100%, 85%, 1));
  font-size: 1rem;
  font-weight: bold;
  line-height: 1.5;
  transition: all 1s;
  margin-left: 20px;
  cursor: pointer;
}

.editButton:hover {
  transition-delay: 50ms;
  transform: rotate3d(0,0,1,-13deg);
}
</style>
