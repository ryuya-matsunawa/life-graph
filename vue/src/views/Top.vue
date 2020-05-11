<template>
  <div class="top">
    <router-link to="/">
      Home
    </router-link>
    <h1>This is an about page</h1>
  </div>
</template>

<script>
import { Line } from 'vue-chartjs';
import Chart from './Chart';
export default {
  components:{
    Chart
  }
}
</script>
